package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.Item.Status;

public class ReservationRow extends JPanel{
	
	private final Date from, to;
	private final String name, username;
	private boolean isReported;
	private JButton button, delete;
	private final ReservationRowListener listener;
	private JLabel isReportedString;
	private final int resId;
	
	public ReservationRow(String name, String username, int resId, Date from, Date to, Date today, boolean isReported, boolean isAdmin,
			ReservationRowListener listener){
		setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEtchedBorder());
		this.listener = listener;
		GridBagConstraints c = new GridBagConstraints();
		this.from = from;
		this.to = to;
		this.name = name;
		this.username = username;
		this.resId = resId;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		add(new JLabel("Koie: " + name + " "), c);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		add(new JLabel("Fra: " + from.day + "." + from.month), c);
		c.gridx = 1;
		add(new JLabel("Til: " + to.day + "." + to.month + "  "), c);
		c.gridy = 2;
		c.gridx = 0;
		if (!isAdmin){
			add(new JLabel("Rapportert: "), c);
			c.gridy = 2;
			c.gridx = 1;
			isReportedString = new JLabel((isReported ? "Ja" : "Nei"));
			add(isReportedString, c);
		}else{
			add(new JLabel("Bruker: " + username), c);
		}
		c.gridy = 1;
		c.gridx = 3;
		ButtonListener l = new ButtonListener();
		if (from.isAfter(today)){
			delete = new JButton("Slett");
			delete.addActionListener(l);
			add(delete, c);
		}
		if (!isReported && !isAdmin){
			if (to.isBefore(today)){
				c.gridy++;
				button = new JButton("Rapport");
				add(button, c);
				button.addActionListener(l);
			}
		}
	}
	
	public int getResId(){
		return resId;
	}
	
	public String getCabinName(){
		return name;
	}
	
	public Date getFrom(){
		return from;
	}
	
	public Date getTo(){
		return to;
	}
	
	private void removeButton(){
		button.removeAll();
		this.remove(button);
		button = null;
	}
	
	private void removeThis(){
		listener.removeReservation(this);
	}
	
	
	
	private class ButtonListener implements ActionListener, UserReportListener{
		
		private UserReport userReport;
		
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == delete){
				int deleteReservation = JOptionPane.showOptionDialog(null, "Er du sikker på at du vil slette din reservasjon?", "Slett reservasjon", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Ja", "Nei"}, "Ja");
				if (deleteReservation == 0){
					removeThis();
				}
				return;
			}
			if (!isReported){
				Map<Integer, String> cabins = Database.getIdNameMap();
				int cabinId = -1;
				for (Integer i: cabins.keySet()){
					if (cabins.get(i).equals(name)){
						cabinId = i;
						break;
					}
				}
				if (cabinId == -1)
					return;
				userReport = new UserReport(name, from.day, from.month, to.day, to.month, cabinId);
				userReport.setListener(this);
			}
		}

		public void okPressed(String comment, List<Item> brokenInventory, int woodUsed, List<Item> lostItems) {
			removeButton();
			isReported = true;
			isReportedString.setText("Ja");
			Map<Integer, String> cabins = Database.getIdNameMap();
			int koieId = -1;
			for (Integer id: cabins.keySet()){
				if (cabins.get(id).equals(name)){
					koieId = id;
					break;
				}
			}
			Koie cabin = Database.getKoie(koieId);
			for (Item item : brokenInventory){
				item.setStatus(Status.BROKEN);
				Database.updateItem(item);
			}
			for (Item item : lostItems){
				Database.addItem(item, koieId);
			}
			double woodLeft = cabin.getVedmengde() - woodUsed;
			cabin.setVedmengde(woodLeft);
			Database.toDatabase(cabin);
			Database.rapporter(koieId, username, comment, resId);
		}

		public void cancelPressed() {
			userReport = null;
		}
	}
}

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

/**
 * 
 * Hver reservasjon blir gjort om til et JPanel og som lager funksjonalitet til brukeren
 */
public class ReservationRow extends JPanel{
	
	private final Date from, to;
	private final String name, username;
	private boolean isReported;
	private JButton button, delete;
	private final ReservationRowListener listener;
	private JLabel isReportedString;
	private final int resId;
	
	/**
	 * Oppretter objektet
	 * @param name - navnet på koia
	 * @param username - emailen til brukeren
	 * @param resId - reservasjonsid i databasen til den aktuelle reservasjon
	 * @param from - dato fra
	 * @param to - dato til
	 * @param today - dagens dato
	 * @param isReported - om en reservasjon er rapportert
	 * @param isAdmin - om det er admin som ser feltet
	 * @param listener - en lytter til reservasjonen
	 */
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
	/**
	 * 
	 * @return resId - returnerer reservasjonsid til en reservasjon
	 */
	public int getResId(){
		return resId;
	}
	
	/**
	 * 
	 * @return name - navnet til koia
	 */
	public String getCabinName(){
		return name;
	}
	
	/**
	 * 
	 * @return from - dato fra
	 */
	public Date getFrom(){
		return from;
	}
	
	/**
	 * 
	 * @return to - dato til
	 */
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
	
	
	/**
	 * 
	 * En lytter til knappene til reservasjonen
	 */
	private class ButtonListener implements ActionListener, UserReportListener{
		
		private UserReport userReport;
		private Koie cabin;
		
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
				cabin = Database.getKoie(cabinId);
				userReport = new UserReport(from.day, from.month, to.day, to.month, cabin);
				userReport.setListener(this);
			}
		}

		public void okPressed(String comment, List<Item> brokenInventory, int woodUsed, List<Item> lostItems) {
			removeButton();
			isReported = true;
			isReportedString.setText("Ja");
			for (Item item : brokenInventory){
				item.setStatus(Status.BROKEN);
			}
			for (Item item : lostItems){
				item.setStatus(Status.LOST_AND_FOUND);
				cabin.getInventory().addItem(item);
			}
			double woodLeft = cabin.getVedmengde() - woodUsed;
			cabin.setVedmengde(woodLeft);
			Database.toDatabase(cabin);
			Database.rapporter(cabin.getId(), username, comment, resId);
			cabin = null;
			userReport = null;
		}

		public void cancelPressed() {
			userReport = null;
			cabin = null;
		}
	}
}

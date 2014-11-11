package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReservationRow extends JPanel{
	
	private final int fromDay, fromMonth, toDay, toMonth;
	private final String name;
	private boolean isReported;
	private JButton button, delete;
	private final ReservationRowListener listener;
	private JLabel isReportedString;
	
	public ReservationRow(String name, Date from, Date to, boolean isReported, boolean isAdmin,
			ReservationRowListener listener){
		setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEtchedBorder());
		this.listener = listener;
		GridBagConstraints c = new GridBagConstraints();
		this.fromDay = from.day;
		this.fromMonth = from.month;
		this.toDay = to.day;
		this.toMonth = to.month;
		this.name = name;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		add(new JLabel("Koie: " + name + " "), c);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		add(new JLabel("Fra: " + fromDay + "." + fromMonth), c);
		c.gridx = 1;
		add(new JLabel("Til: " + toDay + "." + toMonth + "  "), c);
		c.gridy = 2;
		c.gridx = 0;
		add(new JLabel("Rapportert:"), c);
		c.gridy = 2;
		c.gridx = 1;
		isReportedString = new JLabel((isReported ? "Ja" : "Nei"));
		add(isReportedString, c);
		c.gridy = 1;
		c.gridx = 3;
		ButtonListener l = new ButtonListener();
		delete = new JButton("Slett");
		delete.addActionListener(l);
		add(delete, c);
		if (!isReported && !isAdmin){
			c.gridy++;
			button = new JButton("Rapport");
			add(button, c);
			button.addActionListener(l);
		}
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
				userReport = new UserReport(name, fromDay, fromMonth, toDay, toMonth);
				userReport.setListener(this);
			}
		}

		public void okPressed(String comment, List<String> brokenInventory) {
			removeButton();
			isReported = true;
			isReportedString.setText("Ja");
//			Send til databasen
		}

		public void cancelPressed() {
			userReport = null;
		}
	}
}

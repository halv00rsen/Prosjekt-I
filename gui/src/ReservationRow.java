package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReservationRow extends JPanel{
	
	private final int fromDay, fromMonth, toDay, toMonth;
	private final String name, comment;
	private boolean isReported;
	private JButton button, delete;
	private final ReservationRowListener listener;
	
	public ReservationRow(String name, int fromDay, int fromMonth, int toDay, int toMonth, String comment, boolean isReported,
			ReservationRowListener listener){
		setLayout(new GridBagLayout());
		this.listener = listener;
		GridBagConstraints c = new GridBagConstraints();
		this.fromDay = fromDay;
		this.fromMonth = fromMonth;
		this.toDay = toDay;
		this.toMonth = toMonth;
		this.name = name;
		this.comment = comment;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(new JLabel("Koie: " + name + " "), c);
		c.gridx++;
		add(new JLabel("Fra: " + fromDay + "." + fromMonth + " "), c);
		c.gridx++;
		add(new JLabel("Til: " + toDay + "." + toMonth + " "), c);
		c.gridx++;
		add(new JLabel("Kommentar: " + comment + " "), c);
		c.gridx++;
		ButtonListener l = new ButtonListener();
		delete = new JButton("Slett");
		delete.addActionListener(l);
		add(delete, c);
		if (!isReported){
			c.gridx++;
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
//			Send til databasen
		}

		public void cancelPressed() {
			userReport = null;
		}
	}
}

package src;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class ReservationList extends JPanel implements LoginListener, ReservationRowListener{
	
	private String username;
	private final List<ReservationRow> reservations;
	private final GridBagConstraints c;
	
	public ReservationList(){
//		this.setLayout(new GridLayout(2, 1));
		setLayout(new GridBagLayout());
		reservations = new ArrayList<ReservationRow>();
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(new JLabel("Dine reservasjoner: "));
		c.gridy = 1;
	}
	
	private void getReservations(){
		//Skal bruke databasen for å hente alle reservasjoner til brukeren
//		reservations.add("Koie 1:21.10:29.10:Nei");
//		reservations.add("Koie 2:10.2:17.2:Ikke relevant");
		reservations.add(new ReservationRow("Kollekula", 19, 10, 27, 10, "Something", false, this));
		reservations.add(new ReservationRow("Kingestua", 10, 11, 14, 11, "Ikke relevant", true, this));
		for (ReservationRow row : reservations){
			add(row, c);
			c.gridy++;
		}
	}

	public void userHasLoggedIn(String username) {
		this.username = username;
		getReservations();
	}

	public void userHasLoggedOut() {
		c.gridy = 1;
		for (ReservationRow row : reservations){
			this.remove(row);
		}
		reservations.clear();
	}

	@Override
	public void adminHasLoggedIn() {
		// TODO Auto-generated method stub
		
	}

	public void removeReservation(ReservationRow reservation) {
		reservations.remove(reservation);
		this.remove(reservation);
	}
}

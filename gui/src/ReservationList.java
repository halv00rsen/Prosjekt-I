package src;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class ReservationList extends JPanel implements LoginListener, ReservationRowListener{
	
	private String username;
	private final List<ReservationRow> reservations;
	private final GridBagConstraints futureC, hasVisitedC;
	private final JPanel futureReservations, hasVisitedReservations;
	
	public ReservationList(){
//		this.setLayout(new GridLayout(2, 1));
		setLayout(new GridLayout(1,2));
		reservations = new ArrayList<ReservationRow>();
		futureReservations = new JPanel();
		futureReservations.setLayout(new GridBagLayout());
		futureReservations.setBorder(BorderFactory.createTitledBorder("Reservasjoner"));
		JScrollPane pane1 = new JScrollPane(futureReservations);
		pane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		hasVisitedReservations = new JPanel();
		hasVisitedReservations.setLayout(new GridBagLayout());
		hasVisitedReservations.setBorder(BorderFactory.createTitledBorder("Historie"));
		JScrollPane pane2 = new JScrollPane(hasVisitedReservations);
		pane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		futureC = new GridBagConstraints();
		hasVisitedC = new GridBagConstraints();
		add(pane1);
		add(pane2);
	}
	
	private void getReservations(){
		//Skal bruke databasen for å hente alle reservasjoner til brukeren
//		reservations.add("Koie 1:21.10:29.10:Nei");
//		reservations.add("Koie 2:10.2:17.2:Ikke relevant");
		List<UserDatesBooked> bookings = Database.getReservasjonBruker(username);
		HashMap<Integer, String> cabins = Database.getIdNameMap();
		for (UserDatesBooked b: bookings){
			reservations.add(new ReservationRow(cabins.get(b.cabinId), b.from, b.to, false, false, this));
		}
		for (ReservationRow r : reservations){
			futureReservations.add(r, futureC);
			futureC.gridy++;
		}
	}

	public void userHasLoggedIn(String username) {
		this.username = username;
		getReservations();
	}

	public void userHasLoggedOut() {
//		c.gridy = 1;
		futureReservations.removeAll();
		hasVisitedReservations.removeAll();
		reservations.clear();
	}

	@Override
	public void adminHasLoggedIn() {
		// TODO Auto-generated method stub
		
	}

	public void removeReservation(ReservationRow reservation) {
		reservations.remove(reservation);
		futureReservations.remove(reservation);
		hasVisitedReservations.remove(reservation);
	}
}

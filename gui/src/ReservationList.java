package src;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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

public class ReservationList extends JPanel implements LoginListener, ReservationRowListener, ReservationsFrameListener{
	
	public static final boolean DEBUG = true;
	private String username;
	private final List<ReservationRow> reservations;
	private final GridBagConstraints futureC, hasVisitedC;
	private final JPanel futureReservations, hasVisitedReservations;
	private ReservationsListListener listener;
	
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
		futureC.gridx = 0;
		futureC.gridy = 0;
		hasVisitedC.gridy = 0;
		hasVisitedC.gridx = 0;
		add(pane1);
		add(pane2);
	}
	
	public void setListener(ReservationsListListener listener){
		this.listener = listener;
	}
	
	private void getReservations(){
		//Skal bruke databasen for å hente alle reservasjoner til brukeren
//		reservations.add("Koie 1:21.10:29.10:Nei");
//		reservations.add("Koie 2:10.2:17.2:Ikke relevant");
		GregorianCalendar calendar = new GregorianCalendar();
		Date today = new Date((DEBUG ? 2 :calendar.get(java.util.Calendar.DATE)), (DEBUG ? 1:(calendar.get(java.util.Calendar.MONTH) + 1)));
		List<UserDatesBooked> bookings = Database.getReservasjonBruker(username);
		HashMap<Integer, String> cabins = Database.getIdNameMap();
		for (UserDatesBooked b: bookings){
			ReservationRow row = new ReservationRow(cabins.get(b.cabinId), username, b.resID, b.from, b.to, today, Database.isRapportert(b.resID), false, this);
			reservations.add(row);
			if (b.from.isBefore(today) || b.from.equals(today)){
				hasVisitedReservations.add(row, hasVisitedC);
				hasVisitedC.gridy++;
			}
			else{
				futureReservations.add(row, futureC);
				futureC.gridy++;
			}
		}
	}

	public void userHasLoggedIn(String username) {
		this.username = username;
		getReservations();
	}

	public void userHasLoggedOut() {
//		c.gridy = 1;
		username = null;
		futureReservations.removeAll();
		hasVisitedReservations.removeAll();
		reservations.clear();
	}

	@Override
	public void adminHasLoggedIn() {
		// TODO Auto-generated method stub
		username = "admin";
		
	}
	
	private void callListener(String name, Date from, Date to){
		listener.removeReservation(from, to, name);
	}

	public void removeReservation(ReservationRow reservation) {
		callListener(reservation.getCabinName(), reservation.getFrom(), reservation.getTo());
		reservations.remove(reservation);
		futureReservations.remove(reservation);
		hasVisitedReservations.remove(reservation);
		revalidate();
		repaint();
	}

	public void addReservation(String name, Date from, Date to) {
		GregorianCalendar calendar = new GregorianCalendar();
		Date today = new Date((DEBUG ? 2 :calendar.get(java.util.Calendar.DATE)), (DEBUG ? 1:(calendar.get(java.util.Calendar.MONTH) + 1)));
		ReservationRow row = new ReservationRow(name, username,-1, from, to, today, false, false, this);
		reservations.add(row);
		if (from.isBefore(today) || from.equals(today)){
			hasVisitedReservations.add(row, hasVisitedC);
			hasVisitedC.gridy++;
		}
		else{
			futureReservations.add(row, futureC);
			futureC.gridy++;
		}
	}
}

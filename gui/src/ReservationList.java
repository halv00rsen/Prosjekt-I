package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 * Et objekt som håndterer alle reservasjonene til en gitt bruker
 */
public class ReservationList extends JPanel implements LoginListener, ReservationRowListener, ReservationsFrameListener, ChangeTabListener{
	
	public static final boolean DEBUG = true;
	private String username;
	private final List<ReservationRow> reservations;
	private final GridBagConstraints futureC, hasVisitedC;
	private final JPanel futureReservations, hasVisitedReservations;
	private ReservationsListListener listener;
	
	/**
	 * Oppretter objektet
	 */
	public ReservationList(){
		username = null;
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
	
	/**
	 * 
	 * @param listener - setter lytteren til dette objektet
	 */
	public void setListener(ReservationsListListener listener){
		this.listener = listener;
	}
	
	private void getReservations(){
		deleteReservations();
		Date today = Calendar.getTodaysDate();
		List<UserDatesBooked> bookings = Database.getReservasjonBruker(username);
		if (bookings == null){
			JOptionPane.showMessageDialog(null, "Feil med kommunikasjon med databasen");
			return;
		}
		Map<Integer, String> cabins = GUI.getIdMap();
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
	}
	
	private void deleteReservations(){
		futureReservations.removeAll();
		hasVisitedReservations.removeAll();
		reservations.clear();
	}

	public void userHasLoggedOut() {
		username = null;
		deleteReservations();
	}

	public void adminHasLoggedIn() {
		username = "admin";
	}
	
	private void callListener(int resId){
		listener.removeReservation(resId);
	}

	public void removeReservation(ReservationRow reservation) {
		callListener(reservation.getResId());
		reservations.remove(reservation);
		futureReservations.remove(reservation);
		hasVisitedReservations.remove(reservation);
		revalidate();
		repaint();
	}

	public void addReservation(String name, Date from, Date to, int resId) {
		Date today = Calendar.getTodaysDate();
		ReservationRow row = new ReservationRow(name, username,resId, from, to, today, false, false, this);
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

	public void initPanel() {
		if (username != null)
			getReservations();
	}
}

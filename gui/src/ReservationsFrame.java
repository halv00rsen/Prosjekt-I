package src;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReservationsFrame extends JPanel implements LoginListener, ValidDatesListener, ReservationsListListener, ChangeTabListener{
	
	private ValidDates validDates;
	private JButton reserveButton;
//	private JComboBox<String> cabins;
	private ChooseCabin cabins;
	private JComboBox<Integer> day, numDays;
	private JComboBox<Months> month;
	private boolean isLoggedIn;
	private String username;
	private final JLabel isValidDateReservation;
	private final JTextArea cabinInformation;
	private ReservationsFrameListener listener;
	private boolean adminLogin;
	private Koie cabinChosen;
	
	/**
	 * Oppretter et panel der man kan reservere nye koier med dato fra og antall dager reservasjonen skal telle
	 * 
	 */
	public ReservationsFrame(){
		setLayout(new GridLayout(1,2));
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		JPanel panel = new JPanel();
		panel.setLayout(layout);
		cabins = new ChooseCabin();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Koie: "), c);
		c.gridx = 3;
		panel.add(cabins.getComboBox(), c);
		c.gridy = 1;
		c.gridx = 0;
		panel.add(new JLabel("Dag: "), c);
		day = new JComboBox<Integer>();
		month = new JComboBox<Months>();
		numDays = new JComboBox<Integer>();
		validDates = new ValidDates(day, month, numDays);
		cabins.addActionListener(new CabinListener());
		c.gridx = 3;
		panel.add(day, c);
		c.gridy = 2;
		panel.add(month, c);
		c.gridx = 0;
		panel.add(new JLabel("MÃ¥ned:"), c);
		c.gridy = 3;
		panel.add(new JLabel("Antall dager:"), c);
		c.gridx = 3;
		panel.add(numDays, c);
		isLoggedIn = false;
		username = null;
		c.gridy = 5;
		c.gridx = 0;
		panel.add(new JLabel("Er ledig:"), c);
		c.gridx = 3;
		isValidDateReservation = new JLabel("-");
		panel.add(isValidDateReservation, c);
		reserveButton = new JButton("Reserver");
		reserveButton.addActionListener(new ButtonListener());
		c.gridy = 6;
		c.gridx = 2;
		panel.add(reserveButton, c);
		validDates.addListener(this);
		add(panel);
		cabinInformation = new JTextArea(4, 4);
		cabinInformation.setEditable(false);
		cabinInformation.setBorder(BorderFactory.createEtchedBorder());
		cabinInformation.setLineWrap(true);
		cabinInformation.setWrapStyleWord(true);
		JScrollPane pane = new JScrollPane(cabinInformation);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(pane);
		
	}
	
	public void setListener(ReservationsFrameListener l){
		listener = l;
	}
	
	public void updateField(int day, int month, int numDays){
		if (cabinChosen == null)
			return;
		boolean isValid = cabinChosen.getCalendar().reservationIsOk(new Date(day, month), numDays, false);
		isValidDateReservation.setForeground(isValid ? Color.green: Color.red);
		isValidDateReservation.setText((isValid ? "Ja": "Nei"));
	}
	
	private boolean isValidReservation(int day, int month, int numDays){
		return cabinChosen.getCalendar().reservationIsOk(new Date(day, month), numDays, false);
	}
	
	public void userHasLoggedIn(String username){
		this.username = username;
		isLoggedIn = true;
	}
	
	public void userHasLoggedOut(){
		this.username = null;
		isLoggedIn = false;
		adminLogin = false;
	}
	
	private void setCabinInformation(){
		if (cabinChosen == null)
			return;
		String info = "Navn: " + cabinChosen.getName();
		info += "\nKoordinat: " + cabinChosen.getCoordinate();
		info += "\nBygget: " + cabinChosen.getYear();
		info += "\nSengeplasser: " + cabinChosen.getNumBeds();
		info += "\nSitteplasser: " + cabinChosen.getNumSeats();
		info += "\nTerreng: " + cabinChosen.getTerreng();
		info += "\nSykkel: " + cabinChosen.getSykkel();
		info += "\nTopptur: " + cabinChosen.getTopptur();
		info += "\nJakt og fiske: " + cabinChosen.getJaktOgFiske();
		info += "\nSpesialiteter: " + cabinChosen.getSpesialiteter();
		info += "\nUtstyr:";
		for (Item item: cabinChosen.getInventory().getInOrderItems())
			info += " " + item.getName() + ",";
		info += "\n\nKoia er reservert på følgende dager:";
		if (cabinChosen.getCalendar().getDatesBooked().size() == 0){
			info += "\nIngen reservasjoner for denne koia.";
		}else
			for (BookingDate bookings : cabinChosen.getCalendar().getDatesBooked()){
				info += "\n" + bookings.dateFrom.toString() + " til " + bookings.dateTo.toString();
			}
		cabinInformation.setText(info);
	}
	
	private void buttonPressed(){
		if (adminLogin){
			JOptionPane.showMessageDialog(null, "Kan ikke reservere koie som admin.");
			return;
		}
		if (!isLoggedIn){
			JOptionPane.showMessageDialog(null, "Du er ikke logged inn, og kan dermed ikke reservere ei koie."
					+ "\nGÃ¥ til innloggingsfanen for Ã¥ logge inn.");
			return;
		}
		int[] date = validDates.getReservation();
		if (isValidReservation(date[0], date[1], date[2])){
			Date from = new Date(date[0], date[1]);
			Date to = Calendar.getLastDate(from, date[2]);
			cabinChosen.getCalendar().reservePeriod(from, date[2], username, -1, false);
			Database.toDatabase(cabinChosen);
			JOptionPane.showMessageDialog(null, "Din reservasjon til " + cabins.getSelectedItem() + " den " + date[0] + "." + date[1] + " i " + 
											date[2] + " dag(er), ble godkjent og lagret.");
			setCabinInformation();
			updateField(date[0], date[1], date[2]);
			List<UserDatesBooked> bookings = Database.getReservasjonBruker(username);
			int resId = -1;
			cabinChosen.getCalendar().getReservation(from, to).setIsFromDatabase(true);
			for (UserDatesBooked booked: bookings){
				if (booked.from.equals(from) && booked.to.equals(to)){
					resId = booked.resID;
					break;
				}
			}
			if (resId == -1){
				System.out.println("Feil med resId i reservationsFrame");
			}
			for (BookingDate dates: cabinChosen.getCalendar().getDatesBooked()){
				if (dates.equals(from, to)){
					dates.setID(resId);
					break;
				}
			}
			callListener(cabinChosen.getName(), from, to, resId);
		}else{
			JOptionPane.showMessageDialog(null, "Din reservasjon ble ikke godkjent.");
		}
	}
	
	private void callListener(String name, Date from, Date to, int resId){
		listener.addReservation(name, from, to, resId);
	}
	
	private class CabinListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			int cabinId = cabins.getSelectedItem();
			cabinChosen = Database.getKoie(cabinId);
			validDates.setCabin(cabinId);
			setCabinInformation();
		}
	}
	
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			buttonPressed();
		}
		
	}

	public void adminHasLoggedIn() {
		adminLogin = true;
	}

	public void removeReservation(int resId) {
		Database.slettReservasjon(resId);
//		HashMap<Integer, String> cabins = Database.getIdNameMap();
//		int id = -1;
//		for (Integer ids: cabins.keySet()){
//			if (cabins.get(ids).equals(idName)){
//				id = ids;
//				break;
//			}
//		}if (id == -1)
//			return;
//		Koie koie = Database.getKoie(id);
//		koie.getCalendar().removeReservation(from, to);
//		Database.toDatabase(koie);
//		setCabinInformation();
	}

	public void initPanel() {
		cabinChosen = Database.getKoie(cabins.getSelectedItem());
		setCabinInformation();
	}
}

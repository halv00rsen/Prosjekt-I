package src;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RemoveReservationsAdmin extends JPanel implements ReservationRowListener{

	private final JPanel panel;
	private final ChooseCabin chooseCabin;
	private Koie cabin;
	private final ReservationsListListener deleteListener;
	
	public RemoveReservationsAdmin(ReservationsListListener deleteListener){
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		chooseCabin = new ChooseCabin();
		setLayout(new GridLayout(1,2));
		JPanel panel2 = new JPanel();
		panel2.add(new JLabel("Koie: "));
		panel2.add(chooseCabin.getComboBox(), BorderLayout.EAST);
		JScrollPane pane = new JScrollPane(panel);
		chooseCabin.addActionListener(new KoieListener());
		panel.setBorder(BorderFactory.createTitledBorder("Reservasjoner"));
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getAllReservations();
		add(panel2);
		add(pane);
		this.deleteListener = deleteListener;
	}
	
	private void getAllReservations(){
		panel.removeAll();
		cabin = Database.getKoie(chooseCabin.getSelectedItem());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		for (BookingDate booking: cabin.getCalendar().getDatesBooked()){
			panel.add(new ReservationRow(cabin.getName(),booking.person, booking.getID(), booking.dateFrom, booking.dateTo, cabin.getCalendar().getTodaysDate(), true, true, this), c);
			c.gridy++;
		}
		panel.revalidate();
		panel.repaint();
	}

	public void removeReservation(ReservationRow reservation) {
		panel.remove(reservation);
		deleteListener.removeReservation(reservation.getFrom(), reservation.getTo(), reservation.getCabinName());
		panel.revalidate();
		panel.repaint();
	}
	
	private class KoieListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			getAllReservations();
		}
	}
}

package src;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RemoveReservationsAdmin extends JPanel implements ReservationRowListener{

	private JPanel panel;
	private ChooseCabin chooseCabin;
	
	public RemoveReservationsAdmin(){
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		chooseCabin = new ChooseCabin();
		setLayout(new GridLayout(1,2));
		JPanel panel2 = new JPanel();
		panel2.add(new JLabel("Koie: "));
		panel2.add(chooseCabin.getComboBox(), BorderLayout.EAST);
		JScrollPane pane = new JScrollPane(panel);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getAllReservations();
		add(panel2);
		add(pane);
	}
	
	private void getAllReservations(){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new ReservationRow("Kallekula", new Date(13, 11), new Date(18, 11), false, true, this), c);
	}

	public void removeReservation(ReservationRow reservation) {
		panel.remove(reservation);
	}
}

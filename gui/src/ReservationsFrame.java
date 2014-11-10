package src;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ReservationsFrame extends JPanel implements LoginListener, ValidDatesListener{
	
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
		panel.add(new JLabel("Måned:"), c);
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
		add(cabinInformation);
	}
	
	public void addListener(ReservationsFrameListener l){
		listener = l;
	}
	
	public void isValidDate(boolean isValid){
		isValidDateReservation.setForeground(isValid ? Color.green: Color.red);
		isValidDateReservation.setText((isValid ? "Ja": "Nei"));
	}
	
	private boolean isValidReservation(int day, int month, int numDays){
		int cabinId = cabins.getSelectedItem();
		//skal sjekke databasen om denne er valid
		return true;
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
	
	private void setCabinInformation(int cabin){
		String info = "Koie: " + cabin + "\n";
		info += "Byggeår: 1948\nAntall sengeplasser: 10\nKoordinater: 34 23\nVaffelgjern: Ja\nTerreng: S\nGitar: Ja\nBadstue: Ja\n"
				+ "Båt: Nei";
		cabinInformation.setText(info);
	}
	
	private void buttonPressed(){
		if (adminLogin){
			JOptionPane.showMessageDialog(null, "Kan ikke reservere koie som admin.");
			return;
		}
		if (!isLoggedIn){
			JOptionPane.showMessageDialog(null, "Du er ikke logged inn, og kan dermed ikke reservere ei koie."
					+ "\nGå til innloggingsfanen for å logge inn.");
			return;
		}
		int[] date = validDates.getReservation();
		if (isValidReservation(date[0], date[1], date[2])){
			JOptionPane.showMessageDialog(null, "Din reservasjon til " + cabins.getSelectedItem() + " den " + date[0] + "." + date[1] + " i " + 
											date[2] + " dage(r), ble godkjent og lagret.");
		}else{
			JOptionPane.showMessageDialog(null, "Din reservasjon ble ikke godkjent.");
		}
	}
	
	private class CabinListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			int cabinId = cabins.getSelectedItem();
			validDates.setCabin(cabinId);
			setCabinInformation(cabinId);
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
}

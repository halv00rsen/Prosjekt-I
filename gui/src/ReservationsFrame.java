package src;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReservationsFrame extends JPanel implements LoginListener{
	
	private ValidDates validDates;
	private JButton reserveButton;
	private JComboBox<String> cabins;
	private boolean isLoggedIn;
	private String username;
	
	public ReservationsFrame(){
		setLayout(new GridLayout(4, 4));
		add(new JLabel("Velg Koie: "));
		cabins = new JComboBox<String>();
		add(cabins);
		for (String name : getNameCabins())
			cabins.addItem(name);
		add(new JLabel("Velg dato: "));
		validDates = new ValidDates();
		add(validDates);
		reserveButton = new JButton("Reserver");
		reserveButton.addActionListener(new ButtonListener());
		add(reserveButton);
		isLoggedIn = false;
		username = null;
	}
	
	private boolean isValidReservation(int day, int month, int numDays){
		return true;
	}
	
	private List<String> getNameCabins(){
		List<String> koie = new ArrayList<String>();
		koie.add("Koie 1");
		koie.add("Koie 2");
		koie.add("Koie 3");
		koie.add("Koie 4");
		return koie;
	}
	
	public void userHasLoggedIn(String username){
		this.username = username;
		isLoggedIn = true;
	}
	
	public void userHasLoggedOut(){
		this.username = null;
		isLoggedIn = false;
	}
	
	private void buttonPressed(){
		if (!isLoggedIn){
			JOptionPane.showMessageDialog(null, "Du er ikke logged inn, og kan dermed ikke reservere ei koie");
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
	
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			buttonPressed();
		}
		
	}
}

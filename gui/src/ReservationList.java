package src;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class ReservationList extends JPanel implements LoginListener{
	
	private final List<String> reservations;
	private String username;
	private final JTextArea cabinNames, dateFrom, dateTo, comment;
	
	public ReservationList(){
		reservations = new ArrayList<String>();
		this.setLayout(new GridLayout(2, 1));
		add(new JLabel("Dette er siden for å se dine reservasjoner."
				+ "\n Du kan slette reservasjoner og du kan melde inn rapport om en koie du har vært på."));
		cabinNames = new JTextArea(10, 10);
		dateFrom = new JTextArea(10, 5);
		dateTo = new JTextArea(10, 5);
		comment = new JTextArea(10, 10);
		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		setInfoTextArea(cabinNames, "Koie", panel, layout, c);
		setInfoTextArea(dateFrom, "Fra", panel, layout, c);
		setInfoTextArea(dateTo, "Til", panel, layout, c);
		setInfoTextArea(comment, "Rapportert", panel, layout, c);
		this.add(panel);
	}
	
	private void setInfoTextArea(JTextArea area, String name, JPanel panel, GridBagLayout layout, GridBagConstraints c){
		area.setBorder(BorderFactory.createTitledBorder(name));
		area.setEditable(false);
		layout.setConstraints(area, c);
		panel.add(area);
	}
	
	private void getReservations(){
		//Skal bruke databasen for å hente alle reservasjoner til brukeren
		reservations.add("Koie 1:21.10:29.10:Nei");
		reservations.add("Koie 2:10.2:17.2:Ikke relevant");
	}

	public void userHasLoggedIn(String username) {
		this.username = username;
		getReservations();
		String cabins = "", from = "", to = "", comment = "";
		for (String res : reservations){
			String[] splittedRes = res.split(":");
			cabins += splittedRes[0] + "\n";
			from += splittedRes[1] + "\n";
			to += splittedRes[2] + "\n";
			comment += splittedRes[3] + "\n";
		}
		cabinNames.setText(cabins);
		dateFrom.setText(from);
		dateTo.setText(to);
		this.comment.setText(comment);
	}

	public void userHasLoggedOut() {
		reservations.clear();
		cabinNames.setText("");
		dateFrom.setText("");
		dateTo.setText("");
		comment.setText("");
	}

	@Override
	public void adminHasLoggedIn() {
		// TODO Auto-generated method stub
		
	}

}

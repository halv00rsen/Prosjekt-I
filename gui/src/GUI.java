package src;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;

public class GUI implements LoginListener{

	private static JFrame frame;
	
	private final LoginPanel loginPanel;
	private final ReservationsFrame reservationFrame;
	private final ReservationList reservationList;
	private final JTabbedPane tabbedPane;
	
	public GUI(){
		frame = new JFrame("NTNUI-Koiene");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 500);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		loginPanel = new LoginPanel();
		tabbedPane.addTab("Innlogging", null, loginPanel, null);
		
		
		JPanel Koier = new JPanel();
		tabbedPane.addTab("Koier", null, Koier, null);
		
		reservationList = new ReservationList();
		tabbedPane.addTab("Reservasjoner", null, reservationList, null);
		loginPanel.addListener(reservationList);
		
		
		reservationFrame = new ReservationsFrame();
		tabbedPane.addTab("Reserver Koie", null, reservationFrame, null);
		loginPanel.addListener(reservationFrame);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Kart", null, panel_2, null);
//		frame.pack();
		frame.setLocationRelativeTo(null);
//		frame.setResizable(false);
		frame.setVisible(true);
		
	}

	public void userHasLoggedIn(String userName) {
		
	}

	public void userHasLoggedOut() {
		
	}

	public void adminHasLoggedIn() {
		
	}

	public static int getXPosition() {
		if (frame == null)
			return -1;
		return frame.getX();
	}

	public static int getYPosition() {
		if (frame == null)
			return -1;
		return frame.getY();
	}
	
}
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

	public static boolean DEBUG = true;
	public static int WIDTH = 600, HEIGHT = 500;
	
	private static JFrame frame;
	
	private final LoginPanel loginPanel;
	private final ReservationsFrame reservationFrame;
	private final ReservationList reservationList;
	private final JTabbedPane tabbedPane;
	private final CreateUser createUser;
	
	private ItemStatus itemStatus;
	private WoodStatus woodStatus;
	private MessageAdmin messageAdmin;
	private RemoveReservationsAdmin removeReservations;
	
	public GUI(){
		frame = new JFrame("NTNUI-Koiene");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		loginPanel = new LoginPanel();
		tabbedPane.addTab("Innlogging", null, loginPanel, null);
		
		createUser = new CreateUser();
		tabbedPane.addTab("Ny bruker", null, createUser, null);
		
		JPanel Koier = new JPanel();
		tabbedPane.addTab("Koier", null, Koier, null);
		
		reservationList = new ReservationList();
		tabbedPane.addTab("Reservasjoner", null, reservationList, null);
		loginPanel.addListener(reservationList);
		
		
		reservationFrame = new ReservationsFrame();
		tabbedPane.addTab("Reserver Koie", null, reservationFrame, null);
		loginPanel.addListener(reservationFrame);
		loginPanel.addListener(this);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Kart", null, panel_2, null);
//		frame.pack();
		frame.setLocationRelativeTo(null);
//		frame.setResizable(false);
		frame.setVisible(true);
		
	}

	public void userHasLoggedIn(String username) {
		frame.setTitle("NTNUI-Koiene (" + username + ")");
		tabbedPane.remove(createUser);
	}

	public void userHasLoggedOut() {
		frame.setTitle("NTNUI-Koiene");
		tabbedPane.remove(itemStatus);
		itemStatus = null;
		tabbedPane.remove(woodStatus);
		woodStatus = null;
		tabbedPane.remove(messageAdmin);
		messageAdmin = null;
		tabbedPane.remove(removeReservations);
		removeReservations = null;
		tabbedPane.addTab("Ny bruker", createUser);
	}

	public void adminHasLoggedIn() {
//		Utstyrstatus, legg inn nytt utstyr, sjekk vedstatus, veddugnad, 
		frame.setTitle("NTNUI-Koiene (admin)");
		itemStatus = new ItemStatus();
		tabbedPane.addTab("Utstyrstatus", null, itemStatus, null);
		woodStatus = new WoodStatus();
		tabbedPane.addTab("Vedstatus", null, woodStatus, null);
		messageAdmin = new MessageAdmin();
		tabbedPane.addTab("Meldinger", null, messageAdmin, null);
		removeReservations = new RemoveReservationsAdmin();
		tabbedPane.addTab("Fjern reservasjoner", null, removeReservations, null);
		tabbedPane.remove(createUser);
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
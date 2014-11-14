package src;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI implements LoginListener{

	public static boolean DEBUG = true;
	public static int WIDTH = 600, HEIGHT = 500;
	private static Map<Integer, String> allCabins;
	
	private static JFrame frame;
	
	private final LoginPanel loginPanel;
	private final ReservationsFrame reservationFrame;
	private final ReservationList reservationList;
	private final JTabbedPane tabbedPane;
	
	private ItemStatus itemStatus;
	private WoodStatus woodStatus;
	private MessageAdmin messageAdmin;
	private RemoveReservationsAdmin removeReservations;
	private AddItemAdmin addItemAdmin;
	private MailToUserAdmin mailToUser;
	
	public GUI(){
		frame = new JFrame("NTNUI-Koiene");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		loginPanel = new LoginPanel();
		tabbedPane.addTab("Innlogging", null, loginPanel, null);
		
		reservationList = new ReservationList();
		tabbedPane.addTab("Reservasjoner", null, reservationList, null);
		loginPanel.addListener(reservationList);
		
		allCabins = Database.getIdNameMap();
		reservationFrame = new ReservationsFrame();
		tabbedPane.addTab("Reserver Koie", null, reservationFrame, null);
		loginPanel.addListener(reservationFrame);
		loginPanel.addListener(this);
		reservationFrame.setListener(reservationList);
		reservationList.setListener(reservationFrame);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e){
				((ChangeTabListener) tabbedPane.getSelectedComponent()).initPanel();
			}
		});
		
//		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}

	public void userHasLoggedIn(String username) {
		frame.setTitle("NTNUI-Koiene (" + username + ")");
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
		tabbedPane.remove(addItemAdmin);
		addItemAdmin = null;
		tabbedPane.remove(mailToUser);
		mailToUser = null;
	}
	
	public static Map<Integer, String> getIdMap(){
		if (allCabins == null)
			return null;
		return new HashMap<Integer, String>(allCabins);
	}

	public void adminHasLoggedIn() {
//		Utstyrstatus, legg inn nytt utstyr, sjekk vedstatus, veddugnad, 
		frame.setTitle("NTNUI-Koiene (admin)");
		itemStatus = new ItemStatus();
		tabbedPane.addTab("Utstyrstatus", null, itemStatus, null);
		addItemAdmin = new AddItemAdmin();
		tabbedPane.addTab("Legg til utstyr", null, addItemAdmin, null);
		woodStatus = new WoodStatus();
		tabbedPane.addTab("Vedstatus", null, woodStatus, null);
		removeReservations = new RemoveReservationsAdmin(reservationFrame);
		tabbedPane.addTab("Fjern reservasjoner", null, removeReservations, null);
		mailToUser = new MailToUserAdmin();
		tabbedPane.addTab("Mail til bruker", null, mailToUser, null);
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
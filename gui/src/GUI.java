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

/**
 * 
 * Hovedklassen til GUIet. Lager en instans av alt som har med gui, inneholder også selve JFramen.
 */
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
	private RemoveReservationsAdmin removeReservations;
	private AddItemAdmin addItemAdmin;
	private MailToUserAdmin mailToUser;
	private CabinMapField mapField;
//	private AdminReport adminReport;
	
	/**
	 * Oppretter JFrame og legger til alle fanene i programmet
	 */
	public GUI(){
		frame = new JFrame("NTNUI-Koiene");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		loginPanel = new LoginPanel();
		tabbedPane.addTab("Innlogging", null, loginPanel, null);
		
		reservationList = new ReservationList();
//		tabbedPane.addTab("Reservasjoner", null, reservationList, null);
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

	/**
	 * Setter brukernavnet til brukeren i tittelen til programmet.
	 * @param username - brukernavnet til brukeren som har logget inn
	 */
	public void userHasLoggedIn(String username) {
		frame.setTitle("NTNUI-Koiene (" + username + ")");
		tabbedPane.addTab("Reservasjoner", null, reservationList, null);
	}

	/**
	 * Om en bruker/admin logger ut, resetter guiet tittelen og fjerner alle instanser som kun admin
	 * har tilgang til.
	 */
	public void userHasLoggedOut() {
		frame.setTitle("NTNUI-Koiene");
		tabbedPane.remove(itemStatus);
		itemStatus = null;
		tabbedPane.remove(woodStatus);
		woodStatus = null;
		tabbedPane.remove(removeReservations);
		removeReservations = null;
		tabbedPane.remove(addItemAdmin);
		addItemAdmin = null;
		tabbedPane.remove(mailToUser);
		mailToUser = null;
		tabbedPane.remove(mapField);
		mapField = null;
		tabbedPane.remove(reservationList);
//		tabbedPane.remove(adminReport);
//		adminReport = null;
	}
	
	/**
	 * 
	 * @return HashMap - returnerer id og navn til alle koiene i koienettverket
	 */
	public static Map<Integer, String> getIdMap(){
		if (allCabins == null)
			return null;
		return new HashMap<Integer, String>(allCabins);
	}

	/**
	 * Oppretter alle instanser admin skal ha tilgang til
	 */
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
		mapField = new CabinMapField();
		tabbedPane.addTab("Kart", null, mapField, null);
//		adminReport = new AdminReport();
//		tabbedPane.addTab("Rapporter", null, adminReport, null);
	}

	/**
	 * 
	 * @return x - returnerer JFramens x posisjon
	 */
	public static int getXPosition() {
		if (frame == null)
			return -1;
		return frame.getX();
	}

	/**
	 * 
	 * @return y - returnerer JFramens y posisjon
	 */
	public static int getYPosition() {
		if (frame == null)
			return -1;
		return frame.getY();
	}
	
}
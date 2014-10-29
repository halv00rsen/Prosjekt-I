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

public class GUI {

	private JFrame frame;
	
	private LoginPanel loginPanel;
	private ReservationsFrame reservationFrame;
	
	public GUI(){
		frame = new JFrame("NTNUI-Koiene");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		loginPanel = new LoginPanel();
		tabbedPane.addTab("Innlogging", null, loginPanel, null);
		
		
		JPanel Koier = new JPanel();
		tabbedPane.addTab("Koier", null, Koier, null);
		
		JPanel reservations = new JPanel();
		tabbedPane.addTab("Reservasjoner", null, reservations, null);
		
		
		reservationFrame = new ReservationsFrame();
		tabbedPane.addTab("Reserver Koie", null, reservationFrame, null);
		loginPanel.addListener(reservationFrame);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Kart", null, panel_2, null);
		frame.pack();
		frame.setVisible(true);
		
	}
	
}
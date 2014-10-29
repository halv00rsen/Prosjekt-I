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
	private AdminLogin loginUser;
	private ValidDates validDates;
	
	public GUI(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		loginUser = new AdminLogin(new Login());
		JPanel login = new JPanel();
		tabbedPane.addTab("Innlogging", null, login, null);
		login.add(loginUser);
		
		
		JPanel Koier = new JPanel();
		tabbedPane.addTab("Koier", null, Koier, null);
		
		JPanel reservations = new JPanel();
		tabbedPane.addTab("Reservasjoner", null, reservations, null);
		
		
		JPanel reserveCabin = new JPanel();
		tabbedPane.addTab("Reserver Koie", null, reserveCabin, null);
		
		reserveCabin.setLayout(new GridLayout(4, 4));
		reserveCabin.add(new JLabel("Velg Koie: "));
		JComboBox<String> comboBox = new JComboBox<String>();
		reserveCabin.add(comboBox);
		for (String name : getNameCabins())
			comboBox.addItem(name);
		reserveCabin.add(new JLabel("Velg dato: "));
		validDates = new ValidDates();
		reserveCabin.add(validDates);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Kart", null, panel_2, null);
		frame.setVisible(true);
		
	}
	
	private List<String> getNameCabins(){
		List<String> koie = new ArrayList<String>();
		koie.add("Koie 1");
		koie.add("Koie 2");
		koie.add("Koie 3");
		koie.add("Koie 4");
		return koie;
	}
	
	private class Login implements AdminLoginListener{

		public void login(String userName, char[] password) {
			System.out.println(userName);
		}
		
	}
}

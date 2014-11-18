package src;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * Et JPanel som håndterer om en bruker vil logge inn
 */
public class LoginPanel extends JPanel implements ChangeTabListener{
	
	public static final boolean DEBUG = false;
	private final AdminLogin loginUser;
	private final List<LoginListener> listeners;
	private final JButton logoutButton, resetDatabaseButton;
	private final JCheckBox adminCheckBox;
	private final JPanel adminBox, panel;
	private final CreateUser newUser;
	
	/**
	 * Oppretter objektet
	 */
	public LoginPanel(){
		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Innlogging"));
		this.setLayout(new GridLayout(2, 1));
		loginUser = new AdminLogin(new Login());
		listeners = new ArrayList<LoginListener>();
		logoutButton = new JButton("Log ut");
		logoutButton.addActionListener(new Logout());
		panel.add(loginUser);
		adminCheckBox = new JCheckBox();
		adminBox = new JPanel();
		adminBox.add(new JLabel("Adminlogin: "), BorderLayout.WEST);
		adminBox.add(adminCheckBox);
		panel.add(adminBox);
		add(panel);
		newUser = new CreateUser();
		add(newUser);
		resetDatabaseButton = new JButton("Tilbakestill database");
		resetDatabaseButton.addActionListener(new ResetButton());
	}
	
	/**
	 * 
	 * @param l - blir lagt til som lytter av LoginPanel
	 */
	public void addListener(LoginListener l){
		listeners.add(l);
	}
	
	/**
	 * 
	 * @param l - fjerner lytteren av LoginPanel
	 */
	public void removeListener(LoginListener l){
		listeners.remove(l);
	}
	
	private boolean isValidLogin(String username, char[] password){
		if (!DEBUG){
			Bruker user = Database.getBruker(username);
			if (user == null)
				return false;
			return user.isPasswordCorrect(convertPassword(password)) && !user.isAdmin();
		}
		return true;
	}
	
	private String convertPassword(char[] password){
		String pass = "";
		for (char c : password)
			pass += c;
		return pass;
	}
	
	private boolean isValidAdminLogin(String username, char[] password){
		if (!DEBUG){
			Bruker user = Database.getBruker(username);
			if (user == null)
				return false;
			return user.isPasswordCorrect(convertPassword(password)) && user.isAdmin();
		}
		return true;
	}
	
	private void userLoggedIn(String userName){
		for (LoginListener l : listeners)
			l.userHasLoggedIn(userName);
		this.removeAll();
		loginUser.resetFields();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		add(new JLabel("Innlogget som " + userName + ".  "), c);
		c.gridx = 1;
		add(logoutButton, c);
		this.repaint();
	}
	
	private void adminLoggedIn(String username){
		for (LoginListener l: listeners)
			l.adminHasLoggedIn();
		this.removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		loginUser.resetFields();
		add(new JLabel("Logget in som admin.  "), c);
		c.gridx = 1;
		add(logoutButton,c);
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 2;
		add(resetDatabaseButton, c);
		this.repaint();
	}
	
	private void userLoggedOut(){
		for (LoginListener l : listeners)
			l.userHasLoggedOut();
		this.removeAll();
		setLayout(new GridLayout(2,1));
		this.add(panel);
		add(newUser);
		this.repaint();
	}
	
	/**
	 * 
	 * Lytter til logutknappen
	 */
	private class Logout implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			userLoggedOut();
		}
		
	}
	
	/**
	 * 
	 * Lytter til databaseknappen
	 */
	private class ResetButton implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, Database.initializeDatabase() ? "Databasen er tilbakestilt": "Databasen ble ikke tilbakestilt");
		}
	}
	
	/**
	 * 
	 * Lytter på innloggingsknappen
	 */
	private class Login implements AdminLoginListener{

		public void login(String userName, char[] password) {
			if (adminCheckBox.isSelected()){
				if (isValidAdminLogin(userName, password)){
					adminLoggedIn(userName);
				}else{
					JOptionPane.showMessageDialog(null, "Feil brukernavn/passord til adminlogin");
					loginUser.resetFields();
				}
			}
			else if (isValidLogin(userName, password)){
				userLoggedIn(userName);
			}
			else{
				JOptionPane.showMessageDialog(null, "Feil brukernavn/passord");
				loginUser.resetFields();
			}
		}
	}

	public void initPanel() {
		//Ikke i bruk i denne fanen
	}
}

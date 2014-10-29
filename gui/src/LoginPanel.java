package src;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LoginPanel extends JPanel{
	
	private final AdminLogin loginUser;
	private final List<LoginListener> listeners;
	private final JLabel errorLabel;
	private final JButton logoutButton;
	private final JCheckBox adminCheckBox;
	
	public LoginPanel(){
		errorLabel = new JLabel("Feil brukernavn/passord");
//		this.setLayout(new GridLayout(2, 1));
		loginUser = new AdminLogin(new Login());
		listeners = new ArrayList<LoginListener>();
		logoutButton = new JButton("Log ut");
		logoutButton.addActionListener(new Logout());
		this.add(loginUser, BorderLayout.CENTER);
		adminCheckBox = new JCheckBox();
		JPanel panel = new JPanel();
		panel.add(new JLabel("Adminlogin: "), BorderLayout.WEST);
		panel.add(adminCheckBox);
		this.add(panel, BorderLayout.NORTH);
	}
	
	public void addListener(LoginListener l){
		listeners.add(l);
	}
	
	public void removeListener(LoginListener l){
		listeners.remove(l);
	}
	
	private boolean isValidLogin(String userName, char[] password){
		return !userName.equals("admin");
	}
	
	private boolean isValidAdminLogin(String username, char[] password){
		return username.equals("admin");
	}
	
	private void userLoggedIn(String userName){
		for (LoginListener l : listeners)
			l.userHasLoggedIn(userName);
		this.removeAll();
		loginUser.resetFields();
		add(new JLabel("Innlogget som " + userName));
		add(logoutButton);
		this.repaint();
	}
	
	private void userLoggedOut(){
		for (LoginListener l : listeners)
			l.userHasLoggedOut();
		this.removeAll();
		this.add(loginUser);
		this.repaint();
	}
	
	private class Logout implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			userLoggedOut();
		}
		
	}
	
	private class Login implements AdminLoginListener{

		public void login(String userName, char[] password) {
			if (adminCheckBox.isSelected()){
				if (isValidAdminLogin(userName, password)){
					userLoggedIn(userName);
				}else{
					JOptionPane.showMessageDialog(null, "Feil brukernavn/passord til adminlogin");
					loginUser.resetFields();
				}
			}
			else if (isValidLogin(userName, password)){
				userLoggedIn(userName);
			}
			else{
				JOptionPane.showMessageDialog(null, "Feil brukernav/passord");
				loginUser.resetFields();
			}
		}
	}
}

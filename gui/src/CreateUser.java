package src;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateUser extends JPanel{
	
	private final JTextField username;
	private final JPasswordField password, checkPassword;
	private final JButton ok, cancel;
	public final static int leastLengthPassword = 6;
	
	public CreateUser(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 0, 0));
		panel.add(new JLabel("Email: "));
		username = new JTextField(20);
		panel.add(username);
		panel.add(new JLabel("Passord: "));
		password = new JPasswordField();
		panel.add(password);
		panel.add(new JLabel("Gjenta passord: "));
		checkPassword = new JPasswordField();
		panel.add(checkPassword);
		ok = new JButton("Lag bruker");
		panel.add(ok);
		cancel = new JButton("Avbryt");
		panel.add(cancel);
		ButtonListener listener = new ButtonListener();
		ok.addActionListener(listener);
		cancel.addActionListener(listener);
		add(panel);
	}

	private boolean validEmail(){
		String email = username.getText().toLowerCase().trim();
		if (email.indexOf("@") == -1 || email.indexOf(".") == -1){
			JOptionPane.showMessageDialog(null, "Feil format på email");
			return false;
		}
//		check database for email
		return true;
	}
	
	private boolean validPassword(){
		char[] password1 = password.getPassword();
		if (password1.length < leastLengthPassword){
			JOptionPane.showMessageDialog(null, "Passordet er for kort.");
			return false;
		}
		char[] passwordCheck = checkPassword.getPassword();
		for (int a = 0; a < password1.length; a++){
			if (password1[a] != passwordCheck[a]){
				JOptionPane.showMessageDialog(null, "Passordene stemmer ikke med hverandre");
				return false;
			}
		}
//		check valid username in database
		return true;
	}
	
	private void createUserInDatabase(){
		
	}
	
	public void resetFields(){
		username.setText("");
		password.setText("");
		checkPassword.setText("");
	}
	
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == ok){
				if (!validPassword() || !validEmail())
					return;
				createUserInDatabase();
				resetFields();
			}else{
				resetFields();
			}
		}
	}
}

package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * Klassen er en instans av JPanel og gir en bruker/admin mulighet til å lage en ny bruker
 */
public class CreateUser extends JPanel{
	private final JTextField username;
	private final JPasswordField password, checkPassword, adminCode;
	private final JButton ok, cancel;
	public final static int leastLengthPassword = 6;
	private final JCheckBox adminCheckBox;
	private final GridBagConstraints c;
	private final JPanel panel;

	/**
	 * Oppretter objektet der man kan lage en ny bruker i systemet.
	 * 
	 */
	public CreateUser(){
		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Ny bruker"));
		adminCheckBox = new JCheckBox();
		adminCheckBox.addActionListener(new AdminListener());
		adminCode = new JPasswordField(4);
		c = new GridBagConstraints();
		panel.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Email: "), c);
		username = new JTextField(20);
		c.gridx = 1;
		panel.add(username, c);
		c.gridy = 1;
		password = new JPasswordField(20);
		panel.add(password, c);
		c.gridx = 0;
		panel.add(new JLabel("Passord: "), c);
		c.gridy = 2;
		panel.add(new JLabel("Gjenta passord: "), c);
		checkPassword = new JPasswordField(20);
		c.gridx = 1;
		panel.add(checkPassword, c);
		ok = new JButton("Lag bruker");
		c.gridy = 3;
		c.gridx = 0;
		panel.add(ok, c);
		cancel = new JButton("Avbryt");
		c.gridx = 1;
		panel.add(cancel,c);
		c.gridy = 2;
		c.gridx = 2;
		panel.add(new JLabel("Adminbruker: "), c);
		c.gridx = 3;
		panel.add(adminCheckBox, c);
		ButtonListener listener = new ButtonListener();
		ok.addActionListener(listener);
		cancel.addActionListener(listener);
		add(panel);
	}

	/**
	 * Sjekker om emailen er en valid emailadresse og om den finnes fra fÃ¸r i databasen
	 * @return boolean 
	 */
	private boolean validEmail(){
		String email = username.getText().toLowerCase().trim();
		if (adminCheckBox.isSelected()){
			return email != null && email.length() > 3 && email.indexOf("@") == -1;
		}
		if (email.indexOf("@") == -1 || email.indexOf(".") == -1){
			JOptionPane.showMessageDialog(null, "Feil format på email");
			return false;
		}
		if (Database.getBruker(email) != null){
			JOptionPane.showMessageDialog(null, "Brukernavnet er allerede tatt");
			return false;
		}
		return true;
	}
	
	private boolean validAdminCode(){
		char[] code = adminCode.getPassword();
		String hardCode = "1234";
		if (code.length != hardCode.length())
			return false;
		for (int a = 0; a < code.length; a++){
			if (code[a] != hardCode.charAt(a))
				return false;
		}
		return true;
	}
	
	/**
	 * Sjekker om passordene stemmer overens og om det er lang nok lengde pÃ¥ dem
	 * @return boolean
	 */
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
		return true;
	}
	
	private void createUserInDatabase(){
		String pass = "";
		for (char c : password.getPassword()){
			pass += c;
		}
		String username = this.username.getText().trim().toLowerCase();
		Database.addBruker(username, pass, adminCheckBox.isSelected());
		JOptionPane.showMessageDialog(null, "Du har nÃ¥ opprettet en bruker!");
	}
	
	/**
	 * Fjerner all tekst i alle inputfelt pÃ¥ siden
	 */
	public void resetFields(){
		username.setText("");
		password.setText("");
		checkPassword.setText("");
		adminCode.setText("");
	}
	
	/**
	 * 
	 * Lytter til om man skal lage en adminbruker eller ikke
	 */
	private class AdminListener implements ActionListener{
		
		private JLabel adminKode;
		
		private AdminListener(){
			adminKode = new JLabel("Adminkode: ");
		}
		
		/**
		 * Når adminCheckBox blir trykket på, blir denne funksjonen kalt.
		 * Den bestemmer så om det er en admin som skal lage en bruker eller ikke.
		 */
		public void actionPerformed(ActionEvent arg0){
			if (adminCheckBox.isSelected()){
				c.gridx = 2;
				c.gridy = 0;
				panel.add(adminKode, c);
				c.gridy = 1;
				panel.add(adminCode, c);
			}else{
				panel.remove(adminCode);
				panel.remove(adminKode);
				adminCode.setText("");
			}
			panel.revalidate();
			panel.repaint();
		}
	}
	
	/**
	 * 
	 * En lytter til knappene på siden.
	 */
	private class ButtonListener implements ActionListener{
		
		/**
		 * @param arg0 en actionevent som knappene implementerer
		 */
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == ok){
				if (!validPassword() || !validEmail())
					return;
				if (adminCheckBox.isSelected() && !validAdminCode()){
					JOptionPane.showMessageDialog(null, "Adminpassordet er feil");
					return;
				}
				createUserInDatabase();
				resetFields();
			}else{
				resetFields();
			}
		}
	}
}

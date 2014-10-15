package src;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminLogin extends JPanel{
	
	private JTextField userName;
	private JPasswordField password;
	private JButton ok, reset;
	private AdminLoginListener listener;
	
	public AdminLogin(AdminLoginListener listener){
		setLayout(new GridLayout(3, 2, 0, 0));
		ButtonListener buttonListener = new ButtonListener();
		if (listener == null)
			throw new IllegalArgumentException("Listener cannot be null in AdminLogin");
		this.listener = listener;
		add(new JLabel("Login: "));
		
		KeyboardListener boardListener = new KeyboardListener();
		userName = new JTextField();
		userName.addKeyListener(boardListener);
		add(userName);
		userName.setColumns(10);
		
		add(new JLabel("Passord: "));
		
		password = new JPasswordField();
		
		password.addKeyListener(boardListener);
		add(password);
		
		ok = new JButton("Ok");
		ok.addActionListener(buttonListener);
		add(ok);
		
		reset = new JButton("Avbryt");
		reset.addActionListener(buttonListener);
		add(reset);
	}
	
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ok){
				listener.login(userName.getText(), password.getPassword());
			}else if (e.getSource() == reset){
				userName.setText("");
				password.setText("");
			}
		}
	}
	
	public void setListener(AdminLoginListener l){
		listener = l;
	}
	
	private class KeyboardListener implements KeyListener{

		public void keyPressed(KeyEvent arg0) {
			
		}

		public void keyReleased(KeyEvent arg0) {
			
		}

		public void keyTyped(KeyEvent arg0) {
			if (arg0.getKeyChar() == '\n' && listener != null){
				listener.login(userName.getText(), password.getPassword());
				listener = null;
			}
		}
	}
}

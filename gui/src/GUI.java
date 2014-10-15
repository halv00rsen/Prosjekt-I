package src;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	
	public GUI(){
		frame = new JFrame("NTNUI-Koiene");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.getContentPane().setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblLogin = new JLabel("Login: ");
		frame.getContentPane().add(lblLogin);
		
		textField = new JTextField();
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassord = new JLabel("Passord: ");
		frame.getContentPane().add(lblPassord);
		
		passwordField = new JPasswordField();
		frame.getContentPane().add(passwordField);
		
		JButton btnOk = new JButton("Ok");
		frame.getContentPane().add(btnOk);
		
		JButton btnAvbryt = new JButton("Avbryt");
		frame.getContentPane().add(btnAvbryt);
		frame.pack();
		frame.setVisible(true);
		
	}
}

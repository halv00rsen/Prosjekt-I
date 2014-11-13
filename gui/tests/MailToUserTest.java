package tests;

import javax.swing.JFrame;

import src.MailToUserAdmin;

public class MailToUserTest {

	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.add(new MailToUserAdmin());
	}
}

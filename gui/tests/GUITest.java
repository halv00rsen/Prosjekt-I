package tests;

import javax.swing.JFrame;

import src.AdminLogin;
import src.AdminLoginListener;
import src.GUI;

public class GUITest implements AdminLoginListener{
	
	public static void main(String[] args){
		GUITest test = new GUITest();
		test.init();
	}
	
	private void init(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new AdminLogin(this));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void login(String userName, char[] password) {
		String pass = "";
		for (char a : password)
			pass += a;
		System.out.println("Username: " + userName + ", passord: " + pass);
	}
}

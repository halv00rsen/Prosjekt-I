package tests;

import javax.swing.JFrame;

import src.AdminLogin;
import src.AdminLoginListener;
import src.GUI;

public class GUITest implements AdminLoginListener{
	
	/**
	 * 
	 * @param args Standard args
	 */
	public static void main(String[] args){
		GUITest test = new GUITest();
		test.init();
	}
	
	private void init(){
		GUI gui = new GUI();
	}

	public void login(String userName, char[] password) {
		String pass = "";
		for (char a : password)
			pass += a;
		System.out.println("Username: " + userName + ", passord: " + pass);
	}
}

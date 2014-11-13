package tests;

import javax.swing.JFrame;

import src.AddItemAdmin;

public class AddItemAdminTest {
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.add(new AddItemAdmin());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

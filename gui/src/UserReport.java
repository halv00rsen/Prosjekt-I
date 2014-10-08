package src;

import javax.swing.JFrame;

public class UserReport {
	
	private JFrame frame;
	
	public UserReport(){
		frame = new JFrame("Rapport");
		frame.setSize(400, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private String[] getNameCabins(){
		return null;
	}
}

package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GetMail extends JPanel{
	
	private final JButton updateMail;
	
	public GetMail(){
		updateMail = new JButton("Opdater");
		updateMail.addActionListener(new GetMailListener());
		add(updateMail);
	}
	
	private class GetMailListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			System.out.println(Mail.getMail());
		}
		
	}
}

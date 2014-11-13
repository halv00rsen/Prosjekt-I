package src;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MessageAdmin extends JPanel implements ChangeTabListener{

	private final JTextArea messageArea;
	
	public MessageAdmin(){
		setLayout(new GridLayout(1,1));
		messageArea = new JTextArea();
		messageArea.setBorder(BorderFactory.createEtchedBorder());
		messageArea.setText(getMessagesAdmin());
		messageArea.setEditable(false);
		add(messageArea);
	}
	
//	Denne metoden skal prate med databasen
	private String getMessagesAdmin(){
		return "Koie 4 har rapportert om ødelagt bord";
	}

@Override
public void initPanel() {
	// TODO Auto-generated method stub
	
}
}

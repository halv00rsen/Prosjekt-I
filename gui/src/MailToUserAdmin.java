package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MailToUserAdmin extends JPanel implements ChangeTabListener{

	private final JComboBox<Bruker> users;
	private final JTextArea emailInfo;
	private final JTextField subjectField;
	private final JButton sendButton, cancelButton;
	
	public MailToUserAdmin(){
		setLayout(new GridLayout(3,1));
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Velg bruker: "), c);
		users = new JComboBox<Bruker>();
		for (Bruker user: getAllUsers()){
			users.addItem(user);
		}
		c.gridx = 1;
		panel.add(users, c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("Emnefelt: "), c);
		subjectField = new JTextField(15);
		c.gridx = 1;
		panel.add(subjectField, c);
		add(panel);
		emailInfo = new JTextArea();
		emailInfo.setWrapStyleWord(true);
		emailInfo.setLineWrap(true);
		JScrollPane pane = new JScrollPane(emailInfo);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBorder(BorderFactory.createTitledBorder("Emailinfo"));
		add(pane);
		ButtonListener l = new ButtonListener();
		sendButton = new JButton("Send");
		sendButton.addActionListener(l);
		cancelButton = new JButton("Avbryt");
		cancelButton.addActionListener(l);
		JPanel panel2 = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		panel2.add(sendButton, c);
		c.gridx = 1;
		panel2.add(cancelButton, c);
		add(panel2);
	}
	
	private List<Bruker> getAllUsers(){
		List<Bruker> users = Database.getAllBrukers();
//		for (Bruker user : users){
//			if (user.isAdmin())
//				users.remove(user);
//		}
		return users;
	}
	
	private void resetFields(){
		emailInfo.setText("");
		subjectField.setText("");
	}
	
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			//send mail til mailklassen
			if (Mail.sendMail((String) ((Bruker)users.getSelectedItem()).getId(), subjectField.getText(), emailInfo.getText()))
				JOptionPane.showMessageDialog(null, "Mailen ble sendt");
			else{
				JOptionPane.showMessageDialog(null, "Mailen ble ikke sendt");
				return;
			}
			resetFields();
		}
	}

	@Override
	public void initPanel() {
		// TODO Auto-generated method stub
		
	}
}

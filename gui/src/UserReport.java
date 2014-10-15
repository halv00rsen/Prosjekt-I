package src;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JList;

public class UserReport {
	
	private JFrame frame;
	
	public UserReport(){
		frame = new JFrame("Rapport");
		frame.setSize(400, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel cancelOkButtons = new JPanel();
		frame.getContentPane().add(cancelOkButtons, BorderLayout.SOUTH);
		cancelOkButtons.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnOk = new JButton("Ok");
		cancelOkButtons.add(btnOk);
		
		JButton btnAvbryt = new JButton("Avbryt");
		cancelOkButtons.add(btnAvbryt);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(new SpringLayout());
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_1.add(lblNewLabel_1);
		frame.setVisible(true);
	}
	
	private String[] getNameCabins(){
		return null;
	}
}

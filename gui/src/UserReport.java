package src;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTextArea;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class UserReport {
	
	private JFrame frame;
	private JTextArea textArea;
	private ButtonListener buttonListener;
	private JButton okButton, cancelButton;
	private DestroyedItems destroyedItems;
	private final String cabin;
	
	private UserReportListener listener;
	
	public UserReport(String cabin, int dayFrom, int monthFrom, int dayTo, int monthTo){
		frame = new JFrame("Rapport");
		frame.setSize(400, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		buttonListener = new ButtonListener();
		this.cabin = cabin;
		
		JPanel cancelOkButtons = new JPanel();
		frame.getContentPane().add(cancelOkButtons, BorderLayout.SOUTH);
		cancelOkButtons.setLayout(new GridLayout(1, 0, 0, 0));
		
		okButton = new JButton("Ok");
		cancelOkButtons.add(okButton);
		okButton.addActionListener(buttonListener);
		
		cancelButton = new JButton("Avbryt");
		cancelOkButtons.add(cancelButton);
		cancelButton.addActionListener(buttonListener);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Koieinfo", null, panel_1, null);
		panel_1.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panel_1.add(new JLabel("Rapport for " + cabin + ". "), c);
		c.gridx = 0;
		c.gridy = 1;
		panel_1.add(new JLabel("Leid fra " + dayFrom + "." + monthFrom + " til " + dayTo + ". " + monthTo));
		c.gridy = 2;
		panel_1.add(new JLabel("Vedstatus: "), c);
		
		JLabel lbldelagtUtstyr = new JLabel("\u00D8delagt utstyr");
		panel_1.add(lbldelagtUtstyr);
		
		destroyedItems = new DestroyedItems();
		tabbedPane.addTab("Ødelagt utsyr", null, destroyedItems, null);
		
		
		JPanel panel = new JPanel();
		
		JLabel lblSkrivAnnenInformasjon = new JLabel("Skriv annen informasjon her:");
		panel.add(lblSkrivAnnenInformasjon);
		textArea = new JTextArea(16, 30);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane);
		tabbedPane.addTab("Annen Info", null, panel, null);
		frame.setLocation(GUI.getXPosition(), GUI.getYPosition());
		frame.setVisible(true);
	}
	
	public void setListener(UserReportListener listener){
		this.listener = listener;
	}
	
	private void updateEquipmentInCabin(){
		if (cabin.equals("Koie 1")){
			String[] a = new String[] {"Gitar", "Stol", "Bord"};
			destroyedItems.setInventory(a);
		}
	}
	
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == cancelButton){
				listener.cancelPressed();
			}
			else if (arg0.getSource() == okButton){
				listener.okPressed(textArea.getText(), destroyedItems.getDestroyedElements());
			}
			frame.removeAll();
			frame.setVisible(false);
			frame = null;
		}
	}
}

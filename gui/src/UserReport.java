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
	private ChooseCabin cabins;
	
	public UserReport(){
		frame = new JFrame("Rapport");
		frame.setSize(400, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		buttonListener = new ButtonListener();
		
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
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel_1.add(new JLabel("Velg Koie: "), c);
		cabins = new ChooseCabin();
		c.gridx = 1;
		panel_1.add(cabins.getComboBox(), c);
		cabins.addActionListener(buttonListener);
		c.gridx = 0;
		c.gridy = 1;
		panel_1.add(new JLabel("Vedstatus: "), c);
		
		JLabel lbldelagtUtstyr = new JLabel("\u00D8delagt utstyr");
		panel_1.add(lbldelagtUtstyr);
		
		destroyedItems = new DestroyedItems();
		tabbedPane.addTab("Ødelagt utsyr", null, destroyedItems, null);
		updateEquipmentInCabin();
		
		
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
	
	private void updateEquipmentInCabin(){
		String cabin = cabins.getSelectedItem();
		if (cabin.equals("Koie 1")){
			String[] a = new String[] {"Gitar", "Stol", "Bord"};
			destroyedItems.setInventory(a);
//			for (String b : a)
//				equipmentCabin.addItem(b);
		}else{
			String[] a = new String[] {"Ski", "Sofa", "TV"};
			destroyedItems.setInventory(a);
//			for (String b : a)
//				equipmentCabin.addItem(b);
		}
	}
	
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == cancelButton){
				frame.removeAll();
				frame.setVisible(false);
				frame = null;
			}
			else if (arg0.getSource() == okButton){
				System.out.println(cabins.getSelectedItem());
				for (String a : destroyedItems.getDestroyedElements())
					System.out.println(a);
				System.out.println(textArea.getText());
			}else if (arg0.getSource() == cabins.getComboBox()){
				updateEquipmentInCabin();
			}
		}
		
	}
}

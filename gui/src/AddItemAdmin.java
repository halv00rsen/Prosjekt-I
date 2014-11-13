package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import src.Item.Status;

public class AddItemAdmin extends JPanel{
	
	private final ChooseCabin cabins;
	private final JComboBox<Item> allItems;
	private final JComboBox<Status> statusBox;
	private final JButton addItem, removeItem;
	private final JTextField itemNameInput;
	private final JTextArea itemInfo;
	
	public AddItemAdmin(){
		cabins = new ChooseCabin();
		CabinListener listener = new CabinListener();
		cabins.addActionListener(listener);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 2;
		c.ipady = 2;
		c.gridx = 0;
		c.gridy = 0;
		add(new JLabel("Koie: "), c);
		c.gridx = 1;
		add(cabins.getComboBox(), c);
		itemNameInput = new JTextField(10);
		c.gridy = 1;
		c.gridx = 0;
		add(new JLabel("Nytt utstyr: "),c);
		c.gridx = 2;
		addItem = new JButton("Legg til");
		addItem.addActionListener(listener);
		add(addItem, c);
		c.gridx = 1;
		add(itemNameInput, c);
		allItems = new JComboBox<Item>();
		c.gridy = 3;
		c.gridx = 1;
		add(allItems, c);
		c.gridx = 0;
		add(new JLabel("Utstyr: "), c);
		statusBox = new JComboBox<Status>();
		for (Status status : Status.values())
			statusBox.addItem(status);
		c.gridy = 4;
		c.gridx = 1;
		add(statusBox, c);
		c.gridx = 0;
		add(new JLabel("Status valgt utstyr: "), c);
		c.gridy = 5;
		add(new JLabel("Fjern valgt utstyr: "), c);
		c.gridx = 1;
		removeItem = new JButton("Fjern");
		removeItem.addActionListener(listener);
		add(removeItem, c);
		itemInfo = new JTextArea();
		itemInfo.setEditable(false);
		itemInfo.setBorder(BorderFactory.createEtchedBorder());
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 2;
		c.gridy = 2;
		allItems.addActionListener(listener);
		statusBox.addActionListener(listener);
		add(itemInfo, c);
		updateCabin();
	}
	
	private void updateCabin(){
		Koie cabin = Database.getKoie(cabins.getSelectedItem());
		allItems.removeAllItems();
		for (Item item: cabin.getInventory().getAllItems()){
			allItems.addItem(item);
		}
		updateInformation();
	}
	
	private void updateInformation(){
		Item item = (Item) allItems.getSelectedItem();
		itemInfo.setText("Ting: " + item.getName() + "\nStatus: " + item.getStatus());
	}
	
	private class CabinListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == addItem){
				
			}
			else if (arg0.getSource() == removeItem){
				
			}
			else if (arg0.getSource() == allItems || arg0.getSource() == statusBox){
				updateInformation();
			}
			else{
				updateCabin();
			}
		}
	}
}

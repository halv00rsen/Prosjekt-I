package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import src.Item.Status;

public class AddItemAdmin extends JPanel{
	
	private final ChooseCabin cabins;
	private final JComboBox<Item> allItems;
	private final JComboBox<Status> statusBox;
	private final JButton addItem, removeItem, saveInfo, resetInfo;
	private final JTextField itemNameInput;
	private final JTextArea itemInfo;
	private Koie cabinInUse;
	
	public AddItemAdmin(){
		cabins = new ChooseCabin();
		CabinListener listener = new CabinListener();
		cabins.addActionListener(listener);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		itemNameInput = new JTextField(5);
		addItem = new JButton("Legg til");
		addItem.addActionListener(listener);
		allItems = new JComboBox<Item>();
		statusBox = new JComboBox<Status>();
		for (Status status : Status.values())
			statusBox.addItem(status);
		removeItem = new JButton("Fjern");
		removeItem.addActionListener(listener);
		itemInfo = new JTextArea(2, 17);
		itemInfo.setEditable(false);
		itemInfo.setBorder(BorderFactory.createEtchedBorder());
		allItems.addActionListener(listener);
		statusBox.addActionListener(listener);
		updateCabin();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(new JLabel("Koie: "), c);
		c.gridwidth = 3;
		c.gridx = 1;
		add(cabins.getComboBox(), c);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		add(new JLabel("Nytt utstyr: "), c);
		c.gridx = 1;
		add(itemNameInput, c);
		c.gridwidth = 2;
		c.gridx = 2;
		add(addItem, c);
		c.gridwidth = 1;
		c.gridy = 2;
		c.gridx = 0;
		add(new JLabel("Utstyr: "), c);
		c.gridx = 1;
		add(allItems, c);
		c.gridx = 2;
		c.gridheight = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		add(itemInfo, c);
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(new JLabel("Sett status: "), c);
		c.gridx = 1;
		add(statusBox, c);
		c.gridx = 0;
		c.gridy = 4;
		add(new JLabel("Fjern ting: "), c);
		c.gridx = 1;
		add(removeItem, c);
		saveInfo = new JButton("Lagre");
		saveInfo.addActionListener(listener);
		c.gridx = 2;
		add(saveInfo, c);
		resetInfo = new JButton("Reset");
		resetInfo.addActionListener(listener);
		c.gridx = 3;
		add(resetInfo, c);
	}
	
	private void updateCabin(){
		cabinInUse = Database.getKoie(cabins.getSelectedItem());
		allItems.removeAllItems();
		for (Item item: cabinInUse.getInventory().getAllItems()){
			allItems.addItem(item);
		}
		updateInformation();
	}
	
	private void updateInformation(){
		Item item = (Item) allItems.getSelectedItem();
		if (item == null)
			itemInfo.setText("");
		else{
			item.setStatus((Status) statusBox.getSelectedItem());
			itemInfo.setText("Ting: " + item.getName() + "\nStatus: " + item.getStatus());
		}
	}
	
	private void sendToDatabase(){
		Database.toDatabase(cabinInUse);
		JOptionPane.showMessageDialog(this, "Informasjonen ble lagret.");
	}
	
	private class CabinListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == addItem){
				Item item = new Item(itemNameInput.getText());
				cabinInUse.getInventory().addItem(item);
				allItems.addItem(item);
				itemNameInput.setText("");
				updateInformation();
			}
			else if (arg0.getSource() == removeItem){
				Item item = (Item) allItems.getSelectedItem();
				cabinInUse.getInventory().removeItem(item);
				allItems.removeItem(item);
				updateInformation();
			}
			else if (arg0.getSource() == allItems || arg0.getSource() == statusBox){
				if (arg0.getSource() == statusBox){
					((Item) allItems.getSelectedItem()).setStatus((Status) statusBox.getSelectedItem());
				}
				updateInformation();
			}
			else if (arg0.getSource() == saveInfo){
				sendToDatabase();
			}
			else{
				updateCabin();
			}
		}
	}
}

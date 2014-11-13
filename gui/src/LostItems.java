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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import src.Item.Status;

public class LostItems extends JPanel{
	
	private final JButton addButton, removeButton;
	private final JTextField lostItemField;
	private final SortedListModel lostItemsModel;
	private final JList<Item> lostItemsList;
	
	public LostItems(){
		setLayout(new GridLayout(1,2));
		lostItemField = new JTextField(10);
		addButton = new JButton("Legg til");
		removeButton = new JButton("Fjern");
		LostItemButtonListener listener = new LostItemButtonListener();
		addButton.addActionListener(listener);
		removeButton.addActionListener(listener);
		lostItemsModel = new SortedListModel();
		lostItemsList = new JList<Item>(lostItemsModel);
		JScrollPane pane = new JScrollPane(lostItemsList);
		pane.setBorder(BorderFactory.createTitledBorder("Gjenglemt utstyr"));
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Skriv inn glemt utstyr: "), c);
		c.gridy = 1;
		panel.add(lostItemField, c);
		c.gridy = 2;
		panel.add(addButton, c);
		c.gridy = 3;
		panel.add(new JLabel("  "), c);
		c.gridy = 4;
		panel.add(removeButton, c);
		add(panel);
		add(pane);
		
	}
	
	public List<Item> getLostItems(){
		List<Item> lostItems = new ArrayList<Item>();
		for (int a = 0; a < lostItemsModel.getSize(); a++){
			lostItems.add((Item) lostItemsModel.getElementAt(a));
		}
		return lostItems;
	}

	private class LostItemButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0){
			if (arg0.getSource() == addButton){
				String itemName = lostItemField.getText();
				if (itemName.length() == 0)
					return;
				Item item = new Item(itemName, Status.LOST_AND_FOUND);
				lostItemsModel.add(item);
				lostItemField.setText("");
			}else{
				List<Item> selected = lostItemsList.getSelectedValuesList();
				for (Item item : selected){
					lostItemsModel.removeElement(item);
				}
				lostItemsList.getSelectionModel().clearSelection();
			}
		}
	}
}

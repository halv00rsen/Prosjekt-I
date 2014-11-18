package src;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

/**
 * 
 * En instans av JPanel som holder styr på objekter som kan ha blitt ødelagt i ei koie ved besøk.
 * Brukes av UserReport
 */
public class DestroyedItems extends JPanel{
	
	  private JList<Item> inventory, brokenInventory;
	  private JButton addButton, removeButton;
	  private SortedListModel inventoryListModel, brokenInventoryListModel;

	  /**
	   * Oppretter ødelagt utstyr-panelet i rapporten.
	   */
	  public DestroyedItems() {
		  setBorder(BorderFactory.createEtchedBorder());
		  setLayout(new GridLayout(1,3));
		  inventoryListModel = new SortedListModel();
		  inventory = new JList<Item>(inventoryListModel);
		  JScrollPane pane1 = new JScrollPane(inventory);
		  pane1.setBorder(BorderFactory.createTitledBorder("Utstyr i orden"));
		  add(pane1);
		  JPanel panel = new JPanel();
		  GridBagConstraints c = new GridBagConstraints();
		  panel.setLayout(new GridBagLayout());
		  c.gridx = 0;
		  c.gridy = 0;
		  c.fill = GridBagConstraints.HORIZONTAL;
		  addButton = new JButton("Legg til >>");
		  panel.add(addButton, c);
		  addButton.addActionListener(new AddListener());
		  removeButton = new JButton("<< Fjern");
		  c.gridy = 1;
		  panel.add(new JLabel(" "), c);
		  c.gridy = 2;
		  panel.add(removeButton, c);
		  removeButton.addActionListener(new RemoveListener());
		  add(panel);
		  brokenInventoryListModel = new SortedListModel();
		  brokenInventory = new JList<Item>(brokenInventoryListModel);
		  JScrollPane pane2 = new JScrollPane(brokenInventory);
		  pane2.setBorder(BorderFactory.createTitledBorder("Ødelagt utstyr"));
		  add(pane2);
	  }
	  
	  /**
	   * @return items en liste med alt Ã¸delagt utstyr pÃ¥ koia
	   */
	  public List<Item> getDestroyedElements(){
		  List<Item> items = new ArrayList<Item> ();
		  for (int a = 0; a < brokenInventoryListModel.getSize(); a++)
			  items.add((Item) brokenInventoryListModel.getElementAt(a));
		  return items;
	  }
	  
	  
	  /**
	   * Setter alt ødelagt utstyr i koia
	   * @param broken - en liste med alt ødelagt utstyr på koia
	   */
	  public void setBrokenInventory(List<Item> broken){
		  brokenInventoryListModel.clear();
		  for (Item item: broken)
			  brokenInventoryListModel.add(item);
	  }
	  
	  /**
	   * Sletter alt utstyr som ligger i lista
	   * @param inventory en liste med alt utstyr som er i orden i koia
	   */
	  public void setInventory(List<Item> inventory){
		  inventoryListModel.clear();
//		  brokenInventoryListModel.clear();
		  for (Item i : inventory)
			  inventoryListModel.add(i);
	  }
	  /**
	   * 
	   * @param inventory legges til lista til utstyr
	   */
	  private void addInventory(List<Item> inventory){
		  for (Item i : inventory)
			  inventoryListModel.add(i);
	  }
	  
	  /**
	   * 
	   * @param inventory legges til i Ã¸delagt utstyr lista
	   */
	  private void addBrokenInventory(List<Item> inventory){
		  for (Item i : inventory)
			  brokenInventoryListModel.add(i);
	  }
	  
	  /**
	   * sletter alle elementer som er valgt i utstyrlista
	   */
	  private void clearInventorySelected() {
	    List<Item> selected = inventory.getSelectedValuesList();
	    for (Item value : selected)
	    	inventoryListModel.removeElement(value);
	    inventory.getSelectionModel().clearSelection();
	  }

	  /**
	   * Sletter alle elementer som er valgt i den Ã¸delagte utstyrslista
	   */
	  private void clearBrokenInventorySelected() {
		  List<Item> seleceted = brokenInventory.getSelectedValuesList();
		  for (Item value : seleceted)
			  brokenInventoryListModel.removeElement(value);
		  brokenInventory.getSelectionModel().clearSelection();
	  }

	  /**
	   * 
	   * En listener som lytter til legg-til-knappen, og flytter utstyr til Ã¸delagt utstyr
	   */
	  private class AddListener implements ActionListener {
		  public void actionPerformed(ActionEvent e) {
			  List<Item> selected = inventory.getSelectedValuesList();
			  addBrokenInventory(selected);
			  clearInventorySelected();
		  }
	  }

	  /**
	   * 
	   * En lytter som lytter til fjernknappen, flytter fra Ã¸delagt til ikke-Ã¸delagt lista
	   */
	  private class RemoveListener implements ActionListener {
		  public void actionPerformed(ActionEvent e){
			  List<Item> selected = brokenInventory.getSelectedValuesList();
			  addInventory(selected);
			  clearBrokenInventorySelected();
		  }
	  }
}

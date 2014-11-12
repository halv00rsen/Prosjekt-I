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
		  pane1.setBorder(BorderFactory.createTitledBorder("Utstyr"));
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
		  pane2.setBorder(BorderFactory.createTitledBorder("�delagt utstyr"));
		  add(pane2);
	  }
	  
	  /**
	   * @return items en liste med alt ødelagt utstyr på koia
	   */
	  public List<Item> getDestroyedElements(){
		  List<Item> items = new ArrayList<Item> ();
		  for (int a = 0; a < brokenInventoryListModel.getSize(); a++)
			  items.add((Item) brokenInventoryListModel.getElementAt(a));
		  return items;
	  }
	  
	  /**
	   * Sletter alt utstyr som ligger i lista
	   * @param inventory en liste med alt utstyr i koia
	   */
	  public void setInventory(List<Item> inventory){
		  inventoryListModel.clear();
		  brokenInventoryListModel.clear();
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
	   * @param inventory legges til i ødelagt utstyr lista
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
	   * Sletter alle elementer som er valgt i den ødelagte utstyrslista
	   */
	  private void clearBrokenInventorySelected() {
		  List<Item> seleceted = brokenInventory.getSelectedValuesList();
		  for (Item value : seleceted)
			  brokenInventoryListModel.removeElement(value);
		  brokenInventory.getSelectionModel().clearSelection();
	  }

	  /**
	   * 
	   * En listener som lytter til legg-til-knappen, og flytter utstyr til ødelagt utstyr
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
	   * En lytter som lytter til fjernknappen, flytter fra ødelagt til ikke-ødelagt lista
	   */
	  private class RemoveListener implements ActionListener {
		  public void actionPerformed(ActionEvent e){
			  List<Item> selected = brokenInventory.getSelectedValuesList();
			  addInventory(selected);
			  clearBrokenInventorySelected();
		  }
	  }

	/**
	 * 
	 * Klassen håndterer elementene i listene
	 */
	private class SortedListModel extends AbstractListModel<Item> {
	
		  private SortedSet<Item> model;
	
		  public SortedListModel() {
			  model = new TreeSet<Item>();
		  }
	
		  public int getSize() {
			  return model.size();
		  }
	
		  public Item getElementAt(int index) {
			  return (Item) model.toArray()[index];
		  }
	
		  public void add(Item element) {
			  if (model.add(element)) {
				  fireContentsChanged(this, 0, getSize());
			  }
		  }
	
		  public void clear() {
			  model.clear();
			  fireContentsChanged(this, 0, getSize());
		  }
	
		  public boolean contains(Item element) {
			  return model.contains(element);
		  }
	
		  public Item firstElement() {
			  return model.first();
		  }
	
		  public Item lastElement() {
			  return model.last();
		  }
	
		  public boolean removeElement(Item element) {
			  boolean removed = model.remove(element);
			  if (removed) {
				  fireContentsChanged(this, 0, getSize());
			  }
			  return removed;
		  }
	}
}

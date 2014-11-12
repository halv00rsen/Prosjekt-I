package src;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	
	private static final String addButtonString = "Legg til >>", removeButtonString = "<< Fjern", 
			inventoryString = "Utstyr", brokenInventoryString = "�delagt utstyr";

	  private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
	  private JList<Item> inventory, brokenInventory;
	  private JButton addButton, removeButton;
	  private SortedListModel inventoryListModel, brokenInventoryListModel;

	  /**
	   * Oppretter ødelagt utstyr-panelet i rapporten.
	   */
	  public DestroyedItems() {
		  setBorder(BorderFactory.createEtchedBorder());
		  setLayout(new GridBagLayout());
		  inventoryListModel = new SortedListModel();
		  inventory = new JList<Item>(inventoryListModel);
		  add(new JLabel(inventoryString), new GridBagConstraints(0, 0, 1, 1, 0, 0,
		      GridBagConstraints.CENTER, GridBagConstraints.NONE, EMPTY_INSETS, 0, 0));
		  add(new JScrollPane(inventory), new GridBagConstraints(0, 1, 1, 5, .5,
		      1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, EMPTY_INSETS, 0, 0));
		  addButton = new JButton(addButtonString);
		  add(addButton, new GridBagConstraints(1, 2, 1, 2, 0, .25,
		      GridBagConstraints.CENTER, GridBagConstraints.NONE, EMPTY_INSETS, 0, 0));
		  addButton.addActionListener(new AddListener());
		  removeButton = new JButton(removeButtonString);
		  add(removeButton, new GridBagConstraints(1, 4, 1, 2, 0, .25,
		      GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 5, 0, 5), 0, 0));
		  removeButton.addActionListener(new RemoveListener());
		  brokenInventoryListModel = new SortedListModel();
		  brokenInventory = new JList<Item>(brokenInventoryListModel);
		  add(new JLabel(brokenInventoryString), new GridBagConstraints(2, 0, 1, 1, 0, 0,
		      GridBagConstraints.CENTER, GridBagConstraints.NONE, EMPTY_INSETS, 0, 0));
		  add(new JScrollPane(brokenInventory), new GridBagConstraints(2, 1, 1, 5, .5,
		      1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, EMPTY_INSETS, 0, 0));
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
	    public void actionPerformed(ActionEvent e) {
	    	List<Item> selected = brokenInventory.getSelectedValuesList();
	    	addInventory(selected);
	    	clearBrokenInventorySelected();
	    }
	  }
	}

	/**
	 * 
	 * Klassen håndterer elementene i listene
	 */
	class SortedListModel extends AbstractListModel {

	  private SortedSet model;

	  public SortedListModel() {
	    model = new TreeSet();
	  }

	  public int getSize() {
	    return model.size();
	  }

	  public Object getElementAt(int index) {
	    return model.toArray()[index];
	  }

	  public void add(Object element) {
	    if (model.add(element)) {
	      fireContentsChanged(this, 0, getSize());
	    }
	  }

	  public void addAll(Object elements[]) {
	    Collection c = Arrays.asList(elements);
	    model.addAll(c);
	    fireContentsChanged(this, 0, getSize());
	  }

	  public void clear() {
	    model.clear();
	    fireContentsChanged(this, 0, getSize());
	  }

	  public boolean contains(Object element) {
	    return model.contains(element);
	  }

	  public Object firstElement() {
	    return model.first();
	  }

	  public Iterator<String> iterator() {
	    return model.iterator();
	  }

	  public Object lastElement() {
	    return model.last();
	  }

	  public boolean removeElement(Object element) {
	    boolean removed = model.remove(element);
	    if (removed) {
	      fireContentsChanged(this, 0, getSize());
	    }
	    return removed;
	  }
}

package src;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;

/**
 * 
 * En listemodell som håndterer items
 */
public class SortedListModel extends AbstractListModel<Item> {
	
	  private SortedSet<Item> model;

	  /**
	   * Oppretter objektet
	   */
	  public SortedListModel() {
		  model = new TreeSet<Item>();
	  }

	  public int getSize() {
		  return model.size();
	  }

	  public Item getElementAt(int index) {
		  return (Item) model.toArray()[index];
	  }

	  /**
	   * 
	   * @param element - et item som blir lagt til i lista
	   */
	  public void add(Item element) {
		  if (model.add(element)) {
			  fireContentsChanged(this, 0, getSize());
		  }
	  }

	  /**
	   * Sletter alt i modellen
	   */
	  public void clear() {
		  model.clear();
		  fireContentsChanged(this, 0, getSize());
	  }

	  /**
	   * Fjerner et element og sier i fra om det ble fjernet
	   * @param element - Itemet som skal fjernes
	   * @return removed 
	   */
	  public boolean removeElement(Item element) {
		  boolean removed = model.remove(element);
		  if (removed) {
			  fireContentsChanged(this, 0, getSize());
		  }
		  return removed;
	  }
}

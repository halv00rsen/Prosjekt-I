package src;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;

public class SortedListModel extends AbstractListModel<Item> {
	
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

	  public boolean removeElement(Item element) {
		  boolean removed = model.remove(element);
		  if (removed) {
			  fireContentsChanged(this, 0, getSize());
		  }
		  return removed;
	  }
}

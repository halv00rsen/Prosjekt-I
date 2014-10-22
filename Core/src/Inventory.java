package src;

import java.util.List;
import java.util.ArrayList;

public class Inventory {
	private List<Item> items;
	
	public Inventory() {
		items = new ArrayList<Item>();
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		if (items.contains(item)) {
			items.remove(item);
		}
	}

}

package src;

import java.util.List;
import java.util.ArrayList;

public class Inventory {
	private List<Item> items;
	
	public Inventory() {
		items = new ArrayList<Item>();
	}
	
	public boolean hasItem(String name) {
		for (Item item : items) {
			if (item.getName().equals(name)) {
				return true;
			}
		}
		return false;
		
	}
	
	public Item getItemByName(String name) {
		for (Item item : items) {
			if (item.getName().equals(name)) {
				return item;
			}
		}
		return null;
	}
	
	public void addItem(String name) {
		items.add(new Item(name));
		
	}
	
	public void removeItem(Item item) {
		if (items.contains(item)) {
			items.remove(item);
		}
	}

}

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

	public void addItemByName(String name) {
		items.add(new Item(name));
	}
	
	public void removeItem(Item item) {
		if (items.contains(item)) {
			items.remove(item);
		}
	}

	public List<Item> getItems() {
		return items;
	}

	public Item getItemById(int id) {
		for (Item item : items) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}

	public Item getItemByName(String name) {
		for (Item item : items) {
			if (item.getName().equals(name)) {
				return item;
			}
		}
		return null;
	}

	public boolean hasItemByName(String name) {
		for (Item item : items) {
			if (item.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}

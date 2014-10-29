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

	public List<Item> getAllItems() {
		List<Item> allItems = new ArrayList<Item>();
		for (Item item : items) {
			if (item.getStatus() == Item.Status.BROKEN) {
				allItems.add(item);
			}
		}
		return allItems;
	}

	public List<Item> getInOrderItems() {
		List<Item> inOrderItems = new ArrayList<Item>();
		for (Item item : items) {
			if (item.getStatus() == Item.Status.BROKEN) {
				inOrderItems.add(item);
			}
		}
		return inOrderItems;
	}

	public List<Item> getBrokenItems() {
		List<Item> brokenItems = new ArrayList<Item>();
		for (Item item : items) {
			if (item.getStatus() == Item.Status.BROKEN) {
				brokenItems.add(item);
			}
		}
		return brokenItems;
	}

	public List<Item> getLostAndFoundItems() {
		List<Item> lostAndFoundItems = new ArrayList<Item>();
		for (Item item : items) {
			if (item.getStatus() == Item.Status.LOST_AND_FOUND) {
				lostAndFoundItems.add(item);
			}
		}
		return lostAndFoundItems;
	}

	public List<Item> getNewItems() {
		List<Item> newItems = new ArrayList<Item>();
		for (Item item : items) {
			if (item.getId() == Item.DEFAULT_ID) {
				newItems.add(item);
			}
		}
		return newItems;
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

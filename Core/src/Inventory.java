package src;

import java.util.List;
import java.util.ArrayList;

public class Inventory {
	private List<Item> items;
	
	/**
	 * Oppretter tomt Inventory-objekt
	 */
	public Inventory() {
		items = new ArrayList<Item>();
	}
	
	/**
	 * Legger til et Item-objekt
	 * @param item Item-objekt
	 */
	public void addItem(Item item) {
		items.add(item);
	}

	/**
	 * Fjerner et Item-objekt
	 * @param item Item-objekt
	 */
	public void removeItem(Item item) {
		if (items.contains(item)) {
			items.remove(item);
		}
	}

	/**
	 * Returnerer alle Item-objekter
	 * @return Liste med Item-objekter
	 */
	public List<Item> getAllItems() {
		List<Item> allItems = new ArrayList<Item>();
		for (Item item : items) {
			allItems.add(item);
		}
		return allItems;
	}

	/**
	 * Returnerer alle Item-objekter som er i orden
	 * @return Liste med Item-objekter
	 */
	public List<Item> getInOrderItems() {
		List<Item> inOrderItems = new ArrayList<Item>();
		for (Item item : items) {
			if (item.getStatus() == Item.Status.IN_ORDER) {
				inOrderItems.add(item);
			}
		}
		return inOrderItems;
	}

	/**
	 * Returnerer alle Item-objekter som er ødelagte
	 * @return Liste med Item-objekter
	 */
	public List<Item> getBrokenItems() {
		List<Item> brokenItems = new ArrayList<Item>();
		for (Item item : items) {
			if (item.getStatus() == Item.Status.BROKEN) {
				brokenItems.add(item);
			}
		}
		return brokenItems;
	}

	/**
	 * Returnerer alle Item-objekter som er gjenglemte
	 * @return Liste med Item-objekter
	 */
	public List<Item> getLostAndFoundItems() {
		List<Item> lostAndFoundItems = new ArrayList<Item>();
		for (Item item : items) {
			if (item.getStatus() == Item.Status.LOST_AND_FOUND) {
				lostAndFoundItems.add(item);
			}
		}
		return lostAndFoundItems;
	}

	/**
	 * Returnerer alle Item-objekter som nylig lagt til (dvs. ikke har fått en unik ID i databasen enda).
	 * @return Liste med Item-objekter
	 */
	public List<Item> getNewItems() {
		List<Item> newItems = new ArrayList<Item>();
		for (Item item : items) {
			if (item.getId() == Item.DEFAULT_ID) {
				newItems.add(item);
			}
		}
		return newItems;
	}

	/**
	 * Returnerer et Item-objekt med den gitte ID-en
	 * @param id
	 * @return Item-objekt
	 */
	public Item getItemById(int id) {
		for (Item item : items) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}
}

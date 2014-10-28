package tests;

import src.Inventory;
import src.Item;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class InventoryTest {
	@Test
	public void testInstantiation() {
		Inventory inventory = new Inventory();
		assertTrue(inventory instanceof Inventory);
	}
	
	@Test
	public void testAddItemAndHasItem() {
		Inventory inventory = new Inventory();
		inventory.addItemByName("Gitar");
		
		assertTrue(inventory.hasItemByName("Gitar"));
	}
	
	@Test
	public void testRemoveItem() {
		Inventory inventory = new Inventory();
		inventory.addItemByName("Gitar");

		assertTrue(inventory.hasItemByName("Gitar"));

		Item gitar = inventory.getItemByName("Gitar");
		inventory.removeItem(gitar);

		assertFalse(inventory.hasItemByName("Gitar"));
	}
}

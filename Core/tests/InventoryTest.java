package tests;

import src.Inventory;
import src.Item;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class InventoryTest {
	
	@Test
	public void testInstantiationAndSetBroken() {
		Inventory inventory = new Inventory();
		inventory.addItem("Gitar");
		inventory.addItem("Vaffeljern");
		
		assertTrue(inventory.hasItem("Gitar"));
		assertTrue(inventory.hasItem("Vaffeljern"));
		
		Item gitar = inventory.getItemByName("Gitar");
		inventory.removeItem(gitar);
		
		assertFalse(inventory.hasItem("Gitar"));
		
		Item vaffeljern = inventory.getItemByName("Vaffeljern");
		
		assertFalse(vaffeljern.isBroken());
		
		vaffeljern.setBroken();
		assertTrue(vaffeljern.isBroken());
	}

}

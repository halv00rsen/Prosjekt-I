package tests;

import src.Inventory;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class InventoryTest {
	@Test
	public void testInstantiation() {
		Inventory inventory = new Inventory();
		assertTrue(inventory instanceof Inventory);
	}
}

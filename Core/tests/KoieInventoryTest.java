package tests;

import src.Inventory;
import src.Item;

import org.junit.Test;

public class KoieInventoryTest {
	@Test
	public void instantiation() {
		Inventory inventory = new Inventory();
		Item gitar = new Item("Gitar");
		Item vaffeljern = new Item("Vaffeljern");
		inventory.addItem(gitar);
		inventory.addItem(vaffeljern);
	}
}

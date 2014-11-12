package tests;

import src.Database;
import src.Koie;
import src.Inventory;
import src.Item;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class DatabaseTest {
	@Test
	public void getKoieShouldReturnKoie() {
		Koie koie = Database.getKoie(3);
		assertTrue(koie instanceof Koie);
	}
	
	@Test
	public void inventoryShouldExist() {
		Koie koie = Database.getKoie(4);
		Inventory inventory = koie.getInventory();
		assertTrue(inventory instanceof Inventory);
	}
	
	@Test
	public void inventoryShouldHaveItems() {
		Koie koie = Database.getKoie(4);
		Inventory inventory = koie.getInventory();
		List<Item> allItems = inventory.getAllItems();
		assertTrue(allItems.size() > 0);
	}
	
	@Test
	public void itemShouldBeSetToBroken() {
		Koie koie = Database.getKoie(4);
		koie.getInventory().getItemById(10).setStatus(Item.Status.BROKEN);
		assertEquals(koie.getInventory().getItemById(10).getStatus(), Item.Status.BROKEN);

		Database.toDatabase(koie);
		Koie koieEtter = Database.getKoie(4);
		assertEquals(koieEtter.getInventory().getItemById(10).getStatus(), Item.Status.BROKEN);
	}
}

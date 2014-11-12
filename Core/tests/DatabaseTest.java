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
	/*
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
		assertTrue(inventory.getAllItems().size() > 0);
	}
	*/
	@Test
	public void itemShouldBeSetToBroken() {
		Koie koie = Database.getKoie(4);
		assertTrue(koie instanceof Koie);

		Inventory inventory = koie.getInventory();
		assertTrue(inventory.getAllItems().size() > 0);
		
		Item vaffeljern = inventory.getItemById(10);
		for (Item item : inventory.getAllItems()) {
			if (item.getName().equals("Vaffeljern")) {
				vaffeljern = item;
			}
		}

		assertEquals(vaffeljern.getStatus(), Item.Status.IN_ORDER);

		vaffeljern.setStatus(Item.Status.BROKEN);
		assertEquals(vaffeljern.getStatus(), Item.Status.BROKEN);

		Database.toDatabase(koie);
		
		Koie koieEtter = Database.getKoie(4);
		assertTrue(koieEtter instanceof Koie);

		Inventory invEtter = koieEtter.getInventory();
		assertTrue(invEtter.getAllItems().size() > 0);

		Item vaffeljernEtter = invEtter.getItemById(10);

		for (Item item : inventory.getAllItems()) {
			if (item.getName().equals("Vaffeljern")) {
				vaffeljernEtter = item;
			}
		}

		assertEquals(vaffeljernEtter.getStatus(), Item.Status.BROKEN);
	}
/*	
	@Test
	public void toDatabaseTest() {
		Koie koie = new Koie(0, "Testkoia", "Bortenfor dobbeltbladet", 2045);
		Database.toDatabase(koie);
	}
	*/
}

package tests;

import src.Database;
import src.Koie;
import src.Inventory;
import src.Item;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class DatabaseTest {
	@Test
	public void getKoieShouldReturnKoie() {
		Koie koie = Database.getKoie(3);
		assertTrue(koie instanceof Koie);
	}
	
	@Test
	public void inventoryTest() {
		Koie koie = Database.getKoie(4);
		Inventory inventory = koie.getInventory();
		assertTrue(inventory instanceof Inventory);
		
		assertTrue(inventory.getAllItems().size() > 0);
	}
}

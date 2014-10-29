package tests;

import src.Koie;
import src.Inventory;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class KoieInventoryTest {
	@Test
	public void instantiation() {
		Inventory inventory = new Inventory();
		inventory.addItemByName("Gitar");
		inventory.addItemByName("Vaffeljern");
		Koie koie = new Koie("1", "Koia",
							 "6", "10", "71N 10E", 
							 "1968", "terreng", 
							 false, false, false, false, 
							 "spes", inventory);
		assertTrue(koie instanceof Koie);

		System.out.println(koie.toString());
	}
}

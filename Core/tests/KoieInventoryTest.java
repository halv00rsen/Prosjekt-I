package tests;

import src.Koie;
import src.Inventory;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class KoieInventoryTest {
	
	@Test
	public void testInstantiation() {
		Inventory inventory = new Inventory();
		inventory.addItem("Gitar");
		inventory.addItem("Vaffeljern");
/*		
		Koie koie = new Koie(1, "Koia",
							 6, 10, "71N 10E", 
							 1968, "terreng", 
							 false, false, false, false, 
							 "spes", inventory);
		assertTrue(koie instanceof Koie);

		System.out.println(koie.toString());

*/
	}
}

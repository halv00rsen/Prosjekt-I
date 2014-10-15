package tests;

import src.Main;
import src.Koie;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InstantiateKoieTest {

	
	@Test
	public void TestInstantiation() {
		String koieinfo = "Koia, 6, 10, True, True, False, True, S/T, Badstu";
		String equipmentinfo = "Gitar, False, Vaffeljern, False";
		Koie koie = Main.instantiateKoie(koieinfo, equipmentinfo);
		
		assertTrue(koie instanceof Koie);
		assertTrue(koie.getName().equals("Koia"));
		assertEquals(koie.getNumBeds(), 6);
		assertEquals(koie.getNumSeats(), 10);
	}
}

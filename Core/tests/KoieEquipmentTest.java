package tests;

import src.Koie;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class KoieEquipmentTest {
	
	@Test
	public void testInstantiation() {
		Koie koie = new Koie("Koia", 6, 10, false, false, false, false, true, true, "terreng", "spes");
		
		assertTrue(koie instanceof Koie);
	}

}

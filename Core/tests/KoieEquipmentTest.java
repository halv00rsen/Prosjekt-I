package tests;

import src.Koie;
import src.Equipment;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class KoieEquipmentTest {
	
	@Test
	public void testInstantiation() {
		List<Equipment> equipment = new ArrayList<Equipment>();
		Equipment g = new Equipment("Gitar");
		Equipment v = new Equipment("Vaffeljern");
		equipment.add(g);
		equipment.add(v);
		
		Koie koie = new Koie("Koia", 6, 10, false, false, false, false, "terreng", "spes", equipment);
		
		assertTrue(koie instanceof Koie);
		
		List<Equipment> equip = koie.getEquipment();
		for (Equipment e : equip) {
			System.out.println(e.getName());
			System.out.println(e.isBroken());
			assertEquals(e.isBroken(), false);
		}

	}

}

package tests;

import src.Equipment;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EquipmentTest {
	
	@Test
	public void testInstantiationAndSetBroken() {
		
		Equipment e = new Equipment("Gitar");
		assertEquals(e.getName(), "Gitar");
		
		e.setName("El-gitar");
		assertNotEquals(e.getName(), "Gitar");
		assertEquals(e.getName(), "El-gitar");
		
		
		assertEquals(e.isBroken(), false);
		
		e.setBroken();
		assertEquals(e.isBroken(), true);
		
		e.setBroken(false);
		assertEquals(e.isBroken(), false);
		
	}

}

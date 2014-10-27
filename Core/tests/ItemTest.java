package tests;

import src.Item;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ItemTest {
	
	@Test
	public void testInstantiation() {
		Item item = new Item("Gitar");
		assertTrue(item instanceof Item);
	}
	
	@Test
	public void testGetNameAndSetName() {
		Item item = new Item("Gitar");
		assertEquals(item.getName(), "Gitar");
		
		item.setName("El-gitar");
		assertNotEquals(item.getName(), "Gitar");
		assertEquals(item.getName(), "El-gitar");
	}
	
	@Test
	public void testIsBrokenAndSetBroken() {
		Item item = new Item("Gitar");
		assertFalse(item.isBroken());
		
		item.setBroken();
		assertTrue(item.isBroken());
		
		item.setBroken(false);
		assertFalse(item.isBroken());
	}
}

package tests;

import src.Item;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ItemTest {
	@Test
	public void instantiation() {
		Item item = new Item("Gitar");
		assertTrue(item instanceof Item);
	}
	
	@Test
	public void getIdAndSetId() {
		Item item = new Item("Gitar");
		int defaultId = 0;
		assertEquals(item.getId(), defaultId);
		
		int newId = 42;
		item.setId(newId);
		assertNotEquals(item.getId(), defaultId);
		assertEquals(item.getId(), newId);
	}
	
	@Test
	public void getNameAndSetName() {
		String name = "Gitar";
		Item item = new Item(name);
		assertEquals(item.getName(), name);
		
		String newName = "El-gitar";
		item.setName(newName);
		assertNotEquals(item.getName(), name);
		assertEquals(item.getName(), newName);
	}
	
	@Test
	public void isBrokenAndSetBroken() {
		Item item = new Item("Gitar");
		assertFalse(item.isBroken());
		
		item.setBroken();
		assertTrue(item.isBroken());
		
		item.setBroken(false);
		assertFalse(item.isBroken());
	}
}

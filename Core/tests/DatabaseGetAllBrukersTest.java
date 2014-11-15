package tests;

import src.Database;
import src.Bruker;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class DatabaseGetAllBrukersTest {
	@Test
	public void getAllBrukersShouldReturnList() {
		List<Bruker> allBrukers = Database.getAllBrukers();
		assertTrue(allBrukers.size() > 0);
		
		for (Bruker bruker : allBrukers) {
			System.out.println(bruker.getId()+" "+bruker.getPasswordHash()+" "+bruker.isAdmin());
		}
	}
}

package tests;

import src.Bruker;
import org.junit.Test;

public class BrukerTest {
	@Test
	public void instanceTest() {
		Bruker bruker = new Bruker("admin1", Bruker.hashPassword("password"), true);
		System.out.println(bruker.getId());
		System.out.println(bruker.getPasswordHash());
		System.out.println(bruker.isAdmin());
		System.out.println("password");
		System.out.println(Bruker.hashPassword("password"));
		System.out.println(bruker.isPasswordCorrect("password"));
		System.out.println(bruker.isPasswordCorrect("passwrd"));
	}

}

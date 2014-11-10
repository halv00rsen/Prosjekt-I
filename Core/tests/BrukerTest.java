package tests;

import src.Bruker;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class BrukerTest {
	@Test
	public void instanceTest() {
		Bruker bruker = new Bruker("admin1", Bruker.hashPassword("password"), Bruker.Status.ADMIN);
		System.out.println(bruker.getId());
		System.out.println(bruker.getPasswordHash());
		System.out.println(bruker.getStatus());
		System.out.println("password");
		System.out.println(Bruker.hashPassword("password"));
		System.out.println(bruker.isPasswordCorrect("password"));
		System.out.println(bruker.isPasswordCorrect("passwrd"));
	}

}

package tests;

import pic.Pic;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class PicTest {
	@Test
	public void getKoiePositions() {
		HashMap<Integer, int[]> positions = Pic.getKoiePositions();
		assertTrue(positions.size() > 0);
		
		for (Integer i : positions.keySet()) {
			int[] pos = positions.get(i);
			System.out.println(i+" "+pos[0]+" "+pos[1]);
		}
	}
}

package pic;

import java.io.FileReader;
import java.util.Scanner;
import java.util.HashMap;

/** Leser koie-posisjoner fra fil og transformerer dem slik at de kan brukes i GUI-et */
public class Pic {
	private static final String cabinPositions = "gui/pic/cabinPositions.txt";
	private static final double imageXOffset = -91; // 242-333;
	private static final double imageYOffset = -5; // 292-297;

	private static final double guiXScale = 0.74576271186; // 396 / 531;
	private static final double guiYScale = 0.6894308943; // 424 / 615;

	/**
	 * Returnerer posisjonene koiene skal ha i GUI-et
	 * @return Posisjonene til koiene
	 */
	public static HashMap<Integer, int[]> getKoiePositions() {
		try {
			Scanner in = new Scanner(new FileReader(cabinPositions));
			in.nextLine();
			
			HashMap<Integer, int[]> positions = new HashMap<Integer, int[]>();
			while (in.hasNextLine()) {
				String[] fields = in.nextLine().split(", ");	
				int koieId = Integer.parseInt(fields[0]);
				double xPos = Double.parseDouble(fields[1]);
				double yPos = Double.parseDouble(fields[2]);
			
				int adjustedXPos = offsetAndScalePos(xPos, imageXOffset, guiXScale);
				int adjustedYPos = offsetAndScalePos(yPos, imageYOffset, guiYScale);
	
				int[] position = {adjustedXPos, adjustedYPos};
				positions.put(koieId, position);
			}
			in.close();	

			return positions;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Offsetter og skalerer en X- eller Y-posisjon
	 * @param pos X- eller Y-posisjon
	 * @param offset Offset
	 * @param scale Skalering
	 * @return
	 */
	private static int offsetAndScalePos(Double pos, double offset, double scale) {
		Double offsetPos = pos + offset;
		Double scaledPos = offsetPos * scale;
		return scaledPos.intValue();
	}
}

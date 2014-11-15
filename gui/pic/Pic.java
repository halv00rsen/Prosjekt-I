package pic;

import java.io.FileReader;
import java.util.Scanner;
import java.util.HashMap;

public class Pic {
	private static final String cabinPositions = "gui/pic/cabinPositions.txt";
	private static final double imageXOffset = 91; // 333-242;
	private static final double imageYOffset = 5; // 297-292;

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
				double xPos = Integer.parseInt(fields[1]);
				double yPos = Integer.parseInt(fields[2]);
			
				Double adjustedXPos = offsetAndScalePos(xPos, imageXOffset, guiXScale);
				Double adjustedYPos = offsetAndScalePos(yPos, imageYOffset, guiYScale);
	
				System.out.println(koieId+", "+adjustedXPos+", "+adjustedYPos);
				int[] position = {adjustedXPos.intValue(), adjustedYPos.intValue()};
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
	private static double offsetAndScalePos(double pos, double offset, double scale) {
		double offsetPos = pos + offset;
		double scaledPos = offsetPos * scale;
		return scaledPos;
	}

}

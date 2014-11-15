package pic;

import java.io.FileReader;
import java.util.Scanner;
import java.util.HashMap;

public class Pic {
	private static final String cabinPositions = "gui/pic/cabinPositions.txt";
	private static final int imageXOffset = 91; // 333-242;
	private static final int imageYOffset = 5; // 297-292;

	private static final double guiXScale = 396/531;
	private static final double guiYScale = 424/615;

	/**
	 * Returnerer posisjonene koiene skal ha i GUI-et
	 * @return Posisjonene til koiene
	 */
	public static HashMap<Integer, double[]> getKoiePositions() {
		try {
			Scanner in = new Scanner(new FileReader(cabinPositions));
			in.nextLine();
			
			HashMap<Integer, double[]> positions = new HashMap<Integer, double[]>();
			while (in.hasNextLine()) {
				String[] fields = in.nextLine().split(", ");	
				int koieId = Integer.parseInt(fields[0]);
				int xPos = Integer.parseInt(fields[1]);
				int yPos = Integer.parseInt(fields[2]);
			
				double adjustedXPos = offsetAndScalePos(xPos, imageXOffset, guiXScale);
				double adjustedYPos = offsetAndScalePos(yPos, imageYOffset, guiYScale);
	
				double[] position = {koieId, adjustedXPos, adjustedYPos};
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
	private static double offsetAndScalePos(int pos, int offset, double scale) {
		double offsetPos = pos + offset;
		double scaledPos = offsetPos * scale;
		return scaledPos;
	}

}

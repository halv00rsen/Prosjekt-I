package src;

public class Main {
	
	private textGUI textGui;
	
	private void init(){
		textGui = new textGUI();
		textGui.Alternativer("bruker");
	}
	
	private void run(){
		
	}
	
	public static void main(String[] args){
		Main program = new Main();
		program.init();
		program.run();
	}
	
	// Tar to strenger med komma-separerte verdier som input
	// Returnerer et Koie-objekt
	public static Koie instantiateKoie(String koieinfo, String koieequipment) {
		String[] infoparts = koieinfo.split(", ");
		
		String name = infoparts[0];
		int numBeds = Integer.parseInt(infoparts[1]);
		int numSeats = Integer.parseInt(infoparts[2]);
		boolean hasSykkel = Boolean.parseBoolean(infoparts[3]);
		boolean hasTopptur = Boolean.parseBoolean(infoparts[4]);
		boolean hasJakt = Boolean.parseBoolean(infoparts[5]);
		boolean hasFiske = Boolean.parseBoolean(infoparts[6]);
		String terrengType = infoparts[7];
		String spesialiteter = infoparts[8];
		String equipment = koieequipment;
		
		Koie koie = new Koie(name, numBeds, numSeats, hasSykkel, hasTopptur, 
				hasJakt, hasFiske, terrengType, spesialiteter, koieequipment);
		return koie;
	}
}

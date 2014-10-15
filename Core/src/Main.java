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
	
	public static Koie instantiateKoie(String koieinfo) {
		String[] parts = koieinfo.split(", ");
		
		String name = parts[0];
		int numBeds = Integer.parseInt(parts[1]);
		int numSeats = Integer.parseInt(parts[2]);
		boolean hasSykkel = Boolean.parseBoolean(parts[3]);
		boolean hasTopptur = Boolean.parseBoolean(parts[4]);
		boolean hasJakt = Boolean.parseBoolean(parts[5]);
		boolean hasFiske = Boolean.parseBoolean(parts[6]);
		boolean hasGitar = Boolean.parseBoolean(parts[7]);
		boolean hasVaffeljern = Boolean.parseBoolean(parts[8]);
		String terrengType = parts[9];
		String spesialiteter = parts[10];
	
		Koie koie = new Koie(name, numBeds, numSeats, hasSykkel, hasTopptur, 
				hasJakt, hasFiske, hasGitar, hasVaffeljern, terrengType, spesialiteter);
		return koie;
	}
}

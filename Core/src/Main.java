package src;

import java.util.List;

public class Main {
	private static Database database;
	//private textGUI textGui;
	
	private void init(){
		database = new Database();
		database.initializeDatabase("Core/src/initialiseringAvKoier.dat");

		//textGui = new textGUI();
		//textGui.Alternativer("bruker");
	}
	
	private void run(){
	}
	
	public static void main(String[] args){
		Main program = new Main();
		program.init();
		program.run();
	}
	
	private int[] getKoieIdArray() {
		return database.getKoieIdArray();
	}
	
	private String[] getKoieNameArray(int[] koieIds) {
		return database.getKoieNameArray(koieIds);
	}
}

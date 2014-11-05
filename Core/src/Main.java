package src;

import java.util.List;

public class Main {
	//private textGUI textGui;
	
	private void init(){
		Database.initializeDatabase("Core/src/initialiseringAvKoier.dat");

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
	
	private List<Integer> getKoieIdList() {
		return Database.getKoieIdList();
	}
	
	private List<String> getKoieNameList() {
		return Database.getKoieNameList();
	}
}

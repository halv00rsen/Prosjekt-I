package src;

import java.util.List;
import java.util.HashMap;

public class Main {
	//private textGUI textGui;
	
	private void init(){
		Database.initializeDatabase("Core/src/initialiseringAvKoier.txt");

		//textGui = new textGUI();
		//textGui.Alternativer("bruker");
	}
	
	private void run(){
		HashMap<Integer, String> idNameMap = Database.getIdNameMap();
		System.out.println(idNameMap.get(18));
	}
	
	public static void main(String[] args){
		Main program = new Main();
		program.init();
		program.run();
		
		
	}
	
}

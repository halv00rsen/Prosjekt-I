package src;

import java.util.Scanner;
import java.io.FileReader;

public class Main {
	
	private textGUI textGui;
	private Database database;
	
	private void init(){
		//textGui = new textGUI();
		//textGui.Alternativer("bruker");
//		database = new Database();
		
		//legger til koieinfo fra "initialiseringAvKoier.dat"
//		Scanner in = new Scanner("initialiseringAvKoier.dat");
//		String[] kolonner = in.nextLine().split(" ");
//		for (String elem : kolonner) {
//			System.out.println(elem);
//		}
//		in.close();
		//textGui = new textGUI();
		//textGui.Alternativer("bruker");
		database = new Database();
		database.initializeDatabase("Core/src/initialiseringAvKoier.dat");
	}
	
	private void run(){
		
	}
	
	public static void main(String[] args){
		Main program = new Main();
		program.init();
		program.run();
	}

}


package src;

import java.util.Scanner;
import java.io.FileReader;

public class Main {
	
	private textGUI textGui;
	private Database database;
	
	private void init(){
		//textGui = new textGUI();
		//textGui.Alternativer("bruker");
		database = new Database("Core/src/initialiseringAvKoier.txt");
	}
	
	private void run(){
		
	}
	
	public static void main(String[] args){
		Main program = new Main();
		program.init();
		program.run();
	}

}


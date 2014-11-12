package src;

import java.util.HashMap;

public class Main {
	//private textGUI textGui;
	
	private void init(){
		//Database.initializeDatabase();

		//textGui = new textGUI();
		//textGui.Alternativer("bruker");
	}
	
	private void run(){
		//test av hashmap
		HashMap<Integer, String> idNameMap = Database.getIdNameMap();
		System.out.println(idNameMap.get(18));
		
		Koie koie3 = Database.getKoie(3);
		Inventory inv = koie3.getInventory();
		inv.addItem(new Item("ostehøvel"));
		inv.addItem(new Item("gaffel"));
		Database.toDatabase(koie3);
	}
	
	public static void main(String[] args){
		Main program = new Main();
		program.init();
		program.run();
	}
}

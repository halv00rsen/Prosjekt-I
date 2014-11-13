package src;

import java.util.HashMap;

/** Starter og kjører programmet */
public class Main {
	/**
	 * Initialiserer programmet
	 */
	private void init(){
		//Database.initializeDatabase();
	}
	
	/**
	 * Kjører programmet
	 */
	private void run(){
		// Test av HashMap
		HashMap<Integer, String> idNameMap = Database.getIdNameMap();
		System.out.println(idNameMap.get(18));
		
		Koie koie3 = Database.getKoie(3);
		Inventory inv = koie3.getInventory();
		inv.addItem(new Item("ostehøvel"));
		inv.addItem(new Item("gaffel"));
		Database.toDatabase(koie3);
	}
	
	/**
	 * Starter initialisering og kjøringen av programmet
	 * @param args Standard args
	 */
	public static void main(String[] args){
		Main program = new Main();
		program.init();
		program.run();
	}
}

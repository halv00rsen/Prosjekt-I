package src;

/** Starter og kjører programmet */
public class Main {
	GUI gui;
	
	/**
	 * Kjører programmet
	 */
	private void run(){
		gui = new GUI();
	}
	
	/**
	 * Starter initialisering og kjøringen av programmet
	 * @param args Standard args
	 */
	public static void main(String[] args){
		Main program = new Main();
		program.run();
	}
}

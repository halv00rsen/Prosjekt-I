package src;
import java.util.*;	

public class textGUI {
	public static void main(String[] args) {
		textGUI gui = new textGUI();
		gui.Alternativer("admin");
	}
	
	public void Alternativer(String Bruker) {
		int alternativ = -1;
		Scanner scanner = new Scanner(System.in);
		
		while (alternativ != 5){
			System.out.println();
			System.out.println("***MENY***");
			System.out.println("1. Vis Kalender");
			System.out.println("2. Reserver Koie");
			System.out.println("3. Avbestill Koie");
			System.out.println("4. Sjekk Status");
			System.out.println("5. Avslutt");
			System.out.print("Velg alternativ: ");
			alternativ = scanner.nextInt();
			System.out.println();
			
			switch(alternativ) {
			case 1: visKalender(); break;
			case 2: reserverKoie(); break;
			case 3: avbestillKoie(); break;
			case 4: sjekkStatus(); break;
			case 5: avslutt(); break;
			default: break;
			}
		}
		scanner.close();
	}
	
	public void visKalender() {
		System.out.println("Vis kalender");
	}
	
	public void reserverKoie() {
		System.out.println("Reserver Koie");
	}
	
	public void avbestillKoie() {
		System.out.println("Avbestill koie");
	}
	
	public void sjekkStatus() {
		System.out.println("Sjekk status");
	}
	
	public void avslutt() {
		System.out.println("Avslutt");
	}
}










package src;
import java.util.*;	

public class textGUI {
	public static void main(String[] args) {
		textGUI gui = new textGUI();
		gui.Alternativer("admin");
	}
	
	void Alternativer(String Bruker) {
		int alternativ = -1;
		Scanner sc = new Scanner(System.in);
		
		while (alternativ != 5){
			System.out.println();
			System.out.println("***MENY***");
			System.out.println("1. Vis Kalender");
			System.out.println("2. Reserver Koie");
			System.out.println("3. Avbestill Koie");
			System.out.println("4. Sjekk Status");
			System.out.println("5. Avslutt");
			System.out.print("Velg alternativ: ");
			alternativ = sc.nextInt();
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
	}
	
	void visKalender() {
		System.out.println("Vis kalender");
	}
	
	void reserverKoie() {
		System.out.println("Reserver Koie");
	}
	
	void avbestillKoie() {
		System.out.println("Avbestill koie");
	}
	
	void sjekkStatus() {
		System.out.println("Sjekk status");
	}
	
	void avslutt() {
		System.out.println("Avslutt");
	}
}










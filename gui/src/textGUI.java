package src;
import java.util.*;	

public class textGUI {
	// public static void main() skal fjernes når main() klassen i core er ferdig og kan 
	// lage et textGUI objekt.
	public static void main(String[] args) {
		textGUI gui = new textGUI();
		gui.Alternativer("bruker");
	}
	
	
	// En metode som looper over brukerens alternativer (brukeren settes
	public void Alternativer(String login) {
		int alternativ = -1;
		Scanner scanner = new Scanner(System.in);
		
		if (login == "admin") {
			while (alternativ != 5) {	
				System.out.println();
				System.out.println("***MENY***");
				System.out.println("1. Vis Kalender");
				System.out.println("2. Avbestill Koie");
				System.out.println("3. Sjekk Status");
				System.out.println("4. Vis Kart");
				System.out.println("5. Avslutt");
				System.out.print("Velg alternativ: ");
				alternativ = scanner.nextInt();
				System.out.println();
			
				switch(alternativ) {
				case 1: visKalender(); break;
				case 2: avbestillKoieAdmin(); break;
				case 3: sjekkStatus(); break;
				case 4: visKart(); break;
				case 5: avslutt(); break;
				default: break;
				}	
			}
		}
		else if (login == "bruker") {
			while (alternativ != 7) {
				System.out.println();
				System.out.println("***MENY***");
				System.out.println("1. Vis Kalender");
				System.out.println("2. Reserver Koie");
				System.out.println("3. Avbestill Koie");
				System.out.println("4. Sjekk Status");
				System.out.println("5. Vis Kart");
				System.out.println("6. Raporter feil og mangler");
				System.out.println("7. Avslutt");
				System.out.print("Velg alternativ: ");
				alternativ = scanner.nextInt();
				System.out.println();
				
				switch(alternativ) {
				case 1: visKalender(); break;
				case 2: reserverKoie(); break;
				case 3: avbestillKoieBruker(); break;
				case 4: sjekkStatus(); break;
				case 5: visKart(); break;
				case 6: raporterFeil(); break;
				case 7: avslutt(); break;
				default: break;
				}
			}	
		}	
		scanner.close();
	}
	
	//Metode som skal vise kalender oversikt over all koiene og reservasjonene som er gjordt
	public void visKalender() {
		System.out.println("Vis kalender");
	}
	
	//Metode for å reservere koie for gitt bruker
	public void reserverKoie() {
		System.out.println("Reserver Koie");
	}
	
	//Avbestilling av hvilken som heslt reservasjon
	public void avbestillKoieAdmin() {
		System.out.println("Avbestill koie. admin");
	}
	
	//Avbestilling av koie reservert av bruker
	public void avbestillKoieBruker() {
		System.out.println("Avbestill koie. bruker");
	}
	
	//printer en ny text liste med valg av koie som status skal sjekkes på
	public void sjekkStatus() {
		System.out.println("Sjekk status");
	}
	
	//viser kart over hvor koiene befinner seg
	public void visKart() {
		System.out.println("Vis kart");
	}
	
	//lar bruker raportere feil og/eller mangler ved en koie
	public void raporterFeil() {
		System.out.println("Raporter feil");
	}
	
	//avslutter 
	public void avslutt() {
		System.out.println("Avslutt");
	}
}










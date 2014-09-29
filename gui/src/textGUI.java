package src;
import java.util.*;	

public class textGUI {
	// public static void main() skal fjernes n�r main() klassen i core er ferdig og kan 
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
				System.out.println("\n"
						+ "***MENY***\n"
						+ "1. Vis Kalender\n"
						+ "2. Avbestill Koie\n"
						+ "3. Sjekk Status\n"
						+ "4. Vis Kart\n"
						+ "5. Avslutt\n"
						+ "Velg alternativ: ");
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
				System.out.println("\n"
						+ "***MENY***\n"
						+ "1. Vis Kalender\n"
						+ "2. Reserver Koie\n"
						+ "3. Avbestill Koie\n"
						+ "4. Sjekk Status\n"
						+ "5. Vis Kart\n"
						+ "6. Raporter feil og mangler\n"
						+ "7. Avslutt\n"
						+ "Velg alternativ: ");
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
	
	//Metode for � reservere koie for gitt bruker
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
	
	//printer en ny text liste med valg av koie som status skal sjekkes p�
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










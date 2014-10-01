package src;
import java.util.*;	

public class textGUI {
	// public static void main() skal fjernes nï¿½r main() klassen i core er ferdig og kan 
	// lage et textGUI objekt.
	
	private Calendar calendar;//Bare til testing
	private Scanner scanner;
	
	private void initBruker(){
		calendar.reservePeriod(new Date(5,1), 5);
		calendar.reservePeriod(new Date(25, 12), 10);
	}
	
	private void initAdmin(){
		
	}
	
	// En metode som looper over brukerens alternativer (brukeren settes
	public void Alternativer(String login) {
		calendar = new Calendar();//skal fjærnes
		int alternativ = -1;
		scanner = new Scanner(System.in);
		
		if (login == "admin") {
			initAdmin();
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
			initBruker();
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
		System.out.println("Kalender:");
		int counter = 1;
		for (BookingDate booking : calendar.getDatesBooked()){
			System.out.println(counter++ + ". " + booking.toString());
		}
	}
	
	//Metode for ï¿½ reservere koie for gitt bruker
	public void reserverKoie() {
		System.out.println("Reserver Koie [dato:måned:antDager]:");
		System.out.println("Du kan leie fra " + calendar.getTodaysDate() + " til " + 
				calendar.getMaxDate() + ".");
		int[] dato1 = datoFormat();
		if (dato1 == null)
			return;
		Date dateFrom = new Date(dato1[0], dato1[1]);
		if (calendar.reservationIsOk(dateFrom, dato1[2])){
			calendar.reservePeriod(dateFrom, dato1[2]);
			System.out.println("Din reservasjon ble godkjent!");
		}else{
			System.out.println("Din reservasjon ble ikke godkjent.");
		}
	}
	
	private int[] datoFormat(){
		String[] dato = scanner.next().split(":");
		int[] dato1 = new int[3];
		for (int a = 0; a < 3; a++){
			try{
				dato1[a] = Integer.parseInt(dato[a]);
			}catch(Exception e){
				System.out.println("ikke gyldig datoformat");
				return null;
			}
		}if (!Calendar.validDate(dato1[0], dato1[1])){
			System.out.println("Datoen ikke gyldig");
			return null;
		}
		return dato1;
	}
	
	//Avbestilling av hvilken som heslt reservasjon
	public void avbestillKoieAdmin() {
		System.out.println("Avbestill koie. admin");
	}
	
	//Avbestilling av koie reservert av bruker
	public void avbestillKoieBruker() {
		System.out.println("Avbestill koie. bruker [dato:måned:antDager]");
		int[] dato = datoFormat();
		if (dato == null)
			return;
		Date date = new Date(dato[0], dato[1]);
		if (calendar.reservationExcists(date, dato[2])){
			calendar.removeReservation(date, dato[2]);
			System.out.println("Din reservasjon ble slettet");
		}
	}
	
	//printer en ny text liste med valg av koie som status skal sjekkes pï¿½
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










package src;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 
 * Klassen sjekker om det har kommet noen nye reservasjoner via mail
 */
public class GetMail{
	public final boolean DEBUG = false;
	
	/**
	 * Oppretter objektet og timeren som sjekker serveren etter ny mail.
	 */
	public GetMail(){
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			
			public void run(){
				String[] reservation = Mail.getMail();
				if (reservation == null)
					return;
				for (String res: reservation){
					try{
						String[] oneRes = res.split(":");
						String koie = oneRes[0].trim();
						String username = oneRes[1].trim();
						String[] date = oneRes[2].split("\\.");
						Map<Integer, String> cabins;
						if (DEBUG)
							cabins = Database.getIdNameMap();
						else
							cabins = GUI.getIdMap();
						int koieID = -1;
						for (Integer id : cabins.keySet()){
							if (cabins.get(id).equals(koie)){
								koieID = id;
								break;
							}
						}
						Koie cabin = Database.getKoie(koieID);
						cabin.getCalendar().reservePeriod(new Date(Integer.parseInt(date[0].trim()), Integer.parseInt(date[1].trim())), 0, username, -1, false);
						Database.toDatabase(cabin);
						if (DEBUG)
							System.out.println("Lagret i databasen");
							
					}catch (Exception e){
						if (DEBUG){
							e.printStackTrace();
							System.out.println("En error med mailen");
						}
						return;
					}
				}
			}
		}, 1*60*1000, 1*60*1000);
	}
}

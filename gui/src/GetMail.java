package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GetMail{
	public final boolean DEBUG = true;
	
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
						String koie = oneRes[0];
						String username = oneRes[1];
						String[] date = oneRes[2].split(".");
						Map<Integer, String> cabins;
						if (DEBUG)
							cabins = Database.getIdNameMap();
						else
							cabins = GUI.getIdMap();
						int koieID = -1;
						for (Integer id : cabins.keySet()){
							System.out.println(cabins.get(id) + "   " + koie);
							if (cabins.get(id).equals(koie)){
								koieID = id;
								break;
							}
						}
						System.out.println(koieID);
						Koie cabin = Database.getKoie(koieID);
						cabin.getCalendar().reservePeriod(new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1])), 0, username, -1, false);
						Database.toDatabase(cabin);
						System.out.println("Lagret i databasen");
							
					}catch (Exception e){
						if (DEBUG)
							System.out.println("En error med mailen");
						return;
					}
				}
				System.out.println("heisann");
			}
		}, 1*1000, 1*1000);
	}
}

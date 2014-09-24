package src;

import java.util.GregorianCalendar;

public class Calendar {
	
	public final static int daysInFeature = 180;
	
	public boolean dayIsTaken(int day, int month){
		return true;
	}
	
	public void reservePeriod(int day, int month, int days){
		
	}
	
	public static boolean validDate(int day, int month){
		if (day < 1 || month < 1 || month > 12)
			return false;
		if (month == 2){
			java.util.Calendar c = new GregorianCalendar();
			if (c.getWeekYear() % 4 == 0)
				return day <= 29;
			return day <= 28;
		}else if ((month < 8 && month % 2 == 1) || (month > 7 && month % 2 == 0)){
			return day <= 31;
		}
		else if ((month < 8 && month % 2 == 0) || (month > 7 && month % 2 == 1)){
			return day <= 30;
		}
		return false;
	}
}

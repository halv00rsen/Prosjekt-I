package src;

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
		if (month == 2 && day <= 28){
			return true;
		}else if ((month < 8 && month % 2 == 1) || (month > 7 && month % 2 == 1)){
			return true;
		}
		return true;
	}
}

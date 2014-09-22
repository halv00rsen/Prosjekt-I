package src;

public class Calendar {
	
	public final static int daysInFeature = 180;
	
	public boolean dayIsTaken(int day, int month){
		return true;
	}
	
	public void reservePeriod(int day, int month, int days){
		
	}
	
	public static boolean validDate(int day, int month){
		if (day < 1)
			return false;
		if (month == 2 && day <= 28){
			return true;
		}else if ((month == 1 || month == 3)){
			
		}
		return false;
	}
}

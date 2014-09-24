package src;

import java.util.GregorianCalendar;

public class Calendar {
	
	public final static int daysInFeature = 180;
	
	public Calendar(){
		
	}
	
	public boolean dayIsTaken(Date date){
		return true;
	}
	
	public boolean dayIsTaken(int day, int month){
		return true;
	}
	
	public void reservePeriod(Date date, int days){
		
	}
	
	private Date getLastDate(Date date, int days){
		if (days > daysInFeature)
			return null;
		int day = date.day;
		int month = date.month;
		if (validDate(day + days, month))
			return new Date(day + days, month);
		return null;
	}
	
	public void reservePeriod(Date dateFrom, Date dateTo){
		
	}
	
	public static boolean validDate(int day, int month){
		if (day < 1 || month < 1 || month > 12)
			return false;
		if (month == 2){//spesialtilfellet februar
			java.util.Calendar c = new GregorianCalendar();
			if (c.getWeekYear() % 4 == 0)//skuddår
				return day <= 29;
			return day <= 28;
		}else if ((month < 8 && month % 2 == 1) || (month > 7 && month % 2 == 0)){
			return day <= 31;//sjekker alle måneder med 31 dager
		}
		else if ((month < 8 && month % 2 == 0) || (month > 7 && month % 2 == 1)){
			return day <= 30;//sjekker ralle måneder med 30 dager
		}
		return false;
	}
}

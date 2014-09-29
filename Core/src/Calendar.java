package src;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Calendar {
	
	public final static int daysInFeature = 180;
	private final List<BookingDate> datesBooked;
	
	public Calendar(){
		datesBooked = new ArrayList<BookingDate>();
	}
	
	public boolean dayIsTaken(Date date){
		return true;
	}
	
	public boolean dayIsTaken(int day, int month){
		return true;
	}
	
	public void reservePeriod(Date date, int days){
		reservePeriod(date, getLastDate(date, days));
	}
	
	private Date getLastDate(Date date, int days){
		if (days > daysInFeature)
			return null;
		int day = date.day + days;
		int month = date.month;
		while (!validDate(day, month)){
			int daysInMonth = getDaysOfMonth(month);
			month++;
			day -= daysInMonth;
		}
		return new Date(day, month);
	}
	
	public static int getDaysOfMonth(int month){
		if (month == 2){
			java.util.Calendar c = new GregorianCalendar();
			if (c.getWeekYear() % 4 == 0)//skuddår
				return 29;
			return 28;
		}else if ((month < 8 && month % 2 == 1) || (month > 7 && month % 2 == 0)){
			return 31;//sjekker alle måneder med 31 dager
		}
		else if ((month < 8 && month % 2 == 0) || (month > 7 && month % 2 == 1)){
			return 30;//sjekker ralle måneder med 30 dager
		}else
			return -1;
	}
	
	private void reservePeriod(Date dateFrom, Date dateTo){
		
	}
	
	public static int getNumOfDaysBetween(Date dateFrom, Date dateTo){
		int monthDifference = (dateTo.month - dateFrom.month) % 12;
		if (monthDifference < 0)
			monthDifference += 12;
		if (monthDifference == 0){
			return dateTo.day - dateFrom.day;
		}
		else{
			int numDays = getDaysOfMonth(dateFrom.month) - dateFrom.day;
			int monthFrom = dateFrom.month;
			for (int a = 1; a < monthDifference + 1; a++){
				int actualMonth = (monthFrom + a) % 12;
				if (actualMonth < 0)
					actualMonth += 12;
				if (actualMonth == dateTo.month)
					numDays += dateTo.day;
				else{
					numDays += getDaysOfMonth(actualMonth);
				}
			}
			return numDays;
		}
	}
	
	public static boolean validDate(int day, int month){
		int daysInMonth = getDaysOfMonth(month);
		if (day < 1 || daysInMonth == -1 || day > daysInMonth)
			return false;
		return true;
	}
}

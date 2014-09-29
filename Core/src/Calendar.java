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
			if (c.getWeekYear() % 4 == 0)//skudd�r
				return 29;
			return 28;
		}else if ((month < 8 && month % 2 == 1) || (month > 7 && month % 2 == 0)){
			return 31;//sjekker alle m�neder med 31 dager
		}
		else if ((month < 8 && month % 2 == 0) || (month > 7 && month % 2 == 1)){
			return 30;//sjekker ralle m�neder med 30 dager
		}else
			return -1;
	}
	
	private void reservePeriod(Date dateFrom, Date dateTo){
		
	}
	
	public static boolean validDate(int day, int month){
		int daysInMonth = getDaysOfMonth(month);
		if (day < 1 || daysInMonth == -1 || day > daysInMonth)
			return false;
		return true;
	}
}

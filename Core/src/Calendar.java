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
	
	public void removeReservation(Date dateFrom, Date dateTo){
		BookingDate removeBooking = null;
		for (BookingDate booking : datesBooked){
			if (booking.equals(dateFrom, dateTo)){
				removeBooking = booking;
				break;
			}
		}
		if (removeBooking != null){
			datesBooked.remove(removeBooking);
		}
	}
	
	public boolean reservationIsOk(Date dateFrom, int days){
		Date dateTo = getLastDate(dateFrom, days);
		for (BookingDate booking : datesBooked){
			if (booking.datesCollideWithBooking(dateFrom, dateTo))
				return false;
		}
		return true;
	}
	
	public void reservePeriod(Date date, int days){
		reservePeriod(date, getLastDate(date, days));
	}
	
	public List<BookingDate> getDatesBooked(){
		return new ArrayList<BookingDate>(datesBooked);
	}
	
	private void reservePeriod(Date dateFrom, Date dateTo){
		for (BookingDate booking : datesBooked){
			if (booking.datesCollideWithBooking(dateFrom, dateTo))
				return;
		}
		datesBooked.add(new BookingDate(dateFrom, dateTo));
	}
	
	private Date getLastDate(Date date, int days){//returnerer hvilken dato som er x dager foran date
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
	
	public static int getDaysOfMonth(int month){//returnerer antall dager i denne aktuelle måneden
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
	

	public static int getNumOfDaysBetween(Date dateFrom, Date dateTo){//sjekker antall dager mellom dateFrom og dateTo
		int monthDifference = (dateTo.month - dateFrom.month) % 12;//er statisk, brukes av andre klasser, bla BookingDate
		if (monthDifference < 0 || (dateTo.day < dateFrom.day && monthDifference == 0))
			monthDifference += 12;
		if (monthDifference == 0){
			return dateTo.day - dateFrom.day;
		}
		else{
			int numDays = getDaysOfMonth(dateFrom.month) - dateFrom.day;
			int monthFrom = dateFrom.month;
			for (int a = 1; a < monthDifference + 1; a++){
				int actualMonth = (monthFrom + a) % 12;
				if (actualMonth == dateTo.month)
					numDays += dateTo.day;
				else{
					numDays += getDaysOfMonth(actualMonth);
				}
			}
			return numDays;
		}
	}
	
	public static boolean validDate(int day, int month){//sjekker om en dato er på riktig format, day og month er ikke 0-indeksert
		int daysInMonth = getDaysOfMonth(month);//er statisk, kan brukes av andre klasser, bla. Date 
		if (day < 1 || daysInMonth == -1 || day > daysInMonth)
			return false;
		return true;
	}
}

package src;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Calendar {
	
	public final static int daysInFeature = 180;
	private final List<BookingDate> datesBooked;
	private Date todaysDate, maxDate;//dato per dags dato
	
	public Calendar(){
		datesBooked = new ArrayList<BookingDate>();
		java.util.Calendar c = new GregorianCalendar();
		int monthToDay = c.get(GregorianCalendar.MONTH) + 1;
		todaysDate = new Date(c.get(GregorianCalendar.DAY_OF_MONTH), monthToDay);
		maxDate = getLastDate(todaysDate, daysInFeature);
	}
	
	public Date getTodaysDate(){
		return todaysDate;
	}
	
	public Date getMaxDate(){
		return maxDate;
	}
	
	//sjekker om reservasjonen eksisterer og fjerner den
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
	
	public void removeReservation(Date dateFrom, int days){
		removeReservation(dateFrom, getLastDate(dateFrom, days));
	}
	
	//sjekker om en reservasjon finnes
	public boolean reservationExcists(Date dateFrom, int days){
		Date dateTo = getLastDate(dateFrom, days);
		for (BookingDate booking : datesBooked){
			if (booking.equals(dateFrom, dateTo))
				return true;
		}
		return false;
	}
	
	//sjekker om denne reservasjonen kan reserveres, s� ikke overbooking skjer
	public boolean reservationIsOk(Date dateFrom, int days, boolean isFromDatabase){
		return reservationIsOk(dateFrom, getLastDate(dateFrom, days), days, isFromDatabase);
		
	}
	
	private boolean reservationIsOk(Date dateFrom, Date dateTo, int numOfDays, boolean isFromDatabase){
		if ((numOfDays > daysInFeature || numOfDays < 0 || dateFrom == null || dateTo == null) && !isFromDatabase)
			return false;
		for (BookingDate booking : datesBooked){
			if (booking.datesCollideWithBooking(dateFrom, dateTo))
				return false;
		}
		if (!todaysDate.isBefore(dateFrom)){
			return dateFrom.isAfter(todaysDate);
		}
		else if (todaysDate.equals(dateFrom))
			return true;
		int daysFromToNow = todaysDate.getDaysBetween(dateFrom);
		return numOfDays + daysFromToNow <= daysInFeature;
	}
	
	private boolean reservationIsOk(Date dateFrom, Date dateTo, boolean isFromDatabase){
		return reservationIsOk(dateFrom, dateTo, dateFrom.getDaysBetween(dateTo), isFromDatabase);
	}
	
	//reserverer en gitt periode
	public void reservePeriod(Date date, int days, String person, int resID, boolean isFromDatabase){
		reservePeriod(date, getLastDate(date, days), person, resID, isFromDatabase);
	}
	
	//returnerer en kopi av datoer som er booket
	public List<BookingDate> getDatesBooked(){
		return new ArrayList<BookingDate>(datesBooked);
	}
	
	//reserverer ei koie
	public void reservePeriod(Date dateFrom, Date dateTo, String person, int resID, boolean isFromDatabase){
		if (!reservationIsOk(dateFrom, dateTo, isFromDatabase))
			return;
		BookingDate booking = null;
		try{
			booking = new BookingDate(dateFrom, dateTo, person);
		}catch (IllegalArgumentException e){
			System.out.println("Din reservasjon er for gammel.");
			return;
		}
		booking.setID(resID);
		for (int a = 0; a < datesBooked.size(); a++){
			if (!booking.isAfter(datesBooked.get(a))){
				datesBooked.add(a, booking);
				return;
			}
		}
		datesBooked.add(booking);
	}
	
	//returnerer hvilken dato som er x dager foran date
	public static Date getLastDate(Date date, int days){
		if (days > daysInFeature || days < 0)
			return null;
		if (days == 0)
			return new Date(date);
		int day = date.day + days;
		int month = date.month;
		while (!validDate(day, month)){
			int daysInMonth = getDayOfMonth(month);
			month++;
			if (month == 13)
				month = 1;
			day -= daysInMonth;
		}
		return new Date(day, month);
	}
	
	//returnerer antall dager i denne aktuelle m�neden
	public static int getDayOfMonth(int month){
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
	
	//sjekker antall dager mellom dateFrom og dateTo
	//er statisk, brukes av andre klasser, bla BookingDate
	public static int getNumOfDaysBetween(Date dateFrom, Date dateTo){
		int monthDifference = (dateTo.month - dateFrom.month) % 12;
		if (monthDifference < 0 || (dateTo.day < dateFrom.day && monthDifference == 0))
			monthDifference += 12;
		if (monthDifference == 0){
			return dateTo.day - dateFrom.day;
		}
		else{
			int numDays = getDayOfMonth(dateFrom.month) - dateFrom.day;
			int monthFrom = dateFrom.month;
			for (int a = 1; a < monthDifference + 1; a++){
				int actualMonth = (monthFrom + a) % 12;
				if (actualMonth == 0)
					actualMonth += 12;
				if (actualMonth == dateTo.month)
					numDays += dateTo.day;
				else{
					numDays += getDayOfMonth(actualMonth);
				}
			}
			return numDays;
		}
	}
	
	//sjekker om en dato er p� riktig format, day og month er ikke 0-indeksert
	//er statisk, kan brukes av andre klasser, bla. Date 
	public static boolean validDate(int day, int month){
		int daysInMonth = getDayOfMonth(month);
		if (day < 1 || daysInMonth == -1 || day > daysInMonth || month > 12)
			return false;
		return true;
	}
}

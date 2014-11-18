package src;

import java.util.List;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/** Holder på reservasjonene til en {@link Koie} */
public class Calendar {
	public final static int daysInFeature = 180;
	private final List<BookingDate> datesBooked;
	private Date todaysDate; // Dato per dags dato
	
	/**
	 * Oppretter et tomt Calendar-objekt
	 */
	public Calendar() {
		datesBooked = new ArrayList<BookingDate>();
		todaysDate = getTodaysDate();
	}
	
	/**
	 * Returnerer en reservasjon
	 * @param from - dato fra
	 * @param to - dato til
	 * @return booking - hvis reservasjonen eksisterer, blir den returnert
	 */
	public BookingDate getReservation(Date from, Date to) {
		for (BookingDate booking: datesBooked) {
			if (booking.equals(from, to))
				return booking;
		}
		return null;
	}
	
	/**
	 * Returnerer n�v�rende dato
	 * @return Date-objekt med n�v�rende dato
	 */
	public static Date getTodaysDate() {
		java.util.Calendar c = new GregorianCalendar();
		int monthToDay = c.get(GregorianCalendar.MONTH) + 1;
		return new Date(c.get(GregorianCalendar.DAY_OF_MONTH), monthToDay);
	}
	
	/**
	 * Returnerer den siste datoen det kan reserveres på
	 * @return Date-objekt med siste dato
	 */
	public static Date getMaxDate() {
		return getLastDate(getTodaysDate(), daysInFeature);
	}
	
	/**
	 * Sjekker om reservasjonen er gyldig og ikke kolliderer med andre reservasjoner
	 * @param dateFrom Start-dato
	 * @param days Varighet
	 * @param isFromDatabase Om reservasjonen er hentet fra databasen
	 * @return Om reservasjonen er gyldig
	 */
	public boolean reservationIsOk(Date dateFrom, int days, boolean isFromDatabase) {
		return reservationIsOk(dateFrom, getLastDate(dateFrom, days), days, isFromDatabase);
		
	}
	
	/**
	 * Sjekker om reservasjonen er gyldig og ikke kolliderer med andre reservasjoner
	 * @param dateFrom Start-dato
	 * @param dateTo Slutt-dato
	 * @param isFromDatabase Om reservasjon ble hentet fra databasen
	 * @return Om reservasjonen er gyldig
	 */
	private boolean reservationIsOk(Date dateFrom, Date dateTo, boolean isFromDatabase) {
		return reservationIsOk(dateFrom, dateTo, dateFrom.getDaysBetween(dateTo), isFromDatabase);
	}
	
	/**
	 * Sjekker om reservasjonen er gyldig og ikke kolliderer med andre reservasjoner
	 * @param dateFrom Start-dato
	 * @param dateTo Slutt-dato
	 * @param numOfDays Varighet
	 * @param isFromDatabase Om reservasjon ble hentet fra databasen
	 * @return Om reservasjonen er gyldig
	 */
	private boolean reservationIsOk(Date dateFrom, Date dateTo, int numOfDays, boolean isFromDatabase) {
		if ((numOfDays > daysInFeature || numOfDays < 0 || dateFrom == null || dateTo == null)) {
			return false;
		}

		for (BookingDate booking : datesBooked) {
			if (booking.datesCollideWithBooking(dateFrom, dateTo)) {
				return false;
			}
		}

		if (!todaysDate.isBefore(dateFrom)) {
			return dateFrom.isAfter(todaysDate);
		} else if (todaysDate.equals(dateFrom)) {
			return true;
		}

		int daysFromToNow = todaysDate.getDaysBetween(dateFrom);
		return numOfDays + daysFromToNow <= daysInFeature;
	}
	
	/**
	 * Returnerer om en reservasjon finnes
	 * @param dateFrom Start-dato
	 * @param days Varighet
	 * @return True hvis reservasjonene finnes, false ellers
	 */
	public boolean reservationExcists(Date dateFrom, int days) {
		Date dateTo = getLastDate(dateFrom, days);
		for (BookingDate booking : datesBooked) {
			if (booking.equals(dateFrom, dateTo))
				return true;
		}
		return false;
	}

	/**
	 * Reserverer en koie for en gitt periode
	 * @param dateFrom Start-dato
	 * @param dateTo Slutt-dato
	 * @param person Hvilken bruker som reserverer
	 * @param resID Reservasjons-ID
	 * @param isFromDatabase Om reservasjonen er hente fra databasen
	 */
	public void reservePeriod(Date dateFrom, Date dateTo, String person, int resID, boolean isFromDatabase) {
		if (!reservationIsOk(dateFrom, dateTo, isFromDatabase))
			return;
		BookingDate booking = null;
		try {
			booking = new BookingDate(dateFrom, dateTo, person, isFromDatabase);
		} catch (IllegalArgumentException e) {
			System.out.println("Din reservasjon er for gammel.");
			return;
		}
		booking.setID(resID);
		for (int a = 0; a < datesBooked.size(); a++) {
			if (!booking.isAfter(datesBooked.get(a))) {
				datesBooked.add(a, booking);
				return;
			}
		}
		datesBooked.add(booking);
	}
	
	/**
	 * Reserverer en koie for en gitt periode
	 * @param date Start-dato
	 * @param days Varighet
	 * @param person Hvilken bruker som reserverer
	 * @param resID Reservasjons-ID
	 * @param isFromDatabase Om reservasjonen ble hentet fra databasen
	 */
	public void reservePeriod(Date date, int days, String person, int resID, boolean isFromDatabase) {
		reservePeriod(date, getLastDate(date, days), person, resID, isFromDatabase);
	}

	/**
	 * Fjerner en reservasjon hvis den eksisterer
	 * @param dateFrom Start-dato
	 * @param dateTo Slutt-dato
	 */
	public void removeReservation(Date dateFrom, Date dateTo) {
		BookingDate removeBooking = null;
		for (BookingDate booking : datesBooked) {
			if (booking.equals(dateFrom, dateTo)) {
				removeBooking = booking;
				break;
			}
		}
		if (removeBooking != null) {
			datesBooked.remove(removeBooking);
		}
	}
	
	/**
	 * Fjerner en reservasjon hvis den eksisterer
	 * @param dateFrom Start-dato
	 * @param days Varighet
	 */
	public void removeReservation(Date dateFrom, int days) {
		removeReservation(dateFrom, getLastDate(dateFrom, days));
	}
	
	/**
	 * Returnerer en kopi av datoer som er booket
	 * @return Datoer som er booket
	 */
	public List<BookingDate> getDatesBooked() {
		return new ArrayList<BookingDate>(datesBooked);
	}
	
	/**
	 * Returnerer hvilken dato som er et gitt antall dager foran en dato
	 * @param date Dato
	 * @param days Antall dager
	 * @return Dato som er et gitt antall dager foran en dato
	 */
	public static Date getLastDate(Date date, int days) {
		if (days > daysInFeature || days < 0)
			return null;
		if (days == 0)
			return new Date(date);
		int day = date.day + days;
		int month = date.month;
		while (!validDate(day, month)) {
			int daysInMonth = getDayOfMonth(month);
			month++;
			if (month == 13)
				month = 1;
			day -= daysInMonth;
		}
		return new Date(day, month);
	}
	
	/**
	 * Returnerer hvor mange dager det er i en gitt måned
	 * @param month Måneden
	 * @return Hvor mange dager det er i måneden
	 */
	public static int getDayOfMonth(int month) {
		if (month == 2) {
			java.util.Calendar c = new GregorianCalendar();
			if (c.getWeekYear() % 4 == 0) { // Skuddår
				return 29;
			}
			return 28;
		} else if ((month < 8 && month % 2 == 1) || (month > 7 && month % 2 == 0)) {
			return 31; // Sjekker alle måneder med 31 dager
		} else if ((month < 8 && month % 2 == 0) || (month > 7 && month % 2 == 1)) {
			return 30; // Sjekker alle måneder med 30 dager
		} else {
			return -1;
		}
	}
	
	/**
	 * Returnerer antall dager mellom to datoer
	 * @param dateFrom Start-dato
	 * @param dateTo Slutt-dato
	 * @return Antall dager mellom datoene
	 */
	// Er statisk, brukes av andre klasser, bl.a. BookingDate
	public static int getNumOfDaysBetween(Date dateFrom, Date dateTo) {
		int monthDifference = (dateTo.month - dateFrom.month) % 12;
		if (monthDifference < 0 || (dateTo.day < dateFrom.day && monthDifference == 0))
			monthDifference += 12;
		if (monthDifference == 0) {
			return dateTo.day - dateFrom.day;
		} else {
			int numDays = getDayOfMonth(dateFrom.month) - dateFrom.day;
			int monthFrom = dateFrom.month;
			for (int a = 1; a < monthDifference + 1; a++) { 
				int actualMonth = (monthFrom + a) % 12;
				if (actualMonth == 0) {
					actualMonth += 12;
				}
				if (actualMonth == dateTo.month) {
					numDays += dateTo.day;
				} else {
					numDays += getDayOfMonth(actualMonth);
				}
			}
			return numDays;
		}
	}
	
	/**
	 * Sjekker om en dato er gyldig
	 * @param day Dag (ikke 0̈́-indeksert)
	 * @param month Måned (ikke 0-indeksert)
	 * @return Om dag og måned er en gyldig kombinasjon
	 */
	// Er statisk, kan brukes av andre klasser, bl.a. Date 
	public static boolean validDate(int day, int month) {
		int daysInMonth = getDayOfMonth(month);
		if (day < 1 || daysInMonth == -1 || day > daysInMonth || month > 12)
			return false;
		return true;
	}
}

package src;

import java.util.GregorianCalendar;

/** Holder på en dato med dag, måned, år */
public class Date {
	public final int day, month, year;
	
	/**
	 * Oppretter et Date-objekt
	 * @param day Dag i måneden
	 * @param month Måneden
	 */
	public Date(int day, int month){ // Datoene er ikke nullIndeksert
		if (!Calendar.validDate(day, month))
			throw new IllegalArgumentException("Datoen er ikke gyldig.");

		this.day = day;
		this.month = month;

		java.util.Calendar c = new GregorianCalendar();
		int currentMonth = c.get(GregorianCalendar.MONTH) + 1;
		// Sjekker om datoen satt er neste år, ut ifra at dagsspennet kun kan være 180 dager
		year = c.getWeekYear() + (currentMonth > month ? 1 : 
			(currentMonth == month && c.get(GregorianCalendar.DAY_OF_MONTH) > day ? 1 : 0));
	}
	
	/**
	 * Oppretter et Date-objekt som en kopi av et Date-objekt
	 * @param date Date-objekt
	 */
	public Date(Date date){
		month = date.month;
		day = date.day;
		year = date.year;
	}
	
	/**
	 * Sjekker om en dato er lik denne
	 * @param date Dato-objekt
	 * @return Boolean
	 */
	public boolean equals(Date date){
		if (date == null)
			return false;
		return date.month == this.month && date.day == this.day;
	}
	
	/**
	 * Sjekker om en dato er etter denne
	 * @param date Dato-objekt
	 * @return Boolean
	 */
	public boolean isAfter(Date date){
		return equals(date) ? false : Calendar.getNumOfDaysBetween(date, this) < Calendar.daysInFeature;
	}
		
	/**
	 * Sjekker om en dato er før denne
	 * @param date Dato-objekt
	 * @return Boolean
	 */
	public boolean isBefore(Date date){
		return equals(date) ? false : Calendar.getNumOfDaysBetween(this, date) < Calendar.daysInFeature;
	}
	
	/**
	 * Returnerer antall dager mellom denne datoen og en annen
	 * @param date Dato-objekt
	 * @return Antall dager mellom denne datoen og en annen
	 */
	public int getDaysBetween(Date date){
		return Calendar.getNumOfDaysBetween(this, date);
	}
	
	/**
	 * Returnerer string med dato på formatet dd-mm-åååå
	 * @return String med dato på formatet dd-mm-åååå
	 */
	public String toString(){
		return lengthOfDate(day) + "-" + lengthOfDate(month) + "-" + year;
	}
	
	/**
	 * Zero-padder et tall hvis det har bare ett siffer, for å få formatet dd-mm-åååå
	 * @param number Dag eller måned
	 * @return En string med lengde 2
	 */
	private String lengthOfDate(int number){
		return number < 10 ? "0" + number : "" + number;
	}
}

package src;

import java.util.GregorianCalendar;

/** Holder på en dato bestående av dag, måned, år */
public class Date {
	public final int month, day, year;
	
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
	
	// Sjekker om en dato er lik denne
	public boolean equals(Date date){
		if (date == null)
			return false;
		return date.month == this.month && date.day == this.day;
	}
	
	// Sjekker om en dato er etter denne
	public boolean isAfter(Date date){
		return equals(date) ? false: Calendar.getNumOfDaysBetween(date, this) < Calendar.daysInFeature;
	}
	
	// Sjekker om en dato er etter denne
	public boolean isBefore(Date date){
		return equals(date) ? false: Calendar.getNumOfDaysBetween(this, date) < Calendar.daysInFeature;
	}
	
	// Returnerer antall dager mellom dette objektet og en annen dato
	public int getDaysBetween(Date date){
		return Calendar.getNumOfDaysBetween(this, date);
//		int d1 = Calendar.getNumOfDaysBetween(this, date);
//		int d2 = Calendar.getNumOfDaysBetween(date, this);
//		return d1 > d2 ? d1 : d2;
	}
	
	// Returnerer dato på formatet dd-mm-åååå
	public String toString(){
		return lengthOfDate(day) + "-" + lengthOfDate(month) + "-" + year;
	}
	
	//om en m�ned eller dag er mindre enn 10 s� legger den p� en null
	//For � f� formatet dd-mm-����
	private String lengthOfDate(int number){
		return number < 10 ? "0" + number : "" + number;
	}
}

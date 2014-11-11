package src;

import java.util.GregorianCalendar;

public class Date {
	
	public final int month, day, year;
	
	public Date(int day, int month){ //Datoene er ikke nullIndeksert
		if (!Calendar.validDate(day, month))
			throw new IllegalArgumentException("Datoen er ikke riktig");
		this.day = day;
		this.month = month;
		java.util.Calendar c = new GregorianCalendar();
		int actualMonth = c.get(GregorianCalendar.MONTH) + 1;
		//sjekker om datoen satt er neste �r, ut ifra at dagsspennet kun kan v�re 180 dager
		year = c.getWeekYear() + (actualMonth > month? 1: 
			(actualMonth == month && c.get(GregorianCalendar.DAY_OF_MONTH) > day ? 1: 0));
	}
	
	//Lager en kopi av dateinputten
	public Date(Date date){
		month = date.month;
		day = date.day;
		year = date.year;
	}
	
	//sjekker om en dato er lik denne
	public boolean equals(Date date){
		if (date == null)
			return false;
		return date.month == this.month && date.day == this.day;
	}
	
	//sjekker om en dato er etter denne
	public boolean isAfter(Date date){
		return equals(date) ? false: Calendar.getNumOfDaysBetween(date, this) < Calendar.daysInFeature;
	}
	
	//sjekker om en dato er etter denne
	public boolean isBefore(Date date){
		return equals(date) ? false: Calendar.getNumOfDaysBetween(this, date) < Calendar.daysInFeature;
	}
	
	//returnerer antall dager mellom dette objektet og en annen dato
	public int getDaysBetween(Date date){
		return Calendar.getNumOfDaysBetween(this, date);
//		int d1 = Calendar.getNumOfDaysBetween(this, date);
//		int d2 = Calendar.getNumOfDaysBetween(date, this);
//		return d1 > d2 ? d1 : d2;
	}
	
	//returnerer dato p� formatet dd-mm-����
	public String toString(){
		return lengthOfDate(day) + "-" + lengthOfDate(month) + "-" + year;
	}
	
	//om en m�ned eller dag er mindre enn 10 s� legger den p� en null
	//For � f� formatet dd-mm-����
	private String lengthOfDate(int number){
		return number < 10 ? "0" + number : "" + number;
	}
}

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
		int actualMonth = c.get(c.MONTH) + 1;
		//sjekker om datoen satt er neste år, ut ifra at dagsspennet kun kan være 180 dager
		year = c.getWeekYear() + (actualMonth > month? 1: 
			(actualMonth == month && c.get(c.DAY_OF_MONTH) > day ? 1: 0));
	}
	
	//sjekker om en dato er lik denne
	public boolean equals(Date date){
		if (date == null)
			return false;
		return date.month == this.month && date.day == this.day;
	}
	
	//sjekker om en dato er etter denne
	public boolean isAfter(Date date){
		return equals(date) ? false: !isBefore(date);//Calendar.getNumOfDaysBetween(date, this) < Calendar.daysInFeature;
	}
	
	//sjekker om en dato er etter denne
	public boolean isBefore(Date date){
		return equals(date) ? false: Calendar.getNumOfDaysBetween(this, date) < Calendar.daysInFeature;
	}
	
	//returnerer dato på formatet dd-mm-åååå
	public String toString(){
		return lengthOfDate(day) + "-" + lengthOfDate(month) + "-" + year;
	}
	
	//om en måned eller dag er mindre enn 10 så legger den på en null
	//For å få formatet dd-mm-åååå
	private String lengthOfDate(int number){
		return number < 10 ? "0" + number : "" + number;
	}
}

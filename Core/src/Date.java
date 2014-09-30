package src;

public class Date {
	
	public final int month, day;
	
	public Date(int day, int month){ //Datoene er ikke nullIndeksert
		if (!Calendar.validDate(day, month))
			throw new IllegalArgumentException("Datoen er ikke riktig");
		this.day = day;
		this.month = month;
	}
	
	public boolean isSameDate(Date date){
		return date.month == this.month && date.day == this.day;
	}
	
	public boolean isAfter(Date date){
		return isSameDate(date) ? false: Calendar.getNumOfDaysBetween(date, this) < Calendar.daysInFeature;
	}
	
	public boolean isBefore(Date date){
		return isSameDate(date) ? false: Calendar.getNumOfDaysBetween(this, date) < Calendar.daysInFeature;
	}
	
	public String toString(){
		return "month: " + month + ", day: " + day;
	}
}

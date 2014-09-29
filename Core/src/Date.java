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
	
	public boolean dateIsAfterThis(Date date){
		
	}
}

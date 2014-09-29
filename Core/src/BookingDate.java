package src;

public class BookingDate {
	
	public final Date dateFrom, dateTo;
	public final int numberOfDays;
	
	public BookingDate(Date dateFrom, Date dateTo){
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		numberOfDays = Calendar.getNumOfDaysBetween(dateFrom, dateTo);
	}
	
	public boolean datesCollideWithBooking(Date dateFrom, Date dateTo){
		int numDays = Calendar.getNumOfDaysBetween(dateFrom, dateTo);
		
	}
}

package src;

public class BookingDate {
	
	public final Date dateFrom, dateTo;
	public final int numberOfDays;
	
	public BookingDate(Date dateFrom, Date dateTo) throws IllegalArgumentException{
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		numberOfDays = Calendar.getNumOfDaysBetween(dateFrom, dateTo);
		if (numberOfDays > Calendar.daysInFeature || dateFrom == null || dateTo == null)
			throw new IllegalArgumentException("not valid dates for the program");
	}
	
	public boolean datesCollideWithBooking(Date dateFrom, Date dateTo){//sjekker om datoer sendt inn kolliderer med denne bookingen
		return dateFrom.isAfter(this.dateTo) || dateTo.isBefore(this.dateFrom);
	}
	
	public boolean collideWith(BookingDate booking){
		return datesCollideWithBooking(booking.dateFrom, booking.dateTo);
	}
	
	public boolean equals(BookingDate booking){
		if (booking == null)
			return false;
		return dateFrom.equals(booking.dateFrom) && dateTo.equals(booking.dateTo);
	}
	
	public boolean equals(Date dateFrom, Date dateTo){
		return this.dateFrom.equals(dateFrom) && this.dateTo.equals(dateTo);
	}
	
	public String toString(){
		return "[DateFrom: " + dateFrom + ", DateTo: " + dateTo + ", Days: " + numberOfDays + "]";
	}
}

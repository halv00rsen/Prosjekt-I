package src;

/** Holder på en reservasjon */
public class BookingDate {
	public final Date dateFrom, dateTo;
	public final int numberOfDays;
	public final String person;
	private int resID;
	
	/**
	 * Oppretter et BookingDate-objekt
	 * @param dateFrom Start-dato
	 * @param dateTo Slutt-dato
	 * @param person Bruker som reserverer
	 * @throws IllegalArgumentException
	 */
	public BookingDate(Date dateFrom, Date dateTo, String person) throws IllegalArgumentException{
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		numberOfDays = Calendar.getNumOfDaysBetween(dateFrom, dateTo);
		this.person = person;
		if (numberOfDays > Calendar.daysInFeature || dateFrom == null || dateTo == null)
			throw new IllegalArgumentException("not valid dates for the program");
		resID = -1;
	}
		
	/**
	 * Returnerer reservasjons-ID-en
	 * @return Reservasjons-ID
	 */
	public int getID(){
		return resID;
	}

	/**
	 * Setter reservasjons-ID-en
	 * @param id Reservasjons-ID
	 */
	public void setID(int id){
		resID = id;
	}
	
	/**
	 * Sjekker om reservasjons-datoer kolliderer med denne reservasjonen
	 * @param dateFrom Start-dato
	 * @param dateTo Slutt-dato
	 * @return Om datoene kolliderer med denne reservasjonen
	 */
	public boolean datesCollideWithBooking(Date dateFrom, Date dateTo){
		return !(dateFrom.isAfter(this.dateTo) || dateTo.isBefore(this.dateFrom));
	}
	
	//samme som over
	/**
	 * Sjekker om en reservasjon kolliderer med denne reservasjonen
	 * @param booking Reservasjon som BookingDate-objekt
	 * @return Om reservasjonen kolliderer med denne reservasjonen
	 */
	public boolean collideWith(BookingDate booking){
		return datesCollideWithBooking(booking.dateFrom, booking.dateTo);
	}
	
	/**
	 * Sjekker om en reservasjon er etter denne
	 * @param booking Reservasjon som BookingDate-objekt
	 * @return Om en reservasjon er etter denne
	 */
	public boolean isAfter(BookingDate booking){
		if (booking == null)
			return false;
		return booking.dateFrom.isBefore(dateFrom);
			
	}
	
	/**
	 * Sjekker om en reservasjon er lik denne
	 * @param booking Reservasjon som BookingDate-objekt
	 * @return Om en reservasjon er lik denne
	 */
	public boolean equals(BookingDate booking){
		if (booking == null)
			return false;
		return dateFrom.equals(booking.dateFrom) && dateTo.equals(booking.dateTo);
	}
	
	//samme som over
	/**
	 * Sjekker om en reservasjon er lik denne
	 * @param dateFrom Start-dato
	 * @param dateTo Slutt-dato
	 * @return Om reservasjons-datoer er lik denne
	 */
	public boolean equals(Date dateFrom, Date dateTo){
		return this.dateFrom.equals(dateFrom) && this.dateTo.equals(dateTo);
	}
	
	/**
	 * Returnerer informasjonen i objektet som en streng
	 */
	public String toString(){
		return "[Fra: " + dateFrom + ", Til: " + dateTo + ", Antall dager: " + numberOfDays + "]";
	}
}

package src;

/** Holder på en reservasjon */
public class UserDatesBooked {
	public final int cabinId;
	public final Date from, to;
	public final int resID;
	
	/**
	 * Returnerer en reservasjon som et UserDatesBooked-objekt
	 * @param cabinId Koie-ID
	 * @param from Start-dato
	 * @param to Slutt-dato
	 * @param resID Reservasjons-ID
	 */
	public UserDatesBooked(int cabinId, Date from, Date to, int resID){
		this.cabinId = cabinId;
		this.from = from;
		this.to = to;
		this.resID = resID;
	}
}

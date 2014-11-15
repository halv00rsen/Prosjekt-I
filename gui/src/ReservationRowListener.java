package src;

/**
 * 
 * Et interface som lytter til om en reservasjon skal slettes
 */
public interface ReservationRowListener {
	/**
	 * 
	 * @param reservation - en reservasjon som skal slettes fra lytteren
	 */
	public void removeReservation(ReservationRow reservation);
}

package src;

/**
 * 
 * En lytter til siden der man ser sine reservasjoner
 */
public interface ReservationsListListener {
	/**
	 * Sier i fra til lytteren om at denne reservasjonen skal fjernes
	 * @param resId - reservasjonsId i databasen
	 */
	public void removeReservation(int resId);
}

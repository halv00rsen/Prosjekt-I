package src;

/**
 * 
 * En lytter til panelet der man reserverer koier
 */
public interface ReservationsFrameListener {
	/**
	 * Sier i fra til lytteren om at en ny reservasjon er gjort
	 * @param name - navnet på koia
	 * @param from - dato fra
	 * @param to - dato til
	 * @param resId - reservasjonsId i databasen
	 */
	public void addReservation(String name, Date from, Date to, int resId);
}

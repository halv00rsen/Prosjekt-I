package src;

/**
 * 
 * En lytter til validDates
 */
public interface ValidDatesListener {

	/**
	 * Oppdaterer feltene
	 * @param day - dag valgt
	 * @param month - måned valgt
	 * @param numDays - antall dager valgt
	 */
	public void updateField(int day, int month, int numDays);
}

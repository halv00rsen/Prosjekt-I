package src;

/**
 * 
 * Et interface som alle faner av gui m� implementere.
 * N�r en aktuell fane blir �pnet, kj�res denne metoden for � hente data fra databasen
 * til den aktuelle fanen.
 */
public interface ChangeTabListener {
	/**
	 * Henter info som er n�dvendig for en aktuell fane.
	 */
	public void initPanel();
}

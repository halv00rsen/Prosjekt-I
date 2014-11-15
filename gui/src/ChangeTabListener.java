package src;

/**
 * 
 * Et interface som alle faner av gui må implementere.
 * Når en aktuell fane blir åpnet, kjøres denne metoden for å hente data fra databasen
 * til den aktuelle fanen.
 */
public interface ChangeTabListener {
	/**
	 * Blir kalt når den aktuelle fanen åpnes for å hente relevant data
	 */
	public void initPanel();
}

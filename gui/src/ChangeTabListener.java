package src;

/**
 * 
 * Et interface som alle faner av gui må implementere.
 * Når en aktuell fane blir åpnet, kjøres denne metoden for å hente data fra databasen
 * til den aktuelle fanen.
 */
public interface ChangeTabListener {
	/**
	 * Henter info som er nødvendig for en aktuell fane.
	 */
	public void initPanel();
}

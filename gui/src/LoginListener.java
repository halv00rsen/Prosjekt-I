package src;

/**
 * 
 * Et interface som andre paneler kan lytte til hvis de vil vite om noen er logget inn eller ikke
 */
public interface LoginListener {
	/**
	 * Sier om en bruker har logget inn
	 * @param userName - brukernavnet til personen som logget inn
	 */
	public void userHasLoggedIn(String userName);
	/**
	 * Sier om en bruker/admin har logget ut
	 */
	public void userHasLoggedOut();
	/**
	 * Sier om admin har logget inn
	 */
	public void adminHasLoggedIn();
}

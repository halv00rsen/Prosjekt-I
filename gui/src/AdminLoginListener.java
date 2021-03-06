package src;
/**
 * 
 * Et interface som lytter til adminloginpanelet
 */
public interface AdminLoginListener {
	/**
	 * Noen har logget inn
	 * @param userName innskrevet brukernavn
	 * @param password innskrevet passord som en array
	 */
	public void login(String userName, char[] password);
}

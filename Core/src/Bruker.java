package src;

import java.security.MessageDigest;
import java.math.BigInteger;

public class Bruker {
	private final String id;
	private String passwordHash;
	private boolean isAdmin;

	/**
	 * Oppretter Bruker-objekt
	 * @param id Brukernavn (unikt i databasen)
	 * @param passwordHash MD5-hash av passordet
	 * @param isAdmin Om brukeren er admin eller ikke
	 */
	public Bruker(String id, String passwordHash, boolean isAdmin) {
		this.id = id;
		this.passwordHash = passwordHash;
		this.isAdmin = isAdmin;
	}
	
	/**
	 * Hasher et passord
	 * @param passord
	 * @return En MD5-hash av passordet
	 */
	public static String hashPassword(String password) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(password.getBytes());
			byte[] digest = messageDigest.digest();
			BigInteger n = new BigInteger(1, digest);
			String hash = n.toString(16);
			return hash;
		} catch (java.security.NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	/**
	 * Sjekker om et passord er riktig for denne brukeren
	 * @param password
	 * @return true eller false
	 */
	public boolean isPasswordCorrect(String password) {
		String hash = hashPassword(password);
		return passwordHash.equals(hash);
	}

	public String getId() {
		return id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}
	
	/**
	 * Hasher et passordet og lagrer hashen
	 * @param password
	 */
	public void setPassword(String password) {
		this.passwordHash = hashPassword(password);
	}

	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}

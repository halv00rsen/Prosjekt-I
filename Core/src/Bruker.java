package src;

import java.security.MessageDigest;
import java.math.BigInteger;

/** Inneholder informasjon knyttet til en bruker i systemet */
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
	 * @param password En passord-streng
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
	 * @param password En passord-streng
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
	 * Hasher et passord og lagrer hashen i databasen
	 * @param password En passord-streng
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

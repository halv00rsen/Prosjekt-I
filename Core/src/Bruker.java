package src;

import java.security.MessageDigest;
import java.math.BigInteger;

public class Bruker {
	private final String id;
	private String passwordHash;
	private Status status;
	
	/**
	 * BRUKER eller ADMIN
	 */
	public static enum Status {
		BRUKER,
		ADMIN;
	}

	/**
	 * Returnerer en Item.Status basert på en streng
	 * @param statusString Tekststreng
	 * @return returnStatus Status i enum {@link Item.Status} 
	 */
	public static Bruker.Status getBrukerStatus(String statusString) {
		Bruker.Status returnStatus;
		if (statusString.equals("BRUKER")) {
			returnStatus = Bruker.Status.BRUKER;
		} else if (statusString.equals("ADMIN")) {
			returnStatus = Bruker.Status.ADMIN;
		} else {
			returnStatus = null;
		}
		return returnStatus;
	}

	/**
	 * Oppretter Bruker-objekt
	 * @param id Brukernavn (unikt i databasen)
	 * @param passwordHash MD5-hash av passordet
	 * @param status Status i enum {@link Bruker.Status}
	 */
	public Bruker(String id, String passwordHash, Status status) {
		this.id = id;
		this.passwordHash = passwordHash;
		this.status = status;
	}
	
	/**
	 * Hashes a password
	 * @param password
	 * @return An MD5-hash of the password
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

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
}

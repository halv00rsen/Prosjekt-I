package src;

import java.security.MessageDigest;
import java.math.BigInteger;

public class Bruker {
	private final String id;
	private final String passwordHash;
	private final Status status;
	
	/**
	 * BRUKER eller ADMIN
	 */
	public static enum Status {
		BRUKER,
		ADMIN;
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
}

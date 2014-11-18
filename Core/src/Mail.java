package src;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/** Inneholder metoder for å sende epost til brukere om at nytt utstyr må fraktes til en koie */
public class Mail {
	final static String mail = "it1901gruppe10@gmail.com";
	final static String pass = "prosjekt1"; 
	
	/**
	 * Sender en epost
	 * @param to Epost-adresse til mottaker
	 * @param subjectline Emne
	 * @param messageText Melding
	 * @return boolean avhengig av om mail er sendt eller ei.
	 */
	public static boolean sendMail(String to, String subjectline, String messageText) {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.password", pass);
		properties.setProperty("mail.smtp.user", mail);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {
			    	protected PasswordAuthentication  getPasswordAuthentication() {
			        return new PasswordAuthentication(mail, pass);
			    	}
		});
		
		try {
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(mail));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         message.setSubject(subjectline);
	         message.setText(messageText);

	         Transport.send(message);
	         return true;
	    } catch (MessagingException mex) {
	         return false;
	    }
	}
	
	/**
	 * Metode som leser siste mail fra "it1901gruppe10@gmail.com"
	 * @return string med innhold fra mail
	 */
	public static String[] getMail() {
		Properties properties = new Properties();
		properties.setProperty("mail.store.protocol", "imaps");

		try {
			Session session = Session.getInstance(properties, null);
			Store store = session.getStore();
			store.connect("imap.gmail.com", mail, pass);
			Folder inbox = store.getFolder("reservasjon");
			
			inbox.open(Folder.READ_WRITE);
			Message msg = inbox.getMessage(inbox.getMessageCount());
			Multipart mp = (Multipart) msg.getContent();
			String[] res = ((String) mp.getBodyPart(0).getContent()).split(",");
			msg.setFlag(Flags.Flag.DELETED, true);
			inbox.close(true);
			return res;
			
		} catch (Exception e) {
			return null;
		}
	}
}

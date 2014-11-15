package src;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/** Inneholder metoder for å sende epost til brukere om at nytt utstyr må fraktes til en koie */
public class Mail {
//	public static void main(String[] args) {
//		Mail.sendMail("it1901gruppe10@gmail.com", "test", "heisann");
//		Mail.getMail();
//	}
	
	final static String mail = "it1901gruppe10@gmail.com";
	final static String pass = "prosjekt1"; 
	
	/**
	 * Sender en epost
	 * @param to Epost-adresse til mottaker
	 * @param subjectline Emne
	 * @param messageText Melding
	 */
	public static boolean sendMail(String to, String subjectline, String messageText) {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.password", pass);
		properties.setProperty("mail.smtp.user", mail);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		
		//Session session = Session.getDefaultInstance(properties);
		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {
			    	protected PasswordAuthentication  getPasswordAuthentication() {
			        return new PasswordAuthentication(mail, pass);
			    	}
		});
		
		try{
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(mail));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject(subjectline);

	         // Now set the actual message
	         message.setText(messageText);

	         // Send message
	         Transport.send(message);
	         return true;
	    }catch (MessagingException mex) {
	         mex.printStackTrace();
	         return false;
	    }
	}
	
	/**
	 * Metode som leser siste mail fra "it1901gruppe10@gmail.com"
	 * @return string med innhold fra mail
	 */
	public static String getMail() {
		Properties properties = new Properties();
		properties.setProperty("mail.store.protocol", "imaps");
		try {
			Session session = Session.getInstance(properties, null);
			Store store = session.getStore();
			store.connect("imap.gmail.com", mail, pass);
			Folder inbox = store.getFolder("reservasjon");
			
			inbox.open(Folder.READ_ONLY);
			Message msg = inbox.getMessage(inbox.getMessageCount());
			
			String content = (String) msg.getContent();
			return content;
            
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}


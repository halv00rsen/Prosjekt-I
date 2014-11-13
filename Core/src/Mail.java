package src;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Mail {
	
//	public static void main(String[] args) {
//		Mail.sendMail("alekh@stud.ntnu.no", "test", "heisann");
//	}
	
	public static void sendMail(String to, String subjectline, String messageText) {
		
		String from = "it1901gruppe10@gmail.com";
		String pass = "prosjekt1"; 
		
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.password", pass);
		properties.setProperty("mail.smtp.user", from);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		
		//Session session = Session.getDefaultInstance(properties);
		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {
			    	protected PasswordAuthentication  getPasswordAuthentication() {
			        return new PasswordAuthentication(from, pass);
			    	}
		});
		
		try{
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject(subjectline);

	         // Now set the actual message
	         message.setText(messageText);

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
}

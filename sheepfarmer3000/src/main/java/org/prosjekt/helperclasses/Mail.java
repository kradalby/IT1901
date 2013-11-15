package org.prosjekt.helperclasses;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Mail
 * 
 * Dette er klassen som håndterer epostutsending. Den holder konfigurasjonen,
 * samt klassene for å håndtere utsending.
 * 
 * @author Kristoffer Dalby <kradalby@kradalby.no>
 *
 */
public class Mail {
	
	private final String username = "sheepfarmer3000@kradalby.no";
	private final String password = "sheepwatcher1337";
	private final String emailHost = "smtp.gmail.com";


	private String to;
	private String subject;
	private String message;
	
	public Mail(String to, String subject, String message) {
		this.to = to;
		this.subject = subject;
		this.message = message;
	}
	
	/**
	 * Denne funksjonen sender eposten som blir laget når objektet opprettes.
	 * @param None
	 */
	public void sendMail() {
		
		Properties props = System.getProperties();
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", emailHost);
	    props.put("mail.smtp.user", username);
	    props.put("mail.smtp.password", password);
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	    
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(username, password);
                  }
                });	
        
        try {
        	
        	Message message = new MimeMessage(session);
        	message.setFrom(new InternetAddress(username));
        	message.setRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
        	message.setSubject(this.subject);
        	message.setText(this.message);
        	
        	Transport.send(message);
        	
        	System.out.println("The message has been sent");
        	
        } catch (MessagingException e) {
        	e.printStackTrace();
        }
	}
		
	

    
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

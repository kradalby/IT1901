package org.prosjekt.helperclasses;

import com.clockworksms.*;

/**
 * Sms
 * 
 * Denne klassen håndterer sms-utsending via Clockwork SMS sitt api.
 * 
 * @author Kristoffer Dalby <kradalby@kradalby.no>
 */

public class Sms {
	
	private String to;
	private String message;
	
	/**
	 * Variabel med apikeyen vi har hos clockwork.
	 */
	private String apikey = "37cf0d73d079dca9780d7dd99f5f0b907a362237";
	
	public Sms(String to, String message) {
		this.to = to;
		this.message = message;
	}
	
	
	
	public String getTo() {
		return to;
	}



	public void setTo(String to) {
		this.to = to;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}

	
	
	/**
	 * Denne funksjonen sender smsen som blir laget når objektet opprettes.
	 * @param None
	 */
	public void sendSMS() {
		try {
			ClockWorkSmsService clockwss = new ClockWorkSmsService(apikey);
			SMS sms = new SMS(this.to, this.message);
			ClockworkSmsResult result = clockwss.send(sms);
			
			if(result.isSuccess()) {
				System.out.println("Sent sms with ID: " + result.getId());
			} else {
				System.out.println("Error: " + result.getErrorMessage());
			}
			
		} catch (ClockworkException e) {
			e.printStackTrace();
		}
	}
}
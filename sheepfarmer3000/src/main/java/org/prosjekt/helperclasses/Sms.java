package org.prosjekt.helperclasses;

import com.clockworksms.*;

public class Sms {
	
	private String to;
	private String message;
	
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



	public void sendSMS() {
		try {
			ClockWorkSmsService clockwss = new ClockWorkSmsService("37cf0d73d079dca9780d7dd99f5f0b907a362237");
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
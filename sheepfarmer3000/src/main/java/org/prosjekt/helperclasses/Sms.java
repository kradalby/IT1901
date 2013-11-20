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
	
	/**
     *
     * @param to tlf nr melding sendes til. 
     * @param message melding i sms. 
     */
    public Sms(String to, String message) {
		this.to = to;
		this.message = message;
	}
	
	
	
	/**
     *
     * @return tlf nummer. 
     */
    public String getTo() {
		return to;
	}



	/**
     *
     * @param to tlf nummer. 10 tall må inneholde landskode. 
     */
    public void setTo(String to) {
		this.to = to;
	}


    /**
     *
     * @return melding som vises i sms. 
     */
    public String getMessage() {
		return message;
	}



	/**
     *
     * @param message melding som vises i sms. 
     */
    public void setMessage(String message) {
		this.message = message;
	}

	
	
	/**
	 * Denne funksjonen sender smsen som blir laget når objektet opprettes.
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
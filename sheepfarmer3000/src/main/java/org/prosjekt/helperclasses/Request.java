package org.prosjekt.helperclasses;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Request
 * 
 * Dette er klassen som sendes over nettverk med instruksjoner for hva 
 * mottaker skal gjøre. Den vil i hovedsak kun brukes med en Enum
 * kommando, men kan også ha data i et hashmap som skal behandles
 * før responsen sendes.
 * 
 * @author Kristoffer Dalby <kradalby@kradalby.no>
 *
 */
public class Request implements Serializable {


	private static final long serialVersionUID = -15770597860985079L;
	private RequestEnum command;
	/**
	 * Dette er et hashmap som gjør det mulig å sende objekter av annen type en de
	 * predefinerte. Bruker f.eks. til login funksjonalitet.
	 */
	private HashMap<String, Object> items = new HashMap<String, Object>();
	
	
	/**
         * 
         * @param command RequestEnum 
         */
	public void setCommand(RequestEnum command) {
		this.command = command;
	}
	
        /**
         * 
         * @return RequestEnum.
         */
	public RequestEnum getCommand() {
		return this.command;
	}
	
	/**
	 * Metode som lar deg legge objekter til hashmappet med en nøkkel, nøkkelen vil
	 * nøkkelen vil indikere hva slags objekt det er snakk om så det blir brukt
	 * og kastet korrekt.
	 * @param key er nøkkelen som blir brukt for å lagre og hente frem objektet.
	 * @param object er objektet som skal sendes.
	 */
	public void addItem(String key, Object object) {
		this.items.put(key, object);
	}
	
        /**
         * 
         * @param key er nøkkelen som blir brukt for å lagre og hente frem objektet.
         * @return 
         */
	public Object getItem(String key) {
		return this.items.get(key);
	}
}



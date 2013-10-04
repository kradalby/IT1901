package org.prosjekt.helperclasses;

/**
 * @author kradalby
 * Enum for tilgjenglige funksjoner for klient/server kommunikasjon som brukes av klassen NetPackage.
 */
public enum NetPackageEnum {
	// Klient -> Server
	/**
	 * Logg inn.
	 */
	LOGIN,
	
	/**
	 * Legg til vedlagt sau.
	 */
	ADDSHEEP,
	
	/**
	 * Legg til vedlagt bonde.
	 */
	ADDFARMER,
	
	/**
	 * Fjern vedlagt sau.
	 */
	RMSHEEP,
	
	/**
	 * Fjern vedlagt bonde.
	 */
	RMFARMER,
	
	/**
	 * Faa alle sauer av gitt bonde.
	 */
	
	GETSHEEP,
	
	/**
	 * Faa farmer som er innlogget.
	 */
	GETFARMER,
	
	
	//Usikker paa om disse skal brukes enda.
	GETSHEEPS,
	ADDSHEEPS,
	RMSHEEPS,
	
	
	// Server -> Klient
	/**
	 * Generell suksess respons fra server til klient.
	 */
	SUCCESS,
	
	/**
	 * Melding som sendes fra server om Login mislykkes.
	 */
	LOGINFAILED,
	
	/**
	 * Melding som sendes fra server om login er en suksess.
	 */
	LOGINSUCCESSFUL,
	
	/**
	 * Melding som sendes fra serveren om ingen er logget inn.
	 */
	NOTLOGGEDIN,
	
	
}
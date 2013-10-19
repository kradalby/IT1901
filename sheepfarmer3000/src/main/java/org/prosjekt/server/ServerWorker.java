package org.prosjekt.server;

import org.prosjekt.helperclasses.Request;
import org.prosjekt.helperclasses.Response;
import org.prosjekt.helperclasses.Sheep;

/**
 * ServerWorker
 * 
 * Denne metoden inneholder all logikken serveren ordner. Den vil håndtere
 * alle pakkene som kommer inn fra klienter og annet.
 * 
 * @author Kristoffer Dalby <kradalby@kradalby.no>
 *
 */
public class ServerWorker {
	
	/**
	 * Denne metoden er kjernen for nettverks kommunikasjonen med klient.
	 * Den får alle request objektene og håndterer dem basert på Enum
	 * kommandoen som ble lagt ved.
	 * 
	 * @param tar inn en request pakke som skal utføres
	 * @return returnerer et responsobjekt som skal sendes tilbake.
	 */
	public static Response handlePackage(Request request) {
		Response response = new Response();
		
		if (request == null) {
			response.addItem("error", "Request was null");
		} else {
			switch (request.getCommand()) {
			case LOGIN:
				//loginmethod
				break;
			case GETSHEEPBYID:
				//getonesheep
				break;
			case GETALLSHEEPS:
				getAllSheeps(response);
				break;
			case GETALLUSERSSHEEPS:
				break;
			case GETALLUSERSSHEEPSALIVE:
				break;
			case GETALLUSERSSHEEPSDEAD:
				break;
			case ALTERSHEEP:
				break;
			case ALTERUSER:
				break;
			case GETUSERINFO:
				break;
			case ADDSHEEP:
				break;
			default:
				response.addItem("error", "Unknown request");
				break;
			}
		}
		
		return response;
	}
	
	/**
	 * @param returnerer alle sauene i hele systemet.
	 */
	public static void getAllSheeps(Response response) {
		Sheep sheep1 = new Sheep(0, null, null);
		Sheep sheep2 = new Sheep(1, null, null);
		Sheep[] sheeps = new Sheep[2];
		sheeps[0] = sheep1;
		sheeps[1] = sheep2;
		response.addSheeps(sheeps);
	}
	
}

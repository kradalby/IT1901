package org.prosjekt.server;

import org.prosjekt.database.FarmerService;
import org.prosjekt.database.SheepService;
import org.prosjekt.database.repository.FarmerRepository;
import org.prosjekt.database.repository.SheepRepository;
import org.prosjekt.helperclasses.Farmer;
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
			case SETPASSHASH:
				setPasshash(response, request);
				break;
			case GETPASSHASH:
				getPasshash(response, request);
				break;
			case GETSHEEPBYID:
				getSheepById(response, request);
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
			case REMOVESHEEP:
				removeSheep(response, request);
				break;
			case UPDATEUSER:
				updateUser(response, request);
				break;
			case GETUSER:
				getUser(response, request);
				break;
			case ADDSHEEP:
				addSheep(response, request);
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
	
	public static void getSheepById(Response response, Request request) {
		SheepService ss = new SheepRepository();
		int id = (int) request.getItem("sheepid");
		response.setSheep(ss.getSheepAllCordinates(id));		
	}
	
	public static void addSheep(Response response, Request request) {
		SheepService ss = new SheepRepository();
		Sheep sheep = (Sheep) request.getItem("sheep");
		ss.addSheep(sheep);
		response.addItem("success", "success");
	}
	
	public static void removeSheep(Response response, Request request) {
		SheepService ss = new SheepRepository();
		Sheep sheep = (Sheep) request.getItem("sheep");
		ss.removeSheep(sheep);
		response.addItem("success", "success");
	}
	
	public static void getUser(Response response, Request request) {
		FarmerService fs = new FarmerRepository();
		int id = (int) request.getItem("farmerid");
		response.setFarmer(fs.getFarmer(id));
	}
	
	public static void updateUser(Response response, Request request) {
		FarmerService fs = new FarmerRepository();
		Farmer farmer = (Farmer) request.getItem("farmer");
		fs.updateFarmer(farmer);
		response.addItem("success", "success");
	}
	
	public static void setPasshash(Response response, Request request) {
		FarmerService fs = new FarmerRepository();
		String passhash = (String) request.getItem("passhash");
		int id = (int) request.getItem("farmerid");
		fs.setPasshash(passhash, id);
		response.addItem("success", "success");
	}
	
	public static void getPasshash(Response response, Request request) {
		FarmerService fs = new FarmerRepository();
		int id = (int) request.getItem("farmerid");
		response.addItem("passhash", fs.getPasshash(id));
	}
	
}

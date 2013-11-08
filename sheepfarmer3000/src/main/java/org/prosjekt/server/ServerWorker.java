package org.prosjekt.server;

import org.prosjekt.database.FarmerService;
import org.prosjekt.database.LogicService;
import org.prosjekt.database.SheepService;
import org.prosjekt.database.repository.FarmerRepository;
import org.prosjekt.database.repository.LogicRepository;
import org.prosjekt.database.repository.SheepRepository;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Request;
import org.prosjekt.helperclasses.Response;
import org.prosjekt.helperclasses.Sheep;

import java.util.List;
import org.prosjekt.helperclasses.Helper;

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
	 * @param request tar inn en request pakke som skal utføres
	 * @return returnerer et responsobjekt som skal sendes tilbake.
	 */
	public static Response handlePackage(Request request) {
		Response response = new Response();
		
		if (request == null) {
			response.addItem("error", "Request was null");
		} else {
                    switch (request.getCommand()) {
                        case SetPasshash:
                            setPasshash(response, request);
                            break;
                        case GetPasshash:
                            getPasshash(response, request);
                            break;
                        case GetFarmer:
                            getUser(response, request);
                            break;
                        case UpdateFarmer:
                            updateFarmer(response, request);
                            break;
                        case UpdateFarmerArea:
                            updateFarmerArea(response, request);
                            break;
                            
                        case UpdateHelper:
                            updateHelper(response, request);
                            break;
                        case AddHelper:
                            addHelper(response, request);
                            break;
                        case RemoveHelper:
                            removeHelper(response, request);
                            break;
                            
                            
                        case GetSheepById:
                            getSheepById(response, request);
                            break;
                        case REMOVESHEEP:
                            removeSheep(response, request);
                            break;
                        case ADDSHEEP:
                            addSheep(response, request);
				break;
                        case GetFarmerIds:
                            getFarmerIds(response, request);
                            break;
			default:
				response.addItem("error", "Unknown request");
				break;
			}
		}
		
		return response;
	}
	
	/**
	 * Sender alle sauene i hele systemet til clienten.
	 * 
	 * @param response
	 *
	 */
	public static void getAllSheeps(Response response) {
		LogicService ss = new LogicRepository();
		Sheep[] sheeps = ss.getAllSheeps();
		response.addSheeps(sheeps);
	}
	
	/**
	 * Sender en sau til clienten
	 * 
	 * @param response
	 * @param request
	 */
	public static void getSheepById(Response response, Request request) {
		SheepService ss = new SheepRepository();
		String id = (String) request.getItem("sheepid");
		response.setSheep(ss.getSheepAllCordinates(id));		
	}
	
	/**
	 * Mottar en sau fra clienten og legger den til i databasen.
         * Sau MÅ ha currentCoordinate satt. 
	 * 
	 * @param response
	 * @param request
	 */
	public static void addSheep(Response response, Request request) {
		SheepService ss = new SheepRepository();
		Sheep sheep = (Sheep) request.getItem("sheep");
		ss.addSheep(sheep, sheep.getCurrentCordinate());
		response.addItem("success", "success");
	}
	
	/**
	 * Motar en sau fra clienten og fjerner den fra databasen.
	 * 
	 * @param response
	 * @param request
	 */
	public static void removeSheep(Response response, Request request) {
		SheepService ss = new SheepRepository();
		Sheep sheep = (Sheep) request.getItem("sheep");
		ss.removeSheep(sheep.getId());
		response.addItem("success", "success");
	}
	
	/**
	 * Mottar en id for en farmer av clienten og returnerer objektet.
	 * 
	 * @param response
	 * @param request
	 */
	public static void getUser(Response response, Request request) {
		FarmerService fs = new FarmerRepository();
		int id = (int) request.getItem("farmerid");
		response.setFarmer(fs.getFarmer(id));
	}
	
	/**
	 * Mottar et farmer objekt fra client som skal oppdateres i databasen.
	 *
	 * @param response
	 * @param request
	 */
	public static void updateFarmer(Response response, Request request) {
		FarmerService fs = new FarmerRepository();
		Farmer farmer = (Farmer) request.getItem("farmer");
		fs.updateFarmer(farmer);
		response.addItem("success", "success");
	}
        
        /**
	 * Mottar et farmer objekt fra client som skal oppdateres i databasen.
	 *
	 * @param response
	 * @param request
	 */
	public static void updateFarmerArea(Response response, Request request) {
		FarmerService fs = new FarmerRepository();
		Farmer farmer = (Farmer) request.getItem("farmer");
		fs.updateFarmerArea(farmer.getCoordinates(), farmer.getId());
		response.addItem("success", "success");
	}
	
	/**
	 * Mottar en passhash string og en farmer id som skal settes passhash paa i databasen.
	 * 
	 * @param response
	 * @param request
	 */
	public static void setPasshash(Response response, Request request) {
		FarmerService fs = new FarmerRepository();
		String passhash = (String) request.getItem("passhash");
		int id = (int) request.getItem("farmerid");
		fs.setPasshash(passhash, id);
		response.addItem("success", "success");
	}
	
	/**
	 * Mottar en farmerid og returnerer passhashen til denne bonden.
	 * 
	 * @param response
	 * @param request
	 */
	public static void getPasshash(Response response, Request request) {
		FarmerService fs = new FarmerRepository();
		int id = (int) request.getItem("farmerid");
		response.addItem("passhash", fs.getPasshash(id));
	}
        
    private static void getFarmerIds(Response response, Request request) {
        LogicService ls = new LogicRepository();
        List<Integer> farmerIds = ls.getFarmerids();
        response.addItem("farmerids", farmerIds);
    }
    
        
    public static void updateHelper(Response response, Request request) {
        FarmerService fs = new FarmerRepository();
        Helper helper = (Helper) request.getItem("helper");
        fs.updateHelper(helper);
        response.addItem("success", "success");
    }
    
    public static void addHelper(Response response, Request request) {
        FarmerService fs = new FarmerRepository();
        Helper helper = (Helper) request.getItem("helper");
        fs.addHelper(helper);
        response.addItem("success", "success");
    }
    
    public static void removeHelper(Response response, Request request) {
        FarmerService fs = new FarmerRepository();
        Helper helper = (Helper) request.getItem("helper");
        fs.removeHelper(helper);
        response.addItem("success", "success");
    }
}

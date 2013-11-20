package org.prosjekt.client;

import com.google.common.collect.Lists;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Mail;
import org.prosjekt.helperclasses.Passhash;
import org.prosjekt.helperclasses.Request;
import org.prosjekt.helperclasses.RequestEnum;
import org.prosjekt.helperclasses.Response;
import org.prosjekt.helperclasses.Sheep;
import org.prosjekt.helperclasses.Sms;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.prosjekt.database.Admin;
import org.prosjekt.database.repository.LogicRepository;
import org.prosjekt.gui.LoginBox;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Helper;
import org.prosjekt.logic.RandomSheepGenerator;
import org.prosjekt.logic.SheepLogic;
import org.prosjekt.logic.WolfLogic;

/**
 * Denne klassen er en placeholder klasse helt til GUI peepsa finner ut hvor de vil ha metodene.
 * 
 * @author kradalby
 *
 */
public class ClientService {

	static ClientConnection connection = new ClientConnection("129.241.210.105", 4455);
//	static ClientConnection connection = new ClientConnection("localhost", 4455);
	static boolean connected = connection.openServerConnection();
	
	public static void main(String[] args) {
              List<Farmer> farmers = new LogicRepository().getAllFarmers();
              for (Farmer farmer : farmers){
                  RandomSheepGenerator rsg = new RandomSheepGenerator(farmer.getCoordinates(), farmer);
                  SheepLogic.moveSheeps(rsg, farmer.getSheeps(), new LogicRepository());
              }
	}
            
        public static String getPathToResources(String file){
            file = "images/" +file;
            return file;
        }
        
        public static String pathToBackGround(){
            return "images/bakgrunn 450x450.jpg";
        }
	
        //SheepService
	/**
	 * 
	 * Henter en sau med alle dens kordinater.
	 * @param id 	paa sauen
	 * @return 		en sau og alle dens kordinater
	 */
	public static Sheep getSheepById(String id) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.GetSheepById);
			request.addItem("sheepid", id);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return response.getSheep();
		} else {
			return null;
		}
	}
	
	/**
	 * Legge til en sau.
	 * @param sheep		Tar inn et saueobjekt som skal legges til
	 * @return 			returnerer true hvis det er suksess og false om ikke
	 */
	public static boolean addSheep(Sheep sheep) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.ADDSHEEP);
			request.addItem("sheep", sheep);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return !response.isErrorInResponse();
		} else {
			return false;
		}
	}
	
	/**
	 * Fjerne en sau.
	 * @param sheep 	tar inn et saueobjekt som skal fjernes.
	 * @return			returnerer true hvis det er suksess og false om ikke.
	 */
	public static boolean removeSheep(Sheep sheep) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.REMOVESHEEP);
			request.addItem("sheep", sheep);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return !response.isErrorInResponse();
		} else {
			return false;
		}
	}
	
        
        //FarmerService
	/**
	 * Hent ut farmer fra server
	 * @param id		id paa farmer som skal hentes ut
	 * @return			returnerer farmeren eller null
	 */
	public static Farmer getFarmer(int id) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.GetFarmer);
			request.addItem("farmerid", id);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
                        Farmer farmer = response.getFarmer();
                         System.out.println("sheeps: " + farmer.getSheeps().size());
                        return farmer;
		} else {
			return null;
		}
	}
	
	/**
	 * Oppdater en farmers informasjon paa serveren.
	 * @param farmer	farmer objektet som skal oppdateres.
	 * @return			returnerer true hvis det er suksess og false om ikke.
	 */
	public static boolean updateFarmer(Farmer farmer) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.UpdateFarmer);
			request.addItem("farmer", farmer);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return !response.isErrorInResponse();
		} else {
			return false;
		}
	}
        
        /**
         *  Oppdaterer KUN farmerArea på server siden. 
         * @param farmer with farmerArea. 
         * @return 
         */
        public static boolean updateFarmerArea(Farmer farmer) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.UpdateFarmerArea);
			request.addItem("farmer", farmer);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return !response.isErrorInResponse();
		} else {
			return false;
		}
//                return false;
	}
        

        /**
         * Oppdaterer Helper på server. 
         * @param helper
         * @return 
         */
	public static boolean updateHelper(Helper helper) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.UpdateHelper);
			request.addItem("helper", helper);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return !response.isErrorInResponse();
		} else {
			return false;
		}
	}
        
        /**
         * Sletter helper på server. 
         * @param helper
         * @return 
         */
	public static boolean removeHelper(Helper helper) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.RemoveHelper);
			request.addItem("helper", helper);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return !response.isErrorInResponse();
		} else {
			return false;
		}
	}
        
        /**
         * Legger til Helper på server. 
         * @param helper
         * @return 
         */
	public static boolean addHelper(Helper helper) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.AddHelper);
			request.addItem("helper", helper);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return !response.isErrorInResponse();
		} else {
			return false;
		}
	}
        
	/**
	 * Setter passhashen til en bruker.
	 * @param farmerid	id paa brukeren som skal oppdateres
	 * @param passhash	hashet string av passordet.
	 * @return			returnerer true hvis det er suksess og false om ikke.
	 */
	public static boolean setPasshash(int farmerid, String passhash) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.SetPasshash);
			request.addItem("farmerid", farmerid);
			request.addItem("passhash", passhash);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return !response.isErrorInResponse();
		} else {
			return false;
		}
	}
	
	/**
	 * Faa passhash for verifisering av bruker.
	 * @param farmerid	id paa brukeren som skal autentiseres.
	 * @return			returnerer passhash objekt eller null.
	 */
	public static Passhash getPasshash(int farmerid) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.GetPasshash);
			request.addItem("farmerid", farmerid);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return (Passhash) response.getItem("passhash");
		} else {
			return null;
		}
	}

        
        
        /**
         * @return liste med farmerids fra server.
         */
        public static List<Integer> getFarmerIds() {
        if (connected) {
            Request request = new Request();
            request.setCommand(RequestEnum.GetFarmerIds);
            connection.sendPackage(request);
            Response response = (Response) connection.receivePackage();
            List<Integer> ids = (List<Integer>) response.getItem("farmerids");
            System.out.println("Farmerids: " + ids);
            return ids;
        } else {
            return null;
        }
    }
        
 
        /**
         * 
         * @param sheep som skal bli angrepet. 
         * @param useSms 
         * @return 
         */
        public static boolean attack(Sheep sheep, boolean useSms) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.Attack);
			request.addItem("sheep", sheep);
                        request.addItem("useSms", useSms);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return !response.isErrorInResponse();
		} else {
			return false;
		}
	}
        

      
    
}


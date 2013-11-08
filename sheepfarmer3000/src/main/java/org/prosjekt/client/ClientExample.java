package org.prosjekt.client;

import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Mail;
import org.prosjekt.helperclasses.Passhash;
import org.prosjekt.helperclasses.Request;
import org.prosjekt.helperclasses.RequestEnum;
import org.prosjekt.helperclasses.Response;
import org.prosjekt.helperclasses.Sheep;
import org.prosjekt.helperclasses.Sms;

import java.util.List;

/**
 * Denne klassen er en placeholder klasse helt til GUI peepsa finner ut hvor de vil ha metodene.
 * 
 * @author kradalby
 *
 */
public class ClientExample {

	static ClientConnection connection = new ClientConnection("127.0.0.1", 4455);
	static boolean connected = connection.openServerConnection();
	
	public static void main(String[] args) {
            List<Integer> ids = getFarmerIds();
            System.out.println("farmerids: " + ids);
            
            Passhash ph = getPasshash(1);
            System.out.println(ph.getPasshash());
            
            Farmer farmer = getFarmer(1);
            System.out.println(farmer);
            
//            ids = getFarmerIds();
//		Sheep[] sheeps = getAllSheeps();
//		for (int i = 0; i < sheeps.length; i++) {
//			System.out.println(sheeps[i].getId());
//		}
//		
//		Mail mail = new Mail("kradalby@kradalby.no", "test", "dette er en test lol");
//		mail.sendMail();
//		
//		Sms sms = new Sms("4745673429", "Dette er en sauetest!");
//		sms.sendSMS();
	}
	
//	public static Sheep[] getAllSheeps() {
//		if (connected) {
//			Request request = new Request();
//			request.setCommand(RequestEnum.GETALLSHEEPS);
//			connection.sendPackage(request);
//			Response response = (Response) connection.receivePackage();
//			return response.getSheeps();
//		} else {
//			return null;
//		}
//		
//	}
	
	/**
	 * 
	 * Henter en sau med alle dens kordinater.
	 * @param id 	paa sauen
	 * @return 		en sau og alle dens kordinater
	 */
	public static Sheep getSheepById(int id) {
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
	 * @param sheeo 	tar inn et saueobjekt som skal fjernes.
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
			return response.getFarmer();
		} else {
			return null;
		}
	}
	
	/**
	 * Oppdater en farmers informasjon paa serveren.
	 * @param farmer	farmer objektet som skal oppdateres.
	 * @return			returnerer true hvis det er suksess og false om ikke.
	 */
	public static boolean updateUser(Farmer farmer) {
		if (connected) {
			Request request = new Request();
//			request.setCommand(RequestEnum.UPDATEUSER);
			request.addItem("farmer", farmer);
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

    public static List<Integer> getFarmerIds() {
        if (connected) {
            Request request = new Request();
            request.setCommand(RequestEnum.GetFarmerIds);
            connection.sendPackage(request);
            Response response = (Response) connection.receivePackage();
            return (List<Integer>) response.getItem("farmerids");
        } else {
            return null;
        }
    }
    
}


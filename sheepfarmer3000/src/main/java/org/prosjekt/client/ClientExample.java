package org.prosjekt.client;

import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Mail;
import org.prosjekt.helperclasses.Passhash;
import org.prosjekt.helperclasses.Request;
import org.prosjekt.helperclasses.RequestEnum;
import org.prosjekt.helperclasses.Response;
import org.prosjekt.helperclasses.Sheep;
import org.prosjekt.helperclasses.Sms;

public class ClientExample {

	static ClientConnection connection = new ClientConnection("127.0.0.1", 4455);
	static boolean connected = connection.openServerConnection();
	
//	public static void main(String[] args) {
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
//	}
	
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
	
	public static Sheep getSheepById(int id) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.GETSHEEPBYID);
			request.addItem("sheepid", id);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return response.getSheep();
		} else {
			return null;
		}
	}
	
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
	
	public static Farmer getUser(int id) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.GETUSER);
			request.addItem("farmerid", id);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return response.getFarmer();
		} else {
			return null;
		}
	}
	
	public static boolean updateUser(Farmer farmer) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.UPDATEUSER);
			request.addItem("farmer", farmer);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return !response.isErrorInResponse();
		} else {
			return false;
		}
	}
	
	public static boolean setPasshash(int farmerid, String passhash) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.SETPASSHASH);
			request.addItem("farmerid", farmerid);
			request.addItem("passhash", passhash);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return !response.isErrorInResponse();
		} else {
			return false;
		}
	}
	
	public static Passhash getPasshash(int farmerid) {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.GETPASSHASH);
			request.addItem("farmerid", farmerid);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return (Passhash) response.getItem("passhash");
		} else {
			return null;
		}
	}
	
	
}


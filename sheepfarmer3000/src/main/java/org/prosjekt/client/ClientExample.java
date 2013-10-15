package org.prosjekt.client;

import org.prosjekt.helperclasses.Request;
import org.prosjekt.helperclasses.RequestEnum;
import org.prosjekt.helperclasses.Response;
import org.prosjekt.helperclasses.Sheep;

public class ClientExample {

	static ClientConnection connection = new ClientConnection("127.0.0.1", 4455);
	static boolean connected = connection.openServerConnection();
	
	public static void main(String[] args) {
		Sheep[] sheeps = getAllSheeps();
		for (int i = 0; i < sheeps.length; i++) {
			System.out.println(sheeps[i].getId());
		}
	}
	
	public static Sheep[] getAllSheeps() {
		if (connected) {
			Request request = new Request();
			request.setCommand(RequestEnum.GETALLSHEEPS);
			connection.sendPackage(request);
			Response response = (Response) connection.receivePackage();
			return response.getSheeps();
		} else {
			return null;
		}
		
	}
}


package org.prosjekt.server;

import org.prosjekt.helperclasses.Request;
import org.prosjekt.helperclasses.Response;

public class ServerWorker {
	
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
}

package org.prosjekt.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.prosjekt.helperclasses.Request;
import org.prosjekt.helperclasses.Response;

/**
 * ServerSession
 * 
 * Dette er klassen hvor de individuelle klient sesjonene håndteres
 * mot serveren.
 * 
 * @author Kristoffer Dalby <kradalby@kradalby.no>
 *
 */
public class ServerSession extends Thread {

	private Socket socket;
	private int id;
	private boolean running = true;
	
	public ServerSession(Socket socket, int id) {
		this.socket = socket;
		this.id = id;
	}
	
	ObjectOutputStream out;
	ObjectInputStream in;
	
	public void run() {
		try {
			System.out.println("New server connection opened.");
			System.out.println("Session opened with ID: " + id);
			
			while(running) {
                                Request packageReceived = receivePackage();
				Response response = null; 
                                if (packageReceived != null) response = ServerWorker.handlePackage(packageReceived); 
				if (response != null) sendPackage(response);
			}
			
			System.out.println("Connection with session id: " + id + " closed.");
		} catch(Exception e) {
//			e.printStackTrace();
		}
	}
	
	/**
	 * Mottar en request pakke fra klienten for denne sesjonen
	 * og returnerer den om alt gikk bra.
	 * @return returnerer true for OK eller false for ikke OK.
	 */
	public Request receivePackage() {
            Request request = null;
		try {
//			System.out.println("Session " + id + " waiting for object");
			in = new ObjectInputStream(socket.getInputStream());
			request = (Request) in.readObject();
		} catch (SocketException e) {
//			e.printStackTrace();
		} catch (IOException ioe){
//                    ioe.printStackTrace();
                } catch (ClassNotFoundException ex) {
//                    ex.printStackTrace();
                } catch (Exception e){
//                    e.printStackTrace();
                }
                return request;
	}
	
	/**
	 * Sender responsepakke tilbake til klienten for sesjonen.
	 * @param tar inn ett respons objekt.
	 * @return returnerer true for OK og false for ikke OK.
	 */
	public boolean sendPackage(Response response) {
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(response);
			out.flush();
			System.out.println("Package has been sent.");
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void closeNetworkConnection() {
		try {
			if(in != null)
				in.close();
			if(out != null)
				out.close();
			if(socket != null)
				socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

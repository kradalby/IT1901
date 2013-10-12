package org.prosjekt.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.prosjekt.helperclasses.Request;

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
				Request request = ServerWorker.doshiat(recivePackage()); //Doshiat er en placeholder til en ordentligmetode blir laget.
				sendPackage(request);
			}
			
			System.out.println("Connection with session id: " + id + " closed.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Request recivePackage() {
		try {
			System.out.println("Session " + id + " waiting for object");
			in = new ObjectInputStream(socket.getInputStream());
			return (Request) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean sendPackage() {
		
	}
	
	public void close() {
		
	}
	
}

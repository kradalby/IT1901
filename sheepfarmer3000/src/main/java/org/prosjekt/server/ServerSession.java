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
				Request request = ServerWorker.doshiat(receivePackage()); //Doshiat er en placeholder til en ordentligmetode blir laget.
				sendPackage(request);
			}
			
			System.out.println("Connection with session id: " + id + " closed.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Request receivePackage() {
		try {
			System.out.println("Session " + id + " waiting for object");
			in = new ObjectInputStream(socket.getInputStream());
			return (Request) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean sendPackage(Request request) {
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(request);
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

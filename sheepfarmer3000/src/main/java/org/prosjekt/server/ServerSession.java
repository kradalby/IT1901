package org.prosjekt.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSession extends Thread {

	private Socket socket;
	private int id;
	
	public ServerSession(Socket socket, int id) {
		this.socket = socket;
		this.id = id;
		
	}
	
	ObjectOutputStream out;
	ObjectInputStream in;
	
	public void run() {
		
	}
	
	public void recieveObject() {
		
	}
	
	public void sendObject() {
		
	}
	
	public void close() {
		
	}
	
}

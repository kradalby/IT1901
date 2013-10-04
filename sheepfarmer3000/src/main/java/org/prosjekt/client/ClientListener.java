package org.prosjekt.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientListener implements Runnable {
	
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Socket listningSocket = null;
	private int port;
	private String host;
	private boolean run = true;
	
	
	public ClientListener(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void run() {
		
		try {
			
			listningSocket = new Socket(this.host, this.port);
			in = new ObjectInputStream(listningSocket.getInputStream());
			out = new ObjectOutputStream(listningSocket.getOutputStream());
			
			
			while (run == true) {
				
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		
		
	}
	
	public void close() {
		this.run = false;
	}
	
}

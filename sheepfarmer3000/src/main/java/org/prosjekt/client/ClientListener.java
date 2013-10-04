package org.prosjekt.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

import org.prosjekt.helperclasses.NetPackage;

public class ClientListener implements Runnable {
	
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Socket listningSocket = null;
	private int port;
	private String host;
	private boolean run = true;
	private LinkedList<NetPackage> commandQueue;
	
	public ClientListener(String host, int port) {
		this.host = host;
		this.port = port;
		this.commandQueue = new LinkedList<NetPackage>();
	}
	
	public void run() {
		
		try {
			
			this.listningSocket = new Socket(this.host, this.port);
			this.in = new ObjectInputStream(listningSocket.getInputStream());
			this.out = new ObjectOutputStream(listningSocket.getOutputStream());
			
			
			while (run == true) {
				try {
					
				} catch (IOExceptin IOE) {
					
				}
			}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		
		
	}
	
	public void close() {
		this.run = false;
	}
	
}

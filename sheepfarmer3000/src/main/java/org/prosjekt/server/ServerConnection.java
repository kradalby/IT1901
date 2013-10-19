package org.prosjekt.server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerConnection
 * 
 * Denne klassen kjører hovedtråden for serveren som håndterer innkommende
 * tilkoblinger og lager sesjoner av dem.
 * 
 * @author Kristoffer Dalby <kradalby@kradalby.no>
 *
 */
public class ServerConnection extends Thread{

	private final int port = 4455;
	private boolean running;
	
	public void run() {
		running = true;
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			int sessionId = 0;
			while(running) {
				Socket socket;
				socket = (Socket) serverSocket.accept();
				
				
				sessionId++;
				ServerSession serverSession;
				serverSession = new ServerSession(socket, sessionId);
				serverSession.start();
			} 
			
			serverSocket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		running = false;
	}
}

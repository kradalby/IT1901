package org.prosjekt.server;

import java.net.ServerSocket;
import java.net.Socket;

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
				
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		running = false;
	}
}

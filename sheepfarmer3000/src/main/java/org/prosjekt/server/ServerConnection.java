package org.prosjekt.server;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection extends Thread{

	private final int port = 4455;
	private boolean running;
	
	public void run() {
		running = true;
		try {
			ServerSocket serverSocket = new ServerSocket();
			int sessionId = 0;
			while(running) {
				Socket socket;
				socket = (Socket) serverSocket.accept();
				
				
				sessionId++;
				System.out.println("New server session started with id: " + sessionId);
				ServerSession serverSession;
				serverSession = new ServerSession(socket, sessionId);
				
			}
		}
	}
	
	public void close() {
		running = false;
	}
}

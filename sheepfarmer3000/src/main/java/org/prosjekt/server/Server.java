package org.prosjekt.server;

public class Server {

	
	public static void main(String args[]) {
		ServerConnection serverConnection;
		serverConnection = new ServerConnection();
		serverConnection.start();
	}
}

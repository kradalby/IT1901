package org.prosjekt.server;

/**
 * Server
 * 
 * Denne klassen har main-metoden som brukes for å starte serveren.
 * 
 * @author Kristoffer Dalby <kradalby@kradalby.no>
 *
 */
public class Server {
	
	/**
	 * Mainmetode for aa starte serveren og simulatoren.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ServerConnection serverConnection;
		serverConnection = new ServerConnection();
		serverConnection.start();
		
		Simulator simulator;
		simulator = new Simulator();
//		simulator.start();
	}
}

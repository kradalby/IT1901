package org.prosjekt.server;

import java.io.*;
import java.net.*;
import java.security.*;

public class Server {

	private static int port = 4444, maxConnections = 0;

	// Listen for incoming connections and handle them
	public static void main(String[] args) {
		int connectionCounter= 0;

		try {
			ServerSocket listener = new ServerSocket(port);
			Socket server;

			while ((connectionCounter++ < maxConnections) || (maxConnections == 0)) {

				server = listener.accept();
				ServerThread conn_c = new ServerThread(server);
				Thread t = new Thread(conn_c);
				t.start();
				System.out.println("new connection started " + connectionCounter);
				System.out.println(t.getState());
			}
		} catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}
	}
}

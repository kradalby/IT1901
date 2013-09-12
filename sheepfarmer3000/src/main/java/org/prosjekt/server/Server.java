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
				doComms conn_c = new doComms(server);
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

class doComms implements Runnable {
	private Socket server;
	private String line, input;

	doComms(Socket server) {
		this.server = server;
	}

	public void run() {

		input = "";

		try {
			// Get input from the client
			DataInputStream in = new DataInputStream(server.getInputStream());
			PrintStream out = new PrintStream(server.getOutputStream());

			while ((line = in.readLine()) != null && !line.equals(".")) {
				input = input + line;
				out.println("I got:" + line);
			}

			// Now write to the client

			System.out.println("Overall message is:" + input);
			out.println("Overall message is:" + input);

			server.close();
		} catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}
	}
}
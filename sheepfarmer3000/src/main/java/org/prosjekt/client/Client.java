package org.prosjekt.client;

import java.io.*;
import java.net.*;

import org.prosjekt.helperclasses.Sheep;

public class Client {
	private String host;
	private int port;
	
	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public static void main(String args[]) throws IOException {
		Client session = new Client("localhost", 4444);
		
		Socket sessionSocket = null;
		ObjectOutputStream out = null;
		BufferedReader in = null;
		
		try {
			sessionSocket = new Socket(session.host, session.port);
			out = new ObjectOutputStream(sessionSocket.getOutputStream());
		} catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + session.host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to: " + session.host);
            System.exit(1);
        }
		
		Sheep sheep = new Sheep(1,56, null, null);
		out.writeObject(sheep);

		
		out.close();
		in.close();
		sessionSocket.close();
		
	}
	
}

package org.prosjekt.client;

import java.io.*;
import java.net.*;

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
		PrintWriter out = null;
		BufferedReader in = null;
		
		try {
			sessionSocket = new Socket(session.host, session.port);
			out = new PrintWriter(sessionSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(sessionSocket.getInputStream()));
		} catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + session.host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to: " + session.host);
            System.exit(1);
        }
		
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
		String userInput;
		
		while ((userInput = stdin.readLine()) != null) {
			out.println(userInput);
			System.out.println("echo: " + in.readLine());
		}
		
		out.close();
		in.close();
		stdin.close();
		sessionSocket.close();
		
	}
	
}

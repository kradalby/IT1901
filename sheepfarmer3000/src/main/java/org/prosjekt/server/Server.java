package org.prosjekt.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public int port;
	public int maxCon;

	public Server(int port, int maxCon) {
		this.port = port;
		this.maxCon = maxCon;
	}
	
	public static void main(String args[]) throws IOException {
		System.out.println("merp derp lager server: ");
		ServerSocket serverSocket = new ServerSocket(1234);
		while(true) {
			Socket socket = serverSocket.accept();
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			pw.println("merp?");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str = br.readLine();
			
			pw.println("Syre, " + str);
			pw.close();
			socket.close();
			
			System.out.println("Derp: " + str);
		}
	}
	
}

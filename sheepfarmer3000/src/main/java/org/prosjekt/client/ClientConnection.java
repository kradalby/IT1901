package org.prosjekt.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.prosjekt.helperclasses.Request;
import org.prosjekt.helperclasses.Response;

/**
 * ClientConnection
 * 
 * Dette er klassen som håndterer kommunikasjonen fra Klient til Server.
 * 
 * @author Kristoffer Dalby <kradalby@kradalby.no>
 *
 */
public class ClientConnection {

	private static int TIMEOUT = 3000;
	private String host;
	private int port;
	
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket socket;
	
	public ClientConnection(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	/**
	 * Denne funksjonen åpner tilkoblingen til serveren.
	 * @return  returnerer true hvis tilkoblingen blir åpnet uten feil.
	 */
	public boolean openServerConnection() {
		try {
			SocketAddress address = new InetSocketAddress(host,port);
			socket = new Socket();
			socket.connect(address, TIMEOUT);
			System.out.println("Client - new network connection has been opened");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * Denne metoden gjør at klienten gjør seg klar til å ta imot
	 * et respons objekt fra serveren.
	 * @return  returnerer response objektet som mottas eller null om det feiler.
	 */
	public Response receivePackage() {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			return (Response) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Denne metoden sender en request pakke fra klienten til serveren.
	 * @param request objektet som skal sendes.
	 * @return  returnerer true om det blir sent korrekt, og false om noe feiler.
	 */
	public boolean sendPackage(Request request) {
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(request);
			out.flush();
			System.out.println("Package has been sent.");
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Lukker tilkoblingen til serveren.
	 */
	public void closeNetworkConnection() {
		try {
			if(in != null)
				in.close();
			if(out != null)
				out.close();
			if(socket != null)
				socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	



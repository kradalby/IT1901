package org.prosjekt.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.prosjekt.helperclasses.Request;

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
	
	
	public Request receivePackage() {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			return (Request) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
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
	



package org.prosjekt.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;

import org.prosjekt.helperclasses.ObjectCaster;
import org.prosjekt.helperclasses.Sheep;

public class ServerThread implements Runnable{

	private Socket server;
	private ObjectInputStream out;
	ServerThread(Socket server) {
		this.server = server;
	}

	public void run() {


		try {

			out = new ObjectInputStream(server.getInputStream());
			
			while (out != null) {
				Object object;
				object = out.readObject();
				object = ObjectCaster.castObject(object);
				//System.out.println(object.getClass());
				System.out.println(sheep.getId());
				System.out.println(sheep.getWeight());
				
			}

			server.close();
		} catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

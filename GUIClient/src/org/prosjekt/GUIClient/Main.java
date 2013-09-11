package org.prosjekt.GUIClient;

import javax.swing.JFrame;

public class Main {
	private static Gui gui;
	private static JFrame guiFrame;
	protected static TestServer testServer;
	public static void main (String args[]){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				init();
			}
		});
	}
	public static void init(){
		gui = new Gui();
		guiFrame = gui.getGui();
		testServer = new TestServer();
		
	}
	public static void getFarmerFromServer(int id){
		
	}
	

}

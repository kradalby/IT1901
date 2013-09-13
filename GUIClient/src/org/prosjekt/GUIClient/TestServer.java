package org.prosjekt.GUIClient;

import java.util.ArrayList;

public class TestServer {
	private ArrayList<Farmer> farmers;
	private char[] password = {'1','2','3','4'};
	
	public TestServer(){
		Farmer farmer1 = new Farmer("Ola", "Nordmann", "1000", password, "ola@nordmann.no", "81549300", 
				"Kari", "Nordmann", "kari@nordmann.no", "81549300");
		Farmer farmer2 = new Farmer("Kari", "Nordmann", "2000", password, "kari@nordmann.no", "81549300",
				"Ola", "Nordmann", "ola@nordmann.no", "81549300");
		farmer1.setSheeps(createSheeps(farmer1));
		farmer2.setSheeps(createSheeps(farmer2));
		farmers = new ArrayList();
		farmers.add(farmer1);
		farmers.add(farmer2);
	}
	
	public ArrayList<Sheep> createSheeps(Farmer farmer){

		ArrayList<Sheep> sheepList = new ArrayList();
		Sheep sheep1 = new Sheep(1000, farmer);
		Sheep sheep2 = new Sheep(2000, farmer);
		Sheep sheep3 = new Sheep(3000, farmer);
		Sheep sheep4 = new Sheep(4000, farmer);
		Sheep sheep5 = new Sheep(5000, farmer);
		sheepList.add(sheep1);
		sheepList.add(sheep2);
		sheepList.add(sheep3);
		sheepList.add(sheep4);
		sheepList.add(sheep5);
		return sheepList;
		
		
	}
	public ArrayList<Farmer> getFarmerList(){	//Denne må sende tilbake en arraylist med farmers
		return farmers;
	}
	

}

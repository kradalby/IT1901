package org.prosjekt.GUIClient;

import java.util.ArrayList;

public class Sheep {
	private final int id;
	private boolean alive;
	private int weight;
	private String attack;
	private String birth;
	private ArrayList<Integer> coordinates;
	private Farmer farmer;
	
	public Sheep(int id, Farmer farmer){
		this.id = id;
		this.farmer = farmer;
	}

}

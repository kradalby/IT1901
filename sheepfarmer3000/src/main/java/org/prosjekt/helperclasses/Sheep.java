package org.prosjekt.helperclasses;

import java.io.Serializable;
import java.util.ArrayList;

import org.joda.time.DateTime;

public class Sheep implements Serializable {
	private final int id;
	private boolean alive;
	private int weight;
	private String attack;
	private final DateTime birth;
	private ArrayList<Coordinate> cordinates;
	private Farmer farmer;

	public Sheep(int id, int weight, DateTime birth, Farmer farmer) {
		this.id = id;
		this.weight = weight;
		this.birth = birth;
		this.farmer = farmer;
	}

	public int getId() {
		return this.id;
	}
	
	public boolean getAlive() {
		return this.alive;
	}
	
	public void setAlive(boolean status) {
		this.alive = status;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public void setWeigth(int weight) {
		this.weight = weight;
	}
	
	public DateTime getBirth() {
		return this.birth;
	}
	
	//Her må noe gjøres, jeg vet ikke hva Arraylisten skal inneholde
	public ArrayList getAllCordinates() { 
		return this.cordinates;
	}
	//Her må noe gjøres, jeg vet ikke hva Arraylisten skal inneholde
	public ArrayList getCurrentCordinates() {
            throw new UnsupportedOperationException("not yet implemented. ");
//		return this.cordinates.peek();
	}
	
	
	//Trenger vi en setFarmer? bytte av eierskap?
	public Farmer getFarmer() {
		return this.farmer;
	}
	
	
}

package org.prosjekt.helperclasses;

import org.prosjekt.helperclasses.Farmer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.prosjekt.helperclasses.Farmer;

public class Sheep implements Serializable {
	private final int id;
	private final DateTime birth;
	private boolean alive;
	private int weight;
	private String attack;
	private List<Coordinate> cordinates;
	private Farmer farmer;
        private Coordinate mostRecentCoordinate;

	public Sheep(int id, DateTime birth, Farmer farmer) {
		this.id = id;
		this.birth = birth;
		this.farmer = farmer;
                cordinates = Collections.unmodifiableList(new ArrayList());
	}
        
        public Sheep(int id, DateTime birth, Farmer farmer, Coordinate coordinate) {
		this.id = id;
		this.birth = birth;
		this.farmer = farmer;
                cordinates = Collections.unmodifiableList(new ArrayList());
                this.mostRecentCoordinate = coordinate;
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
	
        public void addCoordinate(Coordinate coordinate){
            this.cordinates.add(coordinate);
        }
        
       
	public List getAllCordinates() { 
		return Collections.unmodifiableList(this.cordinates);
	}
	//Returnerer siste elementet i cordinates listen fra denne sauen.
	
        public Coordinate getCurrentCordinate() {
            //throw new UnsupportedOperationException("not yet implemented. ");
		return this.cordinates.get(this.cordinates.size() - 1 );
	}

    public void setMostCurrentCoordinate(Coordinate coordinate) {
        this.mostRecentCoordinate = coordinate;
    }
	
        
	
	//Trenger vi en setFarmer? bytte av eierskap?
	public Farmer getFarmer() {
		return this.farmer;
	}

        /**
         * @return all coordinates of sheep as unmodifiable list.
         */
        public List<Coordinate> getAllCoordinates() {
            return cordinates;
        }

   

      
	
}

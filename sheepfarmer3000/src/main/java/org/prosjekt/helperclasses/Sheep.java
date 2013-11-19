package org.prosjekt.helperclasses;

import org.prosjekt.helperclasses.Farmer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.prosjekt.helperclasses.Farmer;

public class Sheep implements Serializable {
    private final String id;
    private final DateTime birth;
    private boolean alive;
    private int weight;
    private List<Coordinate> attacks;
    private List<Coordinate> cordinates;
    private int farmerid;
    private Coordinate currentCoordinate;
    
    
    
    public Sheep(String id, DateTime birth, int farmerid, Coordinate currentCoordinate) {
        this.id = id;
        this.birth = birth;
        this.alive = true;
        this.farmerid = farmerid;
        cordinates = new ArrayList<Coordinate>();
        attacks = new ArrayList<Coordinate>();
        this.currentCoordinate = currentCoordinate;
    }
    
    public String getId() {
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
    
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public DateTime getBirth() {
        return this.birth;
    }
    public Coordinate getCurrentCordinate() {
        return currentCoordinate;
    }
    
    public void setCurrentCoordinate(Coordinate currentCoordinate) {
        this.currentCoordinate = currentCoordinate;
    }
    
    
    
    public void setCordinates(List<Coordinate> cordinates) {
        this.cordinates = cordinates;
    }

    public List<Coordinate> getCordinates() {
        return cordinates;
    }
   
    
    
    public List<Coordinate> getAttacks() {
        return attacks;
    }
    
    public void setAttacks(List<Coordinate> attacks) {
        this.attacks = attacks;
    }
    
    
   
    public int getFarmerid() {
        return farmerid;
    }
    
    @Override
    public String toString() {
        return "Sheep{" + "id=" + id + ", birth=" + birth + ", alive=" + alive + ", weight=" + weight + ", attack=" + attacks + ", cordinates=" + cordinates + ", farmerid=" + farmerid + ", currentCoordinate=" + currentCoordinate + '}';
    }
    
    
    
    
    
    
}

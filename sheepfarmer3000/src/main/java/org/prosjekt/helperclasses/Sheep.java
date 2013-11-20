package org.prosjekt.helperclasses;

import org.prosjekt.helperclasses.Farmer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.prosjekt.helperclasses.Farmer;

/**
 *
 * @author Christoffer Buvik
 */
public class Sheep implements Serializable {
    private final String id;
    private final DateTime birth;
    private boolean alive;
    private int weight;
    private List<Coordinate> attacks;
    private List<Coordinate> cordinates;
    private int farmerid;
    private Coordinate currentCoordinate;
    
    
    
    /**
     *
     * @param id
     * @param birth
     * @param farmerid
     * @param currentCoordinate
     */
    public Sheep(String id, DateTime birth, int farmerid, Coordinate currentCoordinate) {
        this.id = id;
        this.birth = birth;
        this.alive = true;
        this.farmerid = farmerid;
        cordinates = new ArrayList<Coordinate>();
        attacks = new ArrayList<Coordinate>();
        this.currentCoordinate = currentCoordinate;
    }
    
    /**
     *
     * @return id
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * 
     * @return true hvis denne sau er i live. False hvis den der død. 
     */
    public boolean getAlive() {
        return this.alive;
    }
    
    /**
     * Setter om denne sau er i live eller ikke. 
     * @param status 
     */
    public void setAlive(boolean status) {
        this.alive = status;
    }
    
    /**
     *
     * @return vekt til denne sau. 
     */
    public int getWeight() {
        return this.weight;
    }
    
    /**
     *
     * @param weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    /**
     *
     * @return fødseldato. 
     */
    public DateTime getBirth() {
        return this.birth;
    }
    /**
     *
     * @return sist oppdaterte koordinat. 
     */
    public Coordinate getCurrentCordinate() {
        return currentCoordinate;
    }
    
    /**
     *
     * @param currentCoordinate sist oppdaterte koordinat. 
     */
    public void setCurrentCoordinate(Coordinate currentCoordinate) {
        this.currentCoordinate = currentCoordinate;
    }
    
    
    
    /**
     *
     * @param cordinates Alle koordinater til denne sau. 
     */
    public void setCordinates(List<Coordinate> cordinates) {
        this.cordinates = cordinates;
    }

    /**
     *
     * @return alle koordinater til denne sau. 
     */
    public List<Coordinate> getCordinates() {
        return cordinates;
    }
   
    
    
    /**
     *
     * @return alle angrep til denne sau. 
     */
    public List<Coordinate> getAttacks() {
        return attacks;
    }
    
    /**
     *
     * @param attacks alle angrep til denne sau. 
     */
    public void setAttacks(List<Coordinate> attacks) {
        this.attacks = attacks;
    }
    
    
   
    /**
     *
     * @return farmerid som denne sau er tilknyttet til. 
     */
    public int getFarmerid() {
        return farmerid;
    }
    
    @Override
    public String toString() {
        return "Sheep{" + "id=" + id + ", birth=" + birth + ", alive=" + alive + ", weight=" + weight + ", attack=" + attacks + ", cordinates=" + cordinates + ", farmerid=" + farmerid + ", currentCoordinate=" + currentCoordinate + '}';
    }
    
    
    
    
    
    
}

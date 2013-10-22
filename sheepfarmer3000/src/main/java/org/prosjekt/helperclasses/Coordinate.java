/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.prosjekt.helperclasses.Coordinate;

import sun.util.calendar.Gregorian;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class Coordinate implements Serializable {
    //private final Integer id;
    private double longitude;
    private double latitude;
    private DateTime date;
    private boolean attack;

    public Coordinate(double longitude, double latitude) {
        //Vi trenger en generell maate aa fikse ider til ting.
    	//this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = DateTime.now();
    }
    
    

    public Integer getId() {
        return id;
    }

   

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public boolean isAttack() {
        return attack;
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    
    
}

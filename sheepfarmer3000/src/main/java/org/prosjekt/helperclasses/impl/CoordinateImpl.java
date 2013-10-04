/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses.impl;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.prosjekt.helperclasses.Coordinate;

import sun.util.calendar.Gregorian;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class CoordinateImpl implements Coordinate, Serializable {
    private final Integer id;
    private String longitude;
    private String latitude;
    private DateTime date;
    private boolean attack;

    public CoordinateImpl(Integer id, String longitude, String latitude, DateTime date, boolean attack) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
        this.attack = attack;
    }
    
    

    public Integer getId() {
        return id;
    }

   

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses;

import java.io.Serializable;

import org.joda.time.DateTime;

import sun.util.calendar.Gregorian;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class Coordinate implements Serializable {
	private Integer id;
    private String longitude;
    private String latitude;
    private DateTime date;
    private boolean attack;
    
    public Coordinate() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

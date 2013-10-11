/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses;

import java.io.Serializable;



/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class Coordinate implements Serializable {
    private float longitude;
    private float latitude;
    
    public Coordinate() {
    }
    
    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses;

import java.io.Serializable;
import org.joda.time.DateTime;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;

/**
 *
 * @author Alfredo
 */
public class Coordinate implements ICoordinate, Serializable{
    
    private double latitude;
    private double longitude;
    private DateTime date;
    
    public Coordinate(){
        latitude = -1;
        longitude = -1;
        date = null;        
    }
    
    public Coordinate(double latitude, double longitude, DateTime date){
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }
    
    @Override
    public double getLat(){
        return latitude;
    }
    @Override
    public void setLat(double lat){
        this.latitude = lat;
    }
    @Override
    public double getLon(){
        return longitude;
    }
    @Override
    public void setLon(double lon){
        this.longitude = lon;
    }
    
    public void setTime(DateTime date){
        this.date = date;
    }
    
    public DateTime getDate(){
        return date;
    }

    @Override
    public String toString() {
        return "Coordinate{" + "latitude=" + latitude + ", longitude=" + longitude + ", date=" + date + '}';
    }
    
    
    
}

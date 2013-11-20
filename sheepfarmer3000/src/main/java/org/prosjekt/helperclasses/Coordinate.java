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
public class Coordinate implements ICoordinate, Serializable, Comparable<Coordinate>{
    
    private double latitude;
    private double longitude;
    private DateTime date;
    


    /**
     *
     * @param latitude
     * @param longitude
     */
    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = new DateTime();
    }
    
    
    
    /**
     *
     * @param latitude
     * @param longitude
     * @param date
     */
    public Coordinate(double latitude, double longitude, DateTime date){
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }
    
    /**
     *
     * @return latitude
     */
    @Override
    public double getLat(){
        return latitude;
    }
    /**
     *
     * @param latitude
     */
    @Override
    public void setLat(double lat){
        this.latitude = lat;
    }
    /**
     *
     * @return longitude
     */
    @Override
    public double getLon(){
        return longitude;
    }
    /**
     *
     * @param longitude
     */
    @Override
    public void setLon(double lon){
        this.longitude = lon;
    }
    
    /**
     *
     * @param date
     */
    public void setTime(DateTime date){
        this.date = date;
    }
    
    /**
     *
     * @return date
     */
    public DateTime getDate(){
        return date;
    }

    
    
    @Override
    public String toString() {
        return "Coordinate{" + "latitude=" + latitude + ", longitude=" + longitude + ", date=" + date + '}';
    }

    /**
     * Sammenligner to Coordinate på nyeste dato først.
     * Tar hensyn til millisekund. 
     * @param c
     * @return 
     */
    @Override
    public int compareTo(Coordinate o) {
        return o.date.compareTo(this.date); 
    }
    
    
    
}

package org.prosjekt.staticmaps;

/**
 *
 * @author Alfredo
 */
public class Point {
    private double latitude;
    private double longitude;
    
    /**
     *
     * @param latitude
     * @param longitude
     */
    public Point(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    /**
     *
     * @return
     */
    public double getLatitude(){
        return this.latitude;
    }
    
    /**
     *
     * @return
     */
    public double getLongitude(){
        return this.longitude;
    }
    
    /**
     *
     * @param point
     * @return
     */
    public boolean equals(Point point){
        if (this.latitude == point.latitude &&
                this.longitude == point.longitude)
            return true;
        else 
            return false;
    }
    
    @Override
    public String toString(){
        return String.format("%s,%s", this.latitude, this.longitude);
    }
    
}


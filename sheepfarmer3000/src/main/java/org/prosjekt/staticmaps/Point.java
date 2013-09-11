package org.prosjekt.staticmaps;

/**
 *
 * @author Alfredo
 */
public class Point {
    private float latitude;
    private float longitude;
    
    /**
     *
     * @param latitude
     * @param longitude
     */
    public Point(float latitude, float longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    /**
     *
     * @return
     */
    public float getLatitude(){
        return this.latitude;
    }
    
    /**
     *
     * @return
     */
    public float getLongitude(){
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


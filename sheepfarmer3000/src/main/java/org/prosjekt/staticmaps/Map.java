
package org.prosjekt.staticmaps;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used to store all the information needed
 * to request a map from the google maps api.
 * 
 * 
 */

public class Map {
    
    private Point center;
    private int sizeX, sizeY, zoom;
    private HashMap<String, MarkerList> markerList;
    private Path path;
    
    /**
     *
     * @param center
     * @param zoom
     * @param sizeX
     * @param sizeY
     */
    public Map(Point center, int zoom, int sizeX, int sizeY){
        this.center = center;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.zoom = zoom;
        this.markerList = new HashMap<String, MarkerList>();
        this.markerList.put("default", new MarkerList("default", "default", "default"));
    }
    
    /**
     *
     * @param path
     */
    public void addPath(Path path){
        this.path = path;
    }
    
    /**
     *
     * @param key
     */
    public void addMarkerList(String key){
        this.markerList.put(key, new MarkerList());
    }
    
    /**
     *
     * @param point
     */
    public void addMarker(Point point){
        this.markerList.get("default").addMarker(point);
    }
    
    /**
     *
     * @param point
     * @param markerListKey
     */
    public void addMarker(Point point, String markerListKey){
        this.markerList.get(markerListKey).addMarker(point);
    }
    
    /**
     *
     * @param points
     */
    public void addMarkers(ArrayList<Point> points){
        this.markerList.get("default").addMarkers(points);
    }
    
    /**
     *
     * @param points
     * @param markerListKey
     */
    public void addMarkers(ArrayList<Point> points, String markerListKey){
        this.markerList.get(markerListKey).addMarkers(points);
    }
    
    /**
     *
     * @param point
     */
    public void removeMarker(Point point){
        this.markerList.get("default").removeMarker(point);
    }
    
    /**
     *
     * @param point
     * @param markerListKey
     */
    public void removeMarker(Point point, String markerListKey){
        this.markerList.get("default").removeMarker(point);
    }
    
    /**
     *
     * @param points
     */
    public void removeMarkers(ArrayList<Point> points){
        this.markerList.get("default").removeMarkers(points);
    }
    
    /**
     *
     * @param points
     * @param markerListKey
     */
    public void removeMarkers(ArrayList<Point> points, String markerListKey){
        this.markerList.get(markerListKey).removeMarkers(points);
    }
    
    public String toString(){
        String address = String.format
                ("http://maps.googleapis.com/maps/api/staticmap?center=%s&zoom=%s&size=%sx%s"
                , center, this.zoom, this.sizeX, this.sizeY);
        
        for (String key : markerList.keySet())
            address += markerList.get(key);
        
        if (this.path != null)
            address += this.path;
        
        address += "&sensor=false";
        return address;
    }
    
}



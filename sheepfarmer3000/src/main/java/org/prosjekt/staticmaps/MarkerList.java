
package org.prosjekt.staticmaps;

import java.util.ArrayList;

/**
 * This class contains a list of markers, where they all share
 * the same settings size, color and label. It also contains
 * the settings themselves.
 * 
 * 
 */
public class MarkerList {
    
    /**
     *
     */
    protected final String DEFAULT = "default";
    /**
     *
     */
    protected ArrayList<Point> markers;
    private String markerSize = "mid";
    private String markerColor = "0xFF0000";
    private String markerLabel = "S";
    
    /**
     * Class creator, set any of the parameters as to DEFAULT
     * to get default values.
     * 
     */
    
    public MarkerList(){
        markers = new ArrayList<Point>();
    }
    
    /**
     *
     * @param markerSize
     * @param markerColor
     * @param markerLabel
     */
    public MarkerList (String markerSize, String markerColor, String markerLabel){
         this.markers = new ArrayList<Point>();
         if (!markerSize.equals(DEFAULT))
             this.markerSize = markerSize;
         if (!markerColor.equals(DEFAULT))
             this.markerColor = markerColor;
         if (!markerLabel.equals(DEFAULT))
             this.markerLabel = markerLabel;
    }
    
    /**
     *
     * @param size
     */
    public void setSize(String size){
        this.markerSize = size;
    }
    
    /**
     *
     * @param color
     */
    public void setColor(String color){
        this.markerColor = color;
    }
    
    /**
     *
     * @param label
     */
    public void setLabel(String label){
        this.markerLabel = label;
    }
    
    /**
     * Takes in a Point and adds it to the list of points.
     * 
     * @param point 
     */
    
    public void addMarker(Point point){
        markers.add(point);
    }
    
    /**
     * Takes in an ArrayList<Point> and adds its content to the
     * list of points.
     * 
     * @param points 
     */
    
    public void addMarkers(ArrayList<Point> points){
        for (Point point : points)
            addMarker(point);
    }
    
    /**
     * Takes in a Point, if this point is found in the list it is removed.
     * 
     * @param point 
     */
    
    public void removeMarker(Point point){
        for (Point marker : markers){
            if (marker.equals(point)){
                markers.remove(marker);
                return;
            }
        }
    }
    
    /**
     * Takes in a ArrayList<Point> and if any of the points contained in
     * the supplied list is found in the list of points they are removed.
     * 
     * @param points 
     */
    
    public void removeMarkers(ArrayList<Point> points){
        for (Point point : points)
            removeMarker(point);
    }
    
    /**
     * Returns a string containing all the information for these markers
     * formatted as required by the google api.
     * 
     * @return 
     */
    
    public String toString(){
        
        if (markers.isEmpty())
            return "";
        
        String result = String.format("&markers=size:%s|color:%s|label:%s"
                , this.markerSize, this.markerColor, this.markerLabel);
        
        for (Point marker : markers)
            result += "|" + marker;
        
        return result;
    }
    
}

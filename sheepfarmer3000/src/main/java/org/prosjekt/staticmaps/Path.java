
package org.prosjekt.staticmaps;

import java.util.ArrayList;

/**
 * This class is used to store path attributes
 * along with the points defining the path.
 * 
 */

public class Path extends MarkerList{
    private int weight = 5; //Default weight for paths in the gmaps api;
    private String color = "0x0000ff80"; //Blue with 50% opacity;
    private String fillColor = "0xff000040"; //Red with 25% opacity;
    
    /**
     * Class creator, creates class with default values.
     */
    
    public Path (){
        
    }
    
    /**
     *  Class creator, takes as argument the weight, color and fillcolor.
     *  Some default values are defined, see below for more info.
     * 
     * @param weight set as -1 to use default value (5)
     * @param color set as "default" to use default value ("0x0000ff80")
     * @param fillColor set as "default" to use default value ("0xff000040")
     */
            
    public Path (int weight, String color, String fillColor){
        if (weight != -1)
            this.weight = weight;
        if (!color.equals("default"))
            this.color = color;
        if (!fillColor.equals("default"))
            this.fillColor = color;
    }
    
    /**
     * Creates a string containing all the information necessary
     * for this path as required by the google api.
     *  
     */
    
    @Override    
    public String toString(){
        String result = String.format("&path=weight:%s|color:%s|fillcolor:%s"
                , this.weight, this.color, this.fillColor);        
        for (Point point : markers)
            result += "|" + point; 
        
        return result;
    }
    
}

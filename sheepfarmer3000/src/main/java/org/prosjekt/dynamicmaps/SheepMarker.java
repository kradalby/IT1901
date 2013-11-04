package org.prosjekt.dynamicmaps;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.prosjekt.helperclasses.Sheep;

/**
 * This class is used to represent sheep as markers in the JMapViewer.
 * 
 */
public class SheepMarker extends CustomMapMarker{
    
    /**
     * Default constructor, does nothing and should never be used.
     */
    public SheepMarker(){
        super();
    }
    
    /**
     * Class creator
     * 
     * @param sheep
     */
    public SheepMarker(Sheep sheep) {
        super();
        fileLocation = "C:\\Users\\Alfredo\\Documents\\NetBeansProjects\\New Folder\\TestProject\\sheep.png";
        try{
            img = ImageIO.read(new File(fileLocation));
        }
        catch(IOException e){
            img = null;
        }
        this.lon = sheep.getCurrentCordinate().getLon();
        this.lat = sheep.getCurrentCordinate().getLat();
        this.id = sheep.getId();
    }
}

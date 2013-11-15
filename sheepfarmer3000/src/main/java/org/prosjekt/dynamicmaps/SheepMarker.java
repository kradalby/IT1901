package org.prosjekt.dynamicmaps;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.prosjekt.client.ClientService;
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
     * Returns a new SheepMarker instance for the given sheep.
     * 
     * @param sheep
     */
    public SheepMarker(Sheep sheep) {
        super();
         final URL url = Thread.currentThread().getContextClassLoader().getResource("images/sheep.png");
        try{
            img = ImageIO.read(url);
        }
        catch(IOException e){
            img = null;
        }
        this.lon = sheep.getCurrentCordinate().getLon();
        this.lat = sheep.getCurrentCordinate().getLat();
        this.id = sheep.getId();
    }
}

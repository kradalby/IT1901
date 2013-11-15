package org.prosjekt.dynamicmaps;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.prosjekt.client.ClientService;
import org.prosjekt.helperclasses.Coordinate;


/**
 * This class is used to represent attacks on the map.
 * They are represented with a red crossmark.
 * 
 * @author Alfredo
 */
public class AttackMarker extends CustomMapMarker{
    
    /**
     * Returns a new AttackMarker instance.
     * 
     * @param coord
     * @param id
     */
    public AttackMarker(Coordinate coord, String id){
        super();
        final URL url = Thread.currentThread().getContextClassLoader().getResource("images/crossmark.png");
        try{
            img = ImageIO.read(url);
        }
        catch(IOException e){
            img = null;
        }
        this.lon = coord.getLon();
        this.lat = coord.getLat();
        this.id = id;
    }
}

package org.prosjekt.dynamicmaps;

import java.io.File;
import java.io.IOException;
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
         fileLocation = ClientService.getPathToResources("crossmark.png");
        try{
            img = ImageIO.read(new File(fileLocation));
        }
        catch(IOException e){
            img = null;
        }
        this.lon = coord.getLon();
        this.lat = coord.getLat();
        this.id = id;
    }
}

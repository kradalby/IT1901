/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.dynamicmaps;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.prosjekt.client.ClientService;
import org.prosjekt.helperclasses.Coordinate;


/**
 *
 * @author Alfredo
 */
public class AttackMarker extends CustomMapMarker{
    
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

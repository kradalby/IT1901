
package org.prosjekt.dynamicmaps;

import java.awt.event.MouseEvent;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;

/**
 *
 * @author Alfredo
 */
public class CustomMapController extends DefaultMapController{
    
    CustomMapViewer parentMap;
    
    public CustomMapController(CustomMapViewer map){
        super(map);
        parentMap = map;
        setMovementMouseButton(MouseEvent.BUTTON1);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        super.mouseClicked(e);
        parentMap.mouseEventRegistered(e);
    }
    
}

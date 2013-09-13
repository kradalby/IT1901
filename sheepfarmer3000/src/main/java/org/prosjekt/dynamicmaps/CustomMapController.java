
package org.prosjekt.dynamicmaps;

import java.awt.event.MouseEvent;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;

/**
 *
 * @author Alfredo
 */
public class CustomMapController extends DefaultMapController{
    
    CustomMapViewer customMap;
    
    public CustomMapController(CustomMapViewer map){
        super(map);
        customMap = map;
        setMovementMouseButton(MouseEvent.BUTTON1);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        super.mouseClicked(e);
        
        if (e.getButton() == MouseEvent.BUTTON3){
            customMap.rightClickPopup(e.getX(), e.getY());
        }
    }
    
}

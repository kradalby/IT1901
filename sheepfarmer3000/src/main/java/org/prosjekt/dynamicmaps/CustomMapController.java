
package org.prosjekt.dynamicmaps;

import java.awt.event.MouseEvent;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;
import org.prosjekt.client.Gui;

/**
 *
 * @author Alfredo
 */
public class CustomMapController extends DefaultMapController{
    
    CustomMapViewer parentMap;
    private static Gui gui;
    private int PopoupMouseButton = MouseEvent.BUTTON3;
    
    public CustomMapController(CustomMapViewer map){
        super(map);
        parentMap = map;
        gui= this.parentMap.getGui();
        setMovementMouseButton(MouseEvent.BUTTON1);
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
        super.mouseReleased(e);
        if (e.getButton() == this.PopoupMouseButton){
            gui.createPopup();
        }
    }
  
    
    public void setPopoupMouseButton(int newButton){
        this.PopoupMouseButton = newButton;
    }
    
    public int getPopupMouseButton(){
        return this.PopoupMouseButton;
    }
    
}

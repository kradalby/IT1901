
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
            int currentId = parentMap.getClickedSheep(e.getPoint());
            if (currentId == -1)
                gui.createPopup(e.getX(), e.getY());
            else
                gui.createPopup(e.getX(), e.getY(), currentId);
        }          
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        if (isDoubleClickZoomEnabled() && e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1){
            int clickedSheepId = parentMap.getClickedSheep(e.getPoint());
            if (clickedSheepId == -1)
                parentMap.zoomIn(e.getPoint());
            else if(clickedSheepId >= 0){
                parentMap.zoomIn(e.getPoint());
                parentMap.zoomIn(e.getPoint());
                parentMap.zoomIn(e.getPoint());
            }
        }
    }
  
    
    public void setPopoupMouseButton(int newButton){
        this.PopoupMouseButton = newButton;
    }
    
    public int getPopupMouseButton(){
        return this.PopoupMouseButton;
    }
    
}


package org.prosjekt.dynamicmaps;

import java.awt.Point;
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
            SheepMarker clickedSheep = parentMap.getClickedSheep(e.getPoint());
            if (clickedSheep == null)
                gui.createPopup(e.getX(), e.getY());
            else
                gui.createPopup(e.getX(), e.getY(), clickedSheep.getId());
        }
    }
    
    /*
     * This method will zoom into a sheep if it is doubleclicked
     */
    
    @Override
    public void mouseClicked(MouseEvent e){
        if (isDoubleClickZoomEnabled() && e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1){
            SheepMarker clickedSheep = parentMap.getClickedSheep(e.getPoint());
            if (clickedSheep == null){
                parentMap.zoomIn(e.getPoint());
            }
            else {
                int nextZoom = parentMap.getZoom();
                parentMap.setDisplayPositionByLatLon(clickedSheep.getLat(), clickedSheep.getLon(), parentMap.getTileController().getTileSource().getMaxZoom() - 2);
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

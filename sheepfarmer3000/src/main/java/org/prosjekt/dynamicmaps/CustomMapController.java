
package org.prosjekt.dynamicmaps;

import java.awt.event.MouseEvent;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;

/**
 * 
 * Handles the event listeners from
 * its parent CustomMapViewer.
 * 
 * @author Alfredo
 */
public class CustomMapController extends DefaultMapController{
    
    CustomMapViewer parentMap;
    private int PopoupMouseButton = MouseEvent.BUTTON3;
    
    /**
     * Changes the default button used
     * to move the map to MouseEvent.BUTTON1(left mouse button)
     * and returns a new CustomMapController instance.
     * 
     * @param map
     */
    public CustomMapController(CustomMapViewer map){
        super(map);
        parentMap = map;
        setMovementMouseButton(MouseEvent.BUTTON1);
    }
    
    /**
     * Handles clicks with the current
     * PopupMouseButton(right mouse button).
     * 
     * @param e 
     */
    
    @Override
    public void mouseReleased(MouseEvent e){
        super.mouseReleased(e);
        if (e.getButton() == this.PopoupMouseButton){
            CustomMapMarker clickedMarker = parentMap.getClickedMarker(e.getPoint());
            if (clickedMarker == null)
                parentMap.createPopup(e.getX(), e.getY());
            else
                parentMap.createPopup(e.getX(), e.getY(), clickedMarker);
        }
    }
    
    /**
     * Handles double clicks.
     * 
     * @param e 
     */
    
    @Override
    public void mouseClicked(MouseEvent e){
        if (isDoubleClickZoomEnabled() && e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1){
            CustomMapMarker clickedMarker = parentMap.getClickedMarker(e.getPoint());
            if (clickedMarker == null){
                parentMap.zoomIn(e.getPoint());
            }
            else {
                int nextZoom = parentMap.getZoom();
                parentMap.setDisplayPositionByLatLon(clickedMarker.getLat(), clickedMarker.getLon(), parentMap.getTileController().getTileSource().getMaxZoom() - 2);
            }
        }
    }
  
    
    /**
     * Changes the popup button.
     *
     * @param newButton
     */
    public void setPopoupMouseButton(int newButton){
        this.PopoupMouseButton = newButton;
        return;
    }
    
    /**
     * Returns the popup button.
     *
     * @return
     */
    public int getPopupMouseButton(){
        return this.PopoupMouseButton;
    }
    
}

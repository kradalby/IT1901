
package org.prosjekt.dynamicmaps;

import java.awt.event.MouseEvent;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;

/**
 * 
 * This class handles the event listeners from
 * its parent CustomMapViewer.
 * 
 * @author Alfredo
 */
public class CustomMapController extends DefaultMapController{
    
    CustomMapViewer parentMap;
    private int PopoupMouseButton = MouseEvent.BUTTON3;
    
    /**
     * Class constructor, changes the default button used
     * to move the map to MouseEvent.BUTTON1(left mouse button)
     * 
     * @param map
     */
    public CustomMapController(CustomMapViewer map){
        super(map);
        parentMap = map;
        setMovementMouseButton(MouseEvent.BUTTON1);
    }
    
    /**
     * This method handles clicks with the current
     * PopupMouseButton(right mouse button). If a sheep is
     * clicked on, then a popup with its ID is requested
     * from the parentMap. If not, a popup with no ID is requested.
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
     * This method handles double clicks which are used to
     * zoom in. If a sheep is double clicked, then the map
     * is zoomed in more than than usual and centered on 
     * that sheep. If no sheep was double clicked, the zoom
     * level is increased by one.
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
     *
     * @param newButton
     */
    public void setPopoupMouseButton(int newButton){
        this.PopoupMouseButton = newButton;
        return;
    }
    
    /**
     *
     * @return
     */
    public int getPopupMouseButton(){
        return this.PopoupMouseButton;
    }
    
}

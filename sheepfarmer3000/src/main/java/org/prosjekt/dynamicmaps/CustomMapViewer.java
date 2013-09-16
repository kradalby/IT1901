
package org.prosjekt.dynamicmaps;

import javax.swing.JPopupMenu;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MemoryTileCache;
//import org.openstreetmap.gui.jmapviewer.JMapViewer;
//import org.openstreetmap.gui.jmapviewer.MemoryTileCache;

/**
 *
 * @author Alfredo
 */
   public class CustomMapViewer extends JMapViewer{
    private CustomMapController mapController;
    private Map ownerMap;
    
    public CustomMapViewer(){
        super(new MemoryTileCache(), 8);
        mapController = new CustomMapController(this);
    }
    
    public CustomMapViewer(Map ownerMap){
        this();
        this.ownerMap = ownerMap;
    }
    
    public void rightClickPopup(int x, int y){
        JPopupMenu popup = new JPopupMenu("Options");
    }
}

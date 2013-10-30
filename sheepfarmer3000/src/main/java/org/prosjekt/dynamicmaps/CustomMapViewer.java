package org.prosjekt.dynamicmaps;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.MemoryTileCache;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import org.prosjekt.client.Gui;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Alfredo
 */
public class CustomMapViewer extends JMapViewer{
    
    private final int DEFAULT_SIZE_X = 800;
    private final int DEFAULT_SIZE_Y = 600;
    private static Gui gui;
    private List<SheepMarker> sheepMarkerList;
    
    /**
     * Class constructor
     * 
     * @param gui
     */
    public CustomMapViewer(Gui gui){
        super(new MemoryTileCache(), 8);
        CustomMapViewer.gui = gui;
        sheepMarkerList = new ArrayList<>();
        setSize(DEFAULT_SIZE_X, DEFAULT_SIZE_Y);
    }
    
    /**
     * 
     * @return
     */
    public final Gui getGui(){
        return CustomMapViewer.gui;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (SheepMarker marker : sheepMarkerList){
            paintMarker(g, marker);
        }
        setDisplayToFitMapElements(false, false, true);
    }
        
    /**
     *
     * @param id
     */
    public void removeSheepMarker(String id){
        for (SheepMarker marker : sheepMarkerList){
            if (marker.getId().equals(id)){
                sheepMarkerList.remove(marker);
            }
        }
    }
    
    /**
     *
     * @param ids
     */
    public void removeSheepMarkers(ArrayList<String> ids){
        for (String id : ids){
            removeSheepMarker(id);
        }
    }
    
    /**
     *
     */
    @Override
    public void removeAllMapMarkers(){
        sheepMarkerList.clear();
        repaint();
    }
    
    /**
     *
     * @param coords
     */
    public void addPath(ArrayList<Coordinate> coords){
        if (coords.isEmpty())
            return;
        addMapPolygon(new MapPolygonImpl(coords));
    }
    
    /**
     *
     * @param coords
     */
    public void removePath(ArrayList<Coordinate> coords){
        for (MapPolygon polygon : mapPolygonList){
            if (coords.equals(polygon.getPoints()))
                removeMapPolygon(polygon);
        }
    }
    
    /**
     *
     */
    public void removeAllPaths(){
        removeAllMapPolygons();
    }
    
    /**
     * This method iterates over sheepMarkerList and checks
     * if they contain Point p, if they do then that sheepMarker
     * object is returned.
     *
     * 
     * @param p
     * @return
     */
    public SheepMarker getClickedSheep(Point p){
        for (SheepMarker sheepMarker : sheepMarkerList){
            SheepMarker sheep = sheepMarker;
            if (sheep.containsPoint(p))
                    return sheep;
        }
        return null;
    }
    
    /**
     *
     * @param sheep
     */
    public void addSheep(Sheep sheep){
        SheepMarker sMarker = new SheepMarker(sheep);
        sheepMarkerList.add(sMarker);
        repaint();
    }    
}

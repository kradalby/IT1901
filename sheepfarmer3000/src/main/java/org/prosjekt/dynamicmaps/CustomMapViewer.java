package org.prosjekt.dynamicmaps;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.MemoryTileCache;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
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
    private CustomMapController mapController;
    
    public CustomMapViewer(Gui gui){
        super(new MemoryTileCache(), 8);
        this.gui = gui;
        sheepMarkerList = new ArrayList<>();
        mapController = new CustomMapController(this);
        setSize(DEFAULT_SIZE_X, DEFAULT_SIZE_Y);
        //setDisplayToFitMapElements(false, false, true);
    }
    
    public final Gui getGui(){
        return this.gui;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (SheepMarker marker : sheepMarkerList){
            paintMarker(g, marker);
        }
    }
        
    public void removeSheepMarker(String id){
        for (SheepMarker marker : sheepMarkerList){
            if (marker.getId().equals(id)){
                sheepMarkerList.remove(marker);
            }
        }
    }
    
    public void removeSheepMarkers(ArrayList<String> ids){
        for (String id : ids){
            removeSheepMarker(id);
        }
    }
    
    @Override
    public void removeAllMapMarkers(){
        sheepMarkerList.clear();
        repaint();
    }
    
    public void addPath(ArrayList<Coordinate> coords){
        if (coords.isEmpty())
            return;
        addMapPolygon(new MapPolygonImpl(coords));
    }
    
    public void removePath(ArrayList<Coordinate> coords){
        for (MapPolygon polygon : mapPolygonList){
            if (coords.equals(polygon.getPoints()))
                removeMapPolygon(polygon);
        }
    }
    
    public void removeAllPaths(){
        removeAllMapPolygons();
    }
    
    public SheepMarker getClickedSheep(Point p){
        for (SheepMarker sheepMarker : sheepMarkerList){
            SheepMarker sheep = sheepMarker;
            if (sheep.containsPoint(p))
                    return sheep;
        }
        return null;
    }
    
    public void addSheep(Sheep sheep){
        SheepMarker sMarker = new SheepMarker(sheep);
        sheepMarkerList.add(sMarker);
        repaint();
    }    
}

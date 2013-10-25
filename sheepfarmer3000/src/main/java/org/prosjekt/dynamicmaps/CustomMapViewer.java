package org.prosjekt.dynamicmaps;

import java.awt.Point;
import java.util.ArrayList;
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
    private CustomMapController mapController;
    
    public CustomMapViewer(Gui gui){
        super(new MemoryTileCache(), 8);
        this.gui = gui;
        mapController = new CustomMapController(this);
        setSize(DEFAULT_SIZE_X, DEFAULT_SIZE_Y);
        //setDisplayToFitMapElements(false, false, true);
    }
    
    public final Gui getGui(){
        return this.gui;
    }
    
    public void removeMarker(Coordinate coord){
        for (MapMarker marker : mapMarkerList){
            if (marker.getCoordinate().equals(coord))
                removeMapMarker(marker);
        }
    }
    
    public void removeMarkers(ArrayList<Coordinate> coords){
        for (Coordinate coord : coords)
            removeMarker(coord);
    }
    
    public void removeAllMarkers(){
        removeAllMapMarkers();
    }
    
    public void addPath(ArrayList<Coordinate> coords){
        addMapPolygon(new MapPolygonImpl(coords));
    }
    
    public void removePath(ArrayList<ICoordinate> coords){
        for (MapPolygon polygon : mapPolygonList){
            if (coords.equals(polygon.getPoints()))
                removeMapPolygon(polygon);
        }
    }
    
    public void removeAllPaths(){
        removeAllMapPolygons();
    }
    
    public int getClickedSheep(Point p){
        for (MapMarker marker : mapMarkerList){
            SheepMarker sheep = (SheepMarker) marker;
            if (sheep.containsPoint(p))
                    return sheep.getId();
        }
        return -1;
    }
    
    public void addSheep(Sheep sheep){
        addMapMarker(new SheepMarker(sheep));
    }
    
    public void removeSheep(int id){
        for (MapMarker marker : mapMarkerList){
            SheepMarker sheep = (SheepMarker) marker;
            if (sheep.getId() == id){
                removeMapMarker(sheep);
            }
        }
    }
    
}

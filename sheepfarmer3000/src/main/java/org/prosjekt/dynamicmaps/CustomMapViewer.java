package org.prosjekt.dynamicmaps;

import java.util.ArrayList;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.MemoryTileCache;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import org.prosjekt.client.Gui;

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
        setDisplayToFitMapElements(false, false, true);
    }
    
    public final Gui getGui(){
        return this.gui;
    }
    
    public void addMarker(Coordinate coord){
        addMapMarker(new MapMarkerDot(coord));
    }
    
    public void addMarkers(ArrayList<Coordinate> coords){
        for (Coordinate coord : coords)
            addMarker(coord);
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
    
    public void addSheep(Coordinate coord, int id){
        addMapMarker(new SheepMarker(coord, id));
    }
    
    public void removeSheep(int id){
        for (SheepMarker sheep : mapMarkerList){
            if (sheep.getId() == id){
                removeMapMarker(sheep);
            }
        }
    }
    
}

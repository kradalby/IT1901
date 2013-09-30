package org.prosjekt.dynamicmaps;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.MemoryTileCache;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

/**
 *
 * @author Alfredo
 */
public class CustomMapViewer extends JMapViewer{
    
    private final int DEFAULT_SIZE = 500;
    
    private CustomMapController mapController;
    
    public CustomMapViewer(){
        super(new MemoryTileCache(), 8);
        mapController = new CustomMapController(this);
        setSize(DEFAULT_SIZE, DEFAULT_SIZE);
        setDisplayToFitMapElements(false, false, true);
    }
    
    public void mouseEventRegistered(MouseEvent e){
        MapKit.mouseEventRegistered(e);
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
    
}
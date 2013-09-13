package org.prosjekt.dynamicmaps;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

/**
 *
 * @author Alfredo
 */
public class Map {
    
    private CustomMapViewer map;
    private Coordinate center;
    private int sizeX, sizeY, zoom;
    private ArrayList<MapMarker> markers;
    private ArrayList<MapPolygon> paths;
    private CustomMapController mapController;
    
    public Map(Coordinate center, int zoom, int sizeX, int sizeY){
        this.center = center;
        this.zoom = zoom;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.map = new CustomMapViewer();
        this.mapController = new CustomMapController(map);
        mapController.setMovementMouseButton(MouseEvent.BUTTON1);
        this.markers = new ArrayList<MapMarker>();
        this.paths = new ArrayList<MapPolygon>();
    }
    
    public void addMarker(Coordinate coord){
        markers.add(new MapMarkerDot(coord));
    }
    
    public void addMarkers(ArrayList<Coordinate> coords){
        for (Coordinate coord : coords)
            addMarker(coord);
    }
    
    public void removeMarker(Coordinate coord){
        for (MapMarker marker : markers){
            if (marker.getCoordinate().equals(coord))
                markers.remove(marker);
        }
    }
    
    public void removeMarkers(ArrayList<Coordinate> coords){
        for (Coordinate coord : coords)
            removeMarker(coord);
    }
    
    public void removeAllMarkers(){
        markers.clear();
    }
    
    public void addPath(ArrayList<Coordinate> coords){
        paths.add(new MapPolygonImpl(coords));
    }
    
    public void removePath(ArrayList<ICoordinate> coords){
        for (MapPolygon polygon : paths){
            if (coords.equals(polygon.getPoints()))
                map.removeMapPolygon(polygon);
        }
    }
    
    public void removeAllPaths(){
        paths.clear();
    }
    
    public void refresh(){
        map.removeAllMapMarkers();
        map.removeAllMapPolygons();
        map.setMapMarkerList(markers);
        map.setMapPolygonList(paths);
//        if (markers.isEmpty() && paths.isEmpty())
            map.setDisplayPositionByLatLon(center.getLat(), center.getLon(), zoom);
//       else
//            map.setDisplayToFitMapElements(true, false, true);
        map.setSize(sizeX, sizeX);
    }
    
    public JPanel getJPanel(){
        return this.map;
    }
}

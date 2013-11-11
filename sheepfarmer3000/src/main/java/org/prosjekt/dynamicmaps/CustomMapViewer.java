package org.prosjekt.dynamicmaps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.MemoryTileCache;
import org.prosjekt.gui.AddSheep;
import org.prosjekt.gui.RemoveSheep;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Alfredo
 */
public class CustomMapViewer extends JMapViewer{
    
    private final int DEFAULT_SIZE_X = 800;
    private final int DEFAULT_SIZE_Y = 600;
    private boolean created = false;
    private static Farmer farmer;
    private CustomMapController mapController;
    private List<CustomMapMarker> customMapMarkerList;
    
    /**
     * Class constructor
     * 
     */
    public CustomMapViewer(Farmer farmer){
        super(new MemoryTileCache(), 8);
        customMapMarkerList = new ArrayList<>();
        setSize(DEFAULT_SIZE_X, DEFAULT_SIZE_Y);
        mapController = new CustomMapController(this);
        CustomMapViewer.farmer = farmer;
        this.refreshMap();
        this.showAllSheep();
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (CustomMapMarker marker : customMapMarkerList){
            paintMarker(g, marker);
        }
        if (!created){
            setDisplayToFitMapElements(false, false, true);
        created = true;
        }
    }
    
    /**
     *
     */
    private void clearMap(){
        customMapMarkerList.clear();
        removeAllMapMarkers();
        removeAllMapPolygons();
        repaint();
    }
    
    /**
     *
     * @param coords
     */
    public void addArea(ArrayList<Coordinate> coords){
        if (coords == null || coords.isEmpty())
            return;
        addMapPolygon(new MapPolygonImpl(coords));
    }
    
    public void addPath(List<Coordinate> coords){
        if (coords == null || coords.isEmpty())
                return;
        List<Coordinate> reversedCoords = new ArrayList<>(coords);
        Collections.reverse(reversedCoords);
        reversedCoords.remove(0);
        coords.addAll(reversedCoords);
        MapPolygonImpl pol = new MapPolygonImpl(coords);
        pol.setColor(Color.red);
        addMapPolygon(pol);
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
    public CustomMapMarker getClickedMarker(Point p){
        for (CustomMapMarker mapMarker : customMapMarkerList){
            if (mapMarker.containsPoint(p))
                    return mapMarker;
        }
        return null;
    }
    
    /**
     *
     * @param sheep
     */
    public void addSheep(Sheep sheep){
        SheepMarker sMarker = new SheepMarker(sheep);
        customMapMarkerList.add(sMarker);
        repaint();
    }     
    
    public void addAllAttacks(){
        for (Sheep sheep : farmer.getSheeps()){
            for(Coordinate coord : sheep.getAttacks()){
                customMapMarkerList.add(new AttackMarker(coord, sheep.getId()));
            }
        }
    }
    
    public void addAttack(String sheepId){
        for (Sheep sheep : farmer.getSheeps()){
            if (sheep.getId().equals(sheepId)){
                for (Coordinate coord : sheep.getAttacks()){
                    customMapMarkerList.add(new AttackMarker(coord, sheep.getId()));
                }
            }
        }
    }
    
    public final void showAllSheep(){
        for (Sheep sheep : farmer.getSheeps()){
            this.addSheep(sheep);
        }
    }
    
    public final void refreshMap(){
        this.clearMap();
        this.addArea((ArrayList<Coordinate>)farmer.getCoordinates());
    }
    
    public void createPopup(int x, int y){
        new AddSheep(farmer, getPosition(x, y).getLat(), getPosition(x, y).getLon());
    }
    public void createPopup(int x, int y, CustomMapMarker marker){
        int sheepIndex =0;
        for (int i = sheepIndex; i < farmer.getSheeps().size(); i++){
            if (farmer.getSheeps().get(i).getId().equals(marker.getId())){
                sheepIndex = i;
                break;
            }
        }
        new RemoveSheep(farmer, sheepIndex);
        
    }
    
}

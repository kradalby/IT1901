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
 * This class is used to store the data needed
 * for the map and it is also responsible for
 * drawing the map.
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
    private Sheep showSheepPath = null;
    private boolean showAllAttacks;
    private boolean showAllSheeps;
    
    /**
     * Returns a new CustomMapViewer instance and binds a
     * CustomMapController object to the map. By default
     * the map shows all sheep and the area of the farmer.
     * 
     * @param farmer 
     */
    public CustomMapViewer(Farmer farmer){
        super(new MemoryTileCache(), 8);
        customMapMarkerList = new ArrayList<>();
        setSize(DEFAULT_SIZE_X, DEFAULT_SIZE_Y);
        mapController = new CustomMapController(this);
        CustomMapViewer.farmer = farmer;
        showAllAttacks = false;
        showAllSheeps = true;
        this.refreshMap();
    }
    
    /*
     * Loops throught the lists of the different
     * map objects and calls their paintMarker()
     * method, effectively drawing them on the map.
     */
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
     * Clears all lists of map objects and repaints
     * the map.
     */
    private void clearMap(){
        customMapMarkerList.clear();
        removeAllMapMarkers();
        removeAllMapPolygons();
        repaint();
    }
    
    /**
     * Sets the area of the farmer to the given
     * coordinates.
     * 
     * @param coords
     */
    private void setArea(ArrayList<Coordinate> coords){
        if (coords == null || coords.isEmpty())
            return;
        addMapPolygon(new MapPolygonImpl(coords));
    }
    
    /*
     * Creates a path from the first coordinate
     * to the last one.
     * 
     */
    private void addPath(Sheep sheep){
        List<Coordinate> coords = sheep.getCordinates();
        if (coords == null || coords.isEmpty())
                return;
        List<Coordinate> reversedCoords = new ArrayList<>(coords);
        Collections.reverse(reversedCoords);
        reversedCoords.remove(0);
        coords.addAll(reversedCoords);
        MapPolygonImpl pol = new MapPolygonImpl(coords);
        pol.setColor(Color.red);
        addMapPolygon(pol);
        addAttack(sheep);     
        addSheep(sheep);
    }
    
    /**
     * This method iterates over all CustomMapMarkers and checks
     * if they contain Point p, if they do then the CustomMapMarker
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
     * Adds a new Sheep marker to the map.
     * 
     * @param sheep
     */
    private void addSheep(Sheep sheep){
        SheepMarker sMarker = new SheepMarker(sheep);
        customMapMarkerList.add(sMarker);
        repaint();
    }     
    
    /*
     * Loops through all the farmer's sheeps and
     * adds all their attacks to the map.
     */
    private void addAllAttacks(){
        for (Sheep sheep : farmer.getSheeps()){
            for(Coordinate coord : sheep.getAttacks()){
                customMapMarkerList.add(new AttackMarker(coord, sheep.getId()));
            }
        }
    }
    /*
     * Adds to the map the attacks to the given sheep.
     */
    private void addAttack(Sheep attackedSheep){
        String sheepId = attackedSheep.getId();
        for (Sheep sheep : farmer.getSheeps()){
            if (sheep.getId().equals(sheepId)){
                for (Coordinate coord : sheep.getAttacks()){
                    customMapMarkerList.add(new AttackMarker(coord, sheep.getId()));
                }
            }
        }
    }
    
    /*
     * Adds all the farmer's sheep to the map.
     */
    private final void addAllSheep(){
        for (Sheep sheep : farmer.getSheeps()){
            this.addSheep(sheep);
        }
    }
    
    /**
     * Sets the sheep who's path will be shown.
     * If sheep is null then no path will be shown.
     * 
     * @param sheep
     */
    public void showSheepPath(Sheep sheep){
        this.showSheepPath = sheep;
    }
    
    /**
     * If value is true then all attacks are shown,
     * if it is false then no attacks are shown.
     * 
     * @param value
     */
    public void showAllAttacks(boolean value){
        this.showAllAttacks = value;
    }
    
    /**
     * If value is true then all sheep will be shown,
     * if it is false then no sheep with be shown.
     * 
     * @param value
     */
    public void showAllSheep(boolean value){
        this.showAllSheeps = value;
    }
    
    /**
     * Clears the map and then redraws the farmer's area.
     * It then checks what should be drawn to the map and
     * draws it. All objects to be drawn to the map are
     * created using the current information in the 
     * farmer object.
     */
    public final void refreshMap(){
        this.clearMap();
        this.setArea((ArrayList<Coordinate>)farmer.getCoordinates());
        if (showAllAttacks)
            addAllAttacks();
        if (showSheepPath != null)
            addPath(showSheepPath);
        if (showAllSheeps)
            addAllSheep();
            
    }
    
    /**
     * Creates a new AddSheep popup at the given
     * point and passes a latitude and longitude
     * as argument.
     * 
     * @param x
     * @param y
     */
    public void createPopup(int x, int y){
        new AddSheep(farmer, getPosition(x, y).getLat(), getPosition(x, y).getLon());
    }
    /**
     * If marker is a SheepMarker then the sheep is removed
     * from the farmer's sheep.
     * 
     * @param x
     * @param y
     * @param marker
     */
    public void createPopup(int x, int y, CustomMapMarker marker){
        if (!(marker instanceof SheepMarker)) return;
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

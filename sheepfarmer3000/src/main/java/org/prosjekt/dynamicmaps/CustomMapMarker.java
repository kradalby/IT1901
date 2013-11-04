/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.dynamicmaps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapObjectImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

/**
 *
 * @author Alfredo
 */
public class CustomMapMarker extends MapObjectImpl implements MapMarker{
        
    protected Image img;
    protected String id;
    protected double lon;
    protected double lat;
    protected final int SIZE_X = 20;
    protected final int SIZE_Y = 20;
    protected String fileLocation;
    protected Point lastPainted;
    
    /**
     * Default constructor, does nothing and should never be used.
     */
    public CustomMapMarker(){
        super("");
        this.img = null;
        this.id = null;
    }
    
    /**
     *
     * @return
     */
    @Override
    public double getLon(){
        return this.lon;
    }
    /**
     *
     * @return
     */
    @Override
    public double getLat(){
        return this.lat;
    }
    
    /**
     *
     * @param lon
     */
    @Override
    public void setLon(double lon){
        this.lon = lon;
    }
    
    /**
     *
     * @param lat
     */
    @Override
    public void setLat(double lat){
        this.lat = lat;
    }
    
    /**
     *
     * @return
     */
    public String getId(){
        return this.id;
    }
    
    /**
     *
     * @return
     */
    @Override
    public Font getFont(){
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    public Stroke getStroke(){
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    public Color getBackColor(){
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    public Color getColor(){
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getName(){
        return "";
    }
    
    /**
     *
     * @param g
     * @param p
     * @param i
     */
    @Override
    public void paint(Graphics g, Point p, int i){
        p.setLocation(p.x-(SIZE_X/2), p.y-(SIZE_Y/2));
        lastPainted = p;
        g.drawImage(img, p.x, p.y, SIZE_X, SIZE_Y, null);
    }
    
    /**
     *
     * @return
     */
    @Override
    public double getRadius(){
        return SIZE_X/2;
    }
    
    /**
     *
     * @return
     */
    @Override
    public Coordinate getCoordinate(){
        return new Coordinate(lon, lat);
    }
    
    /**
     *
     * @return
     */
    @Override
    public STYLE getMarkerStyle(){
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    public boolean isVisible(){
        return true;
    }
    
    /**
     * This method returns whether or not the Point p
     * is contained in the radius created by this marker.
     * 
     * @param p
     * @return
     */
    public boolean containsPoint(Point p){
        Rectangle rect = new Rectangle(lastPainted, new Dimension(SIZE_X, SIZE_Y));
        return rect.contains(p);
    }
    

}

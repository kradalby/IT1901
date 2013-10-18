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
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapObjectImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

/**
 *
 * @author Alfredo
 */
public class SheepMarker extends MapObjectImpl implements MapMarker{
    
    private Image img;
    private int id;
    private double lon;
    private double lat;
    private final int SIZE_X = 10;
    private final int SIZE_Y = 10;
    private Point lastPainted;
    
    SheepMarker(){
        super("");
        this.img = null;
        this.id = -1;
    }
    
    SheepMarker(double lon, double lat, int id){
        super("");
        try{
            img = ImageIO.read(getClass().getResource("resources/sheep.bmp"));
        }
        catch(IOException e){
            img = null;
        }
        this.lon = lon;
        this.lat = lat;
        this.id = id;
    }
    
    SheepMarker(Coordinate coord, int id){
        super("");
        try{
            img = ImageIO.read(getClass().getResource("resources/sheep.bmp"));
        }
        catch(IOException e){
            img = null;
        }
        this.lon = coord.getLon();
        this.lat = coord.getLat();
        this.id = id;
    }
    
    @Override
    public double getLon(){
        return this.lon;
    }
    @Override
    public double getLat(){
        return this.lat;
    }
    
    public void setLon(double lon){
        this.lon = lon;
    }
    
    public void setLat(double lat){
        this.lat = lat;
    }
    
    public int getId(){
        return this.id;
    }
    
    public Font getFont(){
        return null;
    }
    
    public Stroke getStroke(){
        return null;
    }
    
    public Color getBackColor(){
        return null;
    }
    
    public Color getColor(){
        return null;
    }
    
    public String getName(){
        return "";
    }
    
    public void paint(Graphics g, Point p, int i){
        p.setLocation(p.x-(SIZE_X/2), p.y-(SIZE_Y/2));
        lastPainted = p;
        g.drawImage(img, p.x, p.y, SIZE_X, SIZE_Y, null);
    }
    
    public double getRadius(){
        return SIZE_X/2;
    }
    
    public Coordinate getCoordinate(){
        return new Coordinate(lon, lat);
    }
    
    public STYLE getMarkerStyle(){
        return null;
    }
    
    public boolean isVisible(){
        return true;
    }
    
    public boolean containsPoint(Point p){
        Rectangle rect = new Rectangle(lastPainted, new Dimension(SIZE_X, SIZE_Y));
        return rect.contains(p);
    }
    
}

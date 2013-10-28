/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.joda.time.DateTime;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Alfredo
 */
public class RandomSheepGenerator {
    private List<Coordinate> path;
    private Random generator;
    private static Farmer farmer;
    
    public RandomSheepGenerator(List<Coordinate> path, Farmer farmer){
        this.farmer = farmer;
        this.path  = path;
        Date date = new Date();
        generator = new Random(date.getTime());
    }
    
    public ArrayList<Sheep> generateSheep(int amount){
        ArrayList<Sheep> result = new ArrayList<>();
        int currentTriangle;
        Triangle triangle1 = new Triangle(path.get(0), path.get(1), path.get(2));
        Triangle triangle2 = new Triangle(path.get(0), path.get(3), path.get(2));
        for (int i = 0; i < amount; i++){
            currentTriangle = generator.nextInt() % 2;
            if (currentTriangle == 0){
                result.add(new Sheep(i, DateTime.now(), farmer, triangle1.generateContainedCoordinate()));
            }
            else{
                result.add(new Sheep(i, DateTime.now(), farmer, triangle2.generateContainedCoordinate()));
            }
        }
        
        return result;
    }
    
    private class Triangle{
        public Coordinate origin;
        public Coordinate vector1;
        public Coordinate vector2;
        
        public Triangle(Coordinate a, Coordinate b, Coordinate c){
            this.origin = a;
            this.vector1 = new Coordinate(b.getLat() - origin.getLat(), b.getLon() - origin.getLon(), null);
            this.vector2 = new Coordinate(c.getLat() - origin.getLat(), c.getLon() - origin.getLon(), null);            
        }
        
        public Coordinate generateContainedCoordinate(){
            double alpha = 1;
            double beta = 1;
            while (alpha + beta > 1){
                alpha = Math.random();
                beta = Math.random();
            }
            return new Coordinate((alpha * vector1.getLat() + (beta * vector2.getLat())) + origin.getLat(), (alpha * vector1.getLon() + (beta * vector2.getLon()) + origin.getLon()) , null);
        }
    }
}

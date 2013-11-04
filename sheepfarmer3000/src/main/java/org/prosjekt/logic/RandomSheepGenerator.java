package org.prosjekt.logic;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
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
        RandomSheepGenerator.farmer = farmer;
        this.path  = path;
        Date date = new Date();
        generator = new Random(date.getTime());
    }
    
    public ArrayList<Sheep> generateSheep(int amount){
        ArrayList<Sheep> result = new ArrayList<>();
        ArrayList<Coordinate> randomCoords = generateCoords(amount);
        for (int i = 0; i < amount; i++){
            Sheep currentSheep = new Sheep(UUID.randomUUID().toString(), DateTime.now(), farmer.getId(), randomCoords.get(i));
            currentSheep.setAttacks(generateCoords(generator.nextInt(2)));
            result.add(currentSheep);
        }
        
        return result;
    }
    
    private ArrayList<Coordinate> generateCoords(int amount){
        
        ArrayList<Coordinate> result = new ArrayList<>();
        RandomSheepGenerator.Triangle currentTriangle;
        ArrayList<RandomSheepGenerator.Triangle> triangles = new ArrayList<>();
        int currentTriangleIndex;
        
        for (int i = 1; i < (path.size() - 1); i++){
            triangles.add(new RandomSheepGenerator.Triangle(path.get(0), path.get(i), path.get(i + 1)));
        }
        
        for (int i = 0; i < amount; i++){
            currentTriangleIndex = generator.nextInt(9999999); 
            currentTriangle = triangles.get(currentTriangleIndex % triangles.size() );
            result.add(currentTriangle.generateContainedCoordinate());
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

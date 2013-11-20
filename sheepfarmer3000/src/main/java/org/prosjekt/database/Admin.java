/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database;

import com.google.common.collect.Lists;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.prosjekt.client.ClientService;
import static org.prosjekt.database.Admin.addSheep;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;
import org.prosjekt.logic.RandomSheepGenerator;

/**
 * Klassen forenkler vedlikehold og generering av databasen. 
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class Admin {
    
    private static void sendAttack(){
        Sheep s = ClientService.getSheepById("1004_sheep3");
        ClientService.attack(s, true);
    }
    
    public static void main(String args[]){
        sendAttack();
        System.exit(0);
        int farmerid = 1004;
//        Farmer f = farmerOppdal(farmerid);
//        ClientService.updateFarmerArea(f);
//        System.exit(0);
        
        
        Farmer f = ClientService.getFarmer(farmerid);
        addSheep(f, f.getCoordinates(), 0, 5, farmerid);
        for (Sheep s : f.getSheeps()){
            ClientService.addSheep(s);
        }
//            ClientService.removeSheep(s);
        
    }
    

    /**
     * Legger inn ny farmer i LOKAL database. 
     * Legger IKKE inn helper. 
     * @param id 
     */
    public static void insertFarmer(int id) {
        String sql = "insert into users (id) values (?) ";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setString(1, "farmer" + id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = "insert into farmer (id, users_id, hashpass) values (?,?,?) ";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setInt(1, id);
            ps.setString(2, "farmer"+id);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Sleep() garanterer at ett område får riktig rekkefølge.
     * @param farmerid
     * @return 
     */
    private static Farmer farmerOppdal(int farmerid) {
        Farmer farmer = new Farmer(farmerid);
        //OPPDAL
        List<Coordinate> farmerArea = Lists.newArrayList();
        farmerArea.add(new Coordinate(62.585715,9.708897));
        sleep();
        farmerArea.add(new Coordinate(62.578047,9.718252));
        sleep();
        farmerArea.add(new Coordinate(62.580498,9.749666));
        sleep();
        farmerArea.add(new Coordinate(62.590891,9.733702));
        farmer.setCoordinates(farmerArea);
        return farmer;
    }
    
     private static Farmer farmerFinnmark(int farmerid) {
        Farmer farmer = new Farmer(farmerid);
        //Finnmark
        List<Coordinate> farmerArea = Lists.newArrayList();
        farmerArea.add(new Coordinate(69.603549,21.52977));
        sleep();
        farmerArea.add(new Coordinate(68.800041,22.716293));
        sleep();
        farmerArea.add(new Coordinate(69.029279,25.440903));
        sleep();
        farmerArea.add(new Coordinate(70.170201,24.67186));
        farmer.setCoordinates(farmerArea);
        return farmer;
    }
    
    
    /**
     * Sleep() garanterer at ett område får riktig rekkefølge.
     * @param farmerid
     * @return 
     */
    public static Farmer farmerMeldal(int farmerid) {
        Farmer farmer = new Farmer(farmerid);
        //OPPDAL
        List<Coordinate> farmerArea = Lists.newArrayList();
        farmerArea.add(new Coordinate(63.055504,9.690773));
        sleep();
        farmerArea.add(new Coordinate(63.076887,9.693519));
        sleep();
        farmerArea.add(new Coordinate(63.081705,9.673607));
        sleep();
        farmerArea.add(new Coordinate(63.069191,9.639618));
        sleep();
        farmerArea.add(new Coordinate(63.056437,9.658501));
        farmer.setCoordinates(farmerArea);
        return farmer;
    }
       
       
    /**
     * 
     * @param farmer 
     * @param area
     * @param start interval sheepid starter på. Eg 0 vil gi 1001_sheep0
     * @param ant antall sauer som skal genereres. 
     * @param farmerid 
     */
    public static void addSheep(Farmer farmer, List<Coordinate> area, int start, int ant, int farmerid){
        RandomSheepGenerator rsg = new RandomSheepGenerator(area, farmer);
        ArrayList<Sheep> sheeps = rsg.generateSheep(start, ant, farmerid + "_");
        farmer.setSheeps(sheeps);
    }
    
    
    private static void sleep(){
        try {
            Thread.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}

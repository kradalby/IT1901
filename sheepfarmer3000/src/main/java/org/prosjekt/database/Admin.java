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
import static org.prosjekt.client.ClientExample.updateFarmerArea;
import org.prosjekt.database.repository.AbstractProperties;
import org.prosjekt.database.repository.SheepRepository;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;
import org.prosjekt.logic.RandomSheepGenerator;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class Admin extends AbstractProperties{
      /**
     * Brukes for å legge inn ny farmer. 
     * @param id 
     */
    
    public static void main(String args[]){
        int farmers = 100;
        int startsequence = 1000;
        for (int i = startsequence; i<farmers + startsequence; i++){
//            insertFarmer(i);
        }
        insertFarmer(1001);
        insertFarmer(1002);
        insertFarmer(1003);
        
    }
    
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
    
    public static Farmer farmerOppdal() {
        int id = 1001;
        Farmer farmer = new Farmer(id);
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
    
    public static Farmer farmerMeldal() {
        int id = 1002;
        Farmer farmer = new Farmer(id);
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
       
       
    public static void addSheep(Farmer farmer, List<Coordinate> area, int antall){
        RandomSheepGenerator rsg = new RandomSheepGenerator(area, farmer);
        ArrayList<Sheep> sheeps = rsg.generateSheep(antall);
        SheepRepository sr = new SheepRepository();
        
        for (Sheep s : sheeps){
            sr.addSheep(s, s.getCurrentCordinate());
        }
        
    }
    
    private static void sleep(){
        try {
            Thread.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}

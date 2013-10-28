/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.prosjekt.database.SheepFarmerConnection;
import org.prosjekt.database.SheepService;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class SheepRepository extends AbstractProperties implements SheepService{
    private FarmerRepository fr;
    /**
     *  Klassen tilbyr CRUD for server. Create, read, update and delete.
     *
     */
    public SheepRepository() {
        fr = new FarmerRepository();
    }
    
 

   


    @Override
    public void addSheep(Sheep sheep) {
//        SheepEntity e = new SheepEntity
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSheep(Sheep sheep) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sheep getSheepAllCordinates(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Sheep[] getAllSheeps() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

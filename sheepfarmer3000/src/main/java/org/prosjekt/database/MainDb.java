/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.prosjekt.database.repository.FarmerRepository;
import org.prosjekt.helperclasses.Farmer;


/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class MainDb {
        
        
    public static void main(String[] args) throws SQLException{
        FarmerRepository fr = new FarmerRepository();
//        fr.printAllTables(con.getConnection());
//        fr.createFarmer(con.getConnection());
        fr.getFarmer(1);
        
               
    }
    
 
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.prosjekt.database.repository.AbstractProperties;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class SheepFarmerConnection {
    
    private static Connection instance;
    
    private SheepFarmerConnection() {
    }
    
    public static void setConnection(Connection connection){
        instance = connection;
    }
    
    public static Connection getInstance() {
        return instance;
    }

    private static Connection createInstance() {
        if (instance == null){
            try {
                AbstractProperties ap = new AbstractProperties();
                instance = DriverManager.getConnection(ap.getUrl(), ap.getUrl(), ap.getUrl());
            } catch (SQLException ex) {
                Logger.getLogger(SheepFarmerConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }
    
}

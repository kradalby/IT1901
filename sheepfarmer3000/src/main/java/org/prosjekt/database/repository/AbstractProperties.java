/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import org.prosjekt.database.SheepFarmerConnection;

/**
 * Klassen leser inn url, user og password fra properties fil.
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class AbstractProperties {
    private final String url;
    private final String user;
    private final String passwd;
    
    /**
     * Leser inn properties fra fil. 
     */
    public AbstractProperties() {
            Properties prop = new Properties();
            try {
                //load a properties file
                prop.load(new FileInputStream("config.properties"));
                
                url = prop.getProperty("database");
                user = prop.getProperty("dbuser");
                passwd = prop.getProperty("dbpassword");
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new IllegalStateException("failed to load properties. ");
            }
    }

    /**
     *
     * @return url til database.
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @return username til database
     */
    public String getUser() {
        return user;
    }

    /**
     *
     * @return passord til database
     */
    public String getPasswd() {
        return passwd;
    }

 
    
}

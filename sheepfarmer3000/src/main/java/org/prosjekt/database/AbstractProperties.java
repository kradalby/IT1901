/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class AbstractProperties {
    private String url;
    private String user;
    private String passwd;
    
    
      
    
    /*
     * 
        database properties
        dbpassword=gruppe3
        database=jdbc:postgresql://localhost:5432/SheepFarmer3000
        dbuser=prosjekt

     */
    
    private void loadProperties(){
            Properties prop = new Properties();
            try {
                //load a properties file
                prop.load(new FileInputStream("config.properties"));
                
                //get the property value and print it out
                url = prop.getProperty("database");
                user = prop.getProperty("dbuser");
                passwd = prop.getProperty("dbpassword");
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } 
    
    
    public AbstractProperties() {
        loadProperties();
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPasswd() {
        return passwd;
    }
    
    
}

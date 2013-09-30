/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class AbstractProperties {
    private final String url;
    private final String user;
    private final String passwd;
    
    public AbstractProperties() {
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
                throw new IllegalStateException("failed to load properties. ");
            }
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

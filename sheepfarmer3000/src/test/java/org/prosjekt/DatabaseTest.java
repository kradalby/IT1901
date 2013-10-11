/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.util.jdbc.DriverDataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import junit.framework.Assert;
import org.hsqldb.jdbcDriver;
import org.hsqldb.util.DatabaseManager;
import org.junit.Before;
import org.junit.Test;
import org.prosjekt.database.SheepFarmerConnection;
import org.prosjekt.database.entities.FarmerEntity;
import org.prosjekt.database.repository.FarmerRepository;
import org.prosjekt.helperclasses.Farmer;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */

public class DatabaseTest {


 
    @Before 
    public void setup() throws SQLException, IOException{
        System.out.println("setting up");
        // Point it to the database
        DriverDataSource ds = new DriverDataSource("org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:.", "sa", "");
        File file = new File("./src/main/resources/db/createTestDb.sql");
        String sql = Files.toString(file, Charsets.UTF_8);
        ds.getConnection().createStatement().executeUpdate(sql);
        SheepFarmerConnection.setConnection(ds.getConnection());
    }
    
    
    @Test
    public void testmeTest(){
        System.out.println("testing");
        FarmerRepository fr = new FarmerRepository();
        FarmerEntity f = new FarmerEntity(1);
        f.setEmail("yo");
        int inserted = fr.insertFarmerEntity(f);
        Assert.assertEquals(1, inserted);
        Farmer fe = fr.getFarmer(1);
        Assert.assertNotNull(fe);
        Assert.assertEquals("yo", fe.getEmail());
        System.out.println("fe.email: " + fe.getEmail());
    }
    
  
}

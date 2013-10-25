/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.googlecode.flyway.core.util.jdbc.DriverDataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.prosjekt.database.SheepFarmerConnection;
import org.prosjekt.database.repository.FarmerRepository;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

/**
 * Test class for all methods in interface FarmerService. 
 * @author Christoffer <christofferbuvik@gmail.com>
 */

public class FarmerServiceTest {
    private boolean debug = true;
    AbstractPropertiesTest ap = new AbstractPropertiesTest();
    private FarmerRepository fr = new FarmerRepository();
    private Connection conn;
    
    //testen kan legge inn og rydde opp. Evt ha minusverdier for farmer og coordinatenene. 
    
     
    private void setup() throws SQLException{
        if (conn == null){
            conn = DriverManager.getConnection(ap.getUrl(), ap.getUser(), ap.getPasswd());
            SheepFarmerConnection.setConnection(conn);
        }
        
    }
    
    private void populateDB() {
        File initTest = new File("./src/main/resources/db/initDb.sql");
        String initTestSql;
        try {
            setup();
            initTestSql = Files.toString(initTest, Charsets.UTF_8);
            conn.createStatement().executeUpdate(initTestSql);
        } catch (IOException ex) {
            Logger.getLogger(FarmerServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException se){
            Logger.getLogger(FarmerServiceTest.class.getName()).log(Level.SEVERE, null, se);
        }
        
        
    }
  
    int farmerid = -99;
    private String insertFarmer1 = "INSERT INTO users (id,firstname,lastname, hashpass, email, phone) VALUES ('test_uuid_farmer1', 'Ole', 'Olsen', 'pass1', 'ole.olsen@bondelaget.no', '12345678');  INSERT INTO farmer VALUES("+farmerid+", 'test_uuid_farmer1');";
    private String deleteFarmer1 = "delete from users where id = 'test_uuid_farmer1'; delete from farmer where id = "+farmerid+";";

    @Test
    public void setPashHash() throws SQLException {
        populateDB();
        setup();
        try {
            conn.createStatement().executeUpdate(insertFarmer1);
            fr.setPasshash("newpass", farmerid);
            Assert.assertEquals("newpass", fr.getPasshash(farmerid));
        } catch (SQLException ex) {
            Logger.getLogger(FarmerServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.createStatement().executeUpdate(deleteFarmer1);
            
        }
        
    }
    
    
    
    @Test 
    public void getFarmer() throws SQLException{
        setup();
        try {
            conn.createStatement().executeUpdate(insertFarmer1);
            conn.createStatement().executeUpdate(
                     "INSERT INTO sheepcoordinate (id, coordinate_id, sheep_id) VALUES "
                     + "('test_sheep1coordinate1', 'sheep1coordinate1', 'test_sheep1'),"
                     + "('test_sheep1coordinate2', 'sheep1coordinate2', 'test_sheep1'),"
                     + "('test_sheep1coordinate3', 'sheep1coordinate3', 'test_sheep1');"
                     
                     + "INSERT INTO coordinate (id, dateevent, latitude, longitude) VALUES "
                     + "('test_sheep1coordinate1',TIMESTAMP '2011-05-16 15:00:00' ,62.00000,9.00000),"
                     + "('test_sheep1coordinate2',TIMESTAMP '2011-05-16 12:00:00' ,61.00000,8.00000),"
                     + "('test_sheep1coordinate3',TIMESTAMP '2011-05-16 15:00:00' ,63.00000,9.00000);"
                     
                     + "INSERT INTO sheep (id, farmerid, birth, alive, lastcoordinateid) VALUES ('test_sheep1', "+farmerid+", '2013-01-01', true, 'test_sheep1coordinate1');");
            fr.getFarmer(farmerid);
            
            List<Sheep> sheeps = fr.getAllSheepWithLastCoordinate(new Farmer(farmerid));
            Sheep sheep1 = sheeps.get(0);
            Assert.assertEquals(1, sheeps.size());
            Assert.assertEquals(0, sheep1.getWeight());
            Assert.assertEquals("test_sheep1", sheep1.getId());
            Assert.assertEquals(true, sheep1.getAlive());
            Assert.assertEquals(new DateTime(2013, 1, 1, 0,0,0), sheep1.getBirth());
          
        } catch (SQLException ex) {
            Logger.getLogger(FarmerServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.createStatement().executeUpdate(deleteFarmer1);
            conn.createStatement().executeUpdate(""
                    + "delete from coordinate where id = 'test_sheep1coordinate1';" 
                    + "delete from coordinate where id = 'test_sheep1coordinate2';" 
                    + "delete from coordinate where id = 'test_sheep1coordinate3';"
                    + "delete from sheepcoordinate where id = 'test_sheep1coordinate1';" 
                    + "delete from sheepcoordinate where id = 'test_sheep1coordinate2';" 
                    + "delete from sheepcoordinate where id = 'test_sheep1coordinate3';"
                    + "delete from sheep where id='test_sheep1'");
        }
        
        
    }
    
  
    
}


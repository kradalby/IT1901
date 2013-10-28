/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.googlecode.flyway.core.util.jdbc.DriverDataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
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
import org.prosjekt.helperclasses.Coordinate;
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
    int farmerid2 = -98;
    private String insertFarmer1 = "INSERT INTO users (id,firstname,lastname, email, phone) VALUES ('test_uuid_farmer1', 'Ole', 'Olsen', 'ole.olsen@bondelaget.no', '12345678');  INSERT INTO farmer VALUES("+farmerid+", 'test_uuid_farmer1', 'pass1');";
    private String insertFarmer2 = "INSERT INTO users (id,firstname,lastname, email, phone) VALUES ('test_uuid_farmer2', 'Hans', 'Hansen', 'hans.hansen@bondelaget.no', '12345678');  INSERT INTO farmer VALUES("+farmerid2+", 'test_uuid_farmer1', 'pass2');";
    private String deleteFarmer1 = "delete from users where id = 'test_uuid_farmer1';";
    private String deleteFarmer2 = "delete from users where id = 'test_uuid_farmer2';";

    @Test
    public void testPashHash() throws SQLException {
        populateDB();
        setup();
        String hashpass = null;
        try {
            conn.createStatement().executeUpdate(insertFarmer1);
            fr.setPasshash("newpass", farmerid);
            hashpass = fr.getPasshash(farmerid).getPasshash();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.createStatement().executeUpdate(deleteFarmer1);
            
        }
        Assert.assertEquals("newpass", hashpass);
        
    }
    
    
    
    @Test 
    public void getSheepsLastCoordinate() throws SQLException{
        System.out.println("getSheepsLastCoordinate test");
        List<Sheep> sheeps = Lists.newArrayList();
        setup();
        try {
            conn.createStatement().executeUpdate(insertFarmer1);
            conn.createStatement().executeUpdate(insertFarmer2);
            conn.createStatement().executeUpdate(
                      "INSERT INTO coordinate (id, dateevent, latitude, longitude) VALUES "
                     + "('test_sheep1coordinate1',TIMESTAMP '2011-05-16 15:00:00' ,62.00000,9.00000),"
                     + "('test_sheep1coordinate2',TIMESTAMP '2011-05-16 12:00:00' ,61.00000,8.00000),"
                     + "('test_sheep1coordinate3',TIMESTAMP '2011-05-16 15:00:00' ,63.00000,9.00000);"
                     + "INSERT INTO sheepcoordinate (id, coordinate_id, sheep_id) VALUES "
                     + "('test_sheep1coordinate1', 'test_sheep1coordinate1', 'test_sheep1'),"
                     + "('test_sheep1coordinate2', 'test_sheep1coordinate2', 'test_sheep1'),"
                     + "('test_sheep1coordinate3', 'test_sheep1coordinate3', 'test_sheep1');"
                     
                     + "INSERT INTO sheep (id, farmerid, birth, alive, lastcoordinateid) VALUES ('test_sheep1', "+farmerid+", '2013-01-01', true, 'test_sheep1coordinate1');");
            fr.getFarmer(farmerid);
            
            sheeps = fr.getAllSheepWithLastCoordinate(farmerid);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FarmerServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.createStatement().executeUpdate(deleteFarmer1);
            conn.createStatement().executeUpdate(deleteFarmer2);
            conn.createStatement().executeUpdate(
                      "delete from sheepcoordinate where id = 'test_sheep1coordinate1';" 
                    + "delete from sheepcoordinate where id = 'test_sheep1coordinate2';" 
                    + "delete from sheepcoordinate where id = 'test_sheep1coordinate3';"
                    + "delete from coordinate where id = 'test_sheep1coordinate1';" 
                    + "delete from coordinate where id = 'test_sheep1coordinate2';" 
                    + "delete from coordinate where id = 'test_sheep1coordinate3';"
                    + "delete from sheep where id='test_sheep1';"
                    );
                    
        }
        org.junit.Assert.assertEquals(1, sheeps.size());
        Sheep sheep1 = sheeps.get(0);
        org.junit.Assert.assertEquals(0, sheep1.getWeight());
        org.junit.Assert.assertEquals("test_sheep1", sheep1.getId());
        org.junit.Assert.assertEquals(true, sheep1.getAlive());
        org.junit.Assert.assertEquals(new DateTime(2013, 1, 1, 0,0,0), sheep1.getBirth());
        Coordinate c = sheep1.getCurrentCordinate();
        org.junit.Assert.assertEquals(15, c.getDate().getHourOfDay());
        org.junit.Assert.assertEquals(62.0, c.getLat(), 0.1);
        org.junit.Assert.assertEquals(9.0, c.getLon(), 0.1);
    }
    
    @Test
    public void farmerAreaTest(){
        List<Coordinate> area = Lists.newArrayList();
        area.add(new Coordinate(5.0044, 2.111, new DateTime(2013, 10, 1, 12, 0, 0, 13)));
        area.add(new Coordinate(5.004, 2.222, new DateTime(2013, 10, 1, 12, 0, 0, 14)));
        area.add(new Coordinate(5.0011, 2.333, new DateTime(2013, 10, 1, 12, 0, 0, 15)));
        
        org.junit.Assert.assertEquals(0, fr.getFarmerArea(farmerid).size());  //testing initial state. no farmerarea coordinates should exist.
        
        List<Coordinate> areaAct = Lists.newArrayList();
        List<Coordinate> areaAfterCleanup = Lists.newArrayList();
        try {
            fr.updateFarmerArea(area, farmerid);
            areaAct = fr.getFarmerArea(farmerid);
        } catch (Exception e){
            Logger.getLogger(FarmerServiceTest.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            //cleanup
            fr.deleteAllCoordinatesByFarmer(farmerid);
            areaAfterCleanup = fr.getFarmerArea(farmerid);
        }
        Collections.sort(areaAct);
        for (Coordinate c : areaAct) System.out.println(c);
        org.junit.Assert.assertEquals(3, areaAct.size());
        //testing last coordinate. 
        org.junit.Assert.assertEquals(5.0011, areaAct.get(0).getLat(), 0.01);
        org.junit.Assert.assertEquals(2.333, areaAct.get(0).getLon(), 0.01);
        org.junit.Assert.assertEquals(15, areaAct.get(0).getDate().getMillisOfSecond());
        
        
        //testing first coordinate. 
        org.junit.Assert.assertEquals(5.0044, areaAct.get(2).getLat(), 0.01);
        org.junit.Assert.assertEquals(2.111, areaAct.get(2).getLon(), 0.01);
        org.junit.Assert.assertEquals(13, areaAct.get(2).getDate().getMillisOfSecond());
        
        org.junit.Assert.assertEquals(0, areaAfterCleanup.size());
    }
    
    
    @Test 
    public void getFarmer() throws SQLException{
        setup();
        Farmer farmer = null;
        Farmer farmerNoArea = null;
        List<Coordinate> area = Lists.newArrayList();
        area.add(new Coordinate(5.0044, 2.111, new DateTime(2013, 10, 1, 12, 0, 0, 13)));
        try {
            conn.createStatement().executeUpdate(insertFarmer1);
            farmerNoArea = fr.getFarmer(farmerid);
            fr.updateFarmerArea(area, farmerid);
            farmer = fr.getFarmer(farmerid);
        } catch (SQLException ex) {
            Logger.getLogger(FarmerServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.createStatement().executeUpdate(deleteFarmer1);
            fr.deleteAllCoordinatesByFarmer(farmerid);
                    
        }
        org.junit.Assert.assertEquals("Ole", farmer.getFirstName());
        org.junit.Assert.assertEquals("Olsen", farmer.getLastName());
        org.junit.Assert.assertEquals("ole.olsen@bondelaget.no", farmer.getEmail());
        org.junit.Assert.assertEquals("12345678", farmer.getPhone());
        org.junit.Assert.assertEquals("pass1", farmer.getPasshash().getPasshash());
        
        org.junit.Assert.assertEquals(0, farmerNoArea.getCoordinates().size());
        org.junit.Assert.assertEquals(1, farmer.getCoordinates().size());
        
    }
    
  
    
}


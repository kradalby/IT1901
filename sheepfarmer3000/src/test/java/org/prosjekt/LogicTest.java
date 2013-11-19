/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt;

import com.google.common.collect.Lists;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.junit.Test;
import org.prosjekt.database.LogicService;
import org.prosjekt.database.SheepFarmerConnection;
import org.prosjekt.database.repository.FarmerRepository;
import org.prosjekt.database.repository.LogicRepository;
import org.prosjekt.database.repository.SheepRepository;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;
import org.prosjekt.logic.RandomSheepGenerator;
import org.prosjekt.logic.SheepLogic;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class LogicTest {
    AbstractPropertiesTest ap = new AbstractPropertiesTest();
    private FarmerRepository fr = new FarmerRepository();
    private SheepRepository sr = new SheepRepository();
    private LogicRepository lr = new LogicRepository();
    private Connection conn;
    
     private void setup() throws SQLException{
        if (conn == null){
            conn = DriverManager.getConnection(ap.getUrl(), ap.getUser(), ap.getPasswd());
            SheepFarmerConnection.setConnection(conn);
        }
        
    }
    public LogicTest() {
    }
    
    
    
    private int farmerid = -254;
    private int farmerid2 = -255;
    private String insertFarmer1 = "INSERT INTO users (id,firstname,lastname, email, phone) VALUES ('test_uuid_farmer1', 'Ole', 'Olsen', 'ole.olsen@bondelaget.no', '12345678');  INSERT INTO farmer VALUES("+farmerid+", 'test_uuid_farmer1', 'pass1');";
    private String insertFarmer2 = "INSERT INTO users (id,firstname,lastname, email, phone) VALUES ('test_uuid_farmer2', 'Hans', 'Hansen', 'hans.hansen@bondelaget.no', '12345678');  INSERT INTO farmer VALUES("+farmerid2+", 'test_uuid_farmer1', 'pass2');";
    private String deleteFarmer1 = "delete from users where id = 'test_uuid_farmer1';";
    private String deleteFarmer2 = "delete from users where id = 'test_uuid_farmer2';";
    
    @Test
    public void moveSheeps() throws SQLException{
        setup();
        List<Coordinate> farmer1area = Lists.newArrayList();
        farmer1area.add(new Coordinate(1.1, 1.1, new DateTime()));
        farmer1area.add(new Coordinate(2.0, 2.0, new DateTime()));
        farmer1area.add(new Coordinate(3.0, 3.0, new DateTime()));
        farmer1area.add(new Coordinate(4.0, 4.0, new DateTime()));
        
        
        List<Sheep> moved = Lists.newArrayList();
        try {
            conn.createStatement().executeUpdate(insertFarmer1);
            fr.updateFarmerArea(farmer1area, farmerid);
            
            Coordinate s1c = new Coordinate(1.1, 1.1, new DateTime());
            Sheep s1 = new Sheep("sheeptest99", new DateTime(), farmerid, s1c);
            sr.addSheep(s1, s1c);
            
            Coordinate s2c = new Coordinate(1.2, 1.2, new DateTime());
            Sheep s2 = new Sheep("sheeptest98", new DateTime(), farmerid, s2c);
            s2.setAlive(false);
            sr.addSheep(s2, s2c);
            
            RandomSheepGenerator rsg = new RandomSheepGenerator(farmer1area, new Farmer(farmerid));
            SheepLogic.moveSheep(rsg, s1, lr);
            SheepLogic.moveSheep(rsg, s2, lr);
            
            moved.add(sr.getSheepAllCordinates("sheeptest99"));
            moved.add(sr.getSheepAllCordinates("sheeptest98"));
            
        } catch (SQLException ex) {
            Logger.getLogger(FarmerServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.createStatement().executeUpdate(deleteFarmer1);
            fr.deleteAllCoordinatesByFarmer(farmerid);
            sr.removeSheep("sheeptest99");
            sr.removeSheep("sheeptest98");
            
        }
        
        org.junit.Assert.assertEquals("sheeptest99", moved.get(0).getId()); 
        org.junit.Assert.assertEquals("sheeptest98", moved.get(1).getId()); 
        org.junit.Assert.assertNotEquals(1.1, moved.get(0).getCurrentCordinate().getLat(), 0.01); 
        org.junit.Assert.assertEquals(1.1, moved.get(0).getCordinates().get(1).getLat(), 0.01); 
        org.junit.Assert.assertEquals(1.2, moved.get(1).getCordinates().get(1).getLat(), 0.01); 
        
    }
    
}


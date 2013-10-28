/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt;

import com.google.common.collect.Lists;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;
import org.junit.Test;
import org.prosjekt.database.LogicService;
import org.prosjekt.database.SheepFarmerConnection;
import org.prosjekt.database.SheepService;
import org.prosjekt.database.repository.LogicRepository;
import org.prosjekt.database.repository.SheepRepository;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Sheep;

/**
 * Test class for all methods in LogicService.
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class LogicServiceTest  {
    private LogicService ls;
    private SheepService ss;
       AbstractPropertiesTest ap = new AbstractPropertiesTest();
       private Connection conn;

    public LogicServiceTest() {
        this.ls = new LogicRepository();
        this.ss = new SheepRepository();
    }
    
    
    private void setup() throws SQLException{
        if (conn == null){
            conn = DriverManager.getConnection(ap.getUrl(), ap.getUser(), ap.getPasswd());
            SheepFarmerConnection.setConnection(conn);
        }
        
    }
    
    
    int farmerid = -99;
    
    
    /*
     *  Tester alle metoder i SheepService og 
     */
    @Test
    public void addSheepMovement() throws SQLException{
        setup();
        String sheepid = UUID.randomUUID().toString();
        System.out.println("Sheepid: " + sheepid);
        Coordinate c = new Coordinate(62.001, 9.44, new DateTime());
        Sheep sheep = new Sheep(sheepid, new DateTime(2013, 1, 1,0,0), farmerid, c);
        List<Coordinate> movements = null;
        List<Coordinate> movements2 = null;
        Sheep sheepTest = null;
        Sheep sheepUpdated = null;
        
        String coordinateid = null;
        try {
            ss.addSheep(sheep, c);
            sheepTest = ss.getSheepAllCordinates(sheepid);
            movements = sheepTest.getAllCoordinates();
            ls.addSheepMovement(sheep, new Coordinate(61.111, 5.0, new DateTime()));
            sheepUpdated = ss.getSheepAllCordinates(sheepid);
            movements2 = sheepUpdated.getAllCoordinates();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            ss.removeSheep(sheepid);
            try {
                conn.createStatement().executeQuery("delete from sheep where farmerid="+farmerid);
            } catch(Exception e){
                //swallow. 
            }
        }
        System.out.println(sheepTest);
        
        org.junit.Assert.assertEquals(1, movements.size());
        Coordinate cActual = movements.get(0);
        org.junit.Assert.assertEquals(62.001, cActual.getLat(), 0.01);
        org.junit.Assert.assertEquals(9.44, cActual.getLon(), 0.01);
        
        org.junit.Assert.assertEquals(62.001, sheepTest.getCurrentCordinate().getLat(), 0.01);
        org.junit.Assert.assertEquals(9.44, sheepTest.getCurrentCordinate().getLon(), 0.01);
        org.junit.Assert.assertEquals(true, sheepTest.getAlive());
        org.junit.Assert.assertEquals(farmerid, sheepTest.getFarmerid());
        org.junit.Assert.assertEquals(1, sheepTest.getBirth().getMonthOfYear());
        org.junit.Assert.assertEquals(0, sheepTest.getBirth().getHourOfDay());
        
        Coordinate lastc = movements2.get(0);
        //TEST sheep object after new movement. 
        org.junit.Assert.assertEquals(61.111, sheepUpdated.getCurrentCordinate().getLat(), 0.01);
        org.junit.Assert.assertEquals(5.0, sheepUpdated.getCurrentCordinate().getLon(), 0.01);
        //TEST all coordinates list after new movement. 
        org.junit.Assert.assertEquals(61.111, lastc.getLat(), 0.01);
        org.junit.Assert.assertEquals(5.0, lastc.getLon(), 0.01);
        
        
    } 
    
    @Test
    public void getAllSheeps() throws SQLException{
        setup();
        farmerid = -150;
        Sheep[] sheepArr = null;
        Sheep sheep = new Sheep("testSheep1", new DateTime(2013, 1, 1,0,0), farmerid, new Coordinate(2.0, 2.0, new DateTime()));
        Sheep sheep2 = new Sheep("testSheep2", new DateTime(2013, 1, 1,0,0), farmerid, new Coordinate(2.0, 2.0, new DateTime()));
        
        Sheep[] sheepArrBeforeTest = null;
        try {
            sheepArrBeforeTest = ls.getAllSheeps();
            ss.addSheep(sheep, sheep.getCurrentCordinate());
            ss.addSheep(sheep2, sheep2.getCurrentCordinate());
            sheepArr = ls.getAllSheeps();
            
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            ss.removeSheep("testSheep1");
            ss.removeSheep("testSheep2");
            
        }
        org.junit.Assert.assertEquals(2+sheepArrBeforeTest.length, sheepArr.length);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.prosjekt.database.FarmerService;
import org.prosjekt.database.SheepFarmerConnection;
import org.prosjekt.database.entities.FarmerEntity;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Passhash;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class FarmerRepository extends AbstractProperties implements FarmerService {
    
    
    public FarmerRepository() {
    }
    

    /*
     *  Helper method to extract all sheeps with last coordinate.
     *  A farmer object has to execute 2 queries. 1 for getting last coordinates of farmer-area, 1 for getting last coordinate of all sheeps.
     */
    public List<Sheep> getAllSheepWithLastCoordinate(Farmer farmer){
        System.out.println("farmer id: " + farmer.getId());
        System.out.println("inside repo");
        List<Sheep> sheeps = Lists.newArrayList();
        String sql = "select s.id as s_id, s.birth as s_birth, s.alive as s_alive "
                + ", s.lastcoordinateid as lastcoordinate "
                +",c.latitude as latitude, c.longitude as longitude, c.dateevent as dateevent "
                + "from sheepcoordinate sc "
                +"join coordinate c on c.id = sc.coordinate_id "
                +"join sheep s on s.id = sc.sheep_id "
                +"join farmer f on f.id = s.farmerid "
                +"join users u on u.id = f.users_id "
                +"where s.lastcoordinateid = sc.id and f.id=?"
                ;
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setInt(1, farmer.getId());
            ResultSet rs = ps.executeQuery();
            System.out.println("inside rs: " + rs.getFetchSize());
            while (rs.next()){
                System.out.println("prcoessing rs row" + rs.getString("lastcoordinate"));
                Sheep sheep = new Sheep(rs.getString("s_id"), new DateTime(rs.getDate("s_birth").getTime()), farmer);
                sheep.setAlive(rs.getBoolean("s_alive"));
                java.sql.Timestamp d = rs.getTimestamp("dateevent");
                sheep.setMostCurrentCoordinate(new Coordinate(rs.getDouble("latitude"), rs.getDouble("longitude"), new DateTime(d)));
                sheeps.add(sheep);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sheeps;
    }
    
    
    @Override
    public Farmer getFarmer(int id){
        
        return null;
        
    }
    
    String deleteAllCoordinatesByFarmerid = 
            "delete from coordinate where id in (" +
                "select c.id " +
                "from farmercoordinate fc " +
                "right join coordinate c on fc.coordinate_id = c.id" +
                "where fc.farmerid = ?)";
    
    private void deleteAllCoordinatesByFarmer(int farmerid){
           //CLEANUP coordinates. 
         try (PreparedStatement preparedStatement = SheepFarmerConnection.getInstance().prepareStatement(deleteAllCoordinatesByFarmerid);) {
             preparedStatement.setInt(1, farmerid);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void updateFarmerArea(List<Coordinate> farmerArea, int farmerid) {
        //INSERT NEW COORDINATES. 
        deleteAllCoordinatesByFarmer(farmerid);
        Map<String, Coordinate> map = Maps.newHashMap();
        String sql = "insert into coordinate (id, longitude, latitude, dateevent) values (?,?,?,?) ";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            SheepFarmerConnection.getInstance().setAutoCommit(false);
            for (Coordinate c : farmerArea){
               String id = UUID.randomUUID().toString();
               ps.setString(1, id);
               ps.setDouble(2,c.getLat());
               ps.setDouble(3, c.getLon());
               ps.setDate(4, new java.sql.Date(c.getDate().getMillis()));
               ps.addBatch();
               map.put(id, c);
            }
            ps.executeBatch();
            SheepFarmerConnection.getInstance().setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        //INSERT NEW FARMER COORDINATES
        String insertFarmerCoordinate = "insert into farmercoordinate (id, coordinate_id, farmerid) values (?,?,?)";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(insertFarmerCoordinate);) {
            SheepFarmerConnection.getInstance().setAutoCommit(false); 
            for (String id : map.keySet()){
                ps.setString(1, id);
                ps.setString(2, id);
                ps.setInt(3, farmerid);
                ps.addBatch();
            }
            ps.executeBatch();
            SheepFarmerConnection.getInstance().setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Coordinate> getFarmerArea(int farmerid){
        List<Coordinate> area = Lists.newArrayList();
        String sql = "select c.longitude as lon, c.latitude as lat, c.dateevent as dateevent from farmercoordinate fc " +
                     "join coordinate c on c.id = fc.coordinate_id " +
                     "where fc.farmerid = 1;";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                area.add(new Coordinate(rs.getDouble("lat"), rs.getDouble("lon"), new DateTime(rs.getDate("dateevent").getTime())));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return area;
    }
    
    
    
    @Override
    public void updateFarmer(Farmer farmer) {
        String sql = "update users set firstname=?, lastname=?, email=?, phone=? from farmer where farmer.id = ? and farmer.users_id = users.id";
        try (PreparedStatement preparedStatement = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            preparedStatement.setString(1, farmer.getFirstName());
            preparedStatement.setString(2, farmer.getLastName());
            preparedStatement.setString(3, farmer.getEmail());
            preparedStatement.setString(4, farmer.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    @Override
    public void setPasshash(String passhash, int farmerid) {
        String sql = "update farmer set hashpass = ? where id = ?";
        try (PreparedStatement preparedStatement = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            preparedStatement.setString(1, passhash);
            preparedStatement.setInt(2, farmerid);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Passhash getPasshash(int farmerid) {
        Passhash ph = null;
        ph = new Passhash(farmerid);
        String sql = "select hashpass from farmer where farmer.id=?";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setInt(1, farmerid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ph.setPasshash(rs.getString("hashpass"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ph;
    }
    


    
    
    
}

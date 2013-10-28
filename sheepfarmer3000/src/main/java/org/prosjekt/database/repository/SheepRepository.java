/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

import com.google.common.collect.Lists;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.prosjekt.database.SheepFarmerConnection;
import org.prosjekt.database.SheepService;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class SheepRepository extends AbstractProperties implements SheepService{

    
    public SheepRepository() {
    }
    

    public Sheep getSheep(String sheepid){
        Sheep sheep = null;
        String sql = "select s.alive as alive, s.birth as birth, s.farmerid as farmerid, c.latitude as lat, c.longitude as lon, c.dateevent as dateevent from sheep s " +
                    "join coordinate c on c.id = s.lastcoordinateid " +
                    "where s.id = ? and c.id = s.lastcoordinateid;";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setString(1,  sheepid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                sheep = new Sheep(sheepid, new DateTime(rs.getDate("birth").getTime()), rs.getInt("farmerid"), 
                        new Coordinate(rs.getDouble("lat"), rs.getDouble("lon"), new DateTime(rs.getTimestamp("dateevent").getTime())));
                sheep.setAlive(rs.getBoolean("alive"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SheepRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sheep;
    }
    
    public List<Coordinate> getCoordinatesBySheep(String sheepid){
        List<Coordinate> coordinates = Lists.newArrayList();
        String sql = "select c.latitude as lat, c.longitude as lon, c.dateevent as dateevent from sheepcoordinate sc " +
                     "join coordinate c on c.id = sc.coordinate_id " +
                     "join sheep s on s.id = sc.sheep_id " +
                     "where s.id=?";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setString(1, sheepid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                coordinates.add(new Coordinate(rs.getDouble("lat"), rs.getDouble("lon"), new DateTime(rs.getTimestamp("dateevent").getTime())));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SheepRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(coordinates);
        return coordinates;
    }

    
    
    @Override
    public Sheep getSheepAllCordinates(String sheepid) {
        Sheep sheep = getSheep(sheepid);
        sheep.setCordinates(getCoordinatesBySheep(sheepid));
        return sheep;
    }

    @Override
    public void addSheep(Sheep sheep, Coordinate currentCoordinate) {
        String cid = new LogicRepository().addSheepMovement(sheep, currentCoordinate);
        String sql = "insert into sheep (id, farmerid, birth, alive, lastcoordinateid) values (?,?,?,?,?) ";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setString(1, sheep.getId());
            ps.setInt(2, sheep.getFarmerid());
            ps.setDate(3, new java.sql.Date(sheep.getBirth().getMillis()));
            ps.setBoolean(4, Boolean.TRUE);
            ps.setString(5, cid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void removeSheep(String sheepid) {
         String deleteAllCoordinatesBySheep = 
            "delete from coordinate where id in (" +
                "select c.id " +
                "from sheepcoordinate sc " +
                "right join coordinate c on c.id = sc.coordinate_id " +
                "where sc.sheep_id = ?)";
           //CLEANUP coordinates. 
         try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(deleteAllCoordinatesBySheep);) {
            ps.setString(1, sheepid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
         try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement("delete from sheep where id = ?");) {
            ps.setString(1, sheepid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

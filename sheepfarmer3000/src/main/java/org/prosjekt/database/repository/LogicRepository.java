/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

import com.google.common.collect.Lists;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.prosjekt.database.LogicService;
import org.prosjekt.database.SheepFarmerConnection;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class LogicRepository extends AbstractProperties implements LogicService{

    @Override
    public String addSheepMovement(Sheep sheep, Coordinate c) {
        
        String cid = UUID.randomUUID().toString();
         //INSERT NEW COORDINATES. 
        String sql = "insert into coordinate (id, latitude, longitude, dateevent) values (?,?,?,?) ";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setString(1, cid);
            ps.setDouble(2,c.getLat());
            ps.setDouble(3, c.getLon());
            ps.setTimestamp(4, new java.sql.Timestamp(c.getDate().getMillis()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        //INSERT NEW SHEEP COORDINATES
        String insertSheepCoordinate = "insert into sheepcoordinate (id, coordinate_id, sheep_id) values (?,?,?)";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(insertSheepCoordinate);) {
            ps.setString(1, cid);
            ps.setString(2, cid);
            ps.setString(3, sheep.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        //UPDATE LASTEST COORDINATE IN SHEEP
         String updateSheepLastCoordinate = "update sheep set lastcoordinateid=? where id=?";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(updateSheepLastCoordinate);) {
            ps.setString(1, cid);
            ps.setString(2, sheep.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cid;
    }

    @Override
    public String addAttack(String sheepid, Coordinate coordinate){
        String aid = UUID.randomUUID().toString();
        String sql = "insert into coordinate (id, latitude, longitude, dateevent) values (?,?,?,?) ";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setString(1, aid);
            ps.setDouble(2,coordinate.getLat());
            ps.setDouble(3, coordinate.getLon());
            ps.setTimestamp(4, new java.sql.Timestamp(coordinate.getDate().getMillis()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        //INSERT NEW SHEEP COORDINATES
        String insertSheepCoordinate = "insert into attack (id, coordinate_id, sheep_id) values (?,?,?)";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(insertSheepCoordinate);) {
            ps.setString(1, aid);
            ps.setString(2, aid);
            ps.setString(3, sheepid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return aid;
    }

    @Override
    public Sheep[] getAllSheeps() {
       List<Sheep>  sheeps  = Lists.newArrayList();
        String sql = "select c.latitude as lat, c.longitude as lon, c.dateevent as dateevent "
                + ", s.id as id, s.birth as birth, s.farmerid as farmerid, s.alive as alive  "
                + " from sheep s join coordinate c on c.id = s.lastcoordinateid";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                java.sql.Timestamp d = rs.getTimestamp("dateevent");
                Coordinate current = new Coordinate(rs.getDouble("lat"),rs.getDouble("lat"), new DateTime(d));
                Sheep sheep = new Sheep(rs.getString("id"), new DateTime(rs.getDate("birth").getTime()), rs.getInt("farmerid"), current); 
                sheep.setAlive(rs.getBoolean("alive"));
                sheeps.add(sheep);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogicRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        Sheep[] sheepsArr = new Sheep[sheeps.size()];
        for (int i = 0; i < sheepsArr.length; i++) {
            sheepsArr[i] = sheeps.get(i);
        }
        
        return sheepsArr;
    }

    @Override
    public List<Integer> getFarmerids() {
        String sql = "select id as fid from farmer";
        List<Integer> farmerids = Lists.newArrayList();
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                farmerids.add(rs.getInt("fid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogicRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return farmerids;
    }

    @Override
    public List<Farmer> getAllFarmers() {
        List<Farmer> farmers = Lists.newArrayList();
        FarmerRepository fr = new FarmerRepository();
        List<Integer> farmerids = getFarmerids();
        for (Integer i : farmerids){
            farmers.add(fr.getFarmer(i));
        }
        return farmers;
    }
    
    
  
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class LogicRepository implements LogicService{

    /**
     * Legger til nytt coordinate til sau. 
     * @param sheep
     * @param c coordinate
     * @return String c.getId()  brukes av testene for å rydde. 
     */
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

    /**
     * Legger inn nytt angrep i databasen. Oppdaterer sau hvis den ikke er i live. 
     * @param sheepid
     * @param coordinate coordinate til angrepet. 
     * @return 
     */
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
        //UPDATE IF SHEEP DIED.
         String updateSheepLastCoordinate = "update sheep set alive=false where id=?";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(updateSheepLastCoordinate);) {
            ps.setString(1, sheepid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogicRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aid;
    }

    /**
     * 
     * @return alle sauer i databasen.  
     */
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
                Coordinate current = new Coordinate(rs.getDouble("lat"),rs.getDouble("lon"), new DateTime(d));
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

    /**
     * 
     * @return  id til alle bonder i databasen. 
     */
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

    /**
     * Se getFarmer(int id) i FarmerRepository. 
     * @return alle bønder i databasen.    
     */
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

    /**
     *  Batch update of new coordinate
     * @param sheeps 
     */
    @Override
    public void addSheepMovements(List<Sheep> sheeps) {
        Map<String, UUID> coordids = Maps.newHashMap();
        Iterator<Sheep> itr = sheeps.iterator();
        while (itr.hasNext()){
            Sheep s  = itr.next();
            if (!s.getAlive()) itr.remove();
            coordids.put(s.getId(), UUID.randomUUID());
            
        }
        
        String sql = "insert into coordinate (id, latitude, longitude, dateevent) values (?,?,?,?) ";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            for (Sheep s : sheeps){
                ps.setString(1, coordids.get(s.getId()).toString());
                ps.setDouble(2, s.getCurrentCordinate().getLat());
                ps.setDouble(3, s.getCurrentCordinate().getLon());
                ps.setTimestamp(4, new java.sql.Timestamp(s.getCurrentCordinate().getDate().getMillis()));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(LogicRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        //INSERT NEW SHEEP COORDINATES
        String insertSheepCoordinate = "insert into sheepcoordinate (id, coordinate_id, sheep_id) values (?,?,?)";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(insertSheepCoordinate);) {
            for (Sheep s : sheeps){
                    String cid = coordids.get(s.getId()).toString();
                    ps.setString(1, cid);
                    ps.setString(2, cid);
                    ps.setString(3, s.getId());
                    ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(LogicRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        //UPDATE LASTEST COORDINATE IN SHEEP
         String updateSheepLastCoordinate = "update sheep set lastcoordinateid=? where id=?";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(updateSheepLastCoordinate);) {
            for (Sheep s : sheeps){
                    String cid = coordids.get(s.getId()).toString();
                    ps.setString(1, cid);
                    ps.setString(2, s.getId());
                    ps.executeUpdate();
                    ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(LogicRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
     
  
    }
    
    
  
}

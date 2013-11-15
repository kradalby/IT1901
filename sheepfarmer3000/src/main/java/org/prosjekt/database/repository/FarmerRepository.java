/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.prosjekt.database.FarmerService;
import org.prosjekt.database.SheepFarmerConnection;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Helper;
import org.prosjekt.helperclasses.Passhash;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class FarmerRepository implements FarmerService {
    
    
    public FarmerRepository() {
    }
    

    /**
     * 
     * @param farmerid
     * @return List<Sheep> med siste koordinat og alle angrep for hver sau.  
     */
    public List<Sheep> getAllSheepWithLastCoordinate(int farmerid){
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
            ps.setInt(1, farmerid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                
                java.sql.Timestamp d = rs.getTimestamp("dateevent");
                Coordinate currentCoordinate = new Coordinate(rs.getDouble("latitude"), rs.getDouble("longitude"), new DateTime(d));
                Sheep sheep = new Sheep(rs.getString("s_id"), new DateTime(rs.getDate("s_birth").getTime()), farmerid, currentCoordinate);
                sheep.setAlive(rs.getBoolean("s_alive"));
                addAttacksToSheep(sheep);
                sheeps.add(sheep);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sheeps;
    }
    
    private Farmer getFarmerPlain(int id){
        Farmer farmer = new Farmer(id);
        String sql = "select f.hashpass as hasspass, u.email as email, u.phone as phone, u.firstname as fn, u.lastname as ln from farmer f"
                + " join users u on u.id = f.users_id"
                + " where f.id = ?";
          try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                farmer.setFirstName(rs.getString("fn"));
                farmer.setLastName(rs.getString("ln"));
                farmer.setEmail(rs.getString("email"));
                farmer.setPhone(rs.getString("phone"));

                Passhash ph = new Passhash(id);
                ph.setPasshash(rs.getString("hasspass"));
                farmer.setPasshash(ph);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        farmer.setSheeps(getAllSheepWithLastCoordinate(id));
        farmer.setCoordinates(getFarmerArea(id));
        return farmer;
    }
    
    
    /**
     * 
     * @param id farmerid
     * @return Farmer med info om alle hjelpere, alle sauer med siste koordinate og alle angrep, koordinater til bondens område. 
     */
    @Override
    public Farmer getFarmer(int id){
        Stopwatch s1 = new Stopwatch();
        s1.start();
        Farmer farmer = getFarmerPlain(id);
        Stopwatch s2 = new Stopwatch();
        s2.start();
        farmer.setSheeps(getAllSheepWithLastCoordinate(id));
        s1.stop();
        farmer.setCoordinates(getFarmerArea(id));
        farmer.setHelpers(getHelpers(id));
        s2.stop();
        System.out.println(id +  " Time1: " + s1.elapsedMillis());
        System.out.println(id + " Time2: " + s2.elapsedMillis());
        return farmer; 
    }
    
    String deleteAllCoordinatesByFarmerid = 
            "delete from coordinate where id in (" +
                "select c.id " +
                "from farmercoordinate fc " +
                "right join coordinate c on c.id = fc.coordinate_id " +
                "where fc.farmerid = ?)";
    

    /**
     * Sletter alle koordinater til en bonde. 
     * @param farmerid 
     */
    public void deleteAllCoordinatesByFarmer(int farmerid){
           //CLEANUP coordinates. 
         try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(deleteAllCoordinatesByFarmerid);) {
            ps.setInt(1, farmerid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * NB! overskriver koordinater til en bonde i databasen. 
     * @param farmerArea List<Coordinate> 
     * @param farmerid 
     */
    @Override
    public void updateFarmerArea(List<Coordinate> farmerArea, int farmerid) {
        //INSERT NEW COORDINATES. 
        deleteAllCoordinatesByFarmer(farmerid);
        Map<String, Coordinate> map = Maps.newHashMap();
        String sql = "insert into coordinate (id, latitude, longitude, dateevent) values (?,?,?,?) ";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            SheepFarmerConnection.getInstance().setAutoCommit(false);
            for (Coordinate c : farmerArea){
               String id = UUID.randomUUID().toString();
               ps.setString(1, id);
               ps.setDouble(2,c.getLat());
               ps.setDouble(3, c.getLon());
               ps.setTimestamp(4, new java.sql.Timestamp(c.getDate().getMillis()));
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
    
    /**
     * 
     * @param farmerid
     * @return koordinater til bondens område.  
     */
    public List<Coordinate> getFarmerArea(int farmerid){
        List<Coordinate> area = Lists.newArrayList();
        String sql = "select c.longitude as lon, c.latitude as lat, c.dateevent as dateevent from farmercoordinate fc " +
                     "join coordinate c on c.id = fc.coordinate_id " +
                     "where fc.farmerid = ?;";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setInt(1, farmerid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                area.add(new Coordinate(rs.getDouble("lat"), rs.getDouble("lon"), new DateTime(rs.getTimestamp("dateevent").getTime())));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(area);
        return area;
    }
    
    
    /**
     * Oppdaterer firstname, lastname, phone, email til en bonde. 
     * @param farmer 
     */
    @Override
    public void updateFarmer(Farmer farmer) {
        String sql = "update users set firstname=?, lastname=?, email=?, phone=? from farmer where farmer.id = ? and farmer.users_id = users.id";
        try (PreparedStatement preparedStatement = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            preparedStatement.setString(1, farmer.getFirstName());
            preparedStatement.setString(2, farmer.getLastName());
            preparedStatement.setString(3, farmer.getEmail());
            preparedStatement.setString(4, farmer.getPhone());
            preparedStatement.setInt(5, farmer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateFarmerArea(farmer.getCoordinates(), farmer.getId());
    } 

    /**
     * Oppdaterer firstname, lastname, email og phone for en Helper. 
     * @param helper 
     */
    @Override
    public void updateHelper(Helper helper) {
        System.out.println("\n\nhelper: " + helper);
        String sql = "update users set firstname=?, lastname=?, email=?, phone=? from helper where helper.id = ? and helper.users_id = users.id";
        try (PreparedStatement preparedStatement = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            preparedStatement.setString(1, helper.getFirstname());
            preparedStatement.setString(2, helper.getLastname());
            preparedStatement.setString(3, helper.getEmail());
            preparedStatement.setString(4, helper.getPhone());
            preparedStatement.setString(5, helper.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    /**
     * Setter nytt passord i databasen for en farmer. 
     * @param passhash nytt passord
     * @param farmerid 
     */
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
    
    /**
     * Henter passord for en farmer. 
     * @param farmerid
     * @return 
     */
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

    /**
     * Sletter en hjelper fra databasen. 
     * @param helper 
     */
    @Override
    public void removeHelper(Helper helper) {
        String sql = "delete from users where id=?";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setString(1, helper.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Legger til en hjelper i databasen. 
     * @param helper 
     */
    @Override
    public void addHelper(Helper helper) {
        String sql = "insert into users (id, firstname, lastname, email, phone) values (?,?,?,?,?) ";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setString(1, helper.getId());
            ps.setString(2, helper.getFirstname());
            ps.setString(3, helper.getLastname());
            ps.setString(4, helper.getEmail());
            ps.setString(5, helper.getPhone());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = "insert into helper (id, users_id, farmer_id) values (?,?,?) ";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setString(1, helper.getId());
            ps.setString(2, helper.getId());
            ps.setInt(3, helper.getFarmerid());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Henter ut alle hjelpere en bonde har. 
     * @param farmerid
     * @return List<Helper>
     */
    private List<Helper> getHelpers(int farmerid){
        List<Helper> list = Lists.newArrayList();
             String sql = "select h.id as hid, u.firstname as fn, u.lastname as ln, u.email as email,u.phone as phone from helper h "
                     + "join users u on u.id = h.users_id "
                     + "where h.farmer_id = ?";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setInt(1, farmerid);
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                list.add(new Helper(rs.getString("hid"), farmerid, rs.getString("fn"), rs.getString("ln"), rs.getString("phone"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Legger til alle angrep en sau har for en sau.  
     * @param sheep 
     */
    private void addAttacksToSheep(Sheep sheep) {
        String sql = "select c.dateevent as dateevent, c.latitude as lat, c.longitude as lon from attack a " +
                    "join sheep s on s.id = a.sheep_id " +
                    "join coordinate c on c.id = a.coordinate_id "
                    + "where s.id = ?";
       try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setString(1, sheep.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                java.sql.Timestamp d = rs.getTimestamp("dateevent");
                Coordinate attackCoordinate = new Coordinate(rs.getDouble("lat"), rs.getDouble("lon"), new DateTime(d));
                sheep.getAttacks().add(attackCoordinate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    


  
    
    
    
}

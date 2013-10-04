/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

import com.google.common.collect.Lists;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
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
public class FarmerRepository extends AbstractProperties {

    /*
     *  Klassen tilbyr CRUD for server. Create, read, update and delete. 
     */
    public FarmerRepository() {
    }

    public void deleteFarmerEntity(int id) {
        String sql = "DELETE FROM farmer where id =" + id;
         try (PreparedStatement preparedStatement = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateFarmerEntity(FarmerEntity entity) {
        String sql = "UPDATE farmer set firstname=?, lastname=?, hashpass=?, email=?, helperfirstname=? where id=?";
         try (PreparedStatement preparedStatement = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            preparedStatement.setString(1, entity.getFirstname());
            preparedStatement.setString(2, entity.getLastname());
            preparedStatement.setString(3, entity.getHashpass());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getHelperfirstname());
            preparedStatement.setInt(6, entity.getId());
            preparedStatement.executeQuery();
        } catch (SQLException ex) {
//            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int insertFarmerEntity(FarmerEntity entity) {
        int ret = 0;
        String sql = "INSERT INTO farmer"
                    + "(id, firstname, lastname, hashpass, email, helperfirstname) VALUES"
                    + "(?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getFirstname());
            preparedStatement.setString(3, entity.getLastname());
            preparedStatement.setString(4, entity.getHashpass());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getHelperfirstname());
            ret = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    /*
     *  READ
     */
    public Farmer getFarmer(int id) {
        Farmer ret = null;
        String sql = "SELECT * FROM farmer where id = ?";
        try (PreparedStatement preparedStatement = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                FarmerEntity entity = new FarmerEntity(rs.getInt("id"));
                entity.setFirstname(rs.getString("firstname"));
                entity.setLastname(rs.getString("lastname"));
                entity.setHashpass(rs.getString("hashpass"));
                entity.setEmail(rs.getString("email"));
                entity.setHelperfirstname(rs.getString("helperfirstname"));
                entity.setHelperlastname(rs.getString("helperlastname"));
                ret = FarmerEntity.farmerEntityToFarmer(entity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public Farmer getFarmerSheepsMostRecentGPS(int id) {
        List<Coordinate> farmerArea = null;
        List<Sheep> sheeps = null;
        Farmer f = null;
        String sql = "SELECT f.id as fid, f.email as f_email, f.firstname as f_firstname, f.lastname as f_lastname, f.hashpass as f_hashpass,"
                + "f.helperfirstname as f_hfn, f.helperlastname as f_hln,"
                + "s.id as sid, s.weight as s_weight, s.birth as s_birth ,"
                + "c.id as cid, c.attack as c_attack, c.dateevent as c_date, c.longitude as c_longitude, c.latitude as c_latitude FROM coordinate c "
                + "join sheep s on s.id = c.sheepid "
                + "join farmer f on f.id = s.farmerid "
                + "where f.id = 1 and (sheepid, dateevent) in ("
                + "select sheepid, max(dateevent) as date from coordinate "
                + "group by sheepid)";
        
         try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            f = new Farmer(id);
            farmerArea = Lists.newArrayList();
            while (rs.next()){
                f.setEmail(rs.getString("f_email"));
                f.setFirstName(rs.getString("f_firstname"));
                f.setLastName(rs.getString("f_lastname"));
                Passhash pass = new Passhash(f.getId()); 
                pass.setPasshash(f.getId(), rs.getString("f_hashpass"));
                f.setPasshash(pass);
                f.setPhone("f_phone");
                f.setHelperFirstname(rs.getString("f_hfn"));
                f.setHelperLastName(rs.getString("f_hln"));
//                f.setHelperEmail(rs.getString("f_hemail")); ikke lagt inn i db. 
//                f.setHelperPhone(rs.getString("f_hphone"));
                
                Integer cid = rs.getInt("cid");
                Boolean attack = rs.getBoolean("c_attack");
                DateTime c_date = new DateTime(rs.getTimestamp("c_date"));
                String lat = rs.getString("c_latitude");
                String longi = rs.getString("c_longitude");
                Coordinate c = new Coordinate(cid, longi, lat, c_date, attack);
//                f.set(rs.getInt("fid"));
//                f.setF;
                if (rs.getInt("fid") > 0){
                    farmerArea.add(c);
                }
                else {
                    Sheep sheep = new Sheep(rs.getInt("sid"), new DateTime(rs.getTimestamp("s_date")), f);
                  sheep.setWeigth(rs.getInt("s_weight"));
                  sheep.setMostCurrentCoordinate(c);
                  f.addSheep(sheep);
                 }
                
                System.out.println(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
    
}

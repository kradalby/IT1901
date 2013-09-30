/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

import com.google.common.collect.Lists;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.prosjekt.database.entities.FarmerEntity;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.IFarmer;
import org.prosjekt.helperclasses.Sheep;
import sun.font.SunFontManager;

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
        try {
            Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPasswd());
            PreparedStatement preparedStatement = null;
            preparedStatement = conn.prepareStatement("DELETE FROM farmer where id =" + id);
            preparedStatement.executeQuery();
        } catch (SQLException ex) {
//            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateFarmerEntity(FarmerEntity entity) {
        try {
            //        conn.setAutoCommit(false);//commit trasaction manually
            String sql = "UPDATE farmer set firstname=?, lastname=?, hashpass=?, email=?, helperfirstname=? where id=?";
            Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPasswd());
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
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

    public void insertFarmerEntity(FarmerEntity entity) {
        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPasswd());) {
            //        conn.setAutoCommit(false);//commit trasaction manually
            String sql = "INSERT INTO farmer"
                    + "(id, firstname, lastname, hashpass, email, helperfirstname) VALUES"
                    + "(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getFirstname());
            preparedStatement.setString(3, entity.getLastname());
            preparedStatement.setString(4, entity.getHashpass());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getHelperfirstname());
            preparedStatement.execute();
        } catch (SQLException ex) {
//            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     *  READ
     */
    public Farmer getFarmer(int id) {
        FarmerEntity entity = new FarmerEntity(id);
        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPasswd())) {
            String sql = "SELECT * FROM farmer where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                entity.setFirstname(rs.getString("firstname"));
                entity.setLastname(rs.getString("lastname"));
                entity.setHashpass(rs.getString("hashpass"));
                entity.setEmail(rs.getString("email"));
                entity.setHelperfirstname(rs.getString("helperfirstname"));
                entity.setHelperlastname(rs.getString("helperlastname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FarmerEntity.farmerEntityToFarmer(entity);
    }

    public Farmer getFarmerSheepsMostRecentGPS(int id) {
        List<Coordinate> farmerArea = null;
        List<Sheep> sheeps = null;
        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPasswd())) {
            String sql = "SELECT f.id as fid, f.email as f_email, f.firstname as f_firstname, f.lastname as f_lastname, f.hashpass as f_hashpass,"
                    + "f.helperfirstname as f_hfn, f.helperlastname as f_hln,"
                    + "s.id as sid, s.weight as s_weight, s.birth as s_birth ,"
                    + "c.id as cid, c.attack as c_attack, c.dateevent as c_date, c.longitude as c_longitude, c.latitude as c_latitude FROM coordinate c "
                    + "join sheep s on s.id = c.sheepid "
                    + "join farmer f on f.id = s.farmerid "
                    + "where f.id = 1 and (sheepid, dateevent) in ("
                    + "select sheepid, max(dateevent) as date from coordinate "
                    + "group by sheepid)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Farmer f = new Farmer(id);
            sheeps = Lists.newArrayList();
            farmerArea = Lists.newArrayList();
            while (rs.next()){
                f.setEmail(rs.getString("f_email"));
                f.setFirstName(rs.getString("f_firstname"));
                f.setLastName(rs.getString("f_lastname"));
                f.setPasshash(rs.getString("f_hashpass"));   
                f.setPhone("f_phone");
                f.setHelperFirstname(rs.getString("f_hfn"));
                f.setHelperLastName(rs.getString("f_hln"));
//                f.setHelperEmail(rs.getString("f_hemail")); ikke lagt inn i db. 
//                f.setHelperPhone(rs.getString("f_hphone"));
                
                Coordinate c = new Coordinate();
                c.setId(rs.getInt("cid"));
                c.setAttack(rs.getBoolean("c_attack"));
                c.setDate(new DateTime(rs.getTimestamp("c_date")));
                c.setLatitude(rs.getString("c_latitude"));
                c.setLongitude(rs.getString("c_longitude"));
//                f.set(rs.getInt("fid"));
//                f.setF;
                if (rs.getInt("fid") > 0){
                    farmerArea.add(c);
                }
                else {
                    Sheep sheep = new Sheep(rs.getInt("sid"), rs.getInt("s_weight"), new DateTime(rs.getTimestamp("s_date")), f);
//                    sheep.addMostRecentGps();
                    sheeps.add(sheep);
                 }
                
                System.out.println(c);
            }
//            System.out.println(rs.getInt(0));
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}

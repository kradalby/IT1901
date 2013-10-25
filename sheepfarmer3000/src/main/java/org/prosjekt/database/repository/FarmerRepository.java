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
     *  READ
     */
    public Farmer getFarmerPlain(int id) {
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
    
    
    
    
    
    
    public Farmer getFarmer2(int id) {
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
                //                pass.setPasshash(f.getId(), rs.getString("f_hashpass"));
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
                    Sheep sheep = new Sheep(rs.getString("sid"), new DateTime(rs.getTimestamp("s_date")), f);
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
    
    
    @Override
    public Farmer getFarmer(int id){
        
        return null;
        
    }
    
    /*
     *  Helper method to extract all sheeps with last coordinate.
     *  A farmer object has to execute 2 queries. 1 for getting last coordinates of farmer-area, 1 for getting last coordinate of all sheeps.
     */
    public List<Sheep> getAllSheepWithLastCoordinate(Farmer farmer){
        List<Sheep> sheeps = Lists.newArrayList();
        String sql = "select s.id as s_id, s.birth as s_birth, s.alive as s_alive from sheepcoordinate sc "
                +"join sheep s on sc.sheep_id = s.id "
                +"join farmer f on f.id = s.farmerid "
                +"joikn users u on u.id = f.users_id "
                +"where s.lastcoordinateid = sc.id and f.id = ?";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setInt(1, farmer.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Sheep sheep = new Sheep(rs.getString("s_id"), new DateTime(rs.getDate("s_birth").getTime()), farmer);
                sheep.setAlive(rs.getBoolean("s_alive"));
                sheeps.add(sheep);
                
                //                sheep.setMostCurrentCoordinate(new Coordinate());
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sheeps;
    }
    
    
    @Override
    public void updateFarmer(Farmer farmer) {
        String sql = "update users set firstname=?, lastname=?, hashpass=?, email=?, phone=? from farmer where farmer.id = ? and farmer.users_id = users.id";
        try (PreparedStatement preparedStatement = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            preparedStatement.setString(1, farmer.getFirstName());
            preparedStatement.setString(2, farmer.getLastName());
            preparedStatement.setString(3, farmer.getPasshash().getPasshash());
            preparedStatement.setString(4, farmer.getEmail());
            preparedStatement.setString(5, farmer.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    @Override
    public void setPasshash(String passhash, int farmerid) {
        String sql = "update users set hashpass = ? from farmer where farmer.id = ? and farmer.users_id = users.id";
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
        String sql = "select * from users "
                + "join farmer on farmer.users_id = users.id where farmer.id=?";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ps.setInt(1, farmerid);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                //                 System.out.println(rs.getString("id") + "hashpass: " + rs.getString("hashpass"));
                ph.setPasshash(rs.getString("hashpass"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ph;
    }
    
    
    public void test() {
        Passhash ph = null;
        String sql = "select * from users where id = 1";
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("firstname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Coordinate> getFarmerAreaCoordinates() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

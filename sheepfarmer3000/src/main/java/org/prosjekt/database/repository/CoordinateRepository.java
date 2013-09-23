/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

import com.google.common.collect.Lists;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.prosjekt.database.entities.CoordinateEntity;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class CoordinateRepository  extends AbstractProperties{

    /*
     *  Klassen tilbyr CRUD for server. Create, read, update and delete. 
     */
    public CoordinateRepository() {
    }

    public void deleteEntity(int id){
        deleteEntity(id, "coordinate");
    }
    
    public void updateFarmerEntity(CoordinateEntity entity){
        try {
            //        conn.setAutoCommit(false);//commit trasaction manually
            String sql = "UPDATE coordinate set sheepid=?, latitude=?, longitude=?, dateevent=?, attack=? where id=?";
            Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPasswd());
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getSheepid());
            preparedStatement.setString(2, entity.getLatitude());
            preparedStatement.setString(3, entity.getLongitude());
            preparedStatement.setDate(4, entity.getDate());
            preparedStatement.setBoolean(5, entity.isAttack());
            preparedStatement.executeQuery();
        } catch (SQLException ex) {
//            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertEntity(CoordinateEntity entity)  {
        try {
            //        conn.setAutoCommit(false);//commit trasaction manually
            String sql = "INSERT INTO coordinate"
                    + "(id, sheepid, latitude, longitude, dateevent, attack) VALUES"
                    + "(?,?,?,?,?,?)";
            Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPasswd());
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getSheepid());
            preparedStatement.setString(3, entity.getLatitude());
            preparedStatement.setString(4, entity.getLongitude());
            preparedStatement.setDate(5, entity.getDate());
            preparedStatement.setBoolean(6, entity.isAttack());
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

   /*
    *  READ
    */
    public List<CoordinateEntity> getCoordinateBySheep(int sheepid){
         CoordinateEntity entity = null;
         List<CoordinateEntity> list = null;
         try {
                String sql = "SELECT * FROM coordinate where sheepid = ? order by dateevent desc";
            Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPasswd());
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, sheepid);
             ResultSet rs = preparedStatement.executeQuery();
             list = Lists.newArrayList();
             while (rs.next()){
                    int id = rs.getInt("id");
                    String latitude = rs.getString("latitude");
                    String longitude = rs.getString("longitude");
                    Boolean attack = rs.getBoolean("attack");
                    Date date = rs.getDate("dateevent");
                    list.add(new CoordinateEntity(id, sheepid, latitude, longitude, attack, date));
                    
             }
        } catch (SQLException ex) {
            Logger.getLogger(CoordinateRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    //GetFarmer with sheeps and last coordinate. do join. Hent ut fra coordinates.  
    
    
}

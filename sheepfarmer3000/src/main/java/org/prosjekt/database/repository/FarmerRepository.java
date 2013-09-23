/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.prosjekt.database.entities.FarmerEntity;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.IFarmer;
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
            PreparedStatement  preparedStatement = null;
             preparedStatement = conn.prepareStatement("DELETE FROM farmer where id =" + id);
             preparedStatement.executeQuery();
        } catch (SQLException ex) {
//            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateFarmerEntity(FarmerEntity entity){
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
    
    public void insertFarmerEntity(FarmerEntity entity)  {
        try {
            //        conn.setAutoCommit(false);//commit trasaction manually
            String sql = "INSERT INTO farmer"
                    + "(id, firstname, lastname, hashpass, email, helperfirstname) VALUES"
                    + "(?,?,?,?,?,?)";
            Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPasswd());
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
    public Farmer getFarmer(int id){
           FarmerEntity entity = new FarmerEntity(id);
               try {
                String sql = "SELECT * FROM farmer where id = ?";
            Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPasswd());
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
             ResultSet rs = preparedStatement.executeQuery();
             while (rs.next()){
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
    
    //GetFarmer with sheeps and last coordinate. do join. Hent ut fra coordinates.  
    //    SELECT *
    //  FROM coordinate c 
    //  join sheep s on c.sheepid = s.id
    //  join farmer f on s.farmerid = f.id where c.id=1
    //group by s.id, c.id,f.id 
    //order by c.dateevent desc limit 1
}

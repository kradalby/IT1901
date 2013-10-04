/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.prosjekt.database.SheepFarmerConnection;
import org.prosjekt.database.entities.FarmerEntity;
import org.prosjekt.database.entities.SheepEntity;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class SheepRepository extends AbstractProperties {
    private FarmerRepository fr;
    /**
     *  Klassen tilbyr CRUD for server. Create, read, update and delete.
     *
     */
    public SheepRepository() {
        fr = new FarmerRepository();
    }
    
    public void deleteEntity(int id) {
        String sql = "DELETE FROM sheep where id =" + id;
        try (PreparedStatement preparedStatement = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            preparedStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateEntity(SheepEntity entity){
        String sql = "UPDATE sheep set farmerid=?, weight=?, birth=?, alive=? where id=?";
        try (PreparedStatement preparedStatement = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            preparedStatement.setInt(1, entity.getFarmerid());
            
            if (entity.getWeight() == null) preparedStatement.setNull(2, Types.NULL);
            else preparedStatement.setInt(2, entity.getWeight());
            
            preparedStatement.setDate(3, entity.getBirth());
            preparedStatement.setBoolean(4, entity.isAlive());
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.executeQuery();
        } catch (SQLException ex) {
            //            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertEntity(SheepEntity entity)  {
        String sql = "INSERT INTO sheep"
                + "(id, farmerid, weight, birth, alive) VALUES"
                + "(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getFarmerid());
            
            if (entity.getWeight() == null) preparedStatement.setNull(3, Types.NULL);
            else preparedStatement.setInt(3, entity.getWeight());
            //
            preparedStatement.setDate(4, entity.getBirth());
            preparedStatement.setBoolean(5, entity.isAlive());
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
     *  READ
     */
    public SheepEntity getSheep(int id){
        SheepEntity entity = null;
        String sql = "SELECT * FROM sheep where id = ?";
        try (PreparedStatement preparedStatement = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int farmerid = rs.getInt("farmerid");
                Integer weight = rs.getInt("weight");
                Boolean attack = rs.getBoolean("alive");
                Date date = rs.getDate("birth");
                entity = new SheepEntity(id, farmerid, weight, date, attack);
            }
        } catch (SQLException ex) {
            Logger.getLogger(org.prosjekt.database.repository.FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }
    
    
}

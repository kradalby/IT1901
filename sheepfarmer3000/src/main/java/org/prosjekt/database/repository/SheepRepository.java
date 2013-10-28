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
    
 

   


    @Override
    public void addSheep(Sheep sheep, Coordinate currentCoordinate) {
//        SheepEntity e = new SheepEntity
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSheep(String sheepid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sheep getSheepAllCordinates(String id) {
        Sheep sheep = null;
        String sql = "select s.id as s_id, s.birth as s_birth, s.alive as s_alive "
                + ", s.lastcoordinateid as lastcoordinate "
                +",c.latitude as latitude, c.longitude as longitude, c.dateevent as dateevent "
                + "from sheepcoordinate sc "
                +"join coordinate c on c.id = sc.coordinate_id "
                +"join sheep s on s.id = sc.sheep_id "
                +"where s.id=?"
                ;
        try (PreparedStatement ps = SheepFarmerConnection.getInstance().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                
                java.sql.Timestamp d = rs.getTimestamp("dateevent");
                Coordinate currentCoordinate = new Coordinate(rs.getDouble("latitude"), rs.getDouble("longitude"), new DateTime(d));
//                Sheep sheep = new Sheep(rs.getString("s_id"), new DateTime(rs.getDate("s_birth").getTime()), farmer, currentCoordinate);
                sheep.setAlive(rs.getBoolean("s_alive"));
//                sheeps.add(sheep);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sheep;
    }

    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database;

import java.sql.SQLException;
import org.prosjekt.database.repository.FarmerRepository;


/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class MainDb {
        
        
    public static void main(String[] args) throws SQLException{
        FarmerRepository fr = new FarmerRepository();
//        fr.printAllTables(con.getConnection());
//        fr.createFarmer(con.getConnection());
        fr.getFarmer(1);
        
               
    }
    
 
}

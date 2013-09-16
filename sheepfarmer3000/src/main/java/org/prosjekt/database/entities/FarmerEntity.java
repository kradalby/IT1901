/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.entities;

import org.prosjekt.helperclasses.Farmer;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class FarmerEntity {
     private final int id;
     private String firstname;
     private String lastname;
     private String hashpass;
     private String email;
     private String helper;

    public FarmerEntity(int id) {
        this.id = id;
    }
     
    public static Farmer toFarmer(FarmerEntity entity){
        return new Farmer
    }
    
    
}

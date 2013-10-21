/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database;

import java.util.List;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Passhash;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public interface FarmerService {
    public void setPasshash(String passhash, int farmerid);
    public Passhash getPasshash(int farmerid);
    
    public void updateFarmer(Farmer farmer);
    
    /**
     * 
     * @param farmerid
     * @return Farmer object with all sheeps, only last coordinate of each sheep.  
     */
    public Farmer getFarmer(int farmerid);
    public void addSheep(int farmerid, Sheep sheep);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database;

import java.util.List;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Helper;
import org.prosjekt.helperclasses.Passhash;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public interface FarmerService {
    public void setPasshash(String passhash, int farmerid);
    public Passhash getPasshash(int farmerid);
    
    
    /**
     * Updates farmer, overwrites current farmer area coordinates if any. 
     * NOTE Sheeps is not updated by this method. 
     * @param farmer 
     */
    public void updateFarmer(Farmer farmer);
    
    /**
     *   
     * @param farmerid
     * @return Farmer object with all sheeps, only last coordinate of each sheep. 
     */
    public Farmer getFarmer(int farmerid);
    
   
    public void updateFarmerArea(List<Coordinate> farmerArea, int farmerid);
    
    public void removeHelper(int farmerid);
    public void addHelper(Helper helper, int farmerid);
    
}

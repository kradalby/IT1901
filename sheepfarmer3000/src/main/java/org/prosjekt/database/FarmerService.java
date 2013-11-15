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
 * Interface tilbyr metoder for å manipulere backendløsning.
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public interface FarmerService {
    public void setPasshash(String passhash, int farmerid);
    public Passhash getPasshash(int farmerid);
    
    
    public void updateFarmer(Farmer farmer);
    public Farmer getFarmer(int farmerid);
    public void updateFarmerArea(List<Coordinate> farmerArea, int farmerid);

    public void updateHelper(Helper helper);
    public void removeHelper(Helper helper);
    public void addHelper(Helper helper);
    
}

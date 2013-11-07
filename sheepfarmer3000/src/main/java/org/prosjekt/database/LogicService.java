/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database;

import java.util.List;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public interface LogicService {
    /**
     * 
     * @param sheep
     * @param coordiante
     * @return coordinate id of this movement.  
     */
    public String addSheepMovement(Sheep sheep,Coordinate coordiante);
    public String addAttack(String sheepid, Coordinate coordiante);
    
    /**
     * 
     * @return List<Farmer> with all sheeps of farmer, included farmerArea.  
     */
    public List<Farmer> getAllFarmers();
    public Sheep[] getAllSheeps();
}

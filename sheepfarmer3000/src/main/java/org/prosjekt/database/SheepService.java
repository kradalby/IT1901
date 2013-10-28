/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database;

import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public interface SheepService {
    /**
     * 
     * @param sheep
     * @return sheep with all coordinates 
     */
    public Sheep getSheepAllCordinates(String id);
    public void addSheep(Sheep sheep, Coordinate currentCoordinate);
    public void removeSheep(String sheepid);
    
    
}

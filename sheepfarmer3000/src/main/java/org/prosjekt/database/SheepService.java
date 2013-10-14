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
    public Sheep getSheepAllCordinates(Sheep sheep);
    public void addSheep(Sheep sheep);
    public void removeSheep(Sheep sheep);
    
    
    
}

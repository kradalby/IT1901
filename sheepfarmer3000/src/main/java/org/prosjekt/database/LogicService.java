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
public interface LogicService {
    
    public void addSheepMovement(Sheep sheep,Coordinate coordiante);
    public void updateSheepAttack(Sheep sheep, Coordinate coordinate);
    
    
    public Sheep[] getAllSheeps();
}

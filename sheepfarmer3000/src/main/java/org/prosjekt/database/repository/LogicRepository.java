/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

import org.prosjekt.database.LogicService;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class LogicRepository extends AbstractProperties implements LogicService{

    @Override
    public void addSheepMovement(Sheep sheep, Coordinate coordiante) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSheepAttack(Sheep sheep, Coordinate coordinate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sheep[] getAllSheeps() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

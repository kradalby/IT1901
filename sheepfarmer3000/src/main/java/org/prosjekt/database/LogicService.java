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
 * Interface tilbyr metoder for å manipulere backendløsning.
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public interface LogicService {

    public String addSheepMovement(Sheep sheep,Coordinate coordiante);
    public String addAttack(Sheep sheep, Coordinate coordiante);
    public List<Farmer> getAllFarmers();
    public Sheep[] getAllSheeps();
    public List<Integer> getFarmerids();

    public void addSheepMovements(List<Sheep> sheeps);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses;

import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public interface Sheep {
        public int getId();
	
	public boolean getAlive();
	public void setAlive(boolean status);
	
	public int getWeight();
	public void setWeigth(int weight);
	
	public DateTime getBirth();
        
        public List<Coordinate> getAllCoordinates();
        public void addCoordinate(Coordinate coordinate);
        public void setMostCurrentCoordinate(Coordinate coordinate);
}

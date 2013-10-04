/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses;

import org.joda.time.DateTime;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public interface Coordinate {
    public Integer getId();

    public String getLongitude();
    public void setLongitude(String longitude);
    
    public String getLatitude();
    public void setLatitude(String latitude);

    public DateTime getDate();
    public void setDate(DateTime date);

    public boolean isAttack();
    public void setAttack(boolean attack);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses.impl;

import org.prosjekt.helperclasses.Passhash;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class PassHashImpl implements Passhash{
     private String passhash;
    private int farmerid;

    public PassHashImpl(int farmerid) {
        this.farmerid = farmerid;
    }
    
    @Override
    public String getPasshash(int farmerid) {
        return passhash;
    }

    @Override
    public void setPasshash(int farmerid, String passhash) {
        this.passhash = passhash;
    }
    
}

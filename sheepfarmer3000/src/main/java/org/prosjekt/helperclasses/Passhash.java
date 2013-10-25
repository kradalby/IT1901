/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses;

import java.io.Serializable;


/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class Passhash implements Serializable{
     private String passhash;
    private int farmerid;

    public Passhash(int farmerid) {
        this.farmerid = farmerid;
    }
    
    public String getPasshash(int farmerid) {
        return passhash;
    }

    public void setPasshash(int farmerid, String passhash) {
        this.passhash = passhash;
    }
    
    public char[] toCharArray(){
        return passhash.toCharArray();
    }
    
}

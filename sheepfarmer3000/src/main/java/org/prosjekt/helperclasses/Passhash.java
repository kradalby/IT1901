/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses;

import java.io.Serializable;


/**
 * Klassen er ment å inneholde passord informasjon. 
 * Med kryptering bør salt legges inn her. 
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class Passhash implements Serializable{
     private String passhash;
    private int farmerid;

    /**
     *
     * @param farmerid
     */
    public Passhash(int farmerid) {
        this.farmerid = farmerid;
    }
    
    /**
     *
     * @return passord
     */
    public String getPasshash() {
        return passhash;
    }

    /**
     *
     * @param passhash
     */
    public void setPasshash(String passhash) {
        this.passhash = passhash;
    }
    
    /**
     *
     * @return passord som charArray. 
     */
    public char[] toCharArray(){
        return passhash.toCharArray();
    }
    
}

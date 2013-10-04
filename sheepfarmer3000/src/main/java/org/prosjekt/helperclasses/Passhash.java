/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public interface Passhash {
    public String getPasshash(int farmerid);
    public void setPasshash(int farmerid, String passhash);
}

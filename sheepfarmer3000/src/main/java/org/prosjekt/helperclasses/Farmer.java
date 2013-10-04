/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses;

import org.prosjekt.helperclasses.impl.SheepImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Christoffer Buvik christofferbuvik@gmail.com
 */
public interface Farmer {
    public int getId();
    public Passhash getPasshash();
    public void setPasshash(Passhash passhash);
    
    public String getEmail();
    public void setEmail(String email);
    
    public String getPhone();
    public void setPhone(String phone);

    public String getHelperFirstname();
    public void setHelperFirstname(String helperFirstname);

    public String getHelperLastName();
    public void setHelperLastName(String helperLastName);
    
    public String getHelperPhone();    
    public void setHelperPhone(String helperPhone);
    
    public String getHelperEmail();
    public void setHelperEmail(String helperEmail);
    
    
    public String getFirstName();
    public void setFirstName(String firstname);
    
    public String getLastName();
    public void setLastName(String lastname);
    
    public void addSheep(Sheep sheep);
    public List<Sheep> getSheeps();

    
}

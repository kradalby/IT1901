/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses;

import java.util.ArrayList;

/**
 *
 * @author Christoffer Buvik christofferbuvik@gmail.com
 */
public interface IFarmer {
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
    
    public int getId();
    
    public String getFirstName();
    
    public String getLastName(); 
    
    public ArrayList<Sheep> getSheeps();

    
}

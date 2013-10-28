/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses;

import java.util.UUID;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class Helper {
    private String id;
    private int farmerid;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;

    public Helper(int farmerid, String firstname, String lastname, String phone, String email) {
        id = UUID.randomUUID().toString();
        this.farmerid = farmerid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public int getFarmerid() {
        return farmerid;
    }

    @Override
    public String toString() {
        return "Helper{" + "id=" + id + ", farmerid=" + farmerid + ", firstname=" + firstname + ", lastname=" + lastname + ", phone=" + phone + ", email=" + email + '}';
    }
    
    

  
    
    
}

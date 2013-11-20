/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.helperclasses;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class Helper implements Serializable{
    private String id;
    private int farmerid;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;

    /**
     * Lager nytt Helper objekt, id blir generert. 
     * @param farmerid
     * @param firstname
     * @param lastname
     * @param phone
     * @param email
     */
    public Helper(int farmerid, String firstname, String lastname, String phone, String email) {
        id = UUID.randomUUID().toString();
        this.farmerid = farmerid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }
    
    /**
     * Lager nytt Helper objekt.
     * @param id
     * @param farmerid
     * @param firstname
     * @param lastname
     * @param phone
     * @param email
     */
    public Helper(String id, int farmerid, String firstname, String lastname, String phone, String email) {
        this.id = id;
        this.farmerid = farmerid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    /**
     *
     * @return firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Må inneholde landsnummer og kun tall 10 siffer. e.g 4712345678
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return id til farmer som denne hjelper er tilknyttet. 
     */
    public int getFarmerid() {
        return farmerid;
    }

    @Override
    public String toString() {
        return "Helper{" + "id=" + id + ", farmerid=" + farmerid + ", firstname=" + firstname + ", lastname=" + lastname + ", phone=" + phone + ", email=" + email + '}';
    }
    
    

  
    
    
}

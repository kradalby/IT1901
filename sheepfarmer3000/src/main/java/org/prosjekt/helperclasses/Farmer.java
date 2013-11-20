package org.prosjekt.helperclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Passhash;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Kenneth
 */
public class Farmer implements Serializable {
    private final int id; //brukes som brukernavn
    private Passhash passhash;
    private String firstName;
    private String lastName;
    private List<Sheep> sheeps;
   
    private String email;
    private String phone;
    private List<Helper> helpers;
    private List<Coordinate> coordinates; 

    
    /**
     * Lager nytt Farmer objekt. 
     * @param id
     */
    public Farmer(int id) {
        this.id = id;
    }
    
    /**
     * Lager nytt Farmer objekt. 
     * @param id 
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     */
    public Farmer(int id, String firstName, String lastName, String email, String phone) {
        helpers = new ArrayList<Helper>();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.sheeps = new ArrayList<Sheep>();
    }
    
    /**
     *
     * @return Passhash
     */
    public Passhash getPasshash(){
        return passhash;
     }
   
    /**
     *  
     * @param passhash
     */
    public void setPasshash(Passhash passhash){
        this.passhash = passhash;
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
     * @return id
     */
    public int getId() {
        return id;
    }
    
    /**
     *
     * @return firstname
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     *
     * @return lastname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return liste med sauer. 
     */
    public List<Sheep> getSheeps() {
        return sheeps;
    }
    
    /**
     *
     * @return liste med koordinater
     */
    public List<Coordinate> getCoordinates(){
        return coordinates;
    }
    
    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @param sheeps
     */
    public void setSheeps(List<Sheep> sheeps) {
        this.sheeps = sheeps;
    }

    /**
     *
     * @return
     */
    public List<Helper> getHelpers() {
        return helpers;
    }

    /**
     *
     * @param helpers
     */
    public void setHelpers(List<Helper> helpers) {
        this.helpers = helpers;
    }

    /**
     * 
     * @param coordinates
     */
    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Farmer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + email + ", phone=" + phone + '}';
    }

  

}


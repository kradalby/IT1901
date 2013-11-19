package org.prosjekt.helperclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Passhash;
import org.prosjekt.helperclasses.Sheep;

public class Farmer implements Serializable {
    private final int id; //brukes som brukernavn
    private Passhash passhash;
    private String firstName;
    private String lastName;
    private List<Sheep> sheeps;
   
    private String email;
    private String phone;
    //kontaktinfo til helper
    private List<Helper> helpers;
    private List<Coordinate> coordinates; //koordinatene til gaaren/teigen

    /*
     *  Temporary constructor, used under db development. 
     */
    public Farmer(int id) {
        this.id = id;
        
        
        
    }
    
    
    
    public Farmer(int id, String firstName, String lastName, String email, String phone) {
        helpers = new ArrayList<Helper>();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.sheeps = Collections.unmodifiableList(new ArrayList<Sheep>());
    }
    
   public Passhash getPasshash(){
        return passhash;
     }
   
    public void setPasshash(Passhash passhash){
        this.passhash = passhash;
    }
    
 
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public List<Sheep> getSheeps() {
        return sheeps;
    }
    
    public List<Coordinate> getCoordinates(){
        return coordinates;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSheeps(List<Sheep> sheeps) {
        this.sheeps = sheeps;
    }

    public List<Helper> getHelpers() {
        return helpers;
    }

    public void setHelpers(List<Helper> helpers) {
        this.helpers = helpers;
    }

    


    

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Farmer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + email + ", phone=" + phone + '}';
    }

  

}


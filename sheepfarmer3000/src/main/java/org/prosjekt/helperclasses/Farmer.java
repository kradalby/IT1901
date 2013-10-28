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
    private String helperFirstname;
    private String helperLastName;
    private String helperPhone;
    private String helperEmail;
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

  

    public static class Builder {

        public Builder(int id) {
            this.item = new Farmer(id);
        }
        private Farmer item;

        public Builder withFirstName(final String firstName) {
            this.item.firstName = firstName;
            return this;
        }

        public Builder withLastName(final String lastName) {
            this.item.lastName = lastName;
            return this;
        }

        public Builder withSheeps(final List<Sheep> sheeps) {
            this.item.sheeps = sheeps;
            return this;
        }

        public Builder withEmail(final String email) {
            this.item.email = email;
            return this;
        }

        public Builder withPhone(final String phone) {
            this.item.phone = phone;
            return this;
        }

        public Builder withHelperFirstname(final String helperFirstname) {
            this.item.helperFirstname = helperFirstname;
            return this;
        }

        public Builder withHelperLastName(final String helperLastName) {
            this.item.helperLastName = helperLastName;
            return this;
        }

        public Builder withHelperPhone(final String helperPhone) {
            this.item.helperPhone = helperPhone;
            return this;
        }

        public Builder withHelperEmail(final String helperEmail) {
            this.item.helperEmail = helperEmail;
            return this;
        }

        public Builder withCordinates(final List<Coordinate> cordinates) {
            this.item.coordinates = cordinates;
            return this;
        }

        public Farmer build() {
            return this.item;
        }
    }
    
    

}


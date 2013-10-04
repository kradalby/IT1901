package org.prosjekt.helperclasses.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Passhash;
import org.prosjekt.helperclasses.Sheep;

public class FarmerImpl implements Serializable,Farmer {
    private final int id; //brukes som brukernavn
    private Passhash passhash;
    private String firstName;
    private String lastName;
    private List<Sheep> sheeps;
   
    private String email;
    private String phone;
    //kontaktinfo til helper
    private String helperFirstname;
    private String helperLastName;
    private String helperPhone;
    private String helperEmail;
    private List<Coordinate> cordinates; //koordinatene til gaaren/teigen

    /*
     *  Temporary constructor, used under db development. 
     */
    public FarmerImpl(int id) {
        this.id = id;
        
        
        
    }
    
    
    
    public FarmerImpl(int id, String firstName, String lastName, String email, String phone, String helperFirstName, String helperLastName, String helperPhone, String helperEmail) {
        
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.helperFirstname = helperFirstName;
        this.helperLastName = helperLastName;
        this.helperPhone = helperPhone;
        this.helperEmail = helperEmail;
        this.sheeps = Collections.unmodifiableList(new ArrayList<Sheep>());
    }
    
   @Override 
   public Passhash getPasshash(){
        return passhash;
     }
   
   @Override
    public void setPasshash(Passhash passhash){
        this.passhash = passhash;
    }
    
 
    @Override
    public String getEmail() {
        return email;
    }
    
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String getPhone() {
        return phone;
    }
    
    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getHelperFirstname() {
        return helperFirstname;
    }

    @Override
    public void setHelperFirstname(String helperFirstname) {
        this.helperFirstname = helperFirstname;
    }

    @Override
    public String getHelperLastName() {
        return helperLastName;
    }

    @Override
    public void setHelperLastName(String helperLastName) {
        this.helperLastName = helperLastName;
    }
    
    @Override
    public String getHelperPhone() {
        return helperPhone;
    }
    
    @Override
    public void setHelperPhone(String helperPhone) {
        this.helperPhone = helperPhone;
    }
    
    @Override
    public String getHelperEmail() {
        return helperEmail;
    }
    
    @Override
    public void setHelperEmail(String helperEmail) {
        this.helperEmail = helperEmail;
    }
    
    @Override
    public int getId() {
        return id;
    }
    
    @Override
    public String getFirstName() {
        return firstName;
    }
    
    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public List<Sheep> getSheeps() {
        return sheeps;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void addSheep(Sheep sheep) {
        this.sheeps = sheeps;
    }


    

    public void setCordinates(List<Coordinate> coordinates) {
        this.cordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Farmer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + email + ", phone=" + phone + '}';
    }

  

    public static class Builder {

        public Builder(int id) {
            this.item = new FarmerImpl(id);
        }
        private FarmerImpl item;

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
            this.item.cordinates = cordinates;
            return this;
        }

        public FarmerImpl build() {
            return this.item;
        }
    }
    
    

}


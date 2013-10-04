/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.entities;

import org.prosjekt.helperclasses.impl.FarmerImpl;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class FarmerEntity {
     private int id;
     private String firstname;
     private String lastname;
     private String hashpass;
     private String email;
     private String helperfirstname;
     private String helperlastname;


    public FarmerEntity(int id) {
        this.id = id;
    }

    public static FarmerEntity farmerToFarmerEntity(FarmerImpl farmer){
        return new Builder(farmer.getId())
                .setFirstname(farmer.getFirstName())
                .setLastname(farmer.getLastName())
                .setEmail(farmer.getEmail())
                .build;
    }
    
    public static FarmerImpl farmerEntityToFarmer(FarmerEntity entity){
        return new FarmerImpl(entity.getId(), entity.getFirstname(), entity.getLastname(), entity.getEmail()
                , null, entity.getHelperfirstname(), entity.getHelperfirstlastname(), null, null);
    }
    
    public int getId() {
        return id;
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

    public String getHashpass() {
        return hashpass;
    }

    public void setHashpass(String hashpass) {
        this.hashpass = hashpass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHelperfirstname() {
        return helperfirstname;
    }

    public void setHelperfirstname(String helperfirstname) {
        this.helperfirstname = helperfirstname;
    }

    public String getHelperfirstlastname() {
        return helperlastname;
    }

    public void setHelperlastname(String helperlastname) {
        this.helperlastname = helperlastname;
    }
    
    
     public static Builder getBuilder(int i) {
        return new Builder(i);
    }
    
    public static Builder getBuilder(FarmerEntity entity) {
        return new Builder(entity);
    }
    
    public static class Builder{
        FarmerEntity build;
        
        public Builder(int id) {
            build = new FarmerEntity(id);
        }

        public Builder(FarmerEntity build) {
            this.build = build;
        }
        
        
        
        public Builder setId(int id) {
            build.id = id;
            return this;
        }
        
        public Builder setFirstname(String firstname) {
            build.firstname = firstname;
            return this;
        }
        
        public Builder setLastname(String lastname) {
            build.lastname = lastname;
            return this;
        }
        
        public Builder setHashpass(String hashpass) {
            build.hashpass = hashpass;
            return this;
        }
        
        public Builder setEmail(String email) {
            build.email = email;
            return this;
        }
        
        public Builder setHelperFirstname(String helperfirstname) {
            build.helperfirstname = helperfirstname;
            return this;
        }
        
        public Builder setHelperLastname(String input) {
            build.helperlastname = input;
            return this;
        }
        
        public FarmerEntity build(){
            return build;
        }
    }

    @Override
    public String toString() {
        return "FarmerEntity{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", hashpass=" + hashpass + ", email=" + email + ", helperfirstname=" + helperfirstname + ", helperlastname=" + helperlastname + '}';
    }
    
    
  
}

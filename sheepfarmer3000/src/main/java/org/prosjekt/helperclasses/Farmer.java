package org.prosjekt.helperclasses;

import java.util.ArrayList;

public class Farmer {
    private final int id; //brukes som brukernavn
    private String firstName;
    private String lastName;
    private ArrayList<Sheep> sheeps;
    private String password;
    private String email;
    private String phone;
    //kontaktinfo til helper
    private String helperFirstname;
    private String helperLastName;
    private String helperPhone;
    private String helperEmail;
    private ArrayList<Coordinate> cordinates; //koordinatene til gaaren/teigen

    /*
     *  Temporary constructor, used under db development. 
     */
    public Farmer(int id) {
        this.id = id;
        
    }
    
    
    
    public Farmer(int id, String firstName, String lastName, String password, String email, String phone, String helperFirstName, String helperLastName, String helperPhone, String helperEmail) {
        
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = PasswordHash.createHash(password);
        this.email = email;
        this.phone = phone;
        this.helperFirstname = helperFirstName;
        this.helperLastName = helperLastName;
        this.helperPhone = helperPhone;
        this.helperEmail = helperEmail;
        this.sheeps = new ArrayList<Sheep>();
    }
    
    public boolean isPassword(String password) {
        return PasswordHash.validatePassword(password, this.password);
    }
    
    /*
     * Sets password if correct current password is submitted.
     *
     * @param currentPassword, newPassword, to get new password, and to verify with the old.
     * @return returns true of the password verifies and ischanged and false if not.
     */
    
    public boolean setPassword(String currentPassword, String newPassword) {
        if (this.isPassword(currentPassword)) {
            this.password = PasswordHash.createHash(newPassword);
            return true;
        } else {
            return false;
        }
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

    public String getPassword() {
        return password;
    }

    public String getHelperFirstname() {
        return helperFirstname;
    }

    public String getHelperLastName() {
        return helperLastName;
    }

    public ArrayList<Coordinate> getCordinates() {
        return cordinates;
    }
    
    
    
    public String getHelperPhone() {
        return helperPhone;
    }
    
    public void setHelperPhone(String helperPhone) {
        this.helperPhone = helperPhone;
    }
    
    public String getHelperEmail() {
        return helperEmail;
    }
    
    public void setHelperEmail(String helperEmail) {
        this.helperEmail = helperEmail;
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
    
    public ArrayList<Sheep> getSheeps() {
        return sheeps;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSheeps(ArrayList<Sheep> sheeps) {
        this.sheeps = sheeps;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCordinates(ArrayList<Coordinate> cordinates) {
        this.cordinates = cordinates;
    }

    @Override
    public String toString() {
        return "Farmer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", email=" + email + ", phone=" + phone + '}';
    }
    
    

}


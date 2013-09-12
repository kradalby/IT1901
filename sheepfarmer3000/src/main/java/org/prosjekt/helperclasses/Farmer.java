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
	private String helperName; 
	private String helperPhone;
	private String helperEmail;
	private ArrayList<> cordinates; //koordinatene til gaaren/teigen
	
	public Farmer(int id, String firstName, String lastName, String password, String email, String phone, String helperName, String helperPhone, String helperEmail) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = PasswordHash.createHash(password);
		this.email = email;
		this.phone = phone;
		this.helperName = helperName;
		this.helperPhone = helperPhone;
		this.helperEmail = helperEmail;
		this.sheeps = new ArrayList<Sheep>();
	}
	
	
}


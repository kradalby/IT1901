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
	private String helper; //kontaktinfo til helper
	private ArrayList<> cordinates; //koordinatene til gaaren/teigen
	
	public Farmer(int id, String firstName, String lastName, String password, String email, String phone) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = hashPassword(password); //dummy funksjon
		this.email = email;
		this.phone = phone;
		this.sheeps = new ArrayList<Sheep>();
	}
}


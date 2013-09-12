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
	
	public Farmer(String name, int id) {
		this.name = name;
		this.id = id;
		this.sheeps = new ArrayList<Sheep>();
	}
}

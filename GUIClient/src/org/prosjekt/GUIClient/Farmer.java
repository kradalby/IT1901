package org.prosjekt.GUIClient;

import java.util.ArrayList;

public class Farmer {
	private final String id;
	private String firstName;
	private String lastName;
	private ArrayList<Sheep> sheeps;
	private char[] password;
	private String email;
	private String phone;
	private String helperFirstName;
	private String helperLastName;
	private String helperEmail;
	private String helperPhone;


	public Farmer(String firstName, String lastName, String id, char[] password, String email, String phone, String helperFirstName, String helperLastName,
			String helperEmail, String helperPhone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.sheeps = new ArrayList<Sheep>();
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.helperFirstName = helperFirstName;
		this.helperLastName = helperLastName;
		this.helperEmail = helperEmail;
		this.helperPhone = helperPhone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ArrayList<Sheep> getSheeps() {
		return sheeps;
	}

	public void setSheeps(ArrayList<Sheep> sheeps) {
		this.sheeps = sheeps;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
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


	public String getHelperFirstName() {
		return helperFirstName;
	}

	public void setHelperFirstName(String helperFirstName) {
		this.helperFirstName = helperFirstName;
	}

	public String getHelperLastName() {
		return helperLastName;
	}

	public void setHelperLastName(String helperLastName) {
		this.helperLastName = helperLastName;
	}

	public String getHelperEmail() {
		return helperEmail;
	}

	public void setHelperEmail(String helperEmail) {
		this.helperEmail = helperEmail;
	}

	public String getHelperPhone() {
		return helperPhone;
	}

	public void setHelperPhone(String helperPhone) {
		this.helperPhone = helperPhone;
	}

	public String getId() {
		return id;
	}

}

package org.prosjekt.helperclasses;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class Sheep {
	private final int id;
	private boolean alive;
	private int weight;
	private String attack;
	private final DateTime birth;
	private ArrayList<> cordinates;
	private Farmer farmer;

	public Sheep(int id, int weight, DateTime birth, Farmer farmer) {
		this.id = id;
		this.weight = weight;
		this.birth = birth;
		this.farmer = farmer;
	}



}

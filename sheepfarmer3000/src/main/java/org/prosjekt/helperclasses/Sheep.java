package org.prosjekt.helperclasses;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class Sheep {
	private final int id;
	private boolean alive;
	private int weight;
	private String attack;
	private DateTime birth;
	private ArrayList<> cordinates;
	private Farmer farmer;

	public Sheep(int id, String name, Farmer farmer, int weight) {
		this.id = id;
		this.farmer = farmer;
	}
}

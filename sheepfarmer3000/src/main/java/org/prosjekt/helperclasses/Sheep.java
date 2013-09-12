package org.prosjekt.helperclasses;

import java.util.ArrayList;

public class Sheep {
	private final int id;
	private boolean alive;
	private int weight;
	private String attack;
	private String birth;
	private ArrayList<> cordinates;
	private Farmer farmer;

	public Sheep(int id, String name, Farmer farmer) {
		this.id = id;
		this.name = name;
		this.farmer = farmer;
	}
}

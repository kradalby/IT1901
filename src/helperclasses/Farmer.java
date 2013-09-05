package helperclasses;

import java.util.ArrayList;

public class Farmer {
	private String name;
	private final int id;
	private ArrayList<Sheep> sheeps;
	
	public Farmer(String name, int id) {
		this.name = name;
		this.id = id;
		this.sheeps = new ArrayList<Sheep>();
	}
}

package org.prosjekt.helperclasses;

import java.io.Serializable;
import java.util.HashMap;

public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 11231235234L;
	private Sheep sheep;
	private Sheep[] sheeps;
	private HashMap<String,Object> items = new HashMap<String,Object>();
	
	
	public Sheep getSheep() {
		return sheep;
	}
	public void setSheep(Sheep sheep) {
		this.sheep = sheep;
	}
	public Sheep[] getSheeps() {
		return sheeps;
	}
	public void addSheeps(Sheep[] sheeps) {
		this.sheeps = sheeps;
	}
	
	
	public void addItem(String key, Object object) {
		this.items.put(key, object);
	}
	
	public Object getItem(String key) {
		return this.items.get(key);
	}
	
	
}

package org.prosjekt.helperclasses;

import java.io.Serializable;
import java.util.HashMap;

public class Request implements Serializable {

	/**
	 * I dont even...
	 */
	private static final long serialVersionUID = -15770597860985079L;
	private RequestEnum command;
	private HashMap<String, Object> items = new HashMap<String, Object>();
	
	
	
	public void setCommand(RequestEnum command) {
		this.command = command;
	}
	
	public RequestEnum getCommand() {
		return this.command;
	}
	
	public void addItem(String key, Object object) {
		this.items.put(key, object);
	}
	
	public Object getItem(String key) {
		return this.items.get(key);
	}
}



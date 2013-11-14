package org.prosjekt.gui;

import org.prosjekt.helperclasses.Coordinate;

public class AttackCoordinate extends Coordinate {
	private String sheepId;
	public AttackCoordinate(Coordinate coordinate, String sheepId){
		super(coordinate.getLon(), coordinate.getLat());
		this.sheepId = sheepId;
	}
	public String getSheepId(){
		return this.sheepId;
	}

}

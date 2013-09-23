package org.prosjekt.helperclasses;

import java.io.IOException;

public class ObjectCaster {
	static Sheep sheep = new Sheep(0, 0, null, null);
	static Farmer farmer = new Farmer(0);
	static Coordinate coord = new Coordinate();
	
	public static Object castObject(Object object) throws IOException {
		if (object.getClass() == sheep.getClass()) {
			Sheep obj;
			obj = (Sheep) object;
			return obj;
		} else if (object.getClass() == farmer.getClass()) {
			Farmer obj;
			obj = (Farmer) object;
			return obj;
		} else if (object.getClass() == coord.getClass()) {
			Coordinate obj;
			obj = (Coordinate) object;
			return obj;
		} else {
			throw new IOException("Object type not found!");
		}
	}
}

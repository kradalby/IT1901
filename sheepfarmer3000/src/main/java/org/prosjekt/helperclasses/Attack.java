package org.prosjekt.helperclasses;

import java.io.Serializable;

import org.joda.time.DateTime;

public class Attack implements Serializable {
	private Sheep sheep;
	private String latitude;
	private String longitude;
	private DateTime timestamp;
	
	public Attack(Sheep sheep, String latitude, String longitude) {
		this.sheep = sheep;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = DateTime.now();
	}

	public Sheep getSheep() {
		return sheep;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public DateTime getTimestamp() {
		return timestamp;
	}
}

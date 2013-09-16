package org.prosjekt.helperclasses;

import java.io.Serializable;

import org.joda.time.DateTime;

public class Attack implements Serializable {
	private Sheep sheep;
	private String latitude;
	private String longitude;
	private DateTime timestamp;
}

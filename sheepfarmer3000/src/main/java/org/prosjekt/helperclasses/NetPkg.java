package org.prosjekt.helperclasses;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author kradalby
 * Dette er en klasse som sendes mellom klient og server. Objektene vi vil 
 * sende(sheep, farmer samt login informasjon og annet) pakkes inn og legges 
 * i ArrayListen data og pakkes opp og utfores ved hjelp av enumen operation.
 * 
 * @param <T> holder for objektene som skal plasseres der.
 */
public class NetPkg<T> implements Serializable {

	NetPkgEnum operation;
	ArrayList<T> data;

	public NetPkg(NetPkgEnum o, ArrayList<T> d) {
		this.operation = o;
		this.data = d;
	}

	public NetPkgEnum getOperation() {
		return operation;
	}

	public ArrayList<T> getData() {
		return data;
	}
	
}

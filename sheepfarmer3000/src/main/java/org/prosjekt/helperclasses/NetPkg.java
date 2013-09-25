package org.prosjekt.helperclasses;

import java.io.Serializable;
import java.util.ArrayList;

public class NetPkg<T> implements Serializable {

	NetPkgEnum operaton;
	ArrayList<T> data;
	
}

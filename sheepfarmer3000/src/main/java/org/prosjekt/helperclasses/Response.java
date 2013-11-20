package org.prosjekt.helperclasses;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Response objekt
 * 
 * Denne klassen er laget som svar på request objekter. Den pakker inn dataen som skal sendes over nettverk.
 * 
 * @author Kristoffer Dalby <kradalby@kradalby.no>
 */

public class Response implements Serializable {


	private static final long serialVersionUID = 11231235234L;
	private Sheep sheep;
	private Sheep[] sheeps;
	private Farmer farmer;
	/**
	 * Dette er et hashmap som gjør det mulig å sende objekter av annen type en de
	 * predefinerte. Bruker f.eks. til login funksjonalitet.
	 */
	private HashMap<String,Object> items = new HashMap<String,Object>();
	
	
	/**
     *
     * @return Sheep objekt. 
     */
    public Sheep getSheep() {
		return sheep;
	}
	/**
     *
     * @param sheep
     */
    public void setSheep(Sheep sheep) {
		this.sheep = sheep;
	}
	/**
     *
     * @return array med Sheep objekter. 
     */
    public Sheep[] getSheeps() {
		return sheeps;
	}
	/**
     *
     * @param sheeps
     */
    public void addSheeps(Sheep[] sheeps) {
		this.sheeps = sheeps;
	}
	
	/**
     *
     * @param farmer
     */
    public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	
	/**
     *
     * @return Farmer
     */
    public Farmer getFarmer() {
		return this.farmer;
	}
	
	/**
	 * Metode som lar deg legge objekter til hashmappet med en nøkkel, nøkkelen vil
	 * nøkkelen vil indikere hva slags objekt det er snakk om så det blir brukt
	 * og kastet korrekt.
	 * @param key er nøkkelen som blir brukt for å lagre og hente frem objektet.
	 * @param object er objektet som skal sendes.
	 */
	public void addItem(String key, Object object) {
		this.items.put(key, object);
	}
	
	/**
     *
     * @param key
     * @return objekt som sendes. 
     */
    public Object getItem(String key) {
		return this.items.get(key);
	}
	
	/**
	 * @return vil gi true om det er en feil i response objektet som blir sent.
	 */
	public boolean isErrorInResponse() {
		return this.items.containsKey("error");
	}
	
}

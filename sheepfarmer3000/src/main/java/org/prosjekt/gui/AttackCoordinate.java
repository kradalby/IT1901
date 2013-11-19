package org.prosjekt.gui;

import org.prosjekt.helperclasses.Coordinate;

/**
 * Klasse som extender Coordinate med id til en sau Brukes for å knytte et
 * Coordinate opp mot et sheep objekt
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class AttackCoordinate extends Coordinate {

    private String sheepId;

    /**
     * Konstruktør, lager et nytt AttackCoordinate basert på et Coordinate
     * objekt og et Sheep objekt
     *
     * @param coordinate koordinatet som skal få et Sheep objektet tilknyttet
     * seg
     * @param sheepId Sheep-objektet som skal knyttes til koordinatet
     */
    public AttackCoordinate(Coordinate coordinate, String sheepId) {
        super(coordinate.getLon(), coordinate.getLat(), coordinate.getDate());
        this.sheepId = sheepId;
    }

    /**
     * Metode for å hente ut Sheepobjektet som er knyttet til dette objektet sin
     * id
     *
     * @return String Sheep-objektet sin id
     */
    public String getSheepId() {
        return this.sheepId;
    }

}

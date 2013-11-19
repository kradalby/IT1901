package org.prosjekt.gui;

import java.awt.Color;
import java.util.List;
import javax.swing.JTable;
import org.prosjekt.helperclasses.Coordinate;

/**
 * Klasse som extender JTabel, lager en tabell spesielt laget for å inneholde
 * Coordinate-objekter
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class CoordinateTable extends JTable {

    private Color darkGreen = new Color(51, 165, 0);
    private Color lightGreen = new Color(211, 255, 205);

    /**
     * Konstruktør, lager en tabell som er spesiallaget for å inneholde
     * Coordinate-objekter, og fyller tabellen med Coordinate-objekter
     *
     * @param coordinateList liste med Coordinate-objekter som tabellen skal
     * inneholde
     */
    public CoordinateTable(List<Coordinate> coordinateList) {
        super(new CoordinateTableModel(coordinateList));
        super.setAutoCreateRowSorter(true);
        super.setOpaque(false);
        this.getTableHeader().setBackground(darkGreen);
        super.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        setBackground(lightGreen);
    }

    /**
     * Bruker til å oppdatere informasjonen som vises i denne tabellen
     *
     * @param coordinateList liste med Coordinate-objekter som tabellen skal
     * inneholde
     */
    public void updateData(List<Coordinate> coordinateList) {
        this.setModel(new CoordinateTableModel(coordinateList));
    }
}

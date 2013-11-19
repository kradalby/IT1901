package org.prosjekt.gui;

import javax.swing.JTable;
import java.awt.Color;
import java.util.List;
import org.prosjekt.helperclasses.Sheep;

/**
 * Klasse som extender JTabel, lager en tabell spesielt laget for å inneholde
 * Sheep-objekter
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class SheepTable extends JTable {

    private Color darkGreen = new Color(51, 165, 0);
    private Color lightGreen = new Color(211, 255, 205);

    /**
     * Konstruktør, lager en tabell som er spesiallaget for å inneholde
     * Sheep-objekter, og fyller tabellen med Sheep-objekter
     *
     * @param sheepList liste med Sheep-objekter som tabellen skal inneholde
     */
    public SheepTable(List<Sheep> sheepList) {
        super(new SheepTableModel(sheepList));
        super.setAutoCreateRowSorter(true);
        super.setOpaque(false);
        this.getTableHeader().setBackground(darkGreen);
        super.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        setBackground(lightGreen);
    }
}

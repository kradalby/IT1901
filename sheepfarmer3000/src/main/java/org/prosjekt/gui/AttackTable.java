package org.prosjekt.gui;

import java.awt.Color;
import java.util.List;
import javax.swing.JTable;
import org.prosjekt.helperclasses.Sheep;

/**
 * Klasse som extender JTabel, spesielt laget for å inneholde holde
 * angrepsinformasjon
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class AttackTable extends JTable {

    private Color darkGreen = new Color(51, 165, 0);
    private Color lightGreen = new Color(211, 255, 205);

    /**
     * Konstruktør, lager en tabell med angrepsinformasjon fra Sheep-objekter
     *
     * @param sheepList liste med Sheep-objekter som fyller tabellen med
     * informasjon
     */
    public AttackTable(List<Sheep> sheepList) {
        super(new AttackTableModel(sheepList));
        super.setAutoCreateRowSorter(true);
        super.setOpaque(false);
        this.getTableHeader().setBackground(darkGreen);
        super.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        setBackground(lightGreen);
    }
}

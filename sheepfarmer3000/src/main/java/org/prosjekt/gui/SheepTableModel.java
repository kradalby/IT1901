package org.prosjekt.gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.prosjekt.helperclasses.Sheep;

/**
 * Klasse laget for at en tabell skal kunne håndtere Sheep-objekter
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class SheepTableModel extends AbstractTableModel {

    private List<Sheep> sheepList;
    private final String[] columns = {"Sheep ID", "Birth Date", "Longitude", "Latitude", "Status"};

    /**
     * Konstruktør, lager en TableModel som gjør at en tabell kan inneholde
     * Sheep-objekter, fyller tabellen med Sheep-objekter
     *
     * @param sheepList listen med Sheep-objekter som tabellen skal
     * vise informasjon om
     */
    public SheepTableModel(List<Sheep> sheepList) {
        this.sheepList = sheepList;

    }

    public String getColumnName(int col) {
        return columns[col].toString();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public int getRowCount() {
        return sheepList.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        Sheep currentSheep = sheepList.get(row);

        if (column == 0) {
            return currentSheep.getId();
        } else if (column == 1) {
            String str = Main.fmt.print(currentSheep.getBirth());
            return str;
        } else if (column == 2) {
            return currentSheep.getCurrentCordinate().getLon();
        } else if (column == 3) {
            return currentSheep.getCurrentCordinate().getLat();
        } else if (column == 4) {
            if (currentSheep.getAlive() == true) {
                return "alive";
            } else if (currentSheep.getAlive() == false) {
                return "dead";
            }
        }
        return null;
    }
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

}

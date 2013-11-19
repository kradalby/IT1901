package org.prosjekt.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Sheep;

/**
 * Klasse laget for at en tabell skal kunne håndtere Sheep-objekter
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class AttackTableModel extends AbstractTableModel {

    private List<AttackCoordinate> attackCoordinateList;
    private List<Sheep> sheepList;
    private final String[] columns = {"SheepID", "Longitude", "Latitude", "Time"};

    /**
     * Konstruktør som lager en TabelModel som kan håndtere Sheep-objekter
     *
     * @param sheepList list med Sheep-objekter som skal fylle tabellen
     */
    public AttackTableModel(List<Sheep> sheepList) {
        this.sheepList = sheepList;

        attackCoordinateList = new ArrayList<AttackCoordinate>();

        for (Sheep s : sheepList) {
            for (Coordinate c : s.getAttacks()) {
                AttackCoordinate a = new AttackCoordinate(c, s.getId());
                attackCoordinateList.add(a);
            }
        }
    }

    @Override
    public String getColumnName(int col) {
        return columns[col].toString();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public int getRowCount() {
        return attackCoordinateList.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        AttackCoordinate currentCoordinate = attackCoordinateList.get(row);

        if (column == 0) {
            return currentCoordinate.getSheepId();
        } else if (column == 1) {
            return currentCoordinate.getLat();
        } else if (column == 2) {
            return currentCoordinate.getLon();
        } else if (column == 3) {
            return Main.fmtCoord.print(currentCoordinate.getDate());
        }
        return null;
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

}

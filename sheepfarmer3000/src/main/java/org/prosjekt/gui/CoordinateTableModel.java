package org.prosjekt.gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.prosjekt.helperclasses.Coordinate;

/**
 * Klasse laget for at en tabell skal kunne håndtere Coordinate-objekter
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class CoordinateTableModel extends AbstractTableModel {

    private List<Coordinate> coordinateList;
    private final String[] columns = {"Longitude", "Latitude", "Time"};

    /**
     * Konstruktør, lager en TableModel som gjør at en tabell kan inneholde
     * Coordinate-objekter, fyller tabellen med Coordinate-objekter
     *
     * @param coordinateList listen med Coordinate-objekter som tabellen skal
     * vise informasjon om
     */
    public CoordinateTableModel(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;

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
        return coordinateList.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        Coordinate currentCoordinate = coordinateList.get(row);

        if (column == 0) {
            return currentCoordinate.getLat();
        } else if (column == 1) {
            return currentCoordinate.getLon();
        } else if (column == 2) {
            return Main.fmtCoord.print(currentCoordinate.getDate());
        }
        return null;
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}

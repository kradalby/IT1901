package org.prosjekt.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import org.prosjekt.helperclasses.Coordinate;

public class CoordinateTableModel extends AbstractTableModel {
	private List<Coordinate> coordinateList;
	private final String[] columns = {"Longitude", "Latitude", "Time"};
	
	
	public CoordinateTableModel(List<Coordinate> coordinateList){
		this.coordinateList = coordinateList;
		
	}
	
	
	public String getColumnName(int col){
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
		
		if (column == 0){
			return currentCoordinate.getLat();
		}
		else if (column == 1){
			return currentCoordinate.getLon();
		}
		else if (column == 2){
			return "implementer datetime";
		}
		return null;
	}
		
	public Class getColumnClass(int c){
		return getValueAt(0, c).getClass();
	}
}

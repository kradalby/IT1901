package org.prosjekt.gui;

import java.awt.Color;
import java.util.List;

import javax.swing.JTable;
import org.prosjekt.helperclasses.Coordinate;

public class CoordinateTable extends JTable{
	private Color darkGreen =  new Color(51,165,0);
	private Color lightGreen = new Color(211,255,205);
	
	public CoordinateTable(List<Coordinate> coordinateList){
		super(new CoordinateTableModel(coordinateList));
		super.setAutoCreateRowSorter(true);
		super.setOpaque(false);
		this.getTableHeader().setBackground(darkGreen);
		super.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		setBackground(lightGreen);
	}
	public void updateData(List<Coordinate> coordinateList){
		this.setModel(new CoordinateTableModel(coordinateList));
	}
}

package org.prosjekt.gui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.prosjekt.helperclasses.Sheep;

public class SheepTable extends JTable {
	private Color darkGreen =  new Color(51,165,0);
	private Color lightGreen = new Color(211,255,205);
	
	public SheepTable(List<Sheep> sheepList){
		super(new SheepTableModel(sheepList));
		super.setAutoCreateRowSorter(true);
		super.setOpaque(false);
		this.getTableHeader().setBackground(darkGreen);
		super.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		setBackground(lightGreen);
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.prosjekt.gui;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.prosjekt.helperclasses.Helper;

/**
 *
 * @author eier
 */
public class CustomComboBoxRenderer extends JLabel implements ListCellRenderer<Helper> {
    public CustomComboBoxRenderer(){
		setOpaque(true);
	}
	@Override
	public Component getListCellRendererComponent(JList<? extends Helper> list,
			Helper value, int index, boolean isSelected, boolean cellHasFocus) {
		Helper selectedHelper = (Helper)value;
		String firstName = selectedHelper.getFirstname();
		String lastName = selectedHelper.getLastname();
		setText(firstName+" "+lastName);
		
		if(isSelected){
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		}
		else{
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		
		
		
		return this;
	}

}


package org.prosjekt.gui;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.prosjekt.helperclasses.Helper;

/**
 * Klasse som gjør det mulig for en JComboBox å inneholde Helper-objekter,
 * sørger for at riktig informasjon fra et Helper-objekt blir vist i listen
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class CustomComboBoxRenderer extends JLabel implements ListCellRenderer<Helper> {

    /**
     * Konstruktør, lager en rendere som gjør at en JComboBox kan inneholde
     * Helper-objekter
     */
    public CustomComboBoxRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Helper> list,
            Helper value, int index, boolean isSelected, boolean cellHasFocus) {
        Helper selectedHelper = (Helper) value;
        String firstName = selectedHelper.getFirstname();
        String lastName = selectedHelper.getLastname();
        setText(firstName + " " + lastName);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }

}

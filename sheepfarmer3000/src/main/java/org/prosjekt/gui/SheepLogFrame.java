package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.prosjekt.client.ClientService;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

/**
 * Klasse som lager Sheep Log-vinduet. Dette vinduet inneholder informasjon om
 * historiske posisjoner til en sau
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class SheepLogFrame extends JFrame implements ActionListener {

    private static CoordinateTable coordinateTable;
    private Farmer user;
    private List<Sheep> sheepList;
    private Color textColor = new Color(32, 87, 0);
    private Font font = new Font("kalinga", Font.PLAIN, 16);
    private JComboBox chooser;
    private List<Coordinate> currentCoordinates;
    private int counter = 0;

    /**
     * Konstruktør, kaller metoder som lager innholdet til dette vinduet og
     * åpner Sheep Log-vinduet.
     *
     * @param user
     */
    public SheepLogFrame(Farmer user) {
        super("Sheep Log");
        super.setContentPane(new BackgroundPanel(ClientService.pathToBackGround()));
        setLayout(new BorderLayout());
        this.user = user;
        sheepList = this.user.getSheeps();

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(20, 80));
        topPanel.setOpaque(false);

        JLabel emptyTop = new JLabel("");
        emptyTop.setPreferredSize(new Dimension(20, 20));

        topPanel.add(emptyTop, BorderLayout.PAGE_START);
        topPanel.add(createChooser(), BorderLayout.CENTER);

        add(topPanel, BorderLayout.PAGE_START);

        add(createTablePanel(), BorderLayout.CENTER);

        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);

    }

    /**
     * Metode som oppdaterer data i listen
     */
    public void updateData() {
        //fireTableDataChanged();
    }

    /**
     * Metode som et JPanel som inneholder sauevelgeren til dette vinduet
     *
     * @return JPanel som inneholder en JComboBox chooser
     */
    private JPanel createChooser() {
        JPanel chooserPanel = new JPanel();
        chooserPanel.setPreferredSize(new Dimension(20, 50));
        chooserPanel.setOpaque(false);

        ArrayList<String> coordinateArrayList = new ArrayList<String>();
        for (Sheep s : user.getSheeps()) {
            coordinateArrayList.add(s.getId());
        }
        String[] coordinateArray = new String[user.getSheeps().size()];
        coordinateArrayList.toArray(coordinateArray);

        chooser = new JComboBox(coordinateArray);
        chooser.addActionListener(this);
        chooser.setSelectedIndex(0);

        JLabel chooseSheep = new JLabel("Choose sheep: ");
        chooseSheep.setPreferredSize(new Dimension(150, 40));
        chooseSheep.setFont(font);
        chooseSheep.setForeground(textColor);

        chooserPanel.add(chooseSheep);
        chooserPanel.add(chooser);
        return chooserPanel;

    }

    /**
     * Metode som lager et øverstenivås element som inneholder informasjon om
     * sauene sauen brukeren har valgt fra chooser
     *
     * @return et JPanel som inneholder et JScrollPane med en tabell i
     */
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setOpaque(false);

        tablePanel.add(createScrollPane(), BorderLayout.CENTER);

        return tablePanel;
    }

    /**
     * Metode som lager et JScrollPane inneholdene en tabell med hisotriske
     * koordinater til en sau
     *
     * @return JScrollPane som inneholder en tabell
     */
    private JScrollPane createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(createLogTable());

        scrollPane.setOpaque(false);

        return scrollPane;
    }

    /**
     * Metode som lager tabellen med informasjon om historiske posisjonsdata til
     * en sau
     *
     * @return SheepTable som inneholder sauene til brukeren
     */
    private JTable createLogTable() {
        coordinateTable = new CoordinateTable(this.currentCoordinates);
        coordinateTable.setOpaque(false);

        return coordinateTable;

    }

    /**
     * Metode som fyller tabellen med data fra en sau
     * @param sheepId Id til sauen som skal ha sine data vist på listen
     */
    private void createTable(String sheepId) {
        Sheep currentSheep = null;

        for (Sheep s : sheepList) {
            if (s.getId().equals(sheepId)) {
                currentSheep = ClientService.getSheepById(s.getId());
                break;
            }
        }

        currentCoordinates = currentSheep.getCordinates();
    }

    /**
     * Metode som oppdaterer data i tabellen
     * @param sheepId id til den nye sauen som skal ha sine data vist på listen
     */
    private void updateTable(String sheepId) {
        Sheep currentSheep = null;
        for (Sheep s : sheepList) {
            if (s.getId().equals(sheepId)) {
                currentSheep = ClientService.getSheepById(s.getId());
                break;
            }
        }
        currentCoordinates = currentSheep.getCordinates();
        coordinateTable.updateData(currentCoordinates);
    }

   /**
     * Metode som tar imot en ActionEvent fra knappene og listen i dette vinduet, og
     * utfører en handling basert på hva slags info ActionEventen har
     *
     * @param e ActionEvent som inneholder informasjon om hvilken knapp som er
     * trykket eller hvilken Sheep som er valgt i listen
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String sheepId = (String) cb.getSelectedItem();
        if (counter == 0) {
            createTable(sheepId);
            counter++;
        } else if (counter > 0) {
            updateTable(sheepId);
        }
    }
}

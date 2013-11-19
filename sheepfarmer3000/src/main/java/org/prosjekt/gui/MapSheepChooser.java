package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.prosjekt.client.ClientService;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

/**
 * Klasse som lager en sheep choser, denne brukes til å velge hvilken sau som
 * vises på kartet når kartet skal vise kun en sau
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class MapSheepChooser extends JFrame implements ActionListener {

    private Farmer user;
    private JComboBox chooser;
    private Font font = new Font("kalinga", Font.PLAIN, 18);
    private Font headerFont = new Font("kalinga", Font.PLAIN, 22);
    private Color textColor = new Color(32, 87, 0);
    private static String OK = "ok";
    private static String CANCEL = "cancel";
    private Sheep currentSheep;

    /**
     * Konstruktør, åpner et SheepChooser-vindu, tar inn et Farmer-objekt som
     * sauene på listen hentes fra
     *
     * @param user Farmer-objekt som sauene som vises i nedtrekksmenyen hentes
     * fra
     */
    public MapSheepChooser(Farmer user) {
        super("Choose sheep");
        super.setContentPane(new BackgroundPanel(ClientService.pathToBackGround()));
        setLayout(new FlowLayout());
        this.user = user;

        JLabel header = new JLabel("");
        header.setPreferredSize(new Dimension(10, 60));
        header.setFont(headerFont);
        header.setForeground(textColor);
        add(header, BorderLayout.PAGE_START);
        createContentPanel();
        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 100);
        setVisible(true);
    }

    /**
     * Metode som fyller vinduet med GUI-elementer
     */
    private void createContentPanel() {
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        contentPanel.setOpaque(false);

        JLabel selectSheep = new JLabel("Sheep");
        selectSheep.setFont(font);
        selectSheep.setForeground(textColor);

        JButton okButton = new JButton(OK);
        okButton.setOpaque(false);
        okButton.setPreferredSize(new Dimension(60, 35));
        okButton.setForeground(textColor);
        okButton.setBackground(textColor);
        okButton.setFont(font);
        okButton.setActionCommand(OK);
        okButton.addActionListener(this);

        JButton cancelButton = new JButton(CANCEL);
        cancelButton.setPreferredSize(new Dimension(95, 35));
        cancelButton.setOpaque(false);
        cancelButton.setForeground(textColor);
        cancelButton.setBackground(textColor);
        cancelButton.setFont(font);
        cancelButton.setActionCommand(CANCEL);
        cancelButton.addActionListener(this);

        contentPanel.add(selectSheep);
        contentPanel.add(createChooser());
        contentPanel.add(okButton);
        contentPanel.add(cancelButton);

        add(contentPanel, BorderLayout.CENTER);

    }

    /**
     * Metode som lager sauevelgeren
     *
     * @return JComboBox som inneholder denne brukerens sauer
     */
    private JComboBox createChooser() {
        ArrayList<String> sheepArrayList = new ArrayList<String>();

        for (Sheep s : user.getSheeps()) {
            sheepArrayList.add(s.getId());
        }

        String[] sheepArray = new String[user.getSheeps().size()];
        sheepArrayList.toArray(sheepArray);

        chooser = new JComboBox(sheepArray);

        chooser.addActionListener(this);
        chooser.setAlignmentY(LEFT_ALIGNMENT);
        chooser.setSelectedIndex(0);
        return chooser;
    }

    /**
     * Metode som henter sauen som er valgt i listen og returnerer den
     *
     * @return Sheep-objektet som er valgt fra listen
     */
    public Sheep getCurrentSheep() {
        return this.currentSheep;
    }

    /**
     * Metode som tar imot en ActionEvent fra knappene og listen til dette
     * vinduet, og utfører en handling basert på hva slags info ActionEventen
     * har
     *
     * @param e ActionEvent som inneholder informasjon om hva brukeren har
     * trykket
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals(OK)) {
            if (currentSheep == null) {
                return;
            }
            Sheep s = this.getCurrentSheep();
            Sheep swithcoords = ClientService.getSheepById(s.getId());
            MainPage.kart.showSheepPath(swithcoords);
            MainPage.kart.showAllAttacks(false);
            MainPage.kart.showAllSheep(false);

            MainPage.kart.refreshMap();
            this.dispose();
        } else if (cmd.equals(CANCEL)) {
            this.dispose();
        } else {
            JComboBox cb = (JComboBox) e.getSource();
            String sheepId = (String) cb.getSelectedItem();
            setCurrentSheep(sheepId);
        }

    }

    /**
     * Metode som setter currentSheep til et Sheep-objekt basert på en parameter
     * sheepId
     *
     * @param sheepId Id til Sheep-objektet som skal settes til currentSheep
     */
    private void setCurrentSheep(String sheepId) {

        for (Sheep s : user.getSheeps()) {
            if (sheepId.equals(s.getId())) {
                currentSheep = s;
            }
        }
    }

}

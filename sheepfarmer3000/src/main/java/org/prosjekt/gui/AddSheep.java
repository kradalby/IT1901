package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.joda.time.DateTime;
import org.prosjekt.client.ClientService;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;
import org.prosjekt.logic.RandomSheepGenerator;

/**
 * Denne klassen lager Add Sheep vinduet til GUI, Add Sheep vinduet gjør det
 * mulig å registrere en ny sau på en bruker
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class AddSheep extends JFrame implements ActionListener {

    private static String OK = "ok";
    private static String CANCEL = "cancel";
    private Farmer user;
    private Font headerFont = new Font("kalinga", Font.PLAIN, 24);
    private Font smallHeaderFont = new Font("kalinga", Font.PLAIN, 22);
    private Font font = new Font("kalinga", Font.PLAIN, 17);
    private Font fontTextField = new Font("kalinga", Font.PLAIN, 12);
    private Color textColor = new Color(32, 87, 0);
    private String lat;
    private String lon;

    private JTextField idField;
    private JTextField longitudeField;
    private JTextField latitudeField;
    private JTextField birthField;

    /**
     * Konstruktør 1, åpner et add sheep vindu, denne kalles fra menyen i GUI
     *
     * @param user brukeren som skal legge til en sheep i sin liste
     */
    public AddSheep(Farmer user) {
        super("Add new sheep");
        super.setContentPane(new BackgroundPanel(ClientService.pathToBackGround()));
        setLayout(new BorderLayout());
        this.user = user;
        RandomSheepGenerator rsg = new RandomSheepGenerator(user.getCoordinates(), user);
        Coordinate newcoord = rsg.generateCoords(1).get(0);
        this.lat = String.valueOf(newcoord.getLat());
        this.lon = String.valueOf(newcoord.getLon());

        createContentPanel();
        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setVisible(true);

    }

    /**
     * Konstruktør 2, åpner et add sheep vindu, denne kalles fra kartet
     *
     * @param user brukeren som skal legge til en sheep i sin liste
     * @param lat latitude koordinat som add sheep vinduet skal initialiseres
     * med
     * @param lon longitude koordinat som add sheep vinduet skal initialiseres
     * med
     */
    public AddSheep(Farmer user, double lat, double lon) {
        super("Add new sheep");
        super.setContentPane(new BackgroundPanel(ClientService.pathToBackGround()));
        setLayout(new BorderLayout());
        this.user = user;
        this.lat = Double.toString(lat);
        this.lon = Double.toString(lon);

        createContentPanel();
        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setVisible(true);

    }

    /**
     * Metode som fyller vinduet med en gjennomsiktig ramme med GUI-elementene i
     * midten
     */
    private void createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BorderLayout());
        Dimension emptyBorders = new Dimension(40, 30);

        JLabel emptyTop = new JLabel("");
        emptyTop.setOpaque(false);
        emptyTop.setPreferredSize(emptyBorders);

        JLabel emptyLeft = new JLabel("");
        emptyLeft.setOpaque(false);
        emptyLeft.setPreferredSize(emptyBorders);

        JLabel emptyRight = new JLabel("");
        emptyRight.setOpaque(false);
        emptyRight.setPreferredSize(emptyBorders);

        JLabel emptyBottom = new JLabel("");
        emptyBottom.setOpaque(false);
        emptyBottom.setPreferredSize(emptyBorders);

        contentPanel.add(emptyTop, BorderLayout.PAGE_START);
        contentPanel.add(emptyLeft, BorderLayout.LINE_START);
        contentPanel.add(emptyRight, BorderLayout.LINE_END);
        contentPanel.add(emptyBottom, BorderLayout.PAGE_END);

        contentPanel.add(createContent());

        add(contentPanel);
    }

    /**
     * Metode som lager et JPanel som inkluderer alle GUI-elementene til dette
     * vinduet
     *
     * @return JPanel som inkluderer alle GUI-elementene til dette vinduet
     */
    private JPanel createContent() {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setOpaque(false);

        content.add(createLeftSide(), BorderLayout.LINE_START);
        content.add(createRightSide(), BorderLayout.LINE_END);
        content.add(createButtons(), BorderLayout.PAGE_END);

        return content;
    }

    /**
     * Metode som lager et JPanel som inkluderer GUI-elementene på venstre side
     * i dette vinduet
     *
     * @return JPanel som inkluderer GUI-elementene på venstre side i dette
     * vinduet
     */
    private JPanel createLeftSide() {
        JPanel leftSide = new JPanel();
        leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
        leftSide.setOpaque(false);
        leftSide.setPreferredSize(new Dimension(180, 100));

        JLabel idLabel = new JLabel("Sheep ID:");
        idLabel.setFont(font);
        idLabel.setForeground(textColor);

        JLabel longitudeLabel = new JLabel("Longitude:");
        longitudeLabel.setFont(font);
        longitudeLabel.setForeground(textColor);

        JLabel latitudeLabel = new JLabel("Latitude:");
        latitudeLabel.setFont(font);
        latitudeLabel.setForeground(textColor);

        JLabel birthLabel = new JLabel("Birth date:");
        birthLabel.setFont(font);
        birthLabel.setForeground(textColor);

        idLabel.setAlignmentY(LEFT_ALIGNMENT);
        leftSide.add(idLabel);

        longitudeLabel.setAlignmentY(LEFT_ALIGNMENT);
        leftSide.add(longitudeLabel);

        latitudeLabel.setAlignmentY(LEFT_ALIGNMENT);
        leftSide.add(latitudeLabel);

        birthLabel.setAlignmentY(LEFT_ALIGNMENT);
        leftSide.add(birthLabel);

        return leftSide;
    }

    /**
     * Metode som lager et JPanel som inkluderer GUI-elementene på høyre side i
     * dette vinduet
     *
     * @return JPanel som inkluderer GUI-elementene på høyre side i dette
     * vinduet
     */
    private JPanel createRightSide() {
        JPanel rightSide = new JPanel();
        rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
        rightSide.setOpaque(false);
        rightSide.setPreferredSize(new Dimension(180, 100));

        idField = new JTextField();
        idField.setOpaque(false);
        idField.setForeground(textColor);
        idField.setFont(fontTextField);
        idField.setActionCommand(OK);
        idField.addActionListener(this);

        longitudeField = new JTextField(lon);
        longitudeField.setOpaque(false);
        longitudeField.setForeground(textColor);
        longitudeField.setFont(fontTextField);
        longitudeField.setActionCommand(OK);
        longitudeField.addActionListener(this);

        latitudeField = new JTextField(lat);
        latitudeField.setOpaque(false);
        latitudeField.setForeground(textColor);
        latitudeField.setFont(fontTextField);
        latitudeField.setActionCommand(OK);
        latitudeField.addActionListener(this);
        RandomSheepGenerator rsg = new RandomSheepGenerator(user.getCoordinates(), user);
        Coordinate newcoord = rsg.generateCoords(1).get(0);
        longitudeField.setText(String.valueOf(newcoord.getLon()));
        latitudeField.setText(String.valueOf(newcoord.getLat()));

        birthField = new JTextField("dd.mm.yyyy");
        birthField.setOpaque(false);
        birthField.setForeground(textColor);
        birthField.setFont(fontTextField);
        birthField.setActionCommand(OK);
        birthField.addActionListener(this);

        idField.setAlignmentY(LEFT_ALIGNMENT);
        rightSide.add(idField);

        longitudeField.setAlignmentY(LEFT_ALIGNMENT);
        rightSide.add(longitudeField);

        latitudeField.setAlignmentY(LEFT_ALIGNMENT);
        rightSide.add(latitudeField);

        birthField.setAlignmentY(LEFT_ALIGNMENT);
        rightSide.add(birthField);

        return rightSide;
    }

    /**
     * Lager knappene til dette vinduet
     *
     * @return JPanel knappene i dette vinduet
     */
    private JPanel createButtons() {
        JPanel buttons = new JPanel();
        buttons.setLayout(new BorderLayout());
        buttons.setOpaque(false);
        buttons.setPreferredSize(new Dimension(180, 50));

        JLabel emptyLeft = new JLabel("");
        emptyLeft.setPreferredSize(new Dimension(130, 10));

        buttons.add(emptyLeft, BorderLayout.LINE_START);

        JPanel insideButtons = new JPanel();
        insideButtons.setOpaque(false);

        JButton okButton = new JButton(OK);
        okButton.setOpaque(false);
        okButton.setForeground(textColor);
        okButton.setBackground(textColor);
        okButton.setFont(font);
        okButton.setActionCommand(OK);
        okButton.addActionListener(this);

        JButton cancelButton = new JButton(CANCEL);
        cancelButton.setOpaque(false);
        cancelButton.setForeground(textColor);
        cancelButton.setBackground(textColor);
        cancelButton.setFont(font);
        cancelButton.setActionCommand(CANCEL);
        cancelButton.addActionListener(this);

        insideButtons.add(okButton, BorderLayout.LINE_START);
        insideButtons.add(cancelButton, BorderLayout.LINE_END);

        buttons.add(insideButtons, BorderLayout.CENTER);

        return buttons;
    }

    /**
     * Metode som tar imot en ActionEvent fra knappene til dette vinduet, og
     * utfører en handling basert på hva slags info ActionEventen har
     *
     * @param e ActionEvent som inneholder informasjon om hvilken knapp som er
     * trykket
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (OK.equals(cmd)) {
            DateTime dt = null;
            try {
                dt = Main.fmt.parseDateTime(birthField.getText());
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(this, "Wrong format on birth date.",
                        "", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!saveChanges(dt)) {
                JOptionPane.showMessageDialog(this, "Changes were not saved! Please try again.",
                        "", JOptionPane.ERROR_MESSAGE);
            } else {
                Main.updateMainUser(user);
                MainPage.kart.refreshMap();
                this.dispose();
            }
        } else if (CANCEL.equals(cmd)) {
            this.dispose();
        }

    }

    /**
     * Metode som lagrer den nye sauen til brukeren, returnerer informasjon om
     * lagringen ble utført eller ikke
     *
     * @param dt DateTime, fødselsdagen til sauen, skrevet inn av brukeren i GUI
     * @return boolean som beskriver om lagringen ble utført
     */
    private boolean saveChanges(DateTime dt) {
        if (testIfValidId()) {
            Coordinate c = null;
            try {
                c = new Coordinate(Double.parseDouble(latitudeField.getText()),
                        Double.parseDouble(longitudeField.getText()));
            } catch (NumberFormatException e) {
                return false;
            }

            Sheep newSheep = new Sheep(idField.getText(), dt, user.getId(), c);
            newSheep.setAlive(true);

            if (ClientService.addSheep(newSheep)) {
                user.getSheeps().add(newSheep);
                MainPage.kart.refreshMap();
                dispose();
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Metode som sjekker om id som brukeren har valgt til sin nye sau ikke
     * eksisterer fra før av, returnerer informasjon om id eksisterer fra før av
     * eller ikke
     *
     * @return boolean som beskriver om id eksisterer fra før eller ikke
     */
    private boolean testIfValidId() {
        boolean tester = true;
        for (Sheep s : user.getSheeps()) {
            if (s.getId().equals(idField.getText())) {
                tester = false;
            }
        }
        return tester;
    }

}

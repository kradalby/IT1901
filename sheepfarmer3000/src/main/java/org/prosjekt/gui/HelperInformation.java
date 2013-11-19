package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.prosjekt.client.ClientService;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Helper;

/**
 * Klasse som lager HelperInformation-vinduet til GUI, et vindu som inneholder
 * informasjon om en brukers registrerte hjelpere
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class HelperInformation extends JFrame implements ActionListener {

    private static String SAVE = "save";
    private static String CLOSE = "close";
    private static String REMOVE = "remove";
    private static String NEWHELPER = "newHelper";
    private Farmer user;
    private Helper currentHelper;
    private Font font = new Font("kalinga", Font.PLAIN, 17);
    private Font fontTextField = new Font("kalinga", Font.PLAIN, 12);
    private Font buttonFont = new Font("kalinga", Font.PLAIN, 15);
    private Color textColor = new Color(32, 87, 0);
    /**
     * Felt som inneholder informasjon om fornavnet til en hjelper
     */
    private JTextField firstNameField;
    /**
     * Felt som inneholder informasjon om etternavnet til en hjelper
     */
    private JTextField lastNameField;
    /**
     * Felt som inneholder informasjon om telefonnummeret til en hjelper
     */
    private JTextField phoneField;
    /**
     * Felt som inneholder epost-adressen til en hjelper
     */
    private JTextField emailField;
    /**
     * JComboBox som brukes til å velge hvilken hjelper informasjonen vist i
     * feltene i GUI skal hentes fra
     */
    private JComboBox<Helper> chooser;
    private DefaultComboBoxModel<Helper> comboBoxModel;
    private int currentlySelectedIndex;
    private boolean newHelperListener;

    /**
     * Konstruktør, åpner helper informastion-vinudet til GUI, og viser
     * informasjon om hjelperne til en bruker
     *
     * @param user Farmer-objekt som informasjon om hjelperne til brukeren
     * hentes fra
     */
    public HelperInformation(Farmer user) {
        super("Helper Information");
        super.setContentPane(new BackgroundPanel(ClientService.pathToBackGround()));
        setLayout(new BorderLayout());
        this.user = user;
        currentlySelectedIndex = 0;
        newHelperListener = false;
        createContentPanel();

        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 280);
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
     * Metode som lager GUI-elementene i midten av vinduet
     *
     * @return JPanel som inneholder GUI-elementene
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
     * Metode som initialiserer feltet chooser
     *
     * @return JComboBox som inneholder Helpere-objektene registrert på denne
     * brukeren
     */
    private JComboBox<Helper> createChooser() {

        chooser = new JComboBox<Helper>();
        comboBoxModel = new DefaultComboBoxModel<Helper>();
        fillChooser();

        chooser.addActionListener(this);
        chooser.setAlignmentY(LEFT_ALIGNMENT);
        chooser.setSelectedIndex(0);
        return chooser;
    }

    /**
     * Metode som fyller feltet chooser med denne brukerens Helper-objekter
     */
    private void fillChooser() {
        for (Helper h : user.getHelpers()) {
            System.out.println(h);
            comboBoxModel.addElement(h);
        }
        chooser.setModel(comboBoxModel);
        chooser.setRenderer(new CustomComboBoxRenderer());
    }

    /**
     * Metode som oppdaterer tekstenfeltene som viser informasjon om en hjelper
     * i vinduet.
     */
    private void updateView() {

        firstNameField.setText(currentHelper.getFirstname());
        lastNameField.setText(currentHelper.getLastname());
        phoneField.setText(currentHelper.getPhone());
        emailField.setText(currentHelper.getEmail());

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

        JLabel chooser = new JLabel("Choose Helper: ");
        chooser.setFont(font);
        chooser.setForeground(textColor);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(font);
        firstNameLabel.setForeground(textColor);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(font);
        lastNameLabel.setForeground(textColor);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(font);
        phoneLabel.setForeground(textColor);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(font);
        emailLabel.setForeground(textColor);

        chooser.setAlignmentY(LEFT_ALIGNMENT);
        leftSide.add(chooser);

        firstNameLabel.setAlignmentY(LEFT_ALIGNMENT);
        leftSide.add(firstNameLabel);

        lastNameLabel.setAlignmentY(LEFT_ALIGNMENT);
        leftSide.add(lastNameLabel);

        phoneLabel.setAlignmentY(LEFT_ALIGNMENT);
        leftSide.add(phoneLabel);

        emailLabel.setAlignmentY(LEFT_ALIGNMENT);
        leftSide.add(emailLabel);

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

        firstNameField = new JTextField();
        firstNameField.setOpaque(false);
        firstNameField.setForeground(textColor);
        firstNameField.setFont(fontTextField);
        firstNameField.setActionCommand(SAVE);
        firstNameField.addActionListener(this);

        lastNameField = new JTextField();
        lastNameField.setOpaque(false);
        lastNameField.setForeground(textColor);
        lastNameField.setFont(fontTextField);
        lastNameField.setActionCommand(SAVE);
        lastNameField.addActionListener(this);

        phoneField = new JTextField();
        phoneField.setOpaque(false);
        phoneField.setForeground(textColor);
        phoneField.setFont(fontTextField);
        phoneField.setActionCommand(SAVE);
        phoneField.addActionListener(this);

        emailField = new JTextField();
        emailField.setOpaque(false);
        emailField.setForeground(textColor);
        emailField.setFont(fontTextField);
        emailField.setActionCommand(SAVE);
        emailField.addActionListener(this);

        rightSide.add(createChooser());

        firstNameField.setAlignmentY(LEFT_ALIGNMENT);
        rightSide.add(firstNameField);

        lastNameField.setAlignmentY(LEFT_ALIGNMENT);
        rightSide.add(lastNameField);

        phoneField.setAlignmentY(LEFT_ALIGNMENT);
        rightSide.add(phoneField);

        emailField.setAlignmentY(LEFT_ALIGNMENT);
        rightSide.add(emailField);

        return rightSide;
    }

    /**
     * Lager knappene til dette vinduet
     *
     * @return JPanel knappene i dette vinduet
     */
    private JPanel createButtons() {
        JPanel buttons = new JPanel();
        BorderLayout customLayout = new BorderLayout();
        customLayout.setHgap(20);
        buttons.setLayout(customLayout);
        buttons.setOpaque(false);
        buttons.setPreferredSize(new Dimension(180, 50));

        JLabel emptyTop = new JLabel("");
        JLabel emptyBottom = new JLabel("");

        emptyTop.setPreferredSize(new Dimension(10, 5));
        emptyBottom.setPreferredSize(new Dimension(10, 5));

        JButton newHelper = new JButton("new");
        newHelper.setOpaque(false);
        newHelper.setForeground(textColor);
        newHelper.setBackground(textColor);
        newHelper.setFont(buttonFont);
        newHelper.setActionCommand(NEWHELPER);
        newHelper.addActionListener(this);

        JButton removeButton = new JButton("remove");
        removeButton.setOpaque(false);
        removeButton.setForeground(textColor);
        removeButton.setBackground(textColor);
        removeButton.setFont(buttonFont);
        removeButton.setActionCommand(REMOVE);
        removeButton.addActionListener(this);

        JButton saveButton = new JButton(SAVE);
        saveButton.setOpaque(false);
        saveButton.setForeground(textColor);
        saveButton.setBackground(textColor);
        saveButton.setFont(buttonFont);
        saveButton.setActionCommand(SAVE);
        saveButton.addActionListener(this);

        JButton closelButton = new JButton(CLOSE);
        closelButton.setOpaque(false);
        closelButton.setForeground(textColor);
        closelButton.setBackground(textColor);
        closelButton.setFont(buttonFont);
        closelButton.setActionCommand(CLOSE);
        closelButton.addActionListener(this);

        buttons.add(emptyTop, BorderLayout.PAGE_START);
        buttons.add(emptyBottom, BorderLayout.PAGE_END);

        JPanel helperButtons = new JPanel();
        BorderLayout helperButtonsLayout = new BorderLayout();
        helperButtonsLayout.setHgap(2);
        helperButtons.setLayout(helperButtonsLayout);
        helperButtons.setOpaque(false);

        JPanel saveButtons = new JPanel();
        BorderLayout saveButtonsLayout = new BorderLayout();
        saveButtonsLayout.setHgap(2);
        saveButtons.setLayout(saveButtonsLayout);
        saveButtons.setOpaque(false);

        helperButtons.add(newHelper, BorderLayout.LINE_START);
        helperButtons.add(removeButton, BorderLayout.LINE_END);

        saveButtons.add(saveButton, BorderLayout.LINE_START);
        saveButtons.add(closelButton, BorderLayout.LINE_END);

        buttons.add(helperButtons, BorderLayout.LINE_START);
        buttons.add(saveButtons, BorderLayout.LINE_END);

        return buttons;
    }

    /**
     * Metode som tar imot en ActionEvent fra knappene og listen i dette vinduet, og
     * utfører en handling basert på hva slags info ActionEventen har
     *
     * @param e ActionEvent som inneholder informasjon om hvilken knapp som er
     * trykket eller hvilken Helper som er valgt i listen
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (SAVE.equals(cmd)) {

            if (newHelperListener) {
                saveNewHelper();
                chooser.setSelectedIndex(0);
                newHelperListener = false;
            } else {
                saveHelperInfo();
            }

            if (!saveChanges()) {
                JOptionPane.showMessageDialog(this, "Changes were not saved! Please try again.",
                        "", JOptionPane.ERROR_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(this, "Changes were successfully saved!.",
                        "", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (CLOSE.equals(cmd)) {
            this.dispose();
        } else if (NEWHELPER.equals(cmd)) {
            newHelper();
        } else if (REMOVE.equals(cmd)) {
            removeHelper();
        } else {
            JComboBox<Helper> cb = (JComboBox<Helper>) e.getSource();
            Helper focusedHelper = (Helper) cb.getSelectedItem();
            System.out.println("focused helper: " + focusedHelper);
            currentHelper = focusedHelper;
            updateView();

        }

    }

    /**
     * Metode som lagrer informasjonen i tekstfeltene til brukeren kalles om en
     * brukeren har endret informasjonen som er registrert på en av brukerens
     * hjelpere
     */
    private void saveHelperInfo() {
        for (Helper h : user.getHelpers()) {
            if (h.getFirstname().equals(currentHelper.getFirstname())
                    && h.getLastname().equals(currentHelper.getLastname())) {

                h.setFirstname(firstNameField.getText());
                h.setLastname(lastNameField.getText());
                h.setEmail(emailField.getText());
                h.setPhone(phoneField.getText());
                currentHelper = h;
                break;
            }
        }
        System.out.println("Current id: " + currentHelper.getId());
        ClientService.updateHelper(currentHelper);
    }

    /**
     * Metode som fjerner en hjelper fra brukerens hjelperliste
     */
    private void removeHelper() {
        if (!(user.getHelpers().size() == 1)) {
            Helper tempHelper = (Helper) comboBoxModel.getSelectedItem();
            comboBoxModel.removeElement(tempHelper);

            user.getHelpers().remove(tempHelper);
            ClientService.removeHelper(tempHelper);
        } else {
            JOptionPane.showMessageDialog(this, "You must have at least one helper registered",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metode som gjør vinduet klart til å ta inn informasjon om en ny hjelper
     */
    private void newHelper() {
        chooser.setEnabled(false);
        newHelperListener = true;
        firstNameField.setText("");
        lastNameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    /**
     * Metode som lagrer den nye hjelperen og legger den til brukerens liste
     */
    private void saveNewHelper() {

        String fn = firstNameField.getText();
        String ln = lastNameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        Helper tempNewHelper = new Helper(user.getId(), fn, ln, phone, email);
        ClientService.addHelper(tempNewHelper);
        user.getHelpers().add(tempNewHelper);

        comboBoxModel.addElement(tempNewHelper);
        chooser.setModel(comboBoxModel);
        chooser.setRenderer(new CustomComboBoxRenderer());

    }

    /**
     * Metode som forsøker å lagre en endring gjort på hjelperne til denne
     * brukerne
     *
     * @return boolean med informasjon om lagringen ble gjennomført
     */
    private boolean saveChanges() {
        chooser.setEnabled(true);

        Farmer tempUser = user;

        if (Main.saveChangesToFarmer(tempUser)) {
            user = tempUser;

            return true;
        }

        return false;
    }

}

package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.prosjekt.client.ClientService;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

/**
 * Klasse som lager et Remove Sheep-vindu, fra dette vinduet kan brukeren fjerne
 * sauer fra sin saueliste
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class RemoveSheep extends JFrame implements ActionListener {

    private static String OK = "ok";
    private static String CANCEL = "cancel";
    private Farmer user;
    private Sheep currentSheep;
    private int sheepIndex;
    private Font font = new Font("kalinga", Font.PLAIN, 17);
    private Font fontTextField = new Font("kalinga", Font.PLAIN, 12);
    private Color textColor = new Color(32, 87, 0);
    private JTextField idField;
    private JTextField longitudeField;
    private JTextField latitudeField;
    private JTextField birthField;
    private JComboBox chooser;

    /**
     * Konstruktør 1, åpner et remove sheep-vindu, denne kalles fra menyen i GUI
     *
     * @param user brukeren som skal fjerne en sau fra sin liste
     */
    public RemoveSheep(Farmer user) {
        super("Remove sheep");
        super.setContentPane(new BackgroundPanel(ClientService.pathToBackGround()));
        setLayout(new BorderLayout());
        this.user = user;
        this.sheepIndex = 0;

        createContentPanel();
        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 280);
        setVisible(true);

    }

    /**
     * Konstruktør 2, åpner et remove sheep-vindu, denne kalles fra kartet ved
     * at brukeren trykker på en sau,
     *
     * @param user brukeren som skal fjerne en sau fra sin liste
     * @param sheepIndex indexnummeret til sauen brukeren trykket på
     */
    public RemoveSheep(Farmer user, int sheepIndex) {
        super("Remove sheep");
        super.setContentPane(new BackgroundPanel(ClientService.pathToBackGround()));
        setLayout(new BorderLayout());
        this.user = user;
        this.sheepIndex = sheepIndex;

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
     * Metode som lager sauevelgeren
     *
     * @return JComboBox med brukerens sauer
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
        chooser.setSelectedIndex(sheepIndex);
        return chooser;
    }

    /**
     * Metode som oppdaterer feltene i vinduet når brukeren velger en sau fra
     * listen
     *
     * @param sheepId id til sauen som bonder har valgt fra listen
     */
    private void updateView(String sheepId) {

        Sheep selectedSheep = null;

        for (Sheep s : user.getSheeps()) {
            if (s.getId().equals(sheepId)) {
                selectedSheep = s;
            }
        }

        idField.setText(selectedSheep.getId());
        String longitude = String.valueOf(selectedSheep.getCurrentCordinate().getLon());
        String latitude = String.valueOf(selectedSheep.getCurrentCordinate().getLat());
        longitudeField.setText(longitude);
        latitudeField.setText(latitude);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy");
        String date = fmt.print(selectedSheep.getBirth());
        birthField.setText(date);

        currentSheep = selectedSheep;	//trengs for at riktig sau skal bli slettet
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

        JLabel chooser = new JLabel("Choose Sheep: ");
        chooser.setFont(font);
        chooser.setForeground(textColor);

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

        chooser.setAlignmentY(LEFT_ALIGNMENT);
        leftSide.add(chooser);

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
        idField.setEditable(false);

        longitudeField = new JTextField();
        longitudeField.setOpaque(false);
        longitudeField.setForeground(textColor);
        longitudeField.setFont(fontTextField);
        longitudeField.setActionCommand(OK);
        longitudeField.addActionListener(this);
        longitudeField.setEditable(false);

        latitudeField = new JTextField();
        latitudeField.setOpaque(false);
        latitudeField.setForeground(textColor);
        latitudeField.setFont(fontTextField);
        latitudeField.setActionCommand(OK);
        latitudeField.addActionListener(this);
        latitudeField.setEditable(false);

        birthField = new JTextField("dd.mm.yyyy");
        birthField.setOpaque(false);
        birthField.setForeground(textColor);
        birthField.setFont(fontTextField);
        birthField.setActionCommand(OK);
        birthField.addActionListener(this);
        birthField.setEditable(false);

        rightSide.add(createChooser());

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
        if (OK.equals(cmd)) {
            if (!saveChanges(birthField.getText())) {	//husk at dataTime antagelig må legges til her her
                JOptionPane.showMessageDialog(this, "Changes were not saved! Please try again.",
                        "", JOptionPane.ERROR_MESSAGE);

            } else {
                SheepListFrame.updateData();
                Main.updateMainUser(user);
                MainPage.kart.refreshMap();
                this.dispose();
            }
        } else if (CANCEL.equals(cmd)) {
            this.dispose();
        } else {
            JComboBox cb = (JComboBox) e.getSource();
            String sheepId = (String) cb.getSelectedItem();
            updateView(sheepId);
        }
    }

    /**
     * Metode som lagrer endringer
     * @param date 
     * @return boolean med informasjon om endring ble lagret
     */
    private boolean saveChanges(String date) {
        List<Sheep> tempSheepList = user.getSheeps();
        Farmer tempUser = user;
        this.setVisible(false);
        for (Sheep s : tempSheepList) {
            if (s.equals(currentSheep)) {
                tempSheepList.remove(s);
                tempUser.setSheeps(tempSheepList);
                if (ClientService.removeSheep(s)) {
                    user = tempUser;
                    dispose();
                    MainPage.kart.refreshMap();
                    return true;
                }
            }
        }
        boolean success = ClientService.removeSheep(currentSheep);
        return success;
    }
}

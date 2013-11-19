package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.List;
import org.prosjekt.client.ClientService;
import org.prosjekt.gui.AddSheep;
import org.prosjekt.gui.BackgroundPanel;
import org.prosjekt.gui.RemoveSheep;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

/**
 * Klasse som lager Sheep List, dette vinduet inneholder en liste med en brukers
 * registrerte sauer
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class SheepListFrame extends JFrame implements ActionListener {

    private static JTable sheepTable;
    private Farmer currentUser;
    private List<Sheep> sheepList;
    private Font headerFont = new Font("kalinga", Font.PLAIN, 30);
    private Color textColor = new Color(32, 87, 0);
    private Font font = new Font("kalinga", Font.PLAIN, 16);
    private final static String addS = "addSheep";
    private final static String removeS = "removeSheep";
    private final static String close = "close";

    /**
     * Konstruktør, kaller metoder som lager innholdet til dette vinduet og
     * åpner Sheep List-vinduet. Viser informasjon om sauene til en bruker
     *
     * @param f brukeren som dette vinduet henter Sheep.objekter fra
     */
    public SheepListFrame(Farmer f) {
        super("Sheep Overwiew");
        super.setContentPane(new BackgroundPanel(ClientService.pathToBackGround()));
        setLayout(new BorderLayout());
        this.currentUser = f;
        sheepList = currentUser.getSheeps();

        add(createHeader(), BorderLayout.PAGE_START);

        add(SheepTablePanel(), BorderLayout.CENTER);

        add(createButtons(), BorderLayout.PAGE_END);

        JLabel emptyLeft = new JLabel("");
        emptyLeft.setOpaque(false);
        emptyLeft.setPreferredSize(new Dimension(100, 60));
        add(emptyLeft, BorderLayout.LINE_START);

        JLabel emptyRight = new JLabel("");
        emptyRight.setOpaque(false);
        emptyRight.setPreferredSize(new Dimension(100, 60));
        add(emptyRight, BorderLayout.LINE_END);

        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);

    }

    /**
     * Metode som oppdaterer data i listen
     */
    public static void updateData() {
        sheepTable.updateUI();
    }

    /**
     * Metode som lager en JPanel med overskriften til dette vinduet
     *
     * @return JPanel som inneholder overskriften til dette vinduet
     */
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(90, 150));
        header.setOpaque(false);

        JLabel emptyLeft = new JLabel("");
        emptyLeft.setPreferredSize(new Dimension(300, 100));
        header.add(emptyLeft, BorderLayout.LINE_START);

        JLabel emptyTop = new JLabel("");
        emptyTop.setPreferredSize(new Dimension(25, 35));
        header.add(emptyTop, BorderLayout.PAGE_START);

        JLabel headerLabel = new JLabel("Sheep Overview");
        headerLabel.setOpaque(false);
        headerLabel.setForeground(textColor);
        headerLabel.setFont(headerFont);
        header.add(headerLabel, BorderLayout.CENTER);

        JLabel emptyBottom = new JLabel("");
        emptyBottom.setPreferredSize(new Dimension(50, 15));
        header.add(emptyBottom, BorderLayout.PAGE_END);

        return header;
    }

    /**
     * Metode som lager en JPanel med knappen til dette vinduet
     *
     * @return JPanel med knappen til dette vinduet
     */
    private JPanel createButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setOpaque(false);
        Dimension buttonSize = new Dimension(120, 50);
        buttonPanel.setPreferredSize(new Dimension(10, 100));

        JButton addSheep = new JButton("add sheep");
        addSheep.setPreferredSize(buttonSize);
        addSheep.setOpaque(false);
        addSheep.setForeground(textColor);
        addSheep.setBackground(textColor);
        addSheep.setFont(font);
        addSheep.setActionCommand(addS);
        addSheep.addActionListener(this);

        JButton removeSheep = new JButton("remove sheep");
        removeSheep.setPreferredSize(buttonSize);
        removeSheep.setOpaque(false);
        removeSheep.setForeground(textColor);
        removeSheep.setBackground(textColor);
        removeSheep.setFont(font);
        removeSheep.setActionCommand(removeS);
        removeSheep.addActionListener(this);

        JButton closeButton = new JButton(close);
        closeButton.setPreferredSize(buttonSize);
        closeButton.setOpaque(false);
        closeButton.setForeground(textColor);
        closeButton.setBackground(textColor);
        closeButton.setFont(font);
        closeButton.setActionCommand(close);
        closeButton.addActionListener(this);

        JLabel emptyLeft = new JLabel("");
        emptyLeft.setOpaque(false);
        emptyLeft.setPreferredSize(new Dimension(190, 30));

        JLabel emptyRight = new JLabel("");
        emptyRight.setOpaque(false);
        emptyRight.setPreferredSize(new Dimension(190, 30));

        JLabel emptyTop = new JLabel("");
        emptyTop.setOpaque(false);
        emptyTop.setPreferredSize(new Dimension(10, 15));

        JLabel emptyBottom = new JLabel("");
        emptyBottom.setOpaque(false);
        emptyBottom.setPreferredSize(new Dimension(10, 30));

        buttonPanel.add(emptyTop, BorderLayout.PAGE_START);
        buttonPanel.add(emptyLeft, BorderLayout.LINE_START);
        buttonPanel.add(emptyRight, BorderLayout.LINE_END);
        buttonPanel.add(emptyBottom, BorderLayout.PAGE_END);

        JPanel insideButtonPanel = new JPanel();
        insideButtonPanel.setOpaque(false);
        BorderLayout customLayout = new BorderLayout();
        customLayout.setHgap(15);
        insideButtonPanel.setLayout(customLayout);

        insideButtonPanel.add(addSheep, BorderLayout.LINE_START);
        insideButtonPanel.add(removeSheep, BorderLayout.CENTER);
        insideButtonPanel.add(closeButton, BorderLayout.LINE_END);

        buttonPanel.add(insideButtonPanel, BorderLayout.CENTER);

        return buttonPanel;

    }

    /**
     * Metode som lager et øverstenivås element som inneholder informasjon om
     * sauene til brukeren
     *
     * @return et JPanel som inneholder et JScrollPane med en tabell i
     */
    private JPanel SheepTablePanel() {
        JPanel sheepTablePanel = new JPanel(new BorderLayout());
        sheepTablePanel.setOpaque(false);
        sheepTablePanel.add(createScrollPane(), BorderLayout.CENTER);

        return sheepTablePanel;
    }

    /**
     * Metode som lager et JScrollPane inneholdene en tabell med sauer
     *
     * @return JScrollPane som inneholder en tabell
     */
    private JScrollPane createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(createSheepTable());
        scrollPane.setOpaque(false);

        return scrollPane;
    }

    /**
     * Metode som lager tabellen med informasjon om brukerens sauer
     *
     * @return SheepTable som inneholder sauene til brukeren
     */
    private JTable createSheepTable() {
        sheepTable = new SheepTable(this.sheepList);
        sheepTable.setOpaque(false);

        return sheepTable;

    }
    
    /**
     * Metode som tar imot en ActionEvent fra knappene til dette
     * vinduet, og utfører en handling basert på hva slags info ActionEventen
     * har
     *
     * @param e ActionEvent som inneholder informasjon om hva brukeren har
     * trykket
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (addS.equals(cmd)) {
            new AddSheep(currentUser);
            this.dispose();

        } else if (removeS.equals(cmd)) {
            new RemoveSheep(currentUser);
			//removeSheepFromList();
            //må legges en tester her

            //removes the currently selected sheep
            //open remove sheep frame
        } else if (close.equals(cmd)) {
            this.dispose();
        }

    }
}

package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.prosjekt.client.ClientService;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

/**
 * Klasse som lager Attack Log vinduet til GUI Dette vinduet viser informasjon
 * om angrep registrert på en bruker
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class AttackLogFrame extends JFrame {

    private static AttackTable attackTable;
    private Farmer user;
    private List<Sheep> sheepList;
    private Font headerFont = new Font("kalinga", Font.PLAIN, 24);
    private Color textColor = new Color(32, 87, 0);
    private Font font = new Font("kalinga", Font.PLAIN, 16);

    /**
     * Konstruktør, lager et attack log vindu med en liste over alle angrep
     * registrert på en bruker
     *
     * @param user brukeren som det skal hentes ut informasjon om angrep fra
     */
    public AttackLogFrame(Farmer user) {
        super("Attack Log");
        super.setContentPane(new BackgroundPanel(ClientService.pathToBackGround()));
        setLayout(new BorderLayout());
        this.user = user;
        sheepList = this.user.getSheeps();

        add(createContent(), BorderLayout.CENTER);

        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }

    /**
     * Metode som lager et JPanel bestående av GUI-elementene som skal vises i
     * dette vinduet
     *
     * @return et JPanel bestående ulike GUI-elementer
     */
    private JPanel createContent() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setPreferredSize(new Dimension(50, 90));

        JLabel header = new JLabel("Attack Log");
        header.setFont(headerFont);
        header.setForeground(textColor);

        JLabel emptyLeft = new JLabel("");
        emptyLeft.setPreferredSize(new Dimension(150, 0));

        JLabel emptyRight = new JLabel("");
        emptyRight.setPreferredSize(new Dimension(150, 0));

        JLabel emptyTop = new JLabel("");
        emptyTop.setPreferredSize(new Dimension(0, 20));

        headerPanel.add(emptyTop, BorderLayout.PAGE_START);
        headerPanel.add(emptyLeft, BorderLayout.LINE_START);
        headerPanel.add(emptyRight, BorderLayout.LINE_END);
        headerPanel.add(header, BorderLayout.CENTER);

        contentPanel.add(headerPanel, BorderLayout.PAGE_START);

        contentPanel.add(createTablePanel(), BorderLayout.CENTER);

        return contentPanel;
    }

    /**
     * Metode som lager et øverstenivås element som inneholder informasjon om
     * angrep
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
     * Metode som lager et JScrollPane inneholdene en tabell med angrep
     *
     * @return JScrollPane som inneholder en tabell
     */
    private JScrollPane createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(createAttackTable());

        scrollPane.setOpaque(false);

        return scrollPane;
    }

    /**
     * Metode som lager tabellen med angrepsinformasjon
     *
     * @return AttackTable som inneholder informasjon om angrep tilknyttet en
     * bruker
     */
    private JTable createAttackTable() {
        attackTable = new AttackTable(this.sheepList);
        attackTable.setOpaque(false);

        return attackTable;

    }

}

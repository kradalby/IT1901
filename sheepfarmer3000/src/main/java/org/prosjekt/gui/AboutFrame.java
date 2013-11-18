/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Martin_
 */
public class AboutFrame extends JFrame {

    private Font font = new Font("kalinga", Font.PLAIN, 12);
    private Font headerFont = new Font("kalinga", Font.PLAIN, 18);
    private Color textColor = new Color(32, 87, 0);
    private String backgroundImage = "images\\innlogging1.jpg";

    public AboutFrame() {

        super.setContentPane(new BackgroundPanel(backgroundImage));
        setLayout(new BorderLayout());

        createContent();

        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(455, 475);
        setVisible(true);

    }

    private void createContent() {
        JLabel emptyTop = new JLabel("");
        emptyTop.setPreferredSize(new Dimension(90, 155));
        add(emptyTop, BorderLayout.PAGE_START);

        JLabel emptyLeft = new JLabel("");
        emptyLeft.setPreferredSize(new Dimension(155, 50));
        add(emptyLeft, BorderLayout.LINE_START);

        JPanel authorsPanel = new JPanel();
        authorsPanel.setLayout(new BoxLayout(authorsPanel, BoxLayout.Y_AXIS));
        authorsPanel.setPreferredSize(new Dimension(100, 200));
        authorsPanel.setOpaque(false);

        JLabel authors = new JLabel("Created by:");
        authors.setFont(headerFont);
        authors.setForeground(textColor);

        JLabel buvik = new JLabel("Buvik, Christoffer");
        buvik.setFont(font);
        buvik.setForeground(textColor);
        buvik.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel barnes = new JLabel("Bårnes, Martin H.");
        barnes.setFont(font);
        barnes.setForeground(textColor);
        barnes.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel alfredo = new JLabel("Ragone, Alfredo Clemente");
        alfredo.setFont(font);
        alfredo.setForeground(textColor);
        alfredo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel kristofferDahl = new JLabel("Dahl, Kristoffer Ringtsad");
        kristofferDahl.setFont(font);
        kristofferDahl.setForeground(textColor);
        kristofferDahl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel oleHalvor = new JLabel("Dahl, Ole Halvor");
        oleHalvor.setFont(font);
        oleHalvor.setForeground(textColor);
        oleHalvor.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel dalby = new JLabel("Dalby, Kristoffer Kradalby");
        dalby.setFont(font);
        dalby.setForeground(textColor);
        dalby.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel emptyMiddle = new JLabel(" ");
        emptyMiddle.setFont(font);
        emptyMiddle.setForeground(textColor);
        emptyMiddle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel text1 = new JLabel("For the course IT1901");
        text1.setFont(font);
        text1.setForeground(textColor);
        text1.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel text2 = new JLabel("NTNU autumn 2013.");
        text2.setFont(font);
        text2.setForeground(textColor);
        text2.setAlignmentX(Component.LEFT_ALIGNMENT);

        authorsPanel.add(authors);
        authorsPanel.add(buvik);
        authorsPanel.add(barnes);
        authorsPanel.add(alfredo);
        authorsPanel.add(kristofferDahl);
        authorsPanel.add(oleHalvor);
        authorsPanel.add(dalby);
        authorsPanel.add(emptyMiddle);
        authorsPanel.add(text1);
        authorsPanel.add(text2);

        add(authorsPanel, BorderLayout.CENTER);
    }

}

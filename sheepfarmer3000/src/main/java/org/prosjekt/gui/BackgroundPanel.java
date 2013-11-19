package org.prosjekt.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Klasse som tar hånd om importering av bilder
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class BackgroundPanel extends JPanel {

    BufferedImage img;

    /**
     * Konstruktør, lager et JPanel som inneholder et bilde, henter bildet fra
     * en filadresse
     *
     * @param fileAdress filadressen til bildet som skal legges til dette
     * JPanelet
     */
    public BackgroundPanel(String fileAdress) {
        try {
            final URL url = Thread.currentThread().getContextClassLoader().getResource(fileAdress);
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }

}

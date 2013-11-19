package org.prosjekt.gui;

import com.google.common.base.Stopwatch;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.prosjekt.client.ClientService;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Passhash;

/**
 * Klasse som starter programmet. Denne klassen brukes også av de andre klassene
 * i GUI til å kaller client klasser og metoder som muligjør henting og endring
 * av data på server
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class Main {

    private static ArrayList<Farmer> farmerList;
    private static Farmer mainCurrentUser;
    public static DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy");
    public static DateTimeFormatter fmtCoord = DateTimeFormat.forPattern("yyyy.MM.dd.kk.mm.ss.SSS");

    /**
     * Metode som kjører programmet
     *
     * @param args
     * @throws URISyntaxException
     */
    public static void main(String args[]) throws URISyntaxException {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                init();
            }
        });

    }

    /**
     * Metode som initialiserer programmet og instansierer klasser for åpning av
     * GUI
     */
    public static void init() {
        farmerList = new ArrayList<Farmer>();
        mainCurrentUser = null;
        new TopLevelGui();
    }

    /**
     * Metode som henter en liste med Farmer id fra serveren
     *
     * @return en liste med id til alle Farmer-objekter på serveren
     */
    public static List<Integer> getFarmerIds() {
        List<Integer> ids = ClientService.getFarmerIds();
        return ids;
    }

    /**
     * Metode som henter Farmer-objektet til brukeren som er logget inn
     *
     * @return Farmer-objektet til innlogget bruker
     */
    public static Farmer getCurrentUser() {
        return mainCurrentUser;
    }

    /**
     * Oppdaterer Farmer-objektet i denne klassen, og kaller en metode på kartet
     * som oppdaterer dette med info fra den nye brukeren
     *
     * @param f det oppdaterte Farmer-objektet til denne brukeren
     */
    public static void updateMainUser(Farmer f) {
        mainCurrentUser = f;
        MainPage.kart.refreshMap();
    }

    /**
     * Metode som sjekker om en Farmer med en bestemt id finnes i databasen
     *
     * @param id id til Farmer det skal sjekkes om eksisterer i databasen
     * @return boolean med informasjon om det finnes en Farmer i databasen med
     * id fra parameter
     */
    public static boolean checkFarmerList(int id) {
        boolean tester = false;
        List<Integer> farmerids = ClientService.getFarmerIds();
        for (Integer i : farmerids) {
            if (i == id) {
                tester = true;
                break;
            }
        }
        return tester;
    }

    /**
     * Metode som henter et Farmer-objekt fra serveren basert på en id
     *
     * @param id id til Farmer-objektet som skal hentes
     * @return Farmer-objektet som har id lik parameteret
     */
    public static Farmer getFarmerById(int id) {
        Stopwatch s = new Stopwatch();
        s.start();
        Farmer f = ClientService.getFarmer(id);
        s.stop();
        System.out.println("Time getFarmer: " + f.getId() + "\t" + s.elapsedMillis());
        return f;
    }

    /**
     * Metode som sammenligner et passord som brukeren har skrevet inn i GUI med
     * et passord fra et Farmer-objekt
     *
     * @param f Famer-objekt som et passord hentes fra
     * @param input passordet brukeren har skrevet inn
     * @return boolean som returnerer informasjon om passordet som er skrevet
     * inn er feil eller riktig
     */
    public static boolean checkPassword(Farmer f, char[] input) {
        boolean tester = false;
        Passhash p = f.getPasshash();
        char[] correctPassword = p.toCharArray();
        if (input.length != correctPassword.length) {
            tester = false;
        } else {
            tester = Arrays.equals(input, correctPassword);
            if (tester) {
                mainCurrentUser = f;
            }
        }
        return tester;
    }

    /**
     * Metode som forsøker å lagre informasjon til server
     *
     * @param f Farmer-objektet som skal lagres på serveren
     * @return boolean med informasjon om lagringen ble utført
     */
    public static boolean saveChangesToFarmer(Farmer f) {
        boolean confirmChange = ClientService.updateFarmer(f);
        return confirmChange;
    }
}

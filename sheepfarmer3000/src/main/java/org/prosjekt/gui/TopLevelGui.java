package org.prosjekt.gui;

/**
 * Klasse som starter GUI. Denne klassen tar for seg først kall til åpning av
 * GUI og har en metode for sjekking av passord
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class TopLevelGui {

    private static MainPage mainPage;

    /**
     * Konstruktør, lager et TopLevelGui-objekt som gjør at metodene i denne
     * klassen kan kalles, og åpner deretter en ny LoginBox
     */
    public TopLevelGui() {
        new LoginBox();
    }

    /**
     * Metode som tar inn informasjonen brukeren har skrevet i Loginn-vinduet
     * kaller metoder som sjekker om brukeren har tastet riktig
     *
     * @param userid int brukerid som brukeren har skrevet inn i loggin-vinduet
     * @param password char[] passorder som brukeren har skrevet inn i
     * loggin-vinduet
     * @return boolean med informasjon om pålogging var vellyket
     */
    protected static boolean checkPassword(int userid, char[] password) {
        boolean loginOK = false;
        if (Main.checkFarmerList(userid)) {
            if (Main.checkPassword(Main.getFarmerById(userid), password)) {
                loginOK = true;
                mainPage = new MainPage();
            }
        }
        return loginOK;
    }
}

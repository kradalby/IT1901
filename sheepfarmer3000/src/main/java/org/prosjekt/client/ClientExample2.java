package org.prosjekt.client;

import com.google.common.collect.Lists;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Mail;
import org.prosjekt.helperclasses.Passhash;
import org.prosjekt.helperclasses.Request;
import org.prosjekt.helperclasses.RequestEnum;
import org.prosjekt.helperclasses.Response;
import org.prosjekt.helperclasses.Sheep;
import org.prosjekt.helperclasses.Sms;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.prosjekt.client.ClientExample.connection;
import org.prosjekt.database.Admin;
import org.prosjekt.database.repository.LogicRepository;
import org.prosjekt.gui.LoginBox;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Helper;
import org.prosjekt.logic.RandomSheepGenerator;
import org.prosjekt.logic.WolfLogic;

/**
 * Denne klassen er en placeholder klasse helt til GUI peepsa finner ut hvor de vil ha metodene.
 *
 * @author kradalby
 *
 */
public class ClientExample2 {
    
    private static List<Farmer> farmers;
    private static List<Integer> farmerids;
    
    private  ClientExample2() {
        
        
    }
    
    private static void create(){
        farmerids = new ArrayList<Integer>();
        farmers = new ArrayList<Farmer>();
        
        Farmer farmer1 = farmerOppdal();
        farmers.add(farmer1);
        setPasshash(farmer1.getId(), "1001");
        addSheeps(farmer1, farmer1.getCoordinates(), 10);
        
        for (Sheep s : farmers.get(0).getSheeps()) s.setAlive(true);
        
        //add 10 coordinates to a sheep. 
        RandomSheepGenerator rsg = new RandomSheepGenerator(farmers.get(0).getCoordinates(), farmers.get(0));
        Sheep s = farmer1.getSheeps().get(0);
        List<Coordinate> c = rsg.generateCoords(15);
        s.setCordinates(c);
        sleep(2); //sleep for å få den siste coordinaten. Coordinates blir sortert på dato og millisec. 
        Coordinate current = rsg.generateCoords(1).get(0);
        s.setCurrentCoordinate(current);
        
        sleep(1000); //sleep for å få den siste coordinaten. Coordinates blir sortert på dato og millisec. 
        Coordinate attack = rsg.generateCoords(1).get(0);
        s.getAttacks().add(attack);
    }
    
    
    
    
    public static void main(String[] args) {
        LogicRepository lr = new LogicRepository();
        
        
        
    }
    
    public static String getPathToResources(String file){
        URL resourceUrl = ClientExample.class.getResource("/"+ file);
        return resourceUrl.getPath();
    }
    
    public static String pathToBackGround(){
        return getPathToResources("bakgrunn 450x450.jpg");
    }
    
    
    
    public static Sheep getSheepById(int id) {
        return null;
    }
    
    
    
    public static boolean addSheep(Sheep sheep) {
        return false;
    }
    
    
    public static boolean removeSheep(Sheep sheep) {
        return false;
    }
    
    
    public static Farmer getFarmer(int id) {
        if (farmers == null) create(); 
        return farmers.get(0);
    }
    
    
    
    public static boolean updateFarmer(Farmer farmer) {
        return false;
    }
    
    
    //SKAL IKKE BRUKES I GUI.
    public static boolean updateFarmerArea(Farmer farmer) {
        return false;
    }
    
    
    public static boolean updateHelper(Helper helper) {
        return true;
    }
    
    public static boolean removeHelper(Helper helper) {
        farmers.get(0).getHelpers().remove(helper);
        return true;
    }
    
    public static boolean addHelper(Helper helper) {
        if (farmers == null) create(); 
        farmers.get(0).getHelpers().add(helper);
        return true;
    }
    
    
    public static boolean setPasshash(int farmerid, String passhash) {
        Passhash ph = new Passhash(farmerid);
        ph.setPasshash(passhash);
        farmers.get(0).setPasshash(ph);
        return true;
    }
    
    
    public static Passhash getPasshash(int farmerid) {
        if (farmers == null) create(); 
        return farmers.get(0).getPasshash();
    }
    
    
    
    public static List<Integer> getFarmerIds() {
        if (farmers == null) create(); 
        return new ArrayList<Integer>(Arrays.asList(1001));
    }
    
    
    
    private static Farmer farmerOppdal() {
        int id = 1001;
        Farmer farmer = new Farmer(id);
        //OPPDAL
        List<Coordinate> farmerArea = Lists.newArrayList();
        farmerArea.add(new Coordinate(62.585715,9.708897));
        sleep(2);
        farmerArea.add(new Coordinate(62.578047,9.718252));
        sleep(2);
        farmerArea.add(new Coordinate(62.580498,9.749666));
        sleep(2);
        farmerArea.add(new Coordinate(62.590891,9.733702));
        farmer.setCoordinates(farmerArea);
        return farmer;
    }
    
    private static void addSheeps(Farmer farmer, List<Coordinate> area, int antall){
        RandomSheepGenerator rsg = new RandomSheepGenerator(area, farmer);
        ArrayList<Sheep> sheeps = rsg.generateSheep(antall);
        farmer.setSheeps(sheeps);
    }
    
    private static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}


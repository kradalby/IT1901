package org.prosjekt.helperclasses;

import java.util.ArrayList;
import org.prosjekt.database.FarmerService;
import org.prosjekt.database.repository.FarmerRepository;
import org.prosjekt.staticmaps.Map;
import org.prosjekt.staticmaps.Point;

/**
 * Denne klassen haandterer alle alerts som skal sendes ut.
 * 
 * @author Kristoffer Dalby <kradalby@kradalby.no>
 *
 */
public class Alert {
	
	private Sheep sheep;
	private boolean sms = false;
	private boolean mail = true;
	private String subject;
	private String message;
        private String gMapsLink;
	private ArrayList<String> emailRecipient = new ArrayList<String>();

	private ArrayList<String> smsRecipient = new ArrayList<String>();
	
        private FarmerService fs;
        
	public Alert(Sheep sheep) {
            fs = new FarmerRepository();
            this.sheep = sheep;
            String link = "https://maps.google.com/maps?z=18&q="
                    + sheep.getCurrentCordinate().getLat()
                    + ","
                    + sheep.getCurrentCordinate().getLon();
            gMapsLink = link;
	}
	
	/**
	 * Metode som genererer mottaker lister basert paa sauen alarmen gjelder.
	 */
	public void getRecipients() {
            Farmer farmer = fs.getFarmer(sheep.getFarmerid());
            
            
		//Denne metoden vil trenge noen endringer naar vi endrer helper.
                //Hent ut List<Farmer>  og slå opp i farmer. 
		emailRecipient.add(farmer.getEmail());
                for (Helper helper : farmer.getHelpers()){
                    emailRecipient.add(helper.getEmail());
                }
//		smsRecipient.add(this.sheep.getFarmer().getPhone());
//		smsRecipient.add(this.sheep.getFarmer().getHelperPhone());
	}
	
	
	/**
	 * Metode som sender en alarm paa epost og sms til listen med mottakere
	 */
	public void sendAttackAlarm() {
		
                subject = "En av dine sauer er under angrep!";
		message = "Hei\n"
				+ "Din sau med ID: " + this.sheep.getId()
				+ "er under angrep. De siste kjente kordinatene til sauen er:\n"
				+ this.sheep.getCurrentCordinate().getLat() + " " 
				+ this.sheep.getCurrentCordinate().getLon()
                                + "\nDu kan sjekke hvor den var på: "
                                + gMapsLink
				+ "\n du kan ogsaa logge inn paa sheepwatcher aa sjekke der.\n"
				+ "mvh Sheepwatcher";
		
		//generate recipients lists.
		this.getRecipients();
		if (mail) {
			for (int i = 0; i < emailRecipient.size(); i++) {
                            if (!emailRecipient.get(i).isEmpty()){   //sender bare mail hvis email er registrert. 
				Mail mail = new Mail(emailRecipient.get(i), subject, message);
				mail.sendMail();
                                System.out.println("mail sendt to " + emailRecipient.get(i));
                            }
			}
		}

		if (sms) {
			for (int i = 0; i < smsRecipient.size(); i++) {
				Sms sms = new Sms(smsRecipient.get(i), message);
				sms.sendSMS();
			}
		}

	}

}

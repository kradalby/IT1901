package org.prosjekt.helperclasses;

import java.util.ArrayList;
import org.prosjekt.database.FarmerService;
import org.prosjekt.database.repository.FarmerRepository;

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
        smsRecipient.add(farmer.getPhone());
        for (Helper helper : farmer.getHelpers()) {
            smsRecipient.add(helper.getPhone());
        }

	}
	
	
	/**
	 * Metode som sender en alarm paa epost og sms til listen med mottakere
	 */
	public void sendAttackAlarm(boolean useSms) {
		sms = useSms;
                subject = "En av dine sauer er under angrep!";
		String emailMessage = message();
        String smsMessage = smsMessage();
		
		//generate recipients lists.
		this.getRecipients();
		if (mail) {
                        System.out.println(emailRecipient);
                        System.out.println("----");
			for (int i = 0; i < emailRecipient.size(); i++) {
                            if (emailRecipient.get(i) != null && !emailRecipient.get(i).isEmpty()){   //sender bare mail hvis email er registrert. 
				Mail mail = new Mail(emailRecipient.get(i), subject, emailMessage);
				mail.sendMail();
                                System.out.println("mail sendt to " + emailRecipient.get(i));
                            }
			}
		}

		if (sms) {
            for (int i = 0; i < smsRecipient.size(); i++) {
                                if(!smsRecipient.get(i).isEmpty()) {
                                System.out.println("rec: " + smsRecipient.get(i));
                                System.out.println(smsRecipient.get(i) + " " + smsMessage);
                    Sms smss = new Sms((String) smsRecipient.get(i), (String) smsMessage);
                    smss.sendSMS();
                                    System.out.println("sms sent to " + smsRecipient.get(i));
                                }
                }
		}

	}

        /**
         * Generer tekst i email. 
         * @return message  
         */
        private String message(){
            StringBuilder sb = new StringBuilder();
            
            sb.append("Hei\n");
            sb.append("Din sau med ID: " + this.sheep.getId() 
                    + "har blitt angrep. De siste kjente kordinatene til sauen er:\n");
            if (!this.sheep.getAlive()) sb.append("Din sau døde desverre i angrepet.");
            sb.append(this.sheep.getCurrentCordinate().getLat() + " "
                    + this.sheep.getCurrentCordinate().getLon()
                    + "\nDu kan sjekke hvor den var på: " 
                    + gMapsLink
                    + "\n du kan ogsaa logge inn paa sheepwatcher aa sjekke der.\n"
                    + "mvh SheepFarmer3000");
            
            return sb.toString();
        }

        /**
         * Tekst i sms. 
         * @return String
         */
        private String smsMessage(){
            StringBuilder sb = new StringBuilder();

            sb.append("Hei\n");
            sb.append("Din sau med ID: " + this.sheep.getId()
                    + "har blitt angrep. De siste kordinatene til sauen er:\n");
            sb.append("Du kan logge inn paa sheepwatcher aa sjekke der.\n"
                    + "mvh SheepFarmer3000");

            return sb.toString();
        }


}

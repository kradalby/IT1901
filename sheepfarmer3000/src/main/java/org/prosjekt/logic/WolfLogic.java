package org.prosjekt.logic;

import java.util.ArrayList;
import org.joda.time.DateTime;
import org.prosjekt.database.repository.LogicRepository;

import org.prosjekt.helperclasses.Alert;
import org.prosjekt.helperclasses.Sheep;

/**
 * @author kradalby
 *
 */
public class WolfLogic {

	/**
	 * Returnerer en vilkaarlig sau utifra en arraylist.
	 * 
	 * @param sheeps liste over alle sauer som kan angripes.
	 * @return returnerer en tilfeldig sau.
	 */
	public static Sheep getRandomSheep(Sheep[] sheeps) {
		int n = sheeps.length;
		int sheepId= 0 + (int)(Math.random() * (((n-1) - 0) + 1));
		Sheep sheep = sheeps[sheepId];
		return sheep;
	}
	
        
        /**
         * Tar inn en sau og dreper den 70% av gangene:)
         * WolfAttack sender ikke ut sms. 
         * @param sheep 
         */
        public static void wolfAttack(Sheep sheep) {
            wolfAttack(sheep, false);
        }
        
	        
        /**
	 * Tar inn en sau og dreper den 70% av gangene:)
         * @param sheep 
         * @param useSms sender sms hvis true. 
         */
	public static void wolfAttack(Sheep sheep, boolean useSms) {
		int chance = (int)(Math.random() * (((100-1) - 0) + 1));
		
		// 70% sjanse for at sauen doer.
		if (chance > 30) {
                    System.out.println("ATTACK SHEEP IS DEAD.");
			sheep.setAlive(false);
		} 	
                else System.out.println("ATTACK SHEEP SURVIVED");
                //Alarm sendes ut uansett om sauen overlever eller ikke. 
                Alert alert = new Alert(sheep);
                alert.sendAttackAlarm(useSms);
                new LogicRepository().addAttack(sheep.getId(), sheep.getCurrentCordinate());
	}
}

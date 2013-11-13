package org.prosjekt.helperclasses;

/**
 * RequestEnum
 * 
 * Denne enum klassen har predifinert kommandoene som kan brukes
 * når man sender en request.
 * 
 * @author Kristoffer Dalby <kradalby@kradalby.no>
 *
 */
public enum RequestEnum {

        //FarmerService
	GetPasshash,
	SetPasshash,
	
        GetFarmer,
        UpdateFarmer,
        UpdateFarmerArea,
        
        UpdateHelper,
        AddHelper,
        RemoveHelper,

        //SheepService
	GetSheepById,
	REMOVESHEEP,
	ADDSHEEP,
    
        //LogicService
        GetFarmerIds,

    
    
	

}

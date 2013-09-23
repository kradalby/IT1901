/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.repository;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class ServerService extends AbstractProperties{
    FarmerRepository fr;
    CoordinateRepository cr;

    public ServerService() {
        fr = new FarmerRepository();
        cr = new CoordinateRepository();
    }

    
//    select *
//from coordinate c 
//join sheep s on c.sheepid = s.id
//join farmer f on s.farmerid = f.id
//
//where f.id = 1 and (sheepid, dateevent) in (
//    select sheepid, max(dateevent) as date
//    from coordinate
//    group by sheepid
    
    


    
}

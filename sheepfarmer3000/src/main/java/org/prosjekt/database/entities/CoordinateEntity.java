/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.entities;

import com.google.common.base.Preconditions;
import java.sql.Date;
import java.sql.Timestamp;
import org.joda.time.DateTime;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class CoordinateEntity {
    private final int id;
    private final int sheepid;
    private String latitude;
    private String longitude;
    private boolean attack;
    private Timestamp date; 

    public CoordinateEntity(int id, int sheepid, String latitude, String longitude, boolean attack, DateTime date) {
        Preconditions.checkNotNull(id, "id must be set");
        Preconditions.checkNotNull(sheepid, "sheepid must be set");
        this.id = id;
        this.sheepid = sheepid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.attack = attack;
        if (date == null) this.date = new Timestamp(new DateTime().getMillis());
        else this.date = new Timestamp(new DateTime().getMillis());
    }

    public int getId() {
        return id;
    }

    public int getSheepid() {
        return sheepid;
    }
    
    

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public boolean isAttack() {
        return attack;
    }

    public Timestamp getTimestamp() {
        return date;
    }
    
    

    @Override
    public String toString() {
        return "CoordinateEntity{" + "id=" + id + ", sheepid=" + sheepid + ", latitude=" + latitude + ", longitude=" + longitude + ", attack=" + attack + ", date=" + date.toString()+ '}';
    }
    
    
    
}

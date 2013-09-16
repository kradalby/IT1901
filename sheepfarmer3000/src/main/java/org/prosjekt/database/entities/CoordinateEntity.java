/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.entities;

import java.sql.Date;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class CoordinateEntity {
    private Integer id;
    private String latitude;
    private String longitude;
    private boolean attack;
    private Date date; 

    public CoordinateEntity(Integer id, String latitude, String longitude, boolean attack, Date date) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.attack = attack;
        this.date = date;
    }

    public Integer getId() {
        return id;
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

    public Date getDate() {
        return date;
    }
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt.database.entities;

import com.google.common.base.Preconditions;
import java.sql.Date;
import org.joda.time.DateTime;
import org.prosjekt.helperclasses.Sheep;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */
public class SheepEntity {

    //Wrapper types to support null in database. 
    private final int id;  //NOT NULL
    private final int farmerid; //NOT NULL
    private Integer weight;
    private Date birth;
    private Boolean alive;

    public SheepEntity(int id, int farmerid, Integer weight, Date birth, Boolean alive) {
        Preconditions.checkNotNull(id, "id must be set.");
        Preconditions.checkNotNull(farmerid, "farmerid must be set.");
        this.id = id;
        this.farmerid = farmerid;
        this.alive = alive;
        weight = weight;
        
        if (birth == null) setBirth(new DateTime());
        else birth = birth;
    }
   
    
    public SheepEntity(Integer id, Integer farmerid, Integer weight) {
        this(id, farmerid, weight, null, true);
    }

    public SheepEntity(int id, Integer farmerid) {
        this(id, farmerid, null, null, true);
    }
    
    
    
    
    
    public int getId() {
        return id;
    }
    
    public Integer getWeight() {
        return weight;
    }
    
    public Date getBirth() {
        return birth;
    }
    
    public Boolean isAlive() {
        return alive;
    }
    
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    
    public void setBirth(DateTime birth) {
        this.birth = new Date(birth.getMillis());
    }
    
    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public int getFarmerid() {
        return farmerid;
    }

 
    @Override
    public String toString() {
        return "SheepEntity{" + "id=" + id + ", farmerid=" + farmerid + ", weight=" + weight + ", birth=" + birth + ", alive=" + alive + '}';
    }
    
    

}

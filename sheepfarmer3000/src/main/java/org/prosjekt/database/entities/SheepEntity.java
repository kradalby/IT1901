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
public class SheepEntity {
    private final int id;
    private Integer weight;
    private Date birth;
    private boolean alive;

    public SheepEntity(int id, Integer weight, Date birth, boolean alive) {
        this.id = id;
        this.weight = weight;
        this.birth = birth;
        this.alive = alive;
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

    public boolean isAlive() {
        return alive;
    }
    
    
}

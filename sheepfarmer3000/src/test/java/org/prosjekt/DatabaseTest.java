/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prosjekt;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author Christoffer <christofferbuvik@gmail.com>
 */

public class DatabaseTest extends AppTest{

    public DatabaseTest() {
        super("DBTEST");
    }
 
    
    @Test
    public void testmeTest(){
        Assert.assertEquals(2,2);
    }
    
}

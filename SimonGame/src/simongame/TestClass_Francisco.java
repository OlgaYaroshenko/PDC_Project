/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simongame;

import java.util.Random;

/**
 *
 * @author Francisco
 */
public class TestClass_Francisco {
    public static void main(String[] args) {
        Random r = new Random();
        int colorOrdinal = r.nextInt(4);
        System.out.println(Color.values()[colorOrdinal]);
        
    }
    
    
}

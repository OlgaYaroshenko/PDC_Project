/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simongame;

/**
 *
 * @author Francisco
 */
public class TestClass_Francisco {
    public static void main(String[] args) {
        Highscores h = new Highscores();
        
        Highscores.saveHighScore(new Player("John", 100));
        
        
        
        
        
        for(String s : Highscores.getHighScoreList())
            System.out.println(s);
    }
    
    
}

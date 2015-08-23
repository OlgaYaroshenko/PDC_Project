/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simongame;

/**
 *
 * @author olga
 */
public class TestClass_Olga {
    public static void main(String[] args) {
        Highscores.saveHighScore(new Player("aa", 100));
        
       for (String s : Highscores.getHighScoreList())
            System.out.println(s);
    }
}

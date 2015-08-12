/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simongame;

import java.util.ArrayList;

/**
 *
 * @author olga
 */
public class Highscores {
    
    private Player player;
    private final ArrayList<Player> highscores;

    public Highscores(ArrayList<Player> highscores) {
        this.highscores = highscores;
    }
    
    public void addHighscore() {
        highscores.add(player);
    }
    
    
    public void displayHighscores() {
        if (!highscores.isEmpty()) {
            for (Player p : highscores) {
                System.out.println("\t " + p.getName() + " " + p.getScore());
            }
        } else {
            System.out.println("There are no highscores!");
        }
    }

}

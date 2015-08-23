/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simongame;

import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author olga
 */
public class Gameplay extends JFrame {
    Sequence sequence = new Sequence();
    
    
    //METHODS-------------------------------------------------------------------
    public void play() throws InterruptedException { //TODO handle exception
        int round = 1;
        Player player = new Player("", 0);
        String userInput = "";
        Scanner scan = new Scanner(System.in);
        while(true) {
            //Showing sequence and round to player
            sequence.generateSequence();
            Print.round(round, sequence);
            
            
            //Getting player to guess sequence
            Print.userPrompt();
            userInput = scan.nextLine();
            if(!sequence.isSequenceCorrect(userInput)) {
                Print.incorrectAnswer(sequence); //TODO modify this to display the correct sequence
                Print.gameOver();
                
                //Check if highScore
                if(player.score() > Highscores.getLowestScore()) {
                    Print.congratulations(); //(e.g. Congratulations, highScore, enter your name:)
                    player.setName(scan.nextLine());
                    Highscores.saveHighScore(player);
                }
                
                break;
            } else {
                round++;
                player.addToScore(round);
                Print.correctAnswer(sequence); //TODO display the correct sequence
            }
            
           
            
            
            
        }
        
    }
    
    
}

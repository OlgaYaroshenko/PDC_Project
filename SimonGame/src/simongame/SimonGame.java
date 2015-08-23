//Adding a comment :O
package simongame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author olga
 */
public class SimonGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Gameplay gameplay = new Gameplay();
        Scanner scan = new Scanner(System.in);
        String userOption = "";
        
        while(!userOption.equals("4")) {
            Print.welcomeMessage();
            Print.image();
            Print.gameOptions();
            userOption = scan.next();
            while(userOption != "4") {
                
                if(userOption.endsWith("1")) {
                    gameplay.play();
                    Print.gameOptions();
                    userOption = scan.next();
                }
                else if(userOption.equals("2")) {
                    Print.instructions();
                    Print.gameOptions();
                    userOption = scan.next();
                }
                else if(userOption.equals("3")) {
                    Print.highscores();
                    Print.gameOptions();
                    userOption = scan.next();
                }
                else if(userOption.equals("4")) {
                    //Print.goodbye();
                    System.exit(1);

                }
            }        
        }
        
        
        
        
        
//        Player player = new Player();
//        player.setNameFromUser();
//        System.out.println(player.toString());
//        try {
//            Highscores.loadHighscores();
//        } catch (IOException ex) {
//            Logger.getLogger(SimonGame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Highscores.addHighscore(player);
//        Highscores.displayHighscores();
        
        
    
    }
    
}

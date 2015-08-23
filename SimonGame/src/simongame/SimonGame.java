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
    public static void main(String[] args) {
        Gameplay gameplay = new Gameplay();
        Scanner scan = new Scanner(System.in);
        String userOption = "";
        
        while(!userOption.equals("4")) {
            Print.welcomeMessage();
            userOption = scan.next();    
            switch (userOption) {
                case "1":
                    //gameplay.playSimon();
                    break;
                case "2":
                    Print.instructions();
                    break;
                case "3":
                    break;
                case "0":
                    //Print goodbye message
                    userOption = "4";
                    break;
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

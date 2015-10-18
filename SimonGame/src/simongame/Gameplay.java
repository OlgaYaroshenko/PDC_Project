package simongame;

import java.util.Scanner;
import javax.swing.JFrame;

/**
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 * 
 * This class provides methods that control the actual game play
 */
public class Gameplay extends JFrame {
    //FIELDS--------------------------------------------------------------------
    private final SequenceGenerator SEQUENCE;
    private final Scanner SCANNER;
    private final Player PLAYER;
    private int round;
    private String userInput;
    
    //CONSTRUCTOR---------------------------------------------------------------
    /**
     * Sets initial game play status
     */
    public Gameplay() {
        this.SEQUENCE = new SequenceGenerator();
        this.SCANNER = new Scanner(System.in);
        this.PLAYER = new Player("", 0);
        this.round = 1;
        this.userInput = "";
    }

    //METHODS-------------------------------------------------------------------
    /**
     * Method used to control the actual game play. Prints instructions for the
     * player, the color sequences, asks the player to input the color sequence,
     * verifies if sequence correct, and checks whether player has lost and
     * achieved a high score
     */
    public void play() {
        boolean isUserInputCorrect = true;
        while (true) {
            if (isUserInputCorrect) {
                //Showing sequence and round to player
                SEQUENCE.generateSequence();
                Print.round(round, SEQUENCE);
                System.out.println(AnsiTextColors.ANSI_BLACK);
            }

            //Getting player to guess sequence
            Print.userPrompt();
            System.out.print(AnsiTextColors.ANSI_WHITE);
            userInput = SCANNER.nextLine();
            System.out.print(AnsiTextColors.ANSI_BLACK);
            
            //Validating and processing user input
            try {
                if(!(userInput.contains("1") || userInput.contains("2") || 
                     userInput.contains("3") || userInput.contains("4")))
                    throw new NumberFormatException();
                
                //Checking if user guessed correct sequence
                if (!SEQUENCE.isSequenceCorrect(userInput)) {
                    Print.incorrectAnswer(SEQUENCE);
                    System.out.print(AnsiTextColors.ANSI_BLACK);
                    Print.gameOver();

                    //Checking if highScore achieved
                    if ((PLAYER.score() > Highscores.getLowestScore() || Highscores.getHighScoreList().length < 10) && PLAYER.score() != 0) {
                        try {
                            Thread.sleep(1000); //Milliseconds
                        } catch (InterruptedException ex) {
                        }
                        Print.congratulations(); //(e.g. Congratulations, highScore, enter your name:)
                        String playerName = SCANNER.nextLine();
                        if (playerName.length() < 1) {
                            PLAYER.setName("No Name");
                        } else {
                            PLAYER.setName(playerName);
                        }
                        Highscores.saveHighScore(PLAYER);
                    }
                    break;
                //Increasing player score  
                } else {
                    PLAYER.addToScore(round);
                    round++;
                    Print.correctAnswer();
                    isUserInputCorrect = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You can only use numbers, no spaces, try again");
                isUserInputCorrect = false;
            }
        }
    }
}

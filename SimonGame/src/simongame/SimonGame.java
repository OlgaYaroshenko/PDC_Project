package simongame;

import java.util.Scanner;

/**
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 *
 * This is the main class/access point which runs the entire program. Through
 * the console it displays to the player all menu options.
 */
public class SimonGame {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        String userOption = "";

        Print.welcomeMessage();
        Print.image(); //Game Logo
        Print.gameOptions();
        userOption = scan.next();

        while (userOption != "4") { //4 = exit
            if (userOption.endsWith("1")) { //1 =start game
                Gameplay gameplay = new Gameplay();
                gameplay.play();
                Print.gameOptions();
                userOption = scan.next();

            } else if (userOption.equals("2")) { //2 = instructions
                Print.instructions();
                Print.gameOptions();
                userOption = scan.next();

            } else if (userOption.equals("3")) { //3 = highScores
                Print.highscores();
                Print.gameOptions();
                userOption = scan.next();

            } else if (userOption.equals("4")) { //4 = exit
                Print.goodbye();
                Thread.sleep(750);
                System.exit(1);

            } else {
                System.out.println("Wrong Input, try again");
                Print.gameOptions();
                userOption = scan.next();
            }
        }
    }
}

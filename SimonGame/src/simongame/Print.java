package simongame;

import java.util.LinkedList;

/**
 *
 * @author olga
 */
public class Print {

    private Print() {
    }

    public static void welcomeMessage() {
        System.out.println("*************************************************************");
        System.out.println("*                WELCOME TO SIMON GAME!                     *");
        System.out.println("*************************************************************");
    }

    public static void image() {
        System.out.println(" (                                                         \n"
                + " )\\ )                          (                           \n"
                + "(()/( (      )                 )\\ )       )     )      (   \n"
                + " /(_)))\\    (      (    (     (()/(    ( /(    (      ))\\  \n"
                + "(_)) ((_)   )\\  '  )\\   )\\ )   /(_))_  )(_))   )\\  ' /((_) \n"
                + "/ __| (_) _((_))  ((_) _(_/(  (_)) __|((_)_  _((_)) (_))   \n"
                + "\\__ \\ | || '  \\()/ _ \\| ' \\))   | (_ |/ _` || '  \\()/ -_)  \n"
                + "|___/ |_||_|_|_| \\___/|_||_|     \\___|\\__,_||_|_|_| \\___|  \n"
                + "                                                           ");
    }

    public static void gameOptions() {
        System.out.println("*************************************************************");
        System.out.println("* Press [1] to start the game, [2] for instructions,        *\n"
                         + "* [3] for highscores, [4] to exit:                          *");
        System.out.println("*************************************************************");
    }

    public static void instructions() {
        System.out.println("*************************************************************");
        System.out.println("* In order to obtain the highest score you will need to     *\n"
                         + "* repeat correctly a longer and longer sequence of numbers  *\n"
                         + "* from 1 to 4.                                              *");
        System.out.println("*************************************************************");
    }

    public static void round(int round, Sequence sequence) {
        System.out.println("*************************************************************");
        System.out.println("*                        Round " + round + ":                           *");
        System.out.println("*************************************************************");
//        for (int i = 0; i < sequence.length(); i++) {
//           System.out.print(i);
//           thread.sleep(1000);
//           System.out.print("\b");
//        }
        
    }

    public static void userPrompt() {
        System.out.println("Please enter the sequence you have just seen without spaces: ");
    }

    public static void correctAnswer(Sequence sequence) {
        System.out.println("Correct! The sequence was: " + sequence + ". Next round.");
    }

    public static void incorrectAnswer(Sequence sequence) {
        System.out.println("Incorrect! The sequence was: " + sequence);
    }

    public static void gameOver() {
        System.out.println("*************************************************************");
        System.out.println("*                      Game over!                           *");
        System.out.println("*************************************************************");
    }
    
        public static void goodbye() {
        System.out.println("Goodbye!");
    }
    
    public static void congratulations() {
        System.out.println("*************************************************************");
        System.out.println("*                   Congratulations!                        *");
        System.out.println("*************************************************************");
        System.out.println("You have achieved highscores! Please enter your name: ");
              
    }
    
    public static void highscores() {
        System.out.println("*************************************************************");
        System.out.println("*                      High Scores:                         *");
        System.out.println("*************************************************************");
        for (String s : Highscores.getHighScoreList())
            System.out.println(s);
    }

    /*
    In order to try how it works copypaste the following calls to main method:
        Print.welcomeMessage();
        Print.image();
        Print.gameOptions();
        Print.instructions();
        Print.round(1);
        Print.userPrompt();
        Print.correctAnswer(null);
        Print.incorrectAnswer(null);
        Print.gameOver();
        Print.congratulations();
    */
}

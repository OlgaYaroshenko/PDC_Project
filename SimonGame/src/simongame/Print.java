package simongame;

/**
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 * 
 * Class which contains methods which have a public static access. 
 * The class methods are intended to display necessary instructions, options,
 * and game play output to the console.
 */
public class Print {
    //CONSTRUCTOR---------------------------------------------------------------
    private Print() {}
    
    //METHODS-------------------------------------------------------------------
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
    
    /**
     * Takes an integer representing the number of rounds played thus far, and
     * a sequence object which is used in order for the method to flash a
     * sequence of colored numbers to the console, which the player will have to
     * input in the same order.
     * 
     * @param round an integer representing the round at which the game is at
     * @param sequence a sequence object which contains a List of numbers in a
     * randomly generated order.
     */
    public static void round(int round, SequenceGenerator sequence) { 
        System.out.println("*************************************************************");
        System.out.println("*                        Round " + round + ":                           *");
        System.out.println("*************************************************************");
        for (int i = 0; i < sequence.getSequenceList().size(); i++) {
            try {
                System.out.print("                            " + sequence.getSequenceList().get(i));
                Thread.sleep(160); //milliseconds
                if (sequence.getSequenceList().get(i).endsWith("1")) {
                    Audio.playSound("Green.wav");
                } else if (sequence.getSequenceList().get(i).endsWith("2")) {
                    Audio.playSound("Red.wav");
                } else if (sequence.getSequenceList().get(i).endsWith("3")) {
                    Audio.playSound("Blue.wav");
                } else if (sequence.getSequenceList().get(i).endsWith("4")) {
                    Audio.playSound("Yellow.wav");
                }
                Thread.sleep(400);
                System.out.print("\b");
                Thread.sleep(400);
            } catch (InterruptedException ex) {
            }
        }
        
    }

    public static void userPrompt() {
        System.out.println("Please enter the sequence you have just seen without spaces: ");
    }

    public static void correctAnswer() {
        System.out.println("Correct! Next round.");
        //Olga: I cut this part, so the player doesn't cheat The sequence was: " + sequence + ".
        //Parameters: SequenceGenerator sequence
    }

    public static void incorrectAnswer(SequenceGenerator sequence) {
        System.out.println("Incorrect! The sequence was: " + sequence);
    }

    public static void gameOver() {
        System.out.println("*************************************************************");
        System.out.println("*                      Game over!                           *");
        System.out.println("*************************************************************");
        Audio.playSound("Game Over.wav");
    }
    
        public static void goodbye() {
        System.out.println("Goodbye!");
        Audio.playSound("Goodbye.wav");
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
        int i = 1;
        for (String s : Highscores.getHighScoreList()) {
            System.out.println(i + "." + s + "round(s)");
            i++;
        }
    }
}

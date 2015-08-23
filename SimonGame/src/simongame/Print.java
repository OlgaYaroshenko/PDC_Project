package simongame;

/**
 *
 * @author olga
 */
public class Print {

    private Print() {
    }

    public static void welcomeMessage() {
        System.out.println("WELCOME TO SIMON GAME!");
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
        System.out.println("Press [1] to start the game, [2] for instructions, [3] for highscores, [4] to exit: ");
    }

    public static void instructions() {
        System.out.println("In order to obtain the highest score you will need to repeat correctly a longer and longer sequence of numbers from 1 to 4.");
    }

    public static void round(int round, Sequence sequence) {
        System.out.println("Round " + round + ":");
//        for (int i = 0; i < sequence.length(); i++) {
//           System.out.print(i);
//           thread.sleep(1000);
//           System.out.print("\b");
//        }
        
    }

    public static void userPrompt() {
        System.out.println("Please enter the sequence you have just seen: ");
    }

    public static void correctAnswer(Sequence sequence) {
        System.out.println("Correct! The sequence was: " + sequence);
    }

    public static void incorrectAnswer(Sequence sequence) {
        System.out.println("Incorrect! The sequence was: " + sequence);
    }

    public static void gameOver() {
        System.out.println("Game over!");
    }
    
    public static void congratulations() {
        System.out.println("Congratulations! You have achieved highscores! Please enter your name: ");
    }
    
    public static void highscores() {
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

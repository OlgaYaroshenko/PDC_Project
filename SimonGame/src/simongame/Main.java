
package simongame;

import java.awt.EventQueue;

/**
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 * 
 * Contains main method which is the starting point for the entire program.
 */
public class Main {
    /**
     * The starting point for the entire program
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            //Create DB object here, get highsccores object

            MainFrame simonGame = new MainFrame();
        });
    }
}

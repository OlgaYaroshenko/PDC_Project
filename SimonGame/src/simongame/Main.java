
package simongame;

import java.awt.EventQueue;


public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            //Create DB object here, get highsccores object
            MainFrame simonGame = new MainFrame();
        });
    }
}

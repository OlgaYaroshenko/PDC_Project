package simongame;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Main frame that holds every component needed to run the program
 * 
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 */
public class MainFrame extends JFrame {
    //FIELDS--------------------------------------------------------------------
    private final URL LOGO_URL = Main.class.getResource("images/Logo.png");
    private final Image LOGO = new ImageIcon(LOGO_URL).getImage();
    private final MenuPanel MAIN_MENU = new MenuPanel();
    private final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
    private final Insets SCN_MAX = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
    private final int TASK_BAR_HEIGHT = SCN_MAX.bottom;
    private final Dimension SCREEN_SIZE = new Dimension(TOOLKIT.getScreenSize().width,
                                          TOOLKIT.getScreenSize().height - TASK_BAR_HEIGHT);
    
    //CONSTRUCTOR---------------------------------------------------------------
    public MainFrame() {
        //Initializing and defining frame settings
        setTitle("Simon");
        setName("Simon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(SCREEN_SIZE);
        setIconImage(LOGO);
        add(MAIN_MENU);
        setLocationRelativeTo(null);
    }
}

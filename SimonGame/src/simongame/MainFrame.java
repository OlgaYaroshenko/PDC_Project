package simongame;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
    //FIELDS--------------------------------------------------------------------
    private final Image LOGO = new ImageIcon("MenuHeader.png").getImage();
    private final MenuPanel MAIN_MENU = new MenuPanel();
    private final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
    private final Insets SCN_MAX = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
    private final int TASK_BAR_SIZE = SCN_MAX.bottom;
    private final Dimension SCREEN_SIZE = new Dimension(TOOLKIT.getScreenSize().width,
                                          TOOLKIT.getScreenSize().height - TASK_BAR_SIZE);
    
    //CONSTRUCTOR---------------------------------------------------------------
    public MainFrame() {
        setTitle("Simon");
        setName("Simon");
        setIconImage(LOGO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(SCREEN_SIZE);
        setIconImage(LOGO);
        add(MAIN_MENU);
        setLocationRelativeTo(null);
    }
}

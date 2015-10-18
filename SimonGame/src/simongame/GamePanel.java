package simongame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 */
public class GamePanel extends JPanel {
    //FIELDS--------------------------------------------------------------------
    private final URL DARK_GREEN_URL = Main.class.getResource("images/DarkGreen.png");
    private final URL DARK_RED_URL = Main.class.getResource("images/DarkRed.png");
    private final URL DARK_YELLOW_URL = Main.class.getResource("images/DarkYellow.png");
    private final URL DARK_BLUE_URL = Main.class.getResource("images/DarkBlue.png");
    private final URL LIGHT_GREEN_URL = Main.class.getResource("images/LightGreen.png");
    private final URL LIGHT_RED_URL = Main.class.getResource("images/LightRed.png");
    private final URL LIGHT_YELLOW_URL = Main.class.getResource("images/LightYellow.png");
    private final URL LIGHT_BLUE_URL = Main.class.getResource("images/LightBlue.png");
    private final URL PLAY_URL = Main.class.getResource("images/PlayButton.png");
    private final URL PLAY_PRESSED_URL = Main.class.getResource("images/PlayButtonPressed.png");
    private final URL HOME_URL = Main.class.getResource("images/HomeButton.png");
    private final URL HOME_PRESSED_URL = Main.class.getResource("images/HomeButtonPressed.png");
    private final URL TITLE_URL = Main.class.getResource("images/TitleLogo.png");
    
    private final Icon DARK_GREEN_ICON = new ImageIcon(DARK_GREEN_URL);
    private final Icon DARK_RED_ICON = new ImageIcon(DARK_RED_URL);
    private final Icon DARK_YELLOW_ICON = new ImageIcon(DARK_YELLOW_URL);
    private final Icon DARK_BLUE_ICON = new ImageIcon(DARK_BLUE_URL);
    private final Icon LIGHT_GREEN_ICON = new ImageIcon(LIGHT_GREEN_URL);
    private final Icon LIGHT_RED_ICON = new ImageIcon(LIGHT_RED_URL);
    private final Icon LIGHT_YELLOW_ICON = new ImageIcon(LIGHT_YELLOW_URL);
    private final Icon LIGHT_BLUE_ICON = new ImageIcon(LIGHT_BLUE_URL);
    private final Icon PLAY_ICON = new ImageIcon(PLAY_URL);
    private final Icon PLAY_PRESSED_ICON = new ImageIcon(PLAY_PRESSED_URL);
    private final Icon HOME_ICON = new ImageIcon(HOME_URL);
    private final Icon HOME_ICON_PRESSED = new ImageIcon(HOME_PRESSED_URL);
    private final Icon TITLE_LOGO = new ImageIcon(TITLE_URL);
    
    private final JButton GREEN_BUTTON = new JButton();
    private final JButton RED_BUTTON = new JButton();
    private final JButton YELLOW_BUTTON = new JButton();
    private final JButton BLUE_BUTTON = new JButton();
    private final JButton PLAY_BUTTON = new JButton();
    private final JButton MAIN_MENU_BUTTON = new JButton();
    
    private final JLabel BEST_SCORE_INDICATOR  = new JLabel();
    private final JLabel ROUND_TALLY = new JLabel();
    private final JLabel TITLE_HEADER = new JLabel();
    
    private final GridBagConstraints GRID_CONSTRAINTS = new GridBagConstraints();
            
    //CONSTRUCTOR---------------------------------------------------------------
    /**
     * Sets the game panel background and layout, and then loads all JComponents
     */
    public GamePanel() {
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        loadPanelComponents();      
    }
    
    //GETTERS-------------------------------------------------------------------
    public JButton getGREEN_BUTTON() {
        return GREEN_BUTTON;
    }

    public JButton getRED_BUTTON() {
        return RED_BUTTON;
    }

    public JButton getYELLOW_BUTTON() {
        return YELLOW_BUTTON;
    }

    public JButton getBLUE_BUTTON() {
        return BLUE_BUTTON;
    }

    public JButton getPLAY_BUTTON() {
        return PLAY_BUTTON;
    }

    public JButton getMAIN_MENU_BUTTON() {
        return MAIN_MENU_BUTTON;
    }

    public JLabel getBEST_SCORE_INDICATOR() {
        return BEST_SCORE_INDICATOR;
    }

    public Icon getDARK_GREEN_ICON() {
        return DARK_GREEN_ICON;
    }

    public Icon getDARK_RED_ICON() {
        return DARK_RED_ICON;
    }

    public Icon getDARK_YELLOW_ICON() {
        return DARK_YELLOW_ICON;
    }

    public Icon getDARK_BLUE_ICON() {
        return DARK_BLUE_ICON;
    }

    public Icon getLIGHT_GREEN_ICON() {
        return LIGHT_GREEN_ICON;
    }

    public Icon getLIGHT_RED_ICON() {
        return LIGHT_RED_ICON;
    }

    public Icon getLIGHT_YELLOW_ICON() {
        return LIGHT_YELLOW_ICON;
    }

    public Icon getLIGHT_BLUE_ICON() {
        return LIGHT_BLUE_ICON;
    }

    public Icon getPLAY_ICON() {
        return PLAY_ICON;
    }

    public Icon getPLAY_PRESSED_ICON() {
        return PLAY_PRESSED_ICON;
    }

    public Icon getHOME_ICON() {
        return HOME_ICON;
    }

    public Icon getHOME_ICON_PRESSED() {
        return HOME_ICON_PRESSED;
    }
    
    public JLabel getROUND_TALLY() {
        return ROUND_TALLY;
    }
    
    //METHODS-------------------------------------------------------------------
    /**
     * Method responsible for loading all the JComponents on to the Game Panel
     */
    private void loadPanelComponents() {
        Font labelFont = new Font("Perpetua Titling MT", Font.BOLD, 12);
        
        GRID_CONSTRAINTS.gridwidth = 2;
        GRID_CONSTRAINTS.ipady = -60;
        
        //Adding header
        TITLE_HEADER.setIcon(TITLE_LOGO);
        add(TITLE_HEADER, GRID_CONSTRAINTS);
        
        //Adding Best Score label
        GRID_CONSTRAINTS.ipady = 15;
        GRID_CONSTRAINTS.gridwidth = 1;
        GRID_CONSTRAINTS.gridy = 1;
        BEST_SCORE_INDICATOR.setForeground(Color.WHITE);
        BEST_SCORE_INDICATOR.setFont(labelFont);
        add(BEST_SCORE_INDICATOR, GRID_CONSTRAINTS);
        
        //Adding Round Tally label
        GRID_CONSTRAINTS.gridx = 1;
        ROUND_TALLY.setForeground(Color.WHITE);
        ROUND_TALLY.setFont(labelFont);
        add(ROUND_TALLY, GRID_CONSTRAINTS);
        
        //Adding Green button
        GRID_CONSTRAINTS.ipadx = -110;
        GRID_CONSTRAINTS.ipady = -85;
        GRID_CONSTRAINTS.gridx = 0;
        GRID_CONSTRAINTS.gridy = 2;
        GREEN_BUTTON.setBorderPainted(false); 
        GREEN_BUTTON.setContentAreaFilled(false); 
        GREEN_BUTTON.setFocusPainted(false); 
        GREEN_BUTTON.setOpaque(false);
        GREEN_BUTTON.setIcon(DARK_GREEN_ICON);
        add(GREEN_BUTTON, GRID_CONSTRAINTS);
        
        //Adding Red button
        GRID_CONSTRAINTS.gridx = 1;
        RED_BUTTON.setBorderPainted(false); 
        RED_BUTTON.setContentAreaFilled(false); 
        RED_BUTTON.setFocusPainted(false); 
        RED_BUTTON.setOpaque(false);
        RED_BUTTON.setIcon(DARK_RED_ICON);
        add(RED_BUTTON, GRID_CONSTRAINTS);
        
        //Addgin Yellow button
        GRID_CONSTRAINTS.gridy = 3;
        GRID_CONSTRAINTS.gridx = 0;
        YELLOW_BUTTON.setBorderPainted(false); 
        YELLOW_BUTTON.setContentAreaFilled(false); 
        YELLOW_BUTTON.setFocusPainted(false); 
        YELLOW_BUTTON.setOpaque(false);
        YELLOW_BUTTON.setIcon(DARK_YELLOW_ICON);
        add(YELLOW_BUTTON, GRID_CONSTRAINTS);
        
        //Adding Blue button
        GRID_CONSTRAINTS.gridx = 1;
        BLUE_BUTTON.setBorderPainted(false); 
        BLUE_BUTTON.setContentAreaFilled(false); 
        BLUE_BUTTON.setFocusPainted(false); 
        BLUE_BUTTON.setOpaque(false);
        BLUE_BUTTON.setIcon(DARK_BLUE_ICON);
        add(BLUE_BUTTON, GRID_CONSTRAINTS);
        
        //Adding Main Menu button. Used to return to Main Menu Panel
        GRID_CONSTRAINTS.anchor = GridBagConstraints.NORTH;
        GRID_CONSTRAINTS.ipadx = 0;
        GRID_CONSTRAINTS.ipady = 0;
        GRID_CONSTRAINTS.gridx = 0;
        GRID_CONSTRAINTS.gridy = 4;
        MAIN_MENU_BUTTON.setBorderPainted(false);
        MAIN_MENU_BUTTON.setContentAreaFilled(false); 
        MAIN_MENU_BUTTON.setFocusPainted(false); 
        MAIN_MENU_BUTTON.setOpaque(false);
        MAIN_MENU_BUTTON.setIcon(HOME_ICON);
        add(MAIN_MENU_BUTTON, GRID_CONSTRAINTS);
        
        //Adding Play button
        GRID_CONSTRAINTS.gridx = 1;
        PLAY_BUTTON.setBorderPainted(false);
        PLAY_BUTTON.setContentAreaFilled(false); 
        PLAY_BUTTON.setFocusPainted(false); 
        PLAY_BUTTON.setOpaque(false);
        PLAY_BUTTON.setIcon(PLAY_ICON);
        add(PLAY_BUTTON, GRID_CONSTRAINTS);
    }
}
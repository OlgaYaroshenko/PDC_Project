

package simongame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 * 
 * Extends JPanel. Represents the main panel the player sees when starting the
 * program. Contains game logo and all menu options for the program, such as
 * starting a new game option.
 */
public class MenuPanel extends JPanel {
    //FIELDS--------------------------------------------------------------------
    private final URL LOGO_URL = MenuPanel.class.getResource("images/MenuHeader.png");
    private final URL INSTRUCTIONS_URL = MenuPanel.class.getResource("images/Instructions.png");
    private final URL HOME_ICON_URL = MenuPanel.class.getResource("images/InstructionsHomeButton.png");
    
    private final Icon LOGO = new ImageIcon(LOGO_URL);
    private final Icon INSTRUCTIONS_IMAGE = new ImageIcon(INSTRUCTIONS_URL);
    private final Icon HOME_ICON = new ImageIcon(HOME_ICON_URL);
    
    private final JLabel LOGO_HEADER = new JLabel(LOGO);
    private final JLabel INSTRUCTIONS_LABEL = new JLabel(INSTRUCTIONS_IMAGE);
    
    private final JPanel MENU_OPTIONS_PANEL = new JPanel(new GridBagLayout());
    private final JPanel HIGHSCORES_PANEL = new JPanel();
    private final JPanel INSTRUCTIONS_PANEL = new JPanel();
    private final GamePanel GAME_PANEL = new GamePanel();
    private final CardLayout CARDLAYOUT = new CardLayout();
    
    private final Dimension DEFAULT_BUTTON_SIZE = new Dimension(150, 25);
    private final JButton PLAY_BUTTON = new JButton("PLAY");
    private final JButton INSTRUCTIONS_BUTTON = new JButton("INSTRUCTIONS");
    private final JButton INSTRUCTIONS_HOME_BUTTON = new JButton();
    private final JButton HIGH_SCORES_BUTTON = new JButton("HIGH SCORES");
    private final JButton HIGH_SCORES_HOME_BUTTON = new JButton("HOME");
    private final JButton EXIT_BUTTON = new JButton("EXIT");
    private final Controller GAME_CONTROLLER;
    
    //CONSTRUCTOR---------------------------------------------------------------
    /**
     * Initializes a Menu Panel with JComponents representing menu options and
     * a card layout in order to load the Main Frame of the program with the
     * selected panel, e.g. clicking on the Play option will cause a switch to
     * the Game Panel.
     */
    public MenuPanel() {
        setLayout(CARDLAYOUT);
        add(GAME_PANEL, "Game");
        add(loadMenuOptionsPanel(), "Menu");
        add(loadHighScoresPanel(null), "HighScores");
        add(loadInstructionsPanel(), "Instructions");
        CARDLAYOUT.show(this, "Menu");
        GAME_CONTROLLER = new Controller(this, GAME_PANEL);
        loadActionListeners();
    }
    
    //GETTERS-------------------------------------------------------------------
    public GamePanel getGAME_PANEL() {
        return GAME_PANEL;
    }
    
    public CardLayout getCARDLAYOUT() {
        return CARDLAYOUT;
    }

    public JButton getPLAY_BUTTON() {
        return PLAY_BUTTON;
    }

    public JButton getINSTRUCTIONS_BUTTON() {
        return INSTRUCTIONS_BUTTON;
    }

    public JButton getINSTRUCTIONS_HOME_BUTTON() {
        return INSTRUCTIONS_HOME_BUTTON;
    }

    public JButton getHIGH_SCORES_BUTTON() {
        return HIGH_SCORES_BUTTON;
    }

    public JButton getHIGH_SCORES_HOME_BUTTON() {
        return HIGH_SCORES_HOME_BUTTON;
    }
    
    public JButton getEXIT_BUTTON() {
        return EXIT_BUTTON;
    }
    
    //METHODS-------------------------------------------------------------------
    /**
     * Loads all JComponents to the Menu Panel
     * @return a JPanel representing the Menu Panel.
     */
    private JPanel loadMenuOptionsPanel() {
        MENU_OPTIONS_PANEL.setBackground(Color.BLACK);
        MENU_OPTIONS_PANEL.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2,2,2,2);
        
        constraints.gridwidth = 4;
        MENU_OPTIONS_PANEL.add(LOGO_HEADER, constraints);
        
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        MENU_OPTIONS_PANEL.add(PLAY_BUTTON, constraints);
        
        constraints.gridx = 1;
        MENU_OPTIONS_PANEL.add(INSTRUCTIONS_BUTTON, constraints);
        
        constraints.gridx = 2;
        MENU_OPTIONS_PANEL.add(HIGH_SCORES_BUTTON, constraints);
        
        constraints.gridx = 3;
        MENU_OPTIONS_PANEL.add(EXIT_BUTTON, constraints);
        
        PLAY_BUTTON.setPreferredSize(DEFAULT_BUTTON_SIZE);
        INSTRUCTIONS_BUTTON.setPreferredSize(DEFAULT_BUTTON_SIZE);
        HIGH_SCORES_BUTTON.setPreferredSize(DEFAULT_BUTTON_SIZE);
        EXIT_BUTTON.setPreferredSize(DEFAULT_BUTTON_SIZE);
        
        return MENU_OPTIONS_PANEL;
    }
    
    /**
     * Adds all JComponents to the Instructions Panel
     * @return a JPanel representing the Instructions Panel
     */
    private JPanel loadInstructionsPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        INSTRUCTIONS_PANEL.setLayout(new GridBagLayout());
        INSTRUCTIONS_PANEL.setBackground(Color.WHITE);
        INSTRUCTIONS_PANEL.add(INSTRUCTIONS_LABEL, constraints);
        constraints.gridy = 1;
        INSTRUCTIONS_HOME_BUTTON.setIcon(HOME_ICON);
        INSTRUCTIONS_HOME_BUTTON.setBorderPainted(false);
        INSTRUCTIONS_HOME_BUTTON.setContentAreaFilled(false);
        INSTRUCTIONS_HOME_BUTTON.setFocusable(false);
        INSTRUCTIONS_PANEL.add(INSTRUCTIONS_HOME_BUTTON, constraints);
        return INSTRUCTIONS_PANEL;
    }
    
    /**
     * Loads all JComponents onto the HighScores Panel
     * 
     * @param h a HighScores object containing a sorted collection of Players
     * @return a JPanel representing the HighScoresPanel
     */
    protected JPanel loadHighScoresPanel(Highscores h) {
        //Defining fonts
        Font scoreFont = new Font("Bauhaus 93", Font.BOLD, 18);
        Font headerFont = new Font("Bauhaus 93", Font.BOLD, 32);
        
        //Setting panel constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipady = 20;
        
        //Setting HighScores panel
        HIGHSCORES_PANEL.setLayout(new GridBagLayout());
        HIGHSCORES_PANEL.setBackground(Color.WHITE);
        HIGHSCORES_PANEL.removeAll();
        
        //Adding top highScores
        JLabel header = new JLabel("HIGH SCORES");
        header.setFont(headerFont);
        HIGHSCORES_PANEL.add(header, constraints);
        int scoresDisplayed = 0;
        int maxScoresOnDisplay = 10;
        if (h != null) {
            for (String playerInfo : h.getHighScoreArray(10)) {
                constraints.gridy = ++scoresDisplayed;
                if(scoresDisplayed > maxScoresOnDisplay)
                    break;
                JLabel scoreLabel = new JLabel(playerInfo + " rounds");
                scoreLabel.setFont(scoreFont);
                HIGHSCORES_PANEL.add(scoreLabel, constraints);
            }
        }
        //Adding homeButton, used to return to main menu
        constraints.gridy = ++scoresDisplayed;
        constraints.weighty = 1.0;
        HIGHSCORES_PANEL.add(HIGH_SCORES_HOME_BUTTON, constraints);
        HIGH_SCORES_HOME_BUTTON.setBorderPainted(false);
        HIGH_SCORES_HOME_BUTTON.setContentAreaFilled(false);
        HIGH_SCORES_HOME_BUTTON.setFocusable(false);
        HIGH_SCORES_HOME_BUTTON.setIcon(HOME_ICON);
        return HIGHSCORES_PANEL;
    }
    
    /**
     * Loads all actions listeners into every JComponent for every panel of the 
     * program
     */
    private void loadActionListeners() {
        PLAY_BUTTON.addActionListener(GAME_CONTROLLER);
        GAME_PANEL.getMAIN_MENU_BUTTON().addActionListener(GAME_CONTROLLER);
        GAME_PANEL.getMAIN_MENU_BUTTON().addMouseListener(GAME_CONTROLLER);
        GAME_PANEL.getPLAY_BUTTON().addMouseListener(GAME_CONTROLLER);
        GAME_PANEL.getGREEN_BUTTON().addMouseListener(GAME_CONTROLLER);
        GAME_PANEL.getRED_BUTTON().addMouseListener(GAME_CONTROLLER);
        GAME_PANEL.getYELLOW_BUTTON().addMouseListener(GAME_CONTROLLER);
        GAME_PANEL.getBLUE_BUTTON().addMouseListener(GAME_CONTROLLER);
        
        INSTRUCTIONS_BUTTON.addActionListener(GAME_CONTROLLER);
        INSTRUCTIONS_HOME_BUTTON.addActionListener(GAME_CONTROLLER);
        
        HIGH_SCORES_BUTTON.addActionListener(GAME_CONTROLLER);
        HIGH_SCORES_HOME_BUTTON.addActionListener(GAME_CONTROLLER);
        
        EXIT_BUTTON.addActionListener(GAME_CONTROLLER);
    }
}



package simongame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//TODO consider adding level of difficulty options. The harder, the faster the sequence flashes
public class MenuPanel extends JPanel {
    //FIELDS--------------------------------------------------------------------
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
    
    private final Icon LOGO = new ImageIcon("MenuHeader.png");
    private final Icon INSTRUCTIONS_IMAGE = new ImageIcon("Instructions.png");
    private final Icon HOME_ICON = new ImageIcon("InstructionsHomeButton.png");
    private final JLabel LOGO_HEADER = new JLabel(LOGO);
    private final JLabel INSTRUCTIONS_LABEL = new JLabel(INSTRUCTIONS_IMAGE);
    
    
    //CONSTRUCTOR---------------------------------------------------------------
    public MenuPanel() {
        setLayout(CARDLAYOUT);
        add(GAME_PANEL, "Game");
        add(loadMenuOptionsPanel(), "Menu");
        add(loadHighScoresPanel(null), "HighScores"); //TODO revise null
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
    
    
    
    private JPanel loadMenuOptionsPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2,2,2,2);
        
        MENU_OPTIONS_PANEL.setBackground(Color.BLACK);
        MENU_OPTIONS_PANEL.setLayout(new GridBagLayout());
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
    
    protected JPanel loadHighScoresPanel(String[] highScores) {
        //TODO create label and pass highScores string to it. Load label in HS panel
        HIGHSCORES_PANEL.setBackground(Color.WHITE);
        HIGHSCORES_PANEL.add(HIGH_SCORES_HOME_BUTTON);
        return HIGHSCORES_PANEL;
    }
    
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

    public JButton getEXIT_BUTTON() {
        return EXIT_BUTTON;
    }
    
    //METHODS-------------------------------------------------------------------
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Image LOGO = new ImageIcon("Logo.png").getImage();
                MenuPanel mainMenu = new MenuPanel();
                
                JFrame frame = new JFrame("Simon");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setSize(750, 700);
                frame.setLocationRelativeTo(null);
                frame.setIconImage(LOGO);
                frame.add(mainMenu);
            }
        });
    }

}

package simongame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    //FIELDS--------------------------------------------------------------------
    private final Icon DARK_GREEN_ICON = new ImageIcon("DarkGreen.png");
    private final Icon DARK_RED_ICON = new ImageIcon("DarkRed.png");
    private final Icon DARK_YELLOW_ICON = new ImageIcon("DarkYellow.png");
    private final Icon DARK_BLUE_ICON = new ImageIcon("DarkBlue.png");
    private final Icon LIGHT_GREEN_ICON = new ImageIcon("LightGreen.png");
    private final Icon LIGHT_RED_ICON = new ImageIcon("LightRed.png");
    private final Icon LIGHT_YELLOW_ICON = new ImageIcon("LightYellow.png");
    private final Icon LIGHT_BLUE_ICON = new ImageIcon("LightBlue.png");
    private final Icon PLAY_ICON = new ImageIcon("PlayButton.png");
    private final Icon PLAY_PRESSED_ICON = new ImageIcon("PlayButtonPressed.png");
    private final Icon HOME_ICON = new ImageIcon("HomeButton.png");
    private final Icon HOME_ICON_PRESSED = new ImageIcon("HomeButtonPressed.png");
    private final Icon TITLE_LOGO = new ImageIcon("TitleLogo.png");
    
    private final JButton GREEN_BUTTON = new JButton();
    private final JButton RED_BUTTON = new JButton();
    private final JButton YELLOW_BUTTON = new JButton();
    private final JButton BLUE_BUTTON = new JButton();
    private final JButton PLAY_BUTTON = new JButton();
    private final JButton MAIN_MENU_BUTTON = new JButton();
    
    private final JLabel BEST_SCORE_INDICATOR  = new JLabel("Best : ");
    private final JLabel ROUND_TALLY = new JLabel("Round : 1");
    private final JLabel TITLE_HEADER = new JLabel();
    
    private final GridBagConstraints GRID_CONSTRAINTS = new GridBagConstraints();
            
    //CONSTRUCTOR---------------------------------------------------------------
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
    private void loadPanelComponents() {
        Font labelFont = new Font("Perpetua Titling MT", Font.BOLD, 12);
        
        GRID_CONSTRAINTS.gridwidth = 2;
        GRID_CONSTRAINTS.ipady = -60;
        
        TITLE_HEADER.setIcon(TITLE_LOGO);
        add(TITLE_HEADER, GRID_CONSTRAINTS);
        
        GRID_CONSTRAINTS.ipady = 15;
        GRID_CONSTRAINTS.gridwidth = 1;
        GRID_CONSTRAINTS.gridy = 1;
        BEST_SCORE_INDICATOR.setForeground(Color.WHITE);
        BEST_SCORE_INDICATOR.setText("Best : 10");
        BEST_SCORE_INDICATOR.setFont(labelFont);
        add(BEST_SCORE_INDICATOR, GRID_CONSTRAINTS);
        
        GRID_CONSTRAINTS.gridx = 1;
        ROUND_TALLY.setForeground(Color.WHITE);
        ROUND_TALLY.setFont(labelFont);
        add(ROUND_TALLY, GRID_CONSTRAINTS);
        
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
        
        GRID_CONSTRAINTS.gridx = 1;
        RED_BUTTON.setBorderPainted(false); 
        RED_BUTTON.setContentAreaFilled(false); 
        RED_BUTTON.setFocusPainted(false); 
        RED_BUTTON.setOpaque(false);
        RED_BUTTON.setIcon(DARK_RED_ICON);
        add(RED_BUTTON, GRID_CONSTRAINTS);
        
        GRID_CONSTRAINTS.gridy = 3;
        GRID_CONSTRAINTS.gridx = 0;
        YELLOW_BUTTON.setBorderPainted(false); 
        YELLOW_BUTTON.setContentAreaFilled(false); 
        YELLOW_BUTTON.setFocusPainted(false); 
        YELLOW_BUTTON.setOpaque(false);
        YELLOW_BUTTON.setIcon(DARK_YELLOW_ICON);
        add(YELLOW_BUTTON, GRID_CONSTRAINTS);
        
        GRID_CONSTRAINTS.gridx = 1;
        BLUE_BUTTON.setBorderPainted(false); 
        BLUE_BUTTON.setContentAreaFilled(false); 
        BLUE_BUTTON.setFocusPainted(false); 
        BLUE_BUTTON.setOpaque(false);
        BLUE_BUTTON.setIcon(DARK_BLUE_ICON);
        add(BLUE_BUTTON, GRID_CONSTRAINTS);
        
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
        
        GRID_CONSTRAINTS.gridx = 1;
        PLAY_BUTTON.setBorderPainted(false);
        PLAY_BUTTON.setContentAreaFilled(false); 
        PLAY_BUTTON.setFocusPainted(false); 
        PLAY_BUTTON.setOpaque(false);
        PLAY_BUTTON.setIcon(PLAY_ICON);
        add(PLAY_BUTTON, GRID_CONSTRAINTS);
    }
    
    /**
     * Main class for testing purposes only
     * 
     * @param args 
     */
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Image LOGO = new ImageIcon("Logo.png").getImage();
                
                GamePanel gameGUI = new GamePanel();
                JFrame frame = new JFrame("Simon");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setSize(750, 700);
                frame.setLocationRelativeTo(null);
                frame.setIconImage(LOGO);
                frame.add(gameGUI);
            }
        });
    }
}

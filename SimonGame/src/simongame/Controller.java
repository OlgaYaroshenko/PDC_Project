package simongame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import static simongame.Highscores.fromDB;

/**
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 * 
 * This class is responsible for controlling the interactions the user has
 * with the Menu Panel and Game Panel of the program.
 * 
 * This class implements ActionListener and MouseListener
 */
public class Controller implements ActionListener, MouseListener {
    
    //FIELDS--------------------------------------------------------------------
    private final GamePanel GAME_PANEL;
    private final Gameplay GAME_PLAY;
    private final MenuPanel MAIN_MENU_PANEL;
    private DBconnection DATABASE;
    private Highscores HIGH_SCORES;
    
    //CONSTRUCTOR---------------------------------------------------------------
    /**
     * Initializes a Controller object which controls user interaction via the
     * main menu panel and the game panel.
     * 
     * Additionally, in order to populate the panels with up to date information,
     * this constructor also initializes a HighScores object which is retrieved
     * and modified via a Database object.
     * 
     * @param mainMenu is a Menu Panel (sub-classed from JPanel) which contains
     * the main menu options (Play | Instructions | View HighScores | Exit)
     * 
     * @param gamePanel is a Game Panel (sub-classed from JPanel) which contains
     * all JComponents necessary for the actual game play to take place.
     */
    public Controller(MenuPanel mainMenu, GamePanel gamePanel) {
        //Initializing and setting up connection to database
        this.DATABASE = new DBconnection();
        this.DATABASE.autoConnectDB();
        //Retrieving player info from database to initialize HighScores
        try {
            HIGH_SCORES = fromDB(DATABASE);
        } catch (SQLException ex) {
            HIGH_SCORES.makeEmpty();
        }
        //Initializing panels and game play status
        this.GAME_PANEL = gamePanel;
        this.GAME_PANEL.getBEST_SCORE_INDICATOR().setText("Best : " + HIGH_SCORES.getHighestScore());
        this.GAME_PLAY = new Gameplay(gamePanel);
        this.MAIN_MENU_PANEL = mainMenu;
    }
    
    //METHODS-------------------------------------------------------------------
    /**
     * Method responsible for starting a new sequence round after the player
     * has correctly guessed a color sequence.
     * 
     * When this method is called, a new color flash sequence animation will
     * begin
     */
    private void startNewRound() {
        GAME_PLAY.setIsSequenceRunning(true);
        GAME_PLAY.getSequenceGenerator().generateSequence();
        //Flashing new color sequence from a seperate thread.
        Thread flashSequenceThread = new Thread(GAME_PLAY);
        flashSequenceThread.start();
    }
    
    /**
     * This method is only used if the player has lost by incorrectly guessing
     * a color sequence.
     * 
     * This method will check whether the player has achieved a new high score
     * and prompt the player to enter their name in case they have.
     * 
     * NOTE: new high scores are only achieved by beating the most recent best
     * score (which will always be displayed in the game panel)
     */
    private void determineOutCome() {
        //Player has lost, playing game over sound.
        Audio.playSound("sound/GameOver.wav"); 
        int roundsAchieved = GAME_PLAY.getRounds();
        //Changing gameplay status, these conditions control which buttons the player is allowd to press
        GAME_PLAY.setIsGameOver(true);
        GAME_PLAY.setIsSequenceRunning(true); //sequence running: flashing color player should've pressed
        //Through a seperate thread: flashing the color the user should have pressed instead
        Thread flashSequenceThread = new Thread(GAME_PLAY);
        flashSequenceThread.start(); 
        
        //Checking if the player achieved a highScore. If so, recording to database
        if (roundsAchieved != 0 && roundsAchieved > HIGH_SCORES.getHighestScore()) {
            GAME_PANEL.getBEST_SCORE_INDICATOR().setText("Best : " + roundsAchieved);
            //Displaying pop up window and getting player details plus time score achieved
            Date date = new Date(System.currentTimeMillis());
            String highScoreMessage = "NEW BEST SCORE | ENTER YOUR NAME FOR THE HISTORY BOOKS";
            String playerName = (JOptionPane.showInputDialog(GAME_PANEL, highScoreMessage, "Simon Game",
                                JOptionPane.INFORMATION_MESSAGE));
            
            //Saving new high score to database
            int maxCharacters = 15;
            if (playerName == null || playerName.contains("null") || playerName.isEmpty()) {
                playerName = "No Name | " + date + " | ";
            } else if(playerName.length() > maxCharacters) {
                String temp = playerName;
                playerName = temp.substring(0, maxCharacters) + "..." + " | " + date + " | ";
            } else {
                playerName += " | " + date + " | ";
            }
            Player player = new Player(playerName, roundsAchieved);
            HIGH_SCORES.saveHighScore(DATABASE, player);
        }
      
        //Enabling play button so player can start a new Game
        GAME_PANEL.getPLAY_BUTTON().setIcon(GAME_PANEL.getPLAY_ICON());
    }
    
    /**
     * 
     * @param e represents the button that the player has pressed within the
     * main menu panel. The main menu panel runs through a card layout manager.
     * Each button represents a different panel. For instance, if the player 
     * presses the Player button on the menu panel, the action to be performed
     * is to switch to the Game Panel.
     */
    @Override
    public void actionPerformed(ActionEvent e) {   
        if(e.getSource() == MAIN_MENU_PANEL.getPLAY_BUTTON())
            MAIN_MENU_PANEL.getCARDLAYOUT().show(MAIN_MENU_PANEL, "Game");
            
        else if(e.getSource() == GAME_PANEL.getMAIN_MENU_BUTTON() && !GAME_PLAY.isSequenceRunning())
            MAIN_MENU_PANEL.getCARDLAYOUT().show(MAIN_MENU_PANEL, "Menu");
            
        else if(e.getSource() == MAIN_MENU_PANEL.getINSTRUCTIONS_BUTTON())
            MAIN_MENU_PANEL.getCARDLAYOUT().show(MAIN_MENU_PANEL, "Instructions");
            
        else if(e.getSource() == MAIN_MENU_PANEL.getINSTRUCTIONS_HOME_BUTTON())
            MAIN_MENU_PANEL.getCARDLAYOUT().show(MAIN_MENU_PANEL, "Menu");
            
        else if(e.getSource() == MAIN_MENU_PANEL.getHIGH_SCORES_HOME_BUTTON())
            MAIN_MENU_PANEL.getCARDLAYOUT().show(MAIN_MENU_PANEL, "Menu");
            
        else if(e.getSource() == MAIN_MENU_PANEL.getHIGH_SCORES_BUTTON()) {
            MAIN_MENU_PANEL.loadHighScoresPanel(HIGH_SCORES);
            MAIN_MENU_PANEL.getCARDLAYOUT().show(MAIN_MENU_PANEL, "HighScores");
            
        } else if(e.getSource() == MAIN_MENU_PANEL.getEXIT_BUTTON()) {
            System.exit(1);
        }
            
    }
    
    /**
     * This method is responsible for controlling what happens solely within the
     * game panel. This method controls the color JComponents and interacts with
     * the Game Play class to control how the game is being played out, depending
     * on when and which color buttons the player presses.
     * 
     * @param e action representing which color button, or menu button the player
     * has pressed during the main game play
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Thread.yield();
        if (e.getSource() == GAME_PANEL.getGREEN_BUTTON() && GAME_PLAY.isGameStarted() && !GAME_PLAY.isSequenceRunning()) {
            GAME_PANEL.getGREEN_BUTTON().setIcon(GAME_PANEL.getLIGHT_GREEN_ICON());
            Audio.playSound("sound/Green.wav");
        
        } else if (e.getSource() == GAME_PANEL.getRED_BUTTON() && GAME_PLAY.isGameStarted() && !GAME_PLAY.isSequenceRunning()) {
            GAME_PANEL.getRED_BUTTON().setIcon(GAME_PANEL.getLIGHT_RED_ICON());
            Audio.playSound("sound/Red.wav");
        
        } else if (e.getSource() == GAME_PANEL.getYELLOW_BUTTON() && GAME_PLAY.isGameStarted() && !GAME_PLAY.isSequenceRunning()) {
            GAME_PANEL.getYELLOW_BUTTON().setIcon(GAME_PANEL.getLIGHT_YELLOW_ICON());
            Audio.playSound("sound/Yellow.wav");
        
        } else if (e.getSource() == GAME_PANEL.getBLUE_BUTTON() && GAME_PLAY.isGameStarted() && !GAME_PLAY.isSequenceRunning()) {
            GAME_PANEL.getBLUE_BUTTON().setIcon(GAME_PANEL.getLIGHT_BLUE_ICON());
            Audio.playSound("sound/Blue.wav");
        
        } else if (e.getSource() == GAME_PANEL.getMAIN_MENU_BUTTON() && !GAME_PLAY.isSequenceRunning()) {
            GAME_PANEL.getMAIN_MENU_BUTTON().setIcon(GAME_PANEL.getHOME_ICON_PRESSED());
        
        } else if (e.getSource() == GAME_PANEL.getPLAY_BUTTON() && !GAME_PLAY.isGameStarted()) {
            GAME_PANEL.getPLAY_BUTTON().setIcon(GAME_PANEL.getPLAY_PRESSED_ICON());
        }
    }
    
    /**
     * This method is responsible for controlling what happens solely within the
     * game panel. This method controls the color JComponents and interacts with
     * the Game Play class to control how the game is being played out, depending
     * on when and which color buttons the player presses.
     * 
     * @param e action representing which color button, or menu button the player
     * has pressed during the main game play
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        Thread.yield();
        if (e.getSource() == GAME_PANEL.getGREEN_BUTTON() && !GAME_PLAY.isSequenceRunning() && GAME_PLAY.isGameStarted()) {
            GAME_PANEL.getGREEN_BUTTON().setIcon(GAME_PANEL.getDARK_GREEN_ICON());
            if(!GAME_PLAY.isEntryCorrect(Color.GREEN)) //If entre not correct, means player has lost
                determineOutCome(); //Player lost, but checking if he/she achieved a new best score
            else if (GAME_PLAY.isRoundAttemptComplete()) //Means player has finished pressing color buttons in correct sequence
                startNewRound();
            
        } else if (e.getSource() == GAME_PANEL.getRED_BUTTON() && !GAME_PLAY.isSequenceRunning() && GAME_PLAY.isGameStarted()) {
            GAME_PANEL.getRED_BUTTON().setIcon(GAME_PANEL.getDARK_RED_ICON());
            if(!GAME_PLAY.isEntryCorrect(Color.RED))
                determineOutCome();
            else if (GAME_PLAY.isRoundAttemptComplete())
                startNewRound();
            
        } else if (e.getSource() == GAME_PANEL.getYELLOW_BUTTON() && !GAME_PLAY.isSequenceRunning() && GAME_PLAY.isGameStarted()) {
            GAME_PANEL.getYELLOW_BUTTON().setIcon(GAME_PANEL.getDARK_YELLOW_ICON());
            if (!GAME_PLAY.isEntryCorrect(Color.YELLOW)) 
                determineOutCome();
            else if (GAME_PLAY.isRoundAttemptComplete())
                startNewRound();
            
        } else if (e.getSource() == GAME_PANEL.getBLUE_BUTTON() && !GAME_PLAY.isSequenceRunning() && GAME_PLAY.isGameStarted()) {
            GAME_PANEL.getBLUE_BUTTON().setIcon(GAME_PANEL.getDARK_BLUE_ICON());
            if(!GAME_PLAY.isEntryCorrect(Color.BLUE))
                determineOutCome();
            else if (GAME_PLAY.isRoundAttemptComplete())
                startNewRound();
            
        } else if (e.getSource() == GAME_PANEL.getMAIN_MENU_BUTTON() && !GAME_PLAY.isSequenceRunning()) {
            GAME_PANEL.getMAIN_MENU_BUTTON().setIcon(GAME_PANEL.getHOME_ICON());
        
        } else if (e.getSource() == GAME_PANEL.getPLAY_BUTTON() && !GAME_PLAY.isGameStarted()) {
            //Changing status of gameplay. These conditions control which buttons the player is allowed to press
            GAME_PLAY.setIsSequenceRunning(true);
            GAME_PLAY.setIsGameStarted(true);
            GAME_PLAY.getSequenceGenerator().generateSequence();
            //Running flash color sequence for first time, using seperate thread
            Thread flashSequenceThread = new Thread(GAME_PLAY);
            flashSequenceThread.start();
        }
    }
    //Un-used
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}

}

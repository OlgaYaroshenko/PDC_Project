package simongame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Controller implements ActionListener, MouseListener {
    
    //FIELDS--------------------------------------------------------------------
    private final GamePanel GAME_PANEL;
    private final Gameplay GAME_PLAY;
    private final MenuPanel MAIN_MENU_PANEL;
    //private Database DATABASE;
    //private HighScores HIGH_SCORES;
    private final ImageIcon ERROR_ICON = new ImageIcon("ErrorIcon.png");
    
    //CONSTRUCTOR---------------------------------------------------------------
    public Controller(MenuPanel mainMenu, GamePanel gamePanel) {
        this.GAME_PANEL = gamePanel;
        this.GAME_PLAY = new Gameplay(gamePanel);
        this.MAIN_MENU_PANEL = mainMenu;
        //TODO instantiate DB and HS here?
        //this.DATABSE = new Databse();
        //this.HIGHSCORES = Database.getHighScores();
    }
    
    //METHODS-------------------------------------------------------------------
    private void startNewRound() {
        GAME_PLAY.setIsSequenceRunning(true);
        GAME_PLAY.getSequence().generateSequence();
        Thread flashSequenceThread = new Thread(GAME_PLAY);
        flashSequenceThread.start();
    }
    
    private void determineOutCome() {
        Audio.playSound("GameOver.wav");
        GAME_PLAY.setIsGameOver(true);
        GAME_PLAY.setIsSequenceRunning(true); //Means a the correct color is flashing (the one user should've pressed)
        Thread flashSequenceThread = new Thread(GAME_PLAY);
        flashSequenceThread.start(); //Flashing the color the user should have pressed instead
        
        //Checking if the player achieved a highScore. If so, recording to database
        if (GAME_PLAY.getRound() > GAME_PLAY.getBest()) { //TODO instead, use: HIGHSCORE.getMax() 
            String highScoreMessage = "High Score | Enter your name for the history books";
            String playerName = JOptionPane.showInputDialog(GAME_PANEL, highScoreMessage, "Simon Game",
                                JOptionPane.INFORMATION_MESSAGE);
            if (playerName == null)
                playerName = "No Name";
            Player player = new Player(playerName, GAME_PLAY.getRound());
            //TODO HIGHSCORES.saveHighScore();
        }
        //Game over, so enabling play button so player can start a new Game
        GAME_PANEL.getPLAY_BUTTON().setIcon(GAME_PANEL.getPLAY_ICON());

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {   
        if(e.getSource() == MAIN_MENU_PANEL.getPLAY_BUTTON()) {
            MAIN_MENU_PANEL.getCARDLAYOUT().show(MAIN_MENU_PANEL, "Game");
            
        } else if(e.getSource() == GAME_PANEL.getMAIN_MENU_BUTTON() && !GAME_PLAY.isSequenceRunning()) {
            MAIN_MENU_PANEL.getCARDLAYOUT().show(MAIN_MENU_PANEL, "Menu");
            
        } else if(e.getSource() == MAIN_MENU_PANEL.getINSTRUCTIONS_BUTTON()) {
            MAIN_MENU_PANEL.getCARDLAYOUT().show(MAIN_MENU_PANEL, "Instructions");
            
        } else if(e.getSource() == MAIN_MENU_PANEL.getINSTRUCTIONS_HOME_BUTTON()) {
            MAIN_MENU_PANEL.getCARDLAYOUT().show(MAIN_MENU_PANEL, "Menu");
            
        } else if(e.getSource() == MAIN_MENU_PANEL.getHIGH_SCORES_HOME_BUTTON()) {
            MAIN_MENU_PANEL.getCARDLAYOUT().show(MAIN_MENU_PANEL, "Menu");
            
        } else if(e.getSource() == MAIN_MENU_PANEL.getHIGH_SCORES_BUTTON()) {
            MAIN_MENU_PANEL.loadHighScoresPanel(null); //TODO instead of null, highScores.getHighScoreArray(int spaceCount);
            MAIN_MENU_PANEL.getCARDLAYOUT().show(MAIN_MENU_PANEL, "HighScores");
            
        } else if(e.getSource() == MAIN_MENU_PANEL.getEXIT_BUTTON()) {
            System.exit(1);
        } 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Thread.yield(); //TODO ?????
        if (e.getSource() == GAME_PANEL.getGREEN_BUTTON() && GAME_PLAY.isGameStarted() && !GAME_PLAY.isSequenceRunning()) {
            GAME_PANEL.getGREEN_BUTTON().setIcon(GAME_PANEL.getLIGHT_GREEN_ICON());
            Audio.playSound("Green.wav");
        
        } else if (e.getSource() == GAME_PANEL.getRED_BUTTON() && GAME_PLAY.isGameStarted() && !GAME_PLAY.isSequenceRunning()) {
            GAME_PANEL.getRED_BUTTON().setIcon(GAME_PANEL.getLIGHT_RED_ICON());
            Audio.playSound("Red.wav");
        
        } else if (e.getSource() == GAME_PANEL.getYELLOW_BUTTON() && GAME_PLAY.isGameStarted() && !GAME_PLAY.isSequenceRunning()) {
            GAME_PANEL.getYELLOW_BUTTON().setIcon(GAME_PANEL.getLIGHT_YELLOW_ICON());
            Audio.playSound("Yellow.wav");
        
        } else if (e.getSource() == GAME_PANEL.getBLUE_BUTTON() && GAME_PLAY.isGameStarted() && !GAME_PLAY.isSequenceRunning()) {
            GAME_PANEL.getBLUE_BUTTON().setIcon(GAME_PANEL.getLIGHT_BLUE_ICON());
            Audio.playSound("Blue.wav");
        
        } else if (e.getSource() == GAME_PANEL.getMAIN_MENU_BUTTON() && !GAME_PLAY.isSequenceRunning()) {
            GAME_PANEL.getMAIN_MENU_BUTTON().setIcon(GAME_PANEL.getHOME_ICON_PRESSED());
        
        } else if (e.getSource() == GAME_PANEL.getPLAY_BUTTON() && !GAME_PLAY.isGameStarted()) {
            GAME_PANEL.getPLAY_BUTTON().setIcon(GAME_PANEL.getPLAY_PRESSED_ICON());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Thread.yield(); //TODO ?????
        if (e.getSource() == GAME_PANEL.getGREEN_BUTTON() && !GAME_PLAY.isSequenceRunning() && GAME_PLAY.isGameStarted()) {
            GAME_PANEL.getGREEN_BUTTON().setIcon(GAME_PANEL.getDARK_GREEN_ICON());
            if(!GAME_PLAY.isEntryCorrect(Color.GREEN))
                determineOutCome();
            else if (GAME_PLAY.isRoundAttemptComplete())
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
            GAME_PLAY.setIsSequenceRunning(true); //TODO fix null pointer exception
            GAME_PLAY.setIsGameStarted(true);
            GAME_PLAY.getSequence().generateSequence();
            Thread flashSequenceThread = new Thread(GAME_PLAY);
            flashSequenceThread.start();
        }

    }
    
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}

}

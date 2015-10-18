
package simongame;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 * 
 * This class is responsible for controlling how the game is run. It is used
 * to determine the game status, such as if the game is over or still running,
 * and whether the player is pressing the correct color buttons.
 * 
 * It is also used to control the game animation, such as flashing the color
 * sequences
 */
public class Gameplay implements Runnable {

    //FIELDS--------------------------------------------------------------------
    private SequenceGenerator SEQUENCE_GENERATOR;
    private final GamePanel GAME_PANEL;
    private boolean isSequenceRunning;
    private boolean isGameStarted;
    private boolean isGameOver;
    private int rounds;
    private int timesColorPressed; //Tallies how many times player has pressed a color button
    
    //CONSTRUCTOR---------------------------------------------------------------
    /**
     * Take a GamePanel object (sub-classed from JPanel) to initialize class
     * GamePanel. Also sets the starting status of the game, and initializes
     * the color Sequence generator
     * 
     * @param gamePanel GamePanel object (sub-classed from JPanel) that displays
     * all JComponents needed for playing a game of Simon
     */
    public Gameplay(GamePanel gamePanel) {
        this.GAME_PANEL = gamePanel;
        this.GAME_PANEL.getROUND_TALLY().setText("Rounds : " + rounds);
        this.SEQUENCE_GENERATOR = new SequenceGenerator();
        //Setting initial Game Status
        this.isSequenceRunning = false;
        this.isGameStarted = false;
        this.isGameOver = false;
        this.rounds = 0;
        this.timesColorPressed = 0;
    }
    
    //GETTERS-------------------------------------------------------------------
    public boolean isSequenceRunning() {
        return isSequenceRunning;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public SequenceGenerator getSequenceGenerator() {
        return SEQUENCE_GENERATOR;
    }

    public int getRounds() {
        return rounds;
    }

    //SETTERS-------------------------------------------------------------------
    public void setIsGameStarted(boolean isGameStarted) {
        this.isGameStarted = isGameStarted;
    }

    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public void setIsSequenceRunning(boolean isSequenceRunning) {
        this.isSequenceRunning = isSequenceRunning;
    }

    //METHODS-------------------------------------------------------------------
    /**
     * Method used after the player has lost a game. Resets the game to 
     * it's initial status, same as when the constructor is called
     */
    public void reset() {
        GAME_PANEL.getROUND_TALLY().setText("Round : " + 0);
        this.SEQUENCE_GENERATOR = new SequenceGenerator();
        this.isSequenceRunning = false;
        this.isGameStarted = false;
        this.isGameOver = false;
        this.rounds = 0;
        this.timesColorPressed = 0;
    }
    
    /**
     * Checks whether the color that the player has just pressed is the correct
     * one, based one the index of the color sequence the computer has already 
     * generated.
     * 
     * @param color a Color enum representing the color the player has just 
     * pressed
     * 
     * @return true if the player has pressed the correct color button
     */
    public boolean isEntryCorrect(Color color) {
        return SEQUENCE_GENERATOR.getCOLOR_SEQUENCE().get(timesColorPressed) == color;
    }
    
    /**
     * Determines whether the player has already pressed all the colors in a sequence
     * based on the timesColorPressed field.
     * E.g. if the computer generates color sequence Yellow, Blue, Green, then that
     * means that the user needs to press 3 colors for the attempt to be complete.
     * 
     * @return true if the player has pressed all the colors contained in the 
     * computer generated color sequence
     */
    public boolean isRoundAttemptComplete() {
        if(SEQUENCE_GENERATOR.getCOLOR_SEQUENCE().size() == timesColorPressed + 1) {
            timesColorPressed = 0; //round complete, so reseting timesColorPressed
            GAME_PANEL.getROUND_TALLY().setText("Rounds : " + (++rounds));
            return true;
        }
        timesColorPressed++; //round not complete, increasing timesColorPressed
        return false;
    }
    
    /**
     * Method used to get the GamePanel to flash a given color.
     * @param color is the color to be flashed on the game panel
     * 
     * @throws InterruptedException 
     */
    private void flash(Color color) throws InterruptedException {
        int flashDelay = 200; //millisecods
        switch (color) {
            case GREEN:
                GAME_PANEL.getGREEN_BUTTON().setIcon(GAME_PANEL.getLIGHT_GREEN_ICON());
                Audio.playSound("sound/Green.wav");
                Thread.sleep(flashDelay);
                GAME_PANEL.getGREEN_BUTTON().setIcon(GAME_PANEL.getDARK_GREEN_ICON());
                Thread.sleep(flashDelay);
                break;

            case RED:
                GAME_PANEL.getRED_BUTTON().setIcon(GAME_PANEL.getLIGHT_RED_ICON());
                Audio.playSound("sound/Red.wav");
                Thread.sleep(flashDelay);
                GAME_PANEL.getRED_BUTTON().setIcon(GAME_PANEL.getDARK_RED_ICON());
                Thread.sleep(flashDelay);
                break;

            case YELLOW:
                GAME_PANEL.getYELLOW_BUTTON().setIcon(GAME_PANEL.getLIGHT_YELLOW_ICON());
                Audio.playSound("sound/Yellow.wav");
                Thread.sleep(flashDelay);
                GAME_PANEL.getYELLOW_BUTTON().setIcon(GAME_PANEL.getDARK_YELLOW_ICON());
                Thread.sleep(flashDelay);
                break;

            case BLUE:
                GAME_PANEL.getBLUE_BUTTON().setIcon(GAME_PANEL.getLIGHT_BLUE_ICON());
                Audio.playSound("sound/Blue.wav");
                Thread.sleep(flashDelay);
                GAME_PANEL.getBLUE_BUTTON().setIcon(GAME_PANEL.getDARK_BLUE_ICON());
                Thread.sleep(flashDelay);
                break;
            
            default:
                throw new IllegalStateException("No color was triggered during flash attempt");
        }
    }
    
    /**
     * Flashes a randomly generated color sequence (based on the 
     * sequenceGenerator) n amount of times. n = the number of rounds in the
     * game
     * 
     * @throws InterruptedException if Threading issue arises
     */
    private void flashColorSequence() throws InterruptedException {
        isSequenceRunning = true;
        if (rounds != 0) 
            Thread.sleep(1000);
        
        for (Color color : SEQUENCE_GENERATOR.getCOLOR_SEQUENCE())
            flash(color);
        
        isSequenceRunning = false;
    }
    
    /**
     * Used to flash colors in the Game Panel by running a separate thread
     * If the game is over the it will just flash the color that the player
     * should've pressed before the game ended. Otherwise it will animate
     * on the Game Panel a sequence of flashes that the player then has to
     * attempt guessing.
     */
    @Override
    public void run() {
        try {
            int numberOfFlashes = 0;
            if(isGameOver) {
                while(numberOfFlashes < 3) {
                    flash(SEQUENCE_GENERATOR.getCOLOR_SEQUENCE().get(timesColorPressed));
                    numberOfFlashes++;
                }
                reset();
            } else
                flashColorSequence();
        } catch (InterruptedException ex) {
            Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showInputDialog("An error has occured, going back to main menu");
            reset();
        }
    }
}
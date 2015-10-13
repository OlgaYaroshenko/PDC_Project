
package simongame;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Vilches 1115994 | Olga Yaroshenko
 */
public class Gameplay implements Runnable {

    //FIELDS--------------------------------------------------------------------
    private Sequence SEQUENCE;
    private final GamePanel GAME_GUI;
    private boolean isSequenceRunning;
    private boolean isGameStarted;
    private boolean isGameOver;
    private int round;
    private int best; //TODO we need to get the best score from database
    private int userColorClicks;
    
    //CONSTRUCTOR---------------------------------------------------------------
    public Gameplay(GamePanel gameGUI) {
        this.GAME_GUI = gameGUI;
        this.SEQUENCE = new Sequence();
        this.isSequenceRunning = false;
        this.isGameStarted = false;
        this.isGameOver = false;
        this.round = 1;
        this.userColorClicks = 1;
    }
    
    //GETTERS-------------------------------------------------------------------
    public boolean isSequenceRunning() {
        return isSequenceRunning;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public Sequence getSequence() {
        return SEQUENCE;
    }

    public int getRound() {
        return round;
    }

    public int getBest() {
        return best;
    }

    public int getUserColorClicks() {
        return userColorClicks;
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

    public void setUserColorClicks(int userColorClicks) {
        this.userColorClicks = userColorClicks;
    }

    //METHODS-------------------------------------------------------------------
    public void reset() {
        this.SEQUENCE = new Sequence();
        this.isSequenceRunning = false;
        this.isGameStarted = false;
        this.isGameOver = false;
        this.round = 1;
        this.userColorClicks = 1;
        GAME_GUI.getROUND_TALLY().setText("Round : " + round);
    }
    
    public boolean isEntryCorrect(Color color) {
        return SEQUENCE.getColorSequence().get(userColorClicks - 1) == color;
    }

    public boolean isRoundAttemptComplete() {
        if(SEQUENCE.getColorSequence().size() == userColorClicks) {
            userColorClicks = 1;
            GAME_GUI.getROUND_TALLY().setText("Round : " + (++round));
            return true;
        }
        userColorClicks++;
        return false;
    }

    public void flash(Color color) throws InterruptedException {
        int flashDelay = 200; //millisecods
        switch (color) {
            case GREEN:
                GAME_GUI.getGREEN_BUTTON().setIcon(GAME_GUI.getLIGHT_GREEN_ICON());
                Audio.playSound("Green.wav");
                Thread.sleep(flashDelay);
                GAME_GUI.getGREEN_BUTTON().setIcon(GAME_GUI.getDARK_GREEN_ICON());
                Thread.sleep(flashDelay);
                break;

            case RED:
                GAME_GUI.getRED_BUTTON().setIcon(GAME_GUI.getLIGHT_RED_ICON());
                Audio.playSound("Red.wav");
                Thread.sleep(flashDelay);
                GAME_GUI.getRED_BUTTON().setIcon(GAME_GUI.getDARK_RED_ICON());
                Thread.sleep(flashDelay);
                break;

            case YELLOW:
                GAME_GUI.getYELLOW_BUTTON().setIcon(GAME_GUI.getLIGHT_YELLOW_ICON());
                Audio.playSound("Yellow.wav");
                Thread.sleep(flashDelay);
                GAME_GUI.getYELLOW_BUTTON().setIcon(GAME_GUI.getDARK_YELLOW_ICON());
                Thread.sleep(flashDelay);
                break;

            case BLUE:
                GAME_GUI.getBLUE_BUTTON().setIcon(GAME_GUI.getLIGHT_BLUE_ICON());
                Audio.playSound("Blue.wav");
                Thread.sleep(flashDelay);
                GAME_GUI.getBLUE_BUTTON().setIcon(GAME_GUI.getDARK_BLUE_ICON());
                Thread.sleep(flashDelay);
                break;
        }
    }

    public void flashColorSequence() throws InterruptedException {
        isSequenceRunning = true;
        if (round != 1) 
            Thread.sleep(1000);
        
        for (Color color : SEQUENCE.getColorSequence())
            flash(color);
        
        isSequenceRunning = false;
    }

    @Override
    public void run() {
        try {
            int numberOfFlashes = 0;
            if(isGameOver) {
                while(numberOfFlashes < 3) {
                    flash(SEQUENCE.getColorSequence().get(userColorClicks-1));
                    numberOfFlashes++;
                }
                reset();
            } else
                flashColorSequence();
        } catch (InterruptedException ex) {
            Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

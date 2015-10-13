
package simongame;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Vilches 1115994 | Olga Yaroshenko
 */
public class Gameplay implements Runnable {

    //FIELDS--------------------------------------------------------------------
    private Sequence SEQUENCE;
    private LinkedList<Color> userSequence;
    private final GamePanel GAME_GUI;
    private boolean isSequenceRunning;
    private boolean isGameStarted;
    private int round;
    
    //CONSTRUCTOR---------------------------------------------------------------
    public Gameplay(GamePanel gameGUI) {
        this.GAME_GUI = gameGUI;
        this.SEQUENCE = new Sequence();
        this.userSequence = new LinkedList<>();
        this.isSequenceRunning = false;
        this.isGameStarted = false;
        this.round = 1;
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
    //SETTERS-------------------------------------------------------------------
    public void setIsGameStarted(boolean isGameStarted) {
        this.isGameStarted = isGameStarted;
    }

    public void setIsSequenceRunning(boolean isSequenceRunning) {
        this.isSequenceRunning = isSequenceRunning;
    }

    //METHODS-------------------------------------------------------------------
    public void reset() {
        this.SEQUENCE = new Sequence();
        this.userSequence = new LinkedList<>();
        this.isSequenceRunning = false;
        this.isGameStarted = false;
        this.round = 1;
        GAME_GUI.getROUND_TALLY().setText("Round : " + round);
    }

    public boolean isUserAnswerCorrect() {
        boolean isCorrect = SEQUENCE.isSequenceCorrect(userSequence);
        userSequence = new LinkedList<>();
        if (isCorrect) {
            round++;
            GAME_GUI.getROUND_TALLY().setText("Round : " + round);
        }

        return isCorrect;
    }

    public boolean isAttemptComplete(Color color) {
        userSequence.addLast(color);
        return userSequence.size() == SEQUENCE.getColorSequence().size();
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
        if (round != 1) {
            Thread.sleep(1000);
        }
        for (Color color : SEQUENCE.getColorSequence()) {
            flash(color);
        }
        isSequenceRunning = false;
    }

    @Override
    public void run() {
        try {
            flashColorSequence();
        } catch (InterruptedException ex) {
            Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

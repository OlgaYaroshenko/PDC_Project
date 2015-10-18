package simongame;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 * 
 * Class which is used to produce a randomly generated sequence of colored
 * numbers. Also has methods to check if player input matches the computer
 * generated sequence
 */
public class SequenceGenerator {

    //FIELDS--------------------------------------------------------------------
    private final Random RANDOM;
    private final LinkedList<Color> COLOR_SEQUENCE;

    //CONSTRUCTOR---------------------------------------------------------------
    public SequenceGenerator() {
        this.RANDOM = new Random();
        this.COLOR_SEQUENCE = new LinkedList<>();
    }

    //METHODS-------------------------------------------------------------------
    /**
     * Each time this method is called, a new randomly generated colored number
     * is added to the end of the sequence List
     */
    public void generateSequence() {
        int colorOrdinal = RANDOM.nextInt(4);
        this.COLOR_SEQUENCE.addLast(Color.values()[colorOrdinal]);
    }
    
    /**
     * 
     * @return a LinkedList object containing a collection of strings which
     * represent the sequence thus far generated. Each string represents a color
     * from 1-4, and each number is colored distinctly.
     */
    public LinkedList<String> getSequenceList() {
        LinkedList<String> sequenceList = new LinkedList<>();
        for (Color color : this.COLOR_SEQUENCE) {
            switch (color) {
                case GREEN:
                    sequenceList.addLast(AnsiTextColors.ANSI_GREEN + color.getColorCode());
                    break;
                case RED:
                    sequenceList.addLast(AnsiTextColors.ANSI_RED + color.getColorCode());
                    break;
                case BLUE:
                    sequenceList.addLast(AnsiTextColors.ANSI_BLUE + color.getColorCode());
                    break;
                case YELLOW:
                    sequenceList.addLast(AnsiTextColors.ANSI_YELLOW + color.getColorCode());
                    break;
            }
        }
        return sequenceList;
    }

    /**
     *
     * @param userInput is the sequence the user types. THIS SEQUENCE MUST BE
     * WITHOUT SPACING. e.g. 124
     *
     * @return true if user sequence matches computer sequence
     */
    public boolean isSequenceCorrect(String userInput) {
        if (userInput.length() > getSequenceList().size()) {
            return false;
        }
        //Comparing each number input by player, against each number in the computer sequence
        for (int i = 0; i < userInput.length(); i++) {
            if (!(userInput.charAt(i) == getSequenceList().get(i).charAt(getSequenceList().get(i).length() - 1))) {
                return false;
            }
        }
        return userInput.length() == getSequenceList().size();
    }

    @Override
    public String toString() {
        String list = "";
        for (int i = 0; i < getSequenceList().size(); i++) {
            list += getSequenceList().get(i);
        }
        return list;
    }
}

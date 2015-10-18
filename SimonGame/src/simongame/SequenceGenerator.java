
package simongame;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 */
public class SequenceGenerator {
    //FIELDS--------------------------------------------------------------------
    private final Random RANDOM;
    private final LinkedList<Color> COLOR_SEQUENCE;
    
    //CONSTRUCTOR---------------------------------------------------------------
    /**
     * Initializes a new random object and an empty List which is used to
     * contain Color enums
     */
    public SequenceGenerator() {
        this.RANDOM = new Random();
        this.COLOR_SEQUENCE = new LinkedList<>();
    }
    
    //GETTERS-------------------------------------------------------------------
    public LinkedList<Color> getCOLOR_SEQUENCE() {
        return COLOR_SEQUENCE;
    }
    
    //METHODS-------------------------------------------------------------------
    /**
     * Used to generate a sequence of Color enums. Every time this method
     * is called a randomly generated color enum will be added to the end of
     * the ColorSequence list field of this object
     */
    public void generateSequence() {
        int colorOrdinal = RANDOM.nextInt(4);
        this.COLOR_SEQUENCE.addLast(Color.values()[colorOrdinal]);
    }

    @Override
    public String toString() {
        return COLOR_SEQUENCE.toString();
    }
}

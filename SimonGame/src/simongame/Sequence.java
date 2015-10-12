
package simongame;

import java.util.LinkedList;
import java.util.Random;

public class Sequence {
    //FIELDS--------------------------------------------------------------------
    private final Random random;
    private final LinkedList<Color> colorSequence;
    
    //CONSTRUCTOR---------------------------------------------------------------
    public Sequence() {
        this.random = new Random();
        this.colorSequence = new LinkedList<>();
    }
    
    //GETTERS-------------------------------------------------------------------
    public LinkedList<Color> getColorSequence() {
        return colorSequence;
    }
    
    //METHODS-------------------------------------------------------------------
    public void generateSequence() {
        int colorOrdinal = random.nextInt(4);
        this.colorSequence.addLast(Color.values()[colorOrdinal]);
    }
    
    public boolean isSequenceCorrect(LinkedList<Color> userSequence) {
        for(int i = 0; i < userSequence.size(); i++) {
            if(!(userSequence.get(i) == colorSequence.get(i)))
                return false;
        }       
        return true;
    }

    @Override
    public String toString() {
        return colorSequence.toString();
    }
}

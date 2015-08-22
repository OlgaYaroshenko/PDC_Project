
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
    
    //METHODS-------------------------------------------------------------------
    public void generateSequence() {
        int colorOrdinal = random.nextInt(4);
        this.colorSequence.addLast(Color.values()[colorOrdinal]);
    }
    
    public LinkedList<String> getSequenceList(){
        LinkedList<String> sequenceList = new LinkedList<>();
        for(Color color : this.colorSequence) {
            switch (color) {
                case GREEN:
                    sequenceList.addLast("\033[32m" + color.getColorCode());
                    break;
                case RED:
                    sequenceList.addLast("\033[31m" + color.getColorCode());
                    break;
                case BLUE:
                    sequenceList.addLast("\033[34m" + color.getColorCode());
                    break;
                case YELLOW:
                    sequenceList.addLast("\033[33m" + color.getColorCode());
                    break;
            }
        }
        return sequenceList;
    }
    
    //FOR TESTING PURPOSES ONLY-------------------------------------------------
    public static void main(String[] args) {
        Sequence sequence = new Sequence();
        
        //Playing round 1
        System.out.println("Round 1");
        sequence.generateSequence();
        System.out.println(sequence.getSequenceList());
        
        System.out.println("Round 2");
        sequence.generateSequence();
        System.out.println(sequence.getSequenceList());
        
        System.out.println("Round 3");
        sequence.generateSequence();
        System.out.println(sequence.getSequenceList());
    }
    
}

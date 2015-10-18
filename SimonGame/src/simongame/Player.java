package simongame;

/**
 * Object that represents a Player, consisting of a name and a score.
 * 
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 */
public class Player implements Comparable<Player> {
    //FIELDS--------------------------------------------------------------------
    private final String NAME;
    private final int SCORE;
    
    //CONSTRUCTOR---------------------------------------------------------------
    public Player(String name, int score) {
        this.NAME = name;
        this.SCORE = score;
    }
    //GETTERS-------------------------------------------------------------------
    public String name() {
        return NAME;
    }

    public int score() {
        return SCORE;
    }
    
    //METHODS-------------------------------------------------------------------
    @Override
    public int compareTo(Player p) {
        if (SCORE > p.SCORE) {
            return -1;
        }
        if (SCORE < p.SCORE) {
            return 1;
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Player) {
            Player ps = (Player) o;
            if (ps.SCORE == SCORE) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + NAME.hashCode();
        hash = 23 * hash + SCORE;
        return hash;
    }

    @Override
    public String toString() {
        return NAME + "\n" + SCORE;
    }
}

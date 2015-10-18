package simongame;

import java.sql.SQLException;
import java.util.*;

/**
 * This class is used for storing, modifying and retrieving a sorted collection 
 * of players. Players are sorted based on their scores.
 * 
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 */
public class Highscores {
    //FIELDS--------------------------------------------------------------------
    private static SortedSet<Player> scoreboard;
    
    //CONSTRUCTOR--it should be private-----------------------------------------
    private Highscores() {}
    
    //METHODS-------------------------------------------------------------------
    /**
     * @return returns a HighScores object with an empty scoreboard tree set
     */
    public static Highscores makeEmpty() {
        Highscores h = new Highscores();
        h.scoreboard = new TreeSet<>();
        return h;
    }
    
    // Creates Highscores object from the database
    public static Highscores fromDB(DBconnection db) throws SQLException{
        Highscores h = db.getHighscoresFromDB();

        return h;  
    }
   
    /**
     * Adds Player to the TreeSet
     */
    public static void addHighscore(Player p) {
        scoreboard.add(p);
        if (scoreboard.size() == 11) {
            scoreboard.remove(scoreboard.last());
        }
    }
    
    /**
     * @param spaceCount is the number of space to use between player name and score (e.g. Player1     10)
     * @return a String array, with each element representing a string of player and score (e.g. Player1 10)
     */
    public String[] getHighScoreArray(int spaceCount) {
        String[] output = new String[scoreboard.size()];
        int count = 0;
        for (Player p : scoreboard) {
            int y = p.name().length();
            StringBuilder sb = new StringBuilder();
            sb.append(p.name());
            for (int i = 0; i < spaceCount - y; i++) {
                sb.append(" ");
            }
            sb.append(p.score());
            output[count] = sb.toString();
            count++;
        }
        return output;
    }
    
    /**
     * For debugging purposes
     * 
     * @param h highScores object
     * @param i number of spaces between player name and score
     */
    public static void printHighscores(Highscores h, int i){
        String[] t = h.getHighScoreArray(i);
        for (String s : t) {
            System.out.println(s);
        }
    }
    
    /**
     * @return an int representing the highest of all top 10 scores
     */
    public int getHighestScore(){
        if(scoreboard.isEmpty()) {
            return 0;
        }
        return scoreboard.first().score();
    }
    
    // Adds Player to TreeSet and database
    public void saveHighScore(DBconnection db, Player player) {
        addHighscore(player);
        db.updateDB(player);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator it = scoreboard.iterator();
        int x = 0;
        //Appending no more than 10 players with the 10 highest scores.
        while(it.hasNext()) {
            if(x == 11)
                break;
            sb.append(it.next().toString());
            sb.append("/n");
            x++;
        }
        return sb.toString();
    }
}
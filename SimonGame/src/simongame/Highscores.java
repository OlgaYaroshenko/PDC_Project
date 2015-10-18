package simongame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 * 
 * Class used for storing a sorted collection of players and providing methods
 * to modify and query this collection
 * 
 * Uses a default constructor
 */
public class Highscores {
    //FIELDS--------------------------------------------------------------------
    private static SortedSet<Player> scoreboard;
    
    //METHODS-------------------------------------------------------------------
    /**
     * @return returns an empty scoreboard tree set
     */
    public static Highscores makeEmpty() {
        Highscores h = new Highscores();
        h.scoreboard = new TreeSet<>();
        return h;
    }

    /**
     *
     * @param s is a string representation of highScores (player name and score)
     * @return a Highscores object representing the top 10 highScores
     */
    public static Highscores fromString(String s) {
        if (s == null) {
            throw new IllegalArgumentException("s cannot be null");
        }
        if (s.length() == 0) {
            throw new IllegalArgumentException("s cannot be empty");
        }
        
        Highscores h = Highscores.makeEmpty();
        String[] split = s.split("\n");
        
        if (split.length % 2 != 0) {
            throw new IllegalArgumentException("s should have an even number of lines.");
        }
        if (split.length > 20) {
            throw new IllegalArgumentException("s has too many lines.");
        }
        
        String buff = "";
        for (int i = 0; i < split.length; i++) {
            if (i % 2 == 0) {
                buff = split[i] + "\n";
            } else {
                h.scoreboard.add(Player.fromStringFile(buff + split[i].trim()));
            }
        }
        return h;
    }
    
    /**
     *
     * @return an array of player objects
     */
    public Player[] scores() {
        Player[] ps = new Player[scoreboard.size()];
        int i = 0;
        for (Player p : scoreboard) {
            ps[i] = p;
            i++;
        }
        return ps;
    }
        
    /**
     *
     * @param p is a player object
     */
    public void addHighscore(Player p) {
        scoreboard.add(p);
        if (scoreboard.size() == 11) {
            scoreboard.remove(scoreboard.last());
        }
    }
    
    /**
     * @param spaceCount is the number of space to use between player name and score (e.g. Player1     10)
     * @return a String array, with each element representing a string of player and score (e.g. Player1 10)
     */
    public static String[] getHighScoreArray(int spaceCount) {
        String[] output = new String[scoreboard.size()];
        int count = 0;
        for (Player p : scoreboard) {
            StringBuilder sb = new StringBuilder();
            sb.append(p.name());
            for (int i = 0; i < spaceCount; i++) {
                sb.append(" ");
            }
            sb.append(p.score());
            output[count] = sb.toString();
            count++;
        }
        return output;
    }
    
    /**
     * @return an int representing the lowest of all top 10 scores
     */
    public static int getLowestScore() {
        File file = new File("highscores.txt");
        String text = "";
        //Scanning highScores .txt file and retrieving the last line which represents the lowest score, passing value to 'text' field
        try {
            Scanner scan = new Scanner(file);
            int x = 0;
            while (true) {
                if(scan.hasNextLine() == false && x==0)
                    return 0;
                
                if(scan.hasNextLine() == false)
                    break;
                
                text = scan.nextLine();
                x++;
            }
        } catch (FileNotFoundException e) {}
        //returning 0 if there are no high scores available yet
        if(text.length()<1)
            return 0;
        //Converting string representation of lowest score into integer    
        return Integer.parseInt(text);
        
    }
    
    /**
     * This method appends a player name and score to the .txt high scores file
     * if this player's score is higher than the current lowest score
     * 
     * @param player is the player object to be saved to the .txt highScores file
     */
    public static void saveHighScore(Player player) {
        Highscores h = null;
        File file = new File("highscores.txt");
        try {
            Scanner scan = new Scanner(file);
            String text = "";
            while (scan.hasNextLine()) {
                text += scan.nextLine() + "\n";
            }
            h = h.fromString(text.trim());

        } catch (FileNotFoundException e) {
        }

        if (h == null) {
            h = Highscores.makeEmpty();
        }
        
        h.addHighscore(player);

        try {
            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
            } catch (IOException ex) {
                Logger.getLogger(SimonGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(h.toString());
            }
        } catch (IOException ex) {
        }
    }
    
    /**
     * @return a string array representing an ordered list of player names along
     * with their scores
     */
    public static String[] getHighScoreList() {
        //Setting up high scores object and graphic settings
        Highscores h = new Highscores();
        
        //getting high scores from .txt file
        File file = new File("highscores.txt");
        try {
            Scanner scan = new Scanner(file);
            String text = "";
            while (scan.hasNextLine()) {
                text += scan.nextLine() + "\n";
            }
            h = Highscores.fromString(text.trim());

        } catch (FileNotFoundException e) {
            System.err.println("No High Scores Exist Yet!");
            return new String[] {""};
        }
        
        return h.getHighScoreArray(1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator it = scoreboard.iterator();
        int x = 0;
        //Appending no more than 10 players with the 10 highest scores.
        while(it.hasNext()) {
            if(x == 10)
                break;
            sb.append(it.next().toString());
            sb.append("\n");
            x++;
        }
        return sb.toString();
    }
    
    
}

package simongame;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.drda.NetworkServerControl;

/**
 *
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 */
public class DBconnection {
    //FIELDS--------------------------------------------------------------------
    public String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private Connection conn = null;
    private final String url = "jdbc:derby://localhost:1527/SimonGameDB;create=true";
    private final String username = "pdc";
    private final String password = "pdc";
    private Statement statement;
    private final String tableName = "PLAYER";
    private ResultSet rs;
    private Player p;
    private String name;
    private int score;
    
    //METHODS-------------------------------------------------------------------
    /**
     * Establishes a connection with the database
     */
    public void autoConnectDB() {
        //Starting server
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    NetworkServerControl serverControl = new NetworkServerControl("simon", "simon");
                    serverControl.start(null);
                } catch (Exception ex) {
                    Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        serverThread.start();
        
        //Connecting to Database
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement();

            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (NAME VARCHAR(50), SCORE INT)");
            }

            statement.close();
        } catch (Throwable ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
    }
    
    /**
     * Retrieved information from the PLAYER table of the database. Based on
     * the name and score of the players, creates and returns a HighScores object
     * 
     * @return a HighScores object which contains players which are sorted
     * in descending order based on their score.
     * @throws SQLException 
     */
    public Highscores getHighscoresFromDB() throws SQLException {
        ArrayList<Player> array = new ArrayList<>();
        statement = conn.createStatement();
        rs = statement.executeQuery("SELECT * FROM " + tableName);
        while (rs.next()) {
            array.add(new Player(rs.getString(1), rs.getInt(2)));
        }
        Highscores h = Highscores.makeEmpty();
        if (!array.isEmpty()) {
            sort(array);
            //Getting at most 10 scores. Top 10
            for (int i = 0; i < array.size() && i < 10; i++) {
                h.addHighscore(array.get(i));
            }
            statement.close();
        }
        return h;
    }
    
    /**
     * Stores player name and score information in the PLAYER table
     * @param p is a player object which contains a player name and score
     */
    public void updateDB(Player p) {
        try {
            statement = conn.createStatement();
            name = p.name();
            score = p.score();
            statement.executeUpdate("INSERT INTO " + tableName + " VALUES ('" + name + "', " + score + ")");
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Checks if the specified table name already exists in the database
     * @param newTableName String representing the name of a table within the
     * game database
     * @return true if the specified table already exists
     */
    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {
            System.out.println("Checking existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);
            //Statement dropStatement=null;
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + " already exists.");
                    flag = true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
        }
        return flag;
    }
    
    /**
     * Compares a player based on the an integer that represents the player's
     * score. The highest ranked players have the highest scores
     */
    public class PlayerComparator implements Comparator<Player> {
        @Override
        public int compare(Player a, Player b) {
            return a.score() > b.score() ? -1 : a.score() == b.score() ? 0 : 1;
        }
    }

    private void sort(ArrayList<Player> array) {
        PlayerComparator comparator = new PlayerComparator();
        Collections.sort(array, comparator);
    }
}

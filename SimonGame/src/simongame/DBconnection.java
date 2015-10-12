
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

/**
 *
 * @author olga
 */
public class DBconnection {

    public String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    Connection conn = null;
    //private final String url = "jdbc:derby://localhost:1527/SimonGameDB;create=true";
    private final String url = "jdbc:derby:SimonGameDB;create=true"; 
    String username = "pdc";
    String password = "pdc";
    Statement statement;
    String tableName = "SimonGameDB";
    ResultSet rs;
    Player p;
    String name;
    int score;

    public void autoConnectDB() {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement(); 

            if(!checkTableExisting(tableName))
                statement.executeUpdate("CREATE TABLE " + tableName + " (NAME VARCHAR(50), SCORE INT)");
            statement.close();
        } catch (Throwable ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
    }
    
    public Highscores getHighscoresFromDB() throws SQLException{
        ArrayList<Player> array = new ArrayList<>();
        statement = conn.createStatement();
        rs = statement.executeQuery("SELECT * FROM " + tableName);
      
        while(rs.next()){
            array.add(new Player(rs.getString(1), rs.getInt(2)));
            
        }
        sort(array);
        Highscores h = Highscores.makeEmpty();
        for (int i = 0; i < 10; i++) {
            h.addHighscore(array.get(i));
        }
        statement.close();
        return h; 
    }

        
    public void updateDB(Player p){
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
     
    private boolean checkTableExisting(String newTableName) {
        boolean flag=false;
        try {
            
            System.out.println("check existing tables.... ");
            String[] types={"TABLE"};
            DatabaseMetaData dbmd=conn.getMetaData();
            ResultSet rsDBMeta=dbmd.getTables(null, null, null, null);
            //Statement dropStatement=null;
            while(rsDBMeta.next())
            {
                    String tableName = rsDBMeta.getString("TABLE_NAME");
                    if(tableName.compareToIgnoreCase(newTableName)==0)
                    {
                        System.out.println(tableName + " already exists.");
                        flag=true;
                    }
                }
                if(rsDBMeta != null)
                    rsDBMeta.close();
            }catch (SQLException ex) {
            }
        return flag; 
    }
    
    
    public static void main(String arg[]) throws SQLException{
        
        DBconnection db = new DBconnection();
        db.autoConnectDB();
        //db.updateDB("Frank", 133);
        //db.updateDB("Saif", 123);
        db.getHighscoresFromDB();

    }
    
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


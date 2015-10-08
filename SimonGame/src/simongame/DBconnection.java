/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simongame;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author olga
 */
public class DBconnection {

    Connection conn = null;
    private final String url = "jdbc:derby://localhost:1527/SimonGameDB;create=true";
    //private final String url="jdbc:derby:SimonGameDB;create=true"; 
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
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement(); 

            if(!checkTableExisting(tableName))
                statement.executeUpdate("CREATE TABLE " + tableName + " ('NAME VARCHAR(50)', SCORE INT)");
            statement.close();
        } catch (Throwable ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
    }
    
    public Highscores getHighscores() throws SQLException{
        Highscores h = Highscores.makeEmpty();
               
        statement = conn.createStatement();
        rs = statement.executeQuery("SELECT * FROM " + tableName);
      
        while(rs.next()){
            h.addHighscore(new Player(rs.getString(1), rs.getInt(2)));
        }
        statement.close();
        System.out.println(h);
        return h;
        
    }

    public void updateDB(String name, int score){
        try {
            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO " + tableName + " VALUES ('name', " + score + ")");
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
        db.updateDB("Olga", 12);
        db.updateDB("Bob", 13);
        db.getHighscores();
    }
}


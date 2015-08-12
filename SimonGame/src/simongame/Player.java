package simongame;

import java.util.Scanner;

/**
 *
 * @author olga
 */
public class Player implements Comparable<Player> {

    private String name;
    private int score;

    public Player() {
        name = "";
        score = 0;
    }

//    public static void main(String[] args) {
//        Player player = new Player();
//        player.setNameFromUser();
//        System.out.println(player.toString());      
//    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    public void setNameFromUser() {
        Scanner sc = new Scanner(System.in);
        String n = "";
        System.out.println("Please enter your name: ");
        while (n.equals("")) {
            n = sc.nextLine();
            if (!n.isEmpty()) {
                setName(n);
                break;
            } else {
                System.out.println("Name can not be blank. Try again: ");
            }
        }
        sc.close();

    }
    
    @Override
    public String toString() {
        return "Player " + getName() + ", score " + getScore();
    }

    @Override
    public int compareTo(Player p) {
        if (score < p.score) {
            return -1;
        }
        if (score > p.score) {
            return 1;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + name.hashCode();
        hash = 23 * hash + score;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Player) {
            Player p = (Player) o;
            if (name.equals(p.name)) {
                return true;
            }
        }
        return false;
    }
}

package simongame;

import java.util.Scanner;

/**
 *
 * @author olga
 */
public class Player {

    private String name;
    private int score;

    public Player() {
        name = "";
        score = 0;
    }

    public static void main(String[] args) {
        Player player = new Player();
        player.setNameFromUser();
        System.out.println(player.toString());
    }

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
    
    public String toString() {
        return "Player " + getName() + ", score " + getScore();
    }
}

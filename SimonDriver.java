/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bhargav Patel
 * 
 */
import java.awt.*;
import java.applet.Applet;
import javax.swing.*;
import java.util.*;

public class SimonDriver {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> simon = new ArrayList<>();
        ArrayList<String> user = new ArrayList<>();
        String[] simonColors = {"red", "blue", "yellow", "green"};
        String color = "";
        String tmpColor = "";
        String temp = "";
        String input = "";
        int score = 0;
        Iterator i = simon.iterator();
        Iterator i2 = user.iterator();

        color = randomColors(simonColors);
        System.out.println("System: ");
        simon.add(color);
        while (i.hasNext()) {
            temp = i.next().toString();
            System.out.print(temp);
            Thread.sleep(1000);
            printBackspaces(temp);
        }

        input = JOptionPane.showInputDialog("Guess Answer!");
        user.add(input);
        if (simon.equals(user)) {
            score++;
            empty(user);

            simonPlay(simon, user, score, simonColors, color, temp, input);

        } else {
            System.out.println("Game Over! Your Score is: " + score);
            System.out.println("The original sequence was: " + simon);
            System.out.println("You entered: " + user);
        }
    }

    /**
     * Gives random colors from the array of colors.
     * @param simonColors array if colors
     * @return the String with value as color.
     */
    public static String randomColors(String[] simonColors) {
        int rnd = new Random().nextInt(simonColors.length);
        return simonColors[rnd];
    }

    /**
     * Makes the word from the console disappear
     * @param temp the word which needs to be disappeared.
     */
    public static void printBackspaces(String temp) {
        for (int i = 0; i < temp.length(); i++) {
            System.out.print("\b");
        }
    }

    /**
     * This method makes the list empty. 
     * @param user the list which needs to be emptied.
     */
    public static void empty(ArrayList<String> user) {
        Iterator i = user.iterator();
        for (int j = 0; j < user.size(); j++) {
            while (i.hasNext()) {
                user.remove(j);
            }
        }
    }

    /**
     * Stimulates the game
     * @param simon the list of colors for the system
     * @param user the list in which the colors entered by the player is added.
     * @param score integer to keep track of score
     * @param simonColors array of colors
     * @param color the String in which randomly generated color is stored.
     * @param temp String in which the iterated element of list is stored.
     * @param input String Array in which the input by player is stored.
     */
    public static void simonPlay(ArrayList<String> simon, ArrayList<String> user, int score, String[] simonColors, String color, String temp, String input) throws InterruptedException {
        Iterator i = simon.iterator();
        int j = 1;
        for (int k = 0; k < j; k++) {
            int size = simon.size();
            color = randomColors(simonColors);
            simon.add(size, color);
            i = simon.iterator();
            System.out.println("System: ");
            while (i.hasNext()) {
                temp = i.next().toString();
                System.out.print(temp);
                Thread.sleep(1000);
                printBackspaces(temp);
                String s = "\n";
                System.out.print(s+"\b");
            }

            input = JOptionPane.showInputDialog("Guess Answer!");
            String[] tmp = input.split(" ");

            for (int z = tmp.length - 1; z >= 0; z--) {
                user.add(tmp[z]);
            }

            if (simon.equals(user)) {
                score++;
                empty(user);
                j++;
            } else {
                System.out.println("Game Over! Your score is: " + score);
                System.out.println("The original sequence was: " + simon);
                System.out.println("You entered: " + user);
                System.exit(0);
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

/**
 *
 * @author matt
 */
public class Player {

    String color;

    Player() {
        this.color = new String();
    }//end constructor

    public Player playerColor(String str) {
        char[] args = str.toCharArray();
        while (args[0] == 'I') {
            if (args[1] == 'B') {
                this.color = "Black";
            } else if (args[1] == 'W') {
                this.color = "White";
            }
            return null;
        }//end while loop
        return null;
    }//end playerColor

    public String getPlayerColor() {
        return this.color;
    }

}

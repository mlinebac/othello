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

    int playerColor;
    int optColor;

    public Player(String str) {
        String[] strArray = str.split(" ");
        // this.color = strArray[1];
        if (strArray[1].equals("B")) {
            this.playerColor = 1;
            this.optColor = -1;
        } else {
            this.playerColor = -1;
            this.optColor = 1;
        }
    }//end playerColor

    public int getPlayerColor() {
        return playerColor;
    }

    public int getOpponentColor() {
        
        return optColor;
    }

}

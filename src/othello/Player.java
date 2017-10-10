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
    int opponentColor;

    public Player(String str) {
        String[] strArray = str.split(" ");
        // this.color = strArray[1];
        if (strArray[1].equals("B")) {
            this.playerColor = 1; //Black
            this.opponentColor = -1;//White
        } else {
            this.playerColor = -1;//Black
            this.opponentColor = 1;//White
        }
    }//end playerColor

    public int getPlayerColor() {
        return playerColor;
    }

    public int getOpponentColor() {
        return opponentColor;
    }

    @Override
    public String toString() {
        if (playerColor == 1) {
            return "R B";
        } else {
            return "R W";
        }
    }

}

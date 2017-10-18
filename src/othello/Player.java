/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

/**
 *
 * @author Matt Lineback
 */
public class Player {
    
    char playerColor;
    char opponentColor;

    public Player(String str) {
        String[] strArray = str.split(" ");
        if (strArray[1].equals("B")) {
            this.playerColor = 'B'; //Black
            this.opponentColor = 'W';//White
        } else {
            this.playerColor = 'W';//Black
            this.opponentColor = 'B';//White
        }
    }//end playerColor

    public char getPlayerColor() {
        return playerColor;
    }

    public char getOpponentColor() {
        return opponentColor;
    }

    @Override
    public String toString() {
        if (playerColor == 'B') {
            return "R B";
        } else {
            return "R W";
        }
    }

}

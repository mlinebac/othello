/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import othello.Board.Cell;

/**
 *
 * @author matt
 */
public class Move {

    public int x;
    public int y;
    public char playerColor;
    Cell playerColorCell;
    Cell opponentColorCell;

    public Move(char color) {

        this.playerColor = color;

    }

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Move(char color, int x, int y) {
        this.x = x;
        this.y = y;
        this.playerColor = color;
        if (color == 'B') {
            this.playerColorCell = Cell.BLACK;
            this.opponentColorCell = Cell.WHITE;
        } else {
            this.playerColorCell = Cell.WHITE;
            this.opponentColorCell = Cell.BLACK;
        }
    }

    public Move(String str) {
        char[] charArray = str.toCharArray();
        if (charArray.length <= 1) {
            if (charArray[0] == 'B') {
                this.playerColor = 'B';
                this.playerColorCell = Cell.BLACK;
                this.opponentColorCell = Cell.WHITE;
            } else {
                this.playerColor = 'W';
                this.playerColorCell = Cell.WHITE;
                this.opponentColorCell = Cell.BLACK;
            }
        }else{
            if (charArray[0] == 'B') {
                this.playerColor = 'B';
                this.playerColorCell = Cell.BLACK;
                this.opponentColorCell = Cell.WHITE;
            } else {
                this.playerColor = 'W';
                this.playerColorCell = Cell.WHITE;
                this.opponentColorCell = Cell.BLACK;
            }
            this.x = Character.getNumericValue(charArray[2]) - 9;
            this.y = Character.getNumericValue(charArray[4]);
       }
    }//end Move constructor

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getMyColor() {
        return playerColor;
    }
    public Cell getMoveColor(){
        Cell moveColor = playerColorCell;
        return moveColor;
    }
    public Cell getPlayerColorCell() {
        return playerColorCell;
    }
    public Cell getOpponentColorCell(){
        return opponentColorCell;
    }

    @Override
    public String toString() {
        char boardChar = ' ';
        switch (x) {
            case 1:
                boardChar = 'a';
                break;
            case 2:
                boardChar = 'b';
                break;
            case 3:
                boardChar = 'c';
                break;
            case 4:
                boardChar = 'd';
                break;
            case 5:
                boardChar = 'e';
                break;
            case 6:
                boardChar = 'f';
                break;
            case 7:
                boardChar = 'g';
                break;
            case 8:
                boardChar = 'h';
                break;

        }
        return (playerColor + " " + boardChar + " " + y);
    }

}

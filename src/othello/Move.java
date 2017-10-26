/*
 * 
 */
package othello;

import othello.Board.Cell;

/**
 *
 * @author Matt Lineback
 */
public class Move {

    public int x;
    public int y;
    public char playerColor;
    Cell playerColorCell;
    Cell opponentColorCell;
    int size;
    double value;
    double [][] values;

    public Move() {

    }

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

        if (charArray.length == 1) {
            if (charArray[0] == 'B') {
                this.playerColor = 'B';
                this.playerColorCell = Cell.BLACK;
                this.opponentColorCell = Cell.WHITE;
            } else {
                this.playerColor = 'W';
                this.playerColorCell = Cell.WHITE;
                this.opponentColorCell = Cell.BLACK;
            }
            this.x = 0;
            this.y = 0;
            this.size = charArray.length;
        } else {
            if (charArray[0] == 'B') {
                this.playerColor = 'B';
                this.playerColorCell = Cell.BLACK;
                this.opponentColorCell = Cell.WHITE;
            } else {
                this.playerColor = 'W';
                this.playerColorCell = Cell.WHITE;
                this.opponentColorCell = Cell.BLACK;

            }
            this.size = charArray.length;
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

    public int getSize() {
        return size;
    }

    public char getMyColor() {
        return playerColor;
    }

    public Cell getMoveColor() {
        Cell moveColor = playerColorCell;
        return moveColor;
    }

    public Cell getPlayerColorCell() {
        return playerColorCell;
    }

    public Cell getOpponentColorCell() {
        return opponentColorCell;
    }
    
    public void setValue(){
        //values = new double [10][10];
        values = new double [][]{
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 10, -5, -1, -1, -1, -1, -5, 10, -1},
            {-1, -5, 1, -1, -1, -1, -1, -1, -5, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -5, -1, -1, -1, -1, -1, -1, -5, -1},
            {-1, 10, -5, -1, -1, -1, -1, -5, 10, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        };
        values[1][1] = 10;
        values[1][8] = 10;
        values[8][1] = 10;
        values[8][8] = 10;
        values[1][2] = -5;
        values[1][7] = -5;
        values[2][1] = -5;
        values[2][2] = -5;
        values[2][7] = -5;
        values[2][8] = -5;
        values[7][1] = -5;
        values[7][2] = -5;
        values[7][7] = -5;
        values[7][8] = -5;
        values[8][2] = -5;
        values[8][7] = -5;
        values[3][1] = 1;
        values[4][1] = 1;
        values[5][1] = 1;
        values[6][1] = 1;
        values[1][3] = 1;
        values[1][4] = 1;
        values[1][5] = 1;
        values[1][6] = 1;
        values[3][8] = 1;
        values[4][8] = 1;
        values[5][8] = 1;
        values[6][8] = 1;
        values[8][3] = 1;
        values[8][4] = 1;
        values[8][5] = 1;
        values[8][6] = 1;
    }
    
    public double getValue(){
        x = this.getX();
        y = this.getY();
        value = values[y][x];
        return value;
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

    @Override
    public boolean equals(Object otherParam) {
        Move other = (Move) otherParam;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.x;
        hash = 41 * hash + this.y;
        return hash;
    }

}

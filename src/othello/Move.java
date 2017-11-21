/*
 * 
 */
package othello;

import othello.Board.Cell;

/**
 *
 * @author Matt Lineback
 */
public final class Move {

    public int x;
    public int y;
    public char playerColor;
    Cell playerColorCell;
    Cell opponentColorCell;
    int size;
    double value;
    double[][] values;
    String passMove;

    public Move() {

    }

    public Move(char color) {
        setMoveValues();
        this.playerColor = color;

    }

    public Move(int x, int y) {
        setMoveValues();
        this.x = x;
        this.y = y;
    }

    public Move(char color, int x, int y) {
        setMoveValues();
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
        setMoveValues();

        char[] charArray = str.toCharArray();

        if (charArray.length == 1) {
            if (charArray[0] == 'B') {
                this.playerColor = 'B';
                //this.playerColorCell = Cell.BLACK;
                //this.opponentColorCell = Cell.WHITE;
            } else {
                this.playerColor = 'W';
                //this.playerColorCell = Cell.WHITE;
                //this.opponentColorCell = Cell.BLACK;
            }
            this.size = charArray.length;
            this.passMove = str;
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

    public char getPlayerColor() {
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

    public void setMoveValues() {
        values = new double[][]{
            {-1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0},
            {-1.0, 15.0, -3.0, 1.0, 0.5, 0.5, 1.0, -3.0, 15.0, -1.0},
            {-1.0, -4.0, -2.0, -1.0, -0.5, -0.5, -1.0, -2.0, -4.0, -1.0},
            {-1.0, 1.5, -1.0, 0.5, 0.0, 0.0, 0.5, -1.0, 1.5, -1.0},
            {-1.0, 0.5, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 0.5, -1.0},
            {-1.0, 0.5, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 0.5, -1.0},
            {-1.0, 1.5, -1.0, 0.5, 0.0, 0.0, 0.5, -1.0, 1.5, -1.0},
            {-1.0, -4.0, -2.0, -1.0, -0.5, -0.5, -1.0, -2.0, -4.0, -1.0},
            {-1.0, 15.0, -3.0, 1.0, 0.5, 0.5, 1.0, -3.0, 15.0, -1.0},
            {-1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0},};
    }

    public double getMoveValue() {
        x = this.getX();
        y = this.getY();
        if(x == 0 && y == 0){
            return value = 0.0;
        }
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
        if (size == 1) {
            return playerColor + "";
        } else {
            return (playerColor + " " + boardChar + " " + y);
        }
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

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
public class Move {

    int x;
    int y;
    int color;
    int optcolor;
    Move move;
    char colorChar;
    String charString;

    public Move(int color) {
        if (color == 1) {
            this.move = new Move("B");
        } else {
            this.move = new Move("W");
        }
    }

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Move(int color, int x, int y) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.optcolor = color * -1;

    }

    /*
    public Move(Move m) {
        this.x = m.x;
        this.y = m.y;
    }
     */
    public Move(String str) {
        char[] charArray = str.toCharArray();
        if (charArray.length <= 1) {
            if (charArray[0] == 'B') {
                this.color = 1;
                this.optcolor = -1;
            } else {
                this.color = -1;
                this.optcolor = 1;
            }
        } else if (charArray.length > 1) {
            if (charArray[0] == 'B') {
                this.color = 1;
                this.optcolor = -1;

            } else {
                this.color = -1;
                this.optcolor = 1;
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

    public int getMyColor() {
        return color;
    }

    public int getOpponentColor() {
        return color * -1;
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

        if (color == 1) {
            colorChar = 'B';
        } else {
            colorChar = 'W';
        }
        return (colorChar + " " + boardChar + " " + y);
    }

}

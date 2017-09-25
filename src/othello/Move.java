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

    int col;
    int row;
    char playerColor;

    public Move() {
        //return passMove();
    }
    public Move(int x, int y){
        this.col = x;
        this.row = y;
    }
    public Move(String str) {
        char[] args = str.toCharArray();
        while (args.length == 3) {
            this.playerColor = args[0];//set player color
            this.col = (int) args[1];//set column
            this.row = (int) args[2];//set row
        }//end while
        if (args.length < 3) {
            System.out.println("enter argument color, col, row");
        }
    }//end Move constructor

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }
}

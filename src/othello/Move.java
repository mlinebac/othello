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
    char playerColor;

    public Move() {
        //return passMove();
    }
    public Move(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Move(String str) {
        char[] args = str.toCharArray();
        while (args.length == 3) {
            this.playerColor = args[0];//set player color
            this.x = (int) args[1];//set column
            this.y = (int) args[2];//set row
        }//end while
        if (args.length < 3) {
            System.out.println("enter argument color, x, y");
        }
    }//end Move constructor

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

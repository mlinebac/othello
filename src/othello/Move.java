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
    
    
    public Move() {
        //return passMove();
    }

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Move(Move m) {
        this.x = m.x;
        this.y = m.y;
    }

    public Move(String str) {
        char[] charArray = str.toCharArray();
        if (charArray[0] == 'B'){
            this.color = -1;
        }else{
            this.color = 1;
        }
        
        this.x = Character.getNumericValue(charArray[2]) - 9;
        this.y = Character.getNumericValue(charArray[4]);
    }//end Move constructor

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

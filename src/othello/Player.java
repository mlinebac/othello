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

    String color;

    Player() {
        this.color = new String();
    }//end constructor
    
    public Player(String str) {
      this.color = str;
        
       
    }//end playerColor

    public String getPlayerColor() {
        return color;
    }

}

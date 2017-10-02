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

    String playerColor;
    
    public Player(String str) {
      String [] strArray = str.split(" ");
      this.playerColor = strArray[1];
      
    }//end playerColor

    public String getPlayerColor() {
        
        return playerColor;
    }

}

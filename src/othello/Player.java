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
    String B;
    String W;

    String getMyColor(char color){
        if (color == 'B'){
            return "Black";
        }else if (color == 'W'){
            return "White";}
        return "Not a correct color";
    }
}

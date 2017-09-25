/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.util.ArrayList;

/**
 *
 * @author matt
 */
public class Board {
    ArrayList moveList;
    int [][] gameBoard = new int [10][10];
   
   public Board(){
       
   }
   
   public Board(int [][] newBoard){
       this.gameBoard = newBoard;
   }
   
   public Board(Board oldBoard){
      this.gameBoard = oldBoard.gameBoard;
   }
   
   public Board(PlayerColor color){
       
    }
   
   public ArrayList generateMoves(Player player){
     return moveList;  
   }
   
   public int applyMove(Player player, Move move){
       return 0; 
   }
   
   public int evaluate(){
       return 0;
   }
   
    @Override
   public String  toString(){
       return null;
   }
}

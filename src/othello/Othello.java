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
public class Othello {
    public final int ME = 1;
    public final int OPPONENT = -1;
    public final int BORDER = -2;
    public final int EMPTY = 0;
    
    public static Board gameBoard;
    public Player currentPlayer;// = int(1,-1);
    //myColor = 
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        // TODO code application logic here
    //myColor = getMyColor();
    
    gameBoard = new Board();
    if (myColor == Black){
        currentPlayer = ME;
    }else{
        currentPlayer = OPPONENT;
    }
    while(!gameBoard.gameOver()){
        if (currentPlayer == ME){
            move = gameBoard.getMyMove();
        }else{
            move = gameBoard.getOpponent();
        }
        gameBoard.applyMove(player, move);
        printBoard(gameBoard)
        currentPlayer = -1 * currentPlayer //switch players
        
    }//end while loop
    printScore();
    }//end main
    
}

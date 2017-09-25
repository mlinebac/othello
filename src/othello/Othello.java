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

    public static int ME = 1;
    public static int OPPONENT = -1;
    public static int BORDER = -2;
    public static int EMPTY = 0;

    public static Board gameBoard;
    public static int currentPlayer;// = int(1,-1);
    public static String myColor;// = currentPlayer.getPlayerColor();
    public static Player player;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        myColor = player.getPlayerColor();

        gameBoard = new Board();
        if (myColor == "Black") {
            currentPlayer = ME;
        } else {
            currentPlayer = OPPONENT;
        }
        while (!gameBoard.gameOver()) {
            if (currentPlayer == ME) {
                move = gameBoard.getMyMove();
            } else {
                move = gameBoard.getOpponent();
            }
            gameBoard.applyMove(player, move);
            printBoard(gameBoard)
            currentPlayer = -1 * currentPlayer; //switch players

        }//end while loop
        printScore();
    }//end main

}

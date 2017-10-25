/*
 * 
 */
package othello;

import java.util.Scanner;

/**
 *
 * @author Matt Lineback
 */
public class Othello {

    public final static int ME = 1;
    public final static int OPPONENT = -1;

    public static Board gameBoard;
    public static int currentPlayer;// = int(1,-1);
    public static char myColor;
    public static char opponentColor;
    public static Player player;
    public static Move move;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("C " + "Please choose your color");
        String str = scan.nextLine();
        player = new Player(str);//intialize player I B or I W only!!!
        myColor = player.getPlayerColor();
        opponentColor = player.getOpponentColor();
        gameBoard = new Board(myColor);
        if (myColor == 'B') {
            currentPlayer = ME;
        } else {
            currentPlayer = OPPONENT;
        }
        //printing out my color to check if I B or I W is correct
        System.out.println(player.toString());
        //printing out game board before moves have been made
        System.out.print(gameBoard.toString());

        //loop through while alternating moves until game is over
        while (!gameBoard.gameOver()) {
            Move aiMove;
            if (currentPlayer == ME) {
                aiMove = gameBoard.getMyMove();
            } else {
                //get opponent move
                aiMove = gameBoard.getOpponentMove();
            }

            System.out.println("C This is the current player:" + currentPlayer);
            //apply move to gameboard
            gameBoard.applyMove(aiMove);
            //print updated gameboard with move applied
            System.out.print(gameBoard.toString());
            //print move without "C" if my move 
            if (currentPlayer == ME) {
                System.out.println(aiMove.toString());
            } else {
                System.out.println("C " + aiMove.toString());
            }
            //switch player turn 
            currentPlayer = currentPlayer * -1;
            //print both scores 
            gameBoard.printScore(myColor, opponentColor);

        }//end while loop
        //print out players score when game is over
        gameBoard.printScore(myColor, opponentColor);
    }//end main

}

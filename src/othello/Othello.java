/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author matt
 */
public class Othello {

    public final static int ME = 1;
    public final static int OPPONENT = -1;

    public static Board gameBoard;
    public static int currentPlayer;// = int(1,-1);
    public static int myColor;

    public static Player player;
    public static Move move;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String strMove;
        Scanner scan = new Scanner(System.in);
        System.out.println("C " + "Please choose your color");
        String str = scan.nextLine();
        player = new Player(str);//intialize player I B or I W only!!!
        myColor = player.getPlayerColor();
        gameBoard = new Board(myColor);
        if (myColor == 1) {
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
                System.out.println("C Opponent's Turn" + "\n C Enter your next move:");
                //scan for opponents turn
                strMove = scan.nextLine();
                //get opponents move 
                aiMove = new Move(strMove);
            }
            //apply move to gameboard
            System.out.println("C This is the current player:" + currentPlayer);
            gameBoard.applyMove(aiMove);
            //print updated gameboard with move applied
            System.out.print(gameBoard.toString());
            if (currentPlayer == ME) {
                System.out.println(aiMove.toString());
            } else {
                System.out.println("C " + aiMove.toString());
            }

            //switch player turn 
            currentPlayer = currentPlayer * -1;
        }//end while loop
        //print out players score when game is over
        gameBoard.printScore(player);
    }//end main

}

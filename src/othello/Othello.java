/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.util.Scanner;

/**
 *
 * @author matt
 */
public class Othello {

    public final static int ME = 1;
    public final static int OPPONENT = -1;
    public final static int EMPTY = 0;

    public static Board gameBoard;
    public static int currentPlayer;// = int(1,-1);
    public static String myColor;
    public static Player player;
    public static Move move;
    //public static Move move;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String strMove;
        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose your color");
        String str = scan.nextLine();
        player = new Player(str);//intialize player I B or I W only!!!
        myColor = player.getPlayerColor();
        gameBoard = new Board(myColor);
        
        if (myColor.equals("B")) {
            currentPlayer = ME;
        } else {
            currentPlayer = OPPONENT;
        }
        
        System.out.println(myColor);
        System.out.println("Black goes first");
        System.out.print(gameBoard.toString());
        
        if (currentPlayer != ME) {
            System.out.println("First Move: ");
            strMove = scan.nextLine();
            move = gameBoard.getMyMove(strMove);
            gameBoard.placeMarker(ME, move);
            System.out.println(gameBoard.toString());
        }
        while (!gameBoard.gameOver()) {
            System.out.println("Enter you next move");
            
            if (currentPlayer == ME) {
                System.out.println("My Turn");
                strMove = scan.nextLine();
                move = gameBoard.getMyMove(strMove);
                //printMove();
            } else {
                System.out.println("Opponent's Turn");
                strMove = scan.nextLine();
                move = gameBoard.getOpponent(strMove);
            }
            gameBoard.placeMarker(currentPlayer, move);
            System.out.print(gameBoard.toString());
            currentPlayer = -1 * currentPlayer; //switch players

        }//end while loop

        // printScore();
    }//end main

}

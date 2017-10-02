/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.util.ArrayList;

/**
 *
 * @author Matt Lineback
 */
public class Board {

    private final int BOARDER = -2;
    private final int BOARD_SIZE = 10;
    private final int BLACK = 1;
    private final int WHITE = -1;
    private final int EMPTY = 0;
    int[][] board;

    //all possible directions player may move
    public final Move up = new Move(0, -1);
    public final Move down = new Move(0, 1);
    public final Move right = new Move(1, 0);
    public final Move left = new Move(-1, 0);
    public final Move upRight = new Move(1, -1);
    public final Move upLeft = new Move(-1, -1);
    public final Move downRight = new Move(1, 1);
    public final Move downLeft = new Move(-1, 1);
    //array of all possible moves
    final Move directions[] = {up, down, right, left,
        upRight, upLeft, downRight, downRight};
    
    
    public Board(){
        board = new int [BOARD_SIZE][BOARD_SIZE];
    }
    
    public Board(String color) {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 1; i < BOARD_SIZE - 1; i++) {
            for (int j = 1; j < BOARD_SIZE - 1; j++) {
                this.board[i][j] = EMPTY;// initialize all cells to EMPTY
            }
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][0] = BOARDER;
            board[0][i] = BOARDER;
            board[BOARD_SIZE - 1][i] = BOARDER;
            board[i][BOARD_SIZE - 1] = BOARDER;
        }
        board[4][4] = WHITE;
        board[5][4] = BLACK;
        board[4][5] = BLACK;
        board[5][5] = WHITE;
    }
    
    
    
    public Board getCopy() {
        Board clone = new Board();
        clone.setState(getState());
        return clone;
    }

    public int[][] getState() {
        int[][] state = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                state[i][j] = board[i][j];
            }
        }
        return state;
    }

    public void setState(int[][] state) {
        board = state;
    }
    
    public boolean isLegalMove(Player color, Move move) {

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {

            }
        }
        return true;
    }

    //generates a list of valid moves a player can make
    public ArrayList<Move> generateMoves(Player player) {
        ArrayList<Move> validMoves = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {

                Move possibleMove = new Move();
                //test if the move is legal 
                boolean isValid = isLegalMove(player, possibleMove);
                //if move is legal add it the list of valid moves
                if (isValid) {
                    validMoves.add(possibleMove);
                }
            }
        }
        return validMoves;
    }

    public Board applyMove(int color, Move move) {
        //legal moves only
        //flips pieces
        
        return placeMarker(color, move);
       
    }

    public Board placeMarker(int color, Move move) {
        Board newBoard;
        newBoard = this.getCopy();
        this.setMarker(color, move.x, move.y);
        return newBoard;
        
    }
    
    public void setMarker(int markerColor, int x, int y){
        board[y][x] = markerColor;
    }
    
    public int getMarker(int x, int y) {
        int markerColor = board[y][x];
        return markerColor;
    }

    //need to finish this method
    public Move getMyMove(String str) {
        Move move = new Move (str);
        return move;
    }

    //need to finish this method
    public Move getOpponent(String str) {
        Move move = new Move(str);
        return move;
    }

    public int evaluate() {
        return 0;
    }

    public boolean gameOver() {
        //Player player = Othello.player;
        //Move move = getMyMove();
        //if (isLegalMove(player, move) == true){
        return false;
        // }else
        // return true;

    }

    public void printBoard() {
        char[] ah = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        for (int i = 0; i < ah.length; i++) {
            System.out.print(ah[i] + " ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        printBoard();
        String str = "";
        int count = 1;
        char playerChar ;
        for (int row = 1; row < board.length - 1; row++) {
            str += count;
            for (int col = 1; col < board.length - 1; col++) {
                switch (board[row][col]) {
                    case WHITE:
                        playerChar = 'W';
                        break;
                    case BLACK:
                        playerChar = 'B';
                        break;
                    case EMPTY:
                        playerChar = '-';
                        break;
                    default:
                        playerChar = '*';
                        break;
                }
                str += " " + playerChar;
            }//end col loop
            count++;
            str += "\n";
        }//end row loop
        return str;
    }
}//end Board class

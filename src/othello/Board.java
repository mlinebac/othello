/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author matt
 */
public class Board {
    private final int BOARDER = -2;
    private final int BOARD_SIZE = 10;
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

    public Board(Board oldBoard) {
        this.board = oldBoard.board;
    }

    public Board(Player player){
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 1; i < BOARD_SIZE - 1; i++) {
            for (int j = 1; j < BOARD_SIZE - 1; j++) {
                this.board[i][j] = '-';// initialize all cells to EMPTY
            }
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][0] = BOARDER;
            board[0][i] = BOARDER;
            board[BOARD_SIZE - 1][i] = BOARDER;
            board[i][BOARD_SIZE - 1] = BOARDER;
        }

        if ("Black".equals(player.getPlayerColor())) {
            board[4][4] = 'W'; //White
            board[5][4] = 'B'; //Black
            board[4][5] = 'B'; //Black
            board[5][5] = 'W'; //White
        } else {
            board[4][4] = 'B'; //Black
            board[5][4] = 'W'; //White
            board[4][5] = 'W'; //White
            board[5][5] = 'B'; //Black
        }

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

                Move possibleMove = new Move(i, j);
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

    public int applyMove(Player player, Move move) {
        return 0;
    }

    public int evaluate() {
        return 0;
    }

    
    public String toString() {
        String str = "";
        for (int row = 1; row < board.length-1; row++){
            for(int col = 1; col < board.length-1; col++){
                str += " " + (char)board[row][col];
            }
            str += "\r\n";
        }
        return str;
        
        /*
        for (int[] x : board) {
        {
          for (int y : x)  {
              System.out.print(y + " ");
          }
          System.out.println();
            }
            
        }
        */
    }
}//end Board class
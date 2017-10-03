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
    
    
    
    
    
    private final int BOARDER = -2;
    private final int BOARD_SIZE = 10;
    private final int BLACK = 1;
    private final int WHITE = -1;
    private final int EMPTY = 0;
    int[][] board;
    public Board() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
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
        board[4][4] = -1; //white
        board[5][4] = 1; //black
        board[4][5] = 1; //black
        board[5][5] = -1; //white
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

    public boolean isLegalMove(Move move, int color) {
        int x = move.getX();
        int y = move.getY();

        if (getMarker(x, y) == EMPTY) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    int posX = x + i;
                    int posY = y + j;
                    int current = getMarker(posX, posY);
                    while (current != color && current != EMPTY && current != BOARDER) {
                        posX += i;
                        posY += j;
                        current = getMarker(posX, posY);
                        if (current == color) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;

        /*
        boolean legal = false;
        int opponent = -1 * color;
        int currentPlayer = color;

        int x = move.getX();
        int y = move.getY();

        //check to see if the cell is empty
        if (board[y][x] != EMPTY) {
            return false;
        } else {
            for (int i = 0; i < directions.length; i++) {
                Move direction = directions[i];

                int dirX = direction.getX();
                int dirY = direction.getY();
                int jump = 2;

                try {
                    if (board[y + dirY][x + dirX] == opponent) {
                        while ((y + (jump * dirY)) > -1
                                && (y + (jump * dirY)) < 8
                                && (x + (jump * dirX)) < 8
                                && (x + (jump * dirX)) > -1) {
                            if (board[y + jump * dirY][x + jump * dirX] != EMPTY) {
                                if (board[y + jump * dirY][x + jump * dirX] == currentPlayer) {
                                    return true;
                                } else if (board[y + jump * dirY][x + jump * dirX] == EMPTY) {
                                    break;
                                }
                            } else {
                                break;
                            }
                            jump++;
                        }// end while loop
                    }

                } catch (Exception e) {

                }
            }//end for loop of directions
        }//end if board is not empty

        return legal;
         */
    }//end of isLegalMove method

    //generates a list of valid moves a player can make
    public ArrayList<Move> generateMoves(int color) {
        ArrayList<Move> validMoves = new ArrayList<>();
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board.length - 1; j++) {
                Move move = new Move(i, j);
                if (isLegalMove(move, color)) {
                    validMoves.add(move);
                }
            }
        }
        return validMoves;
    }

    public Board applyMove(int color, Move move) {
        //if (isLegalMove(move, color) != true) {
        // System.out.println("illegal move");
        //} else {
        return placeMarker(color, move);
        //}
    }

    public Board placeMarker(int color, Move move) {

        if (isLegalMove(move, color) != true) {
            System.out.println("\nillegal move!!!\n");
        }
        Board newBoard;
        newBoard = this.getCopy();
        int x = move.getX();
        int y = move.getY();
        this.setMarker(color, x, y);

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int square = newBoard.getMarker(x + i, y + j);
                int k = x + i;
                int l = y + j;
                while (square != EMPTY && square != color
                        && square != BOARDER) {
                    k += i;
                    l += j;
                    square = newBoard.getMarker(k, l);
                }
                if (square == color) {
                    k -= i;
                    l -= j;
                    square = newBoard.getMarker(k, l);
                    while (square != EMPTY && square != color
                            && square != BOARDER) {
                        newBoard.setMarker(color, k, l);
                        k -= i;
                        l -= j;
                        square = newBoard.getMarker(k, l);
                    }
                }
            }
        }

        return newBoard;
    }

    public void setMarker(int markerColor, int x, int y) {
        if (!(x <= BOARDER || y <= BOARDER)) {
        this.board[y][x] = markerColor;
        }else 
        System.out.println("Not a move!!!");
    }

    public int getMarker(int x, int y) {
        int markerColor = board[y][x];
        return markerColor;
    }

    //need to finish this method
    public Move getMyMove(String str) {
        Move move = new Move(str);
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
        char playerChar;
        for (int row = 1; row < board.length - 1; row++) {
            str += count;
            for (int col = 1; col < board.length - 1; col++) {
                switch (board[row][col]) {
                    case -1:
                        playerChar = 'W';
                        break;
                    case 1:
                        playerChar = 'B';
                        break;
                    case 0:
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

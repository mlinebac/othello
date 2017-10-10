/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Matt Lineback
 */
public class Board {

    //all possible move directions
    public final Move up = new Move(0, -1); // up
    public final Move down = new Move(0, 1); // down
    public final Move left = new Move(-1, 0); // left
    public final Move right = new Move(1, 0); // right
    public final Move upLeft = new Move(-1, -1); // up and left
    public final Move upRight = new Move(1, -1); // up and right
    public final Move downLeft = new Move(-1, 1); // down and left
    public final Move downRight = new Move(1, 1); // down and right
    //array of all directions
    final Move directions[] = {up, down, left, right, upLeft, upRight,
        downLeft, downRight};
    public static int boardColor;
    private int Black;
    private int White;
    private final int BOARDER = -2;
    private final int BOARD_SIZE = 10;
    private final int EMPTY = 0;
    int[][] board;

    //constructor for empty board;
    public Board() {
        board = new int[BOARD_SIZE][BOARD_SIZE];

    }

    //constructor that takes a player color and intiates board with boarders and 
    //players at starting positions
    public Board(int color) {
        Board.boardColor = color;
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
        //initial starting position of board and pieces
        if (color == 1) {
            Black = 1; //set my color to Black
            White = -1; //set opponent color to white
            board[4][4] = White;//opponent as White
            board[5][4] = Black;//me as Black
            board[4][5] = Black; //me as Black
            board[5][5] = White; //opponent as White
        } else {
            Black = -1; // set opponent color to Black
            White = 1; // set my color to White
            board[4][4] = White; //me as White
            board[5][4] = Black; //opponent as Black
            board[4][5] = Black; //opponent as Black
            board[5][5] = White; //me as White
        }
    }

    //get new version of board by taking old state and applying it to new one
    public Board getCopy() {
        Board clone = new Board();
        clone.setState(getState());
        return clone;
    }

    //get current state of board and returns it
    public int[][] getState() {
        int[][] state = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                state[i][j] = board[i][j];
            }
        }
        return state;
    }

    //set current state of board 
    public void setState(int[][] state) {
        board = state;
    }

    //check if move is legal 
    public boolean isLegalMove(int color, Move move) {
        boolean legal = false;
        int currentPlayer = color;
        int opponent = color * -1;
        int x = move.getX();
        int y = move.getY();

        //check to see if the cell is empty
        if (board[y][x] != EMPTY) {
            return false;
        } else {
            //check in all directions by applying list of moves
            for (Move direction : directions) {
                int dirX = direction.getX();
                int dirY = direction.getY();
                int jump = 2;
                if (board[y + dirY][x + dirX] == opponent) {
                    //do this while jump does not got out of bounds
                    while ((y + (jump * dirY)) > -1
                            && (y + (jump * dirY)) < 8
                            && (x + (jump * dirX)) < 8
                            && (x + (jump * dirX)) > -1) {
                        //if jump does not jump over empty or the players same color
                        //return is a legal move
                        if (board[y + jump * dirY][x + jump * dirX] != EMPTY) {
                            if (board[y + jump * dirY][x + jump * dirX] == currentPlayer) {
                                return true;
                                //if jump does jump over players same color
                                //break out of while loop
                            } else if (board[y + jump * dirY][x + jump * dirX] == EMPTY) {
                                break;
                            }
                        } else {
                            break;
                        }
                        jump++;
                    }// end while loop
                }

            }

        }
        return legal;

    }

    //generates a list of valid moves a player can make
    public ArrayList<Move> generateMoves(int myColor) {
        ArrayList<Move> validMoves = new ArrayList<>();
        //check the entire board for legal moves a player can make

        for (char i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board.length - 1; j++) {

                Move move = new Move(myColor, i, j);
                //if it's a legal move add it to the list of legal moves
                if (isLegalMove(myColor, move)) {
                    validMoves.add(move);
                }
            }
        }
        //return list of legal moves for player
        return validMoves;
    }

    //returns updated board if move was to be made
    public Board applyMove(int color, Move move) {

        //get new instance of board
        Board newBoard;
        newBoard = this.getCopy();
        int x = move.getX();
        int y = move.getY();
        //set marker location on new board
        setMarker(color, x, y);
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int space = newBoard.getMarker(x + i, y + j);
                int k = x + i;
                int l = y + j;
                while (space != EMPTY && space != color
                        && space != BOARDER) {
                    k += i;
                    l += j;
                    space = newBoard.getMarker(k, l);
                }
                if (space == color) {
                    k -= i;
                    l -= j;
                    space = newBoard.getMarker(k, l);
                    while (space != EMPTY && space != color
                            && space != BOARDER) {
                        setMarker(color, k, l);//flips marker
                        k -= i;
                        l -= j;
                        space = newBoard.getMarker(k, l);

                    }
                }
            }
        }

        return newBoard;

    }

    public Move getMyMove() {
        //pick a random move from my list of valid moves
        Random rand = new Random();
        ArrayList<Move> myList = generateMoves(Othello.ME);
        if (!myList.isEmpty()) {
            int index = rand.nextInt(myList.size());
            return myList.get(index);
        } else {
            System.out.println("No more legal moves");
            return new Move();
        }
    }

    public Move getOpponentMove() {
        int x;
        int y;
        Move opponentMove;
        Scanner opponentScan = new Scanner(System.in);
        String strMove = opponentScan.nextLine();

        opponentMove = new Move(strMove);
        x = opponentMove.getX();
        y = opponentMove.getY();

        Move move = new Move(x, y);

        if (isLegalMove(Othello.OPPONENT, move) != false) {
            return new Move(x, y);
        }
        System.out.println("C " + "Bad Move !!! Enter another move: ");
        return getOpponentMove();

    }

    //put marker at this coordinate 
    public void setMarker(int marker, int x, int y) {
        if (!(x <= BOARDER || y <= BOARDER)) {
            this.board[y][x] = marker;
        } else {
            System.out.println("Not a move!!!");
        }
    }

    //get marker at this coordinate and return it 
    public int getMarker(int x, int y) {
        int markerColor = board[y][x];
        return markerColor;
    }

    //method needs to be finished!!!
    public int evaluate() {
        return 0;
    }

    //evaluate moves and if there is no legal move then the game is over and return true
    public boolean gameOver() {

        return false; //returning false for now until method is finished;
    }

    //checks the board and counts num of player pieces on the board and returns count sum;
    public int getPlayerScore(Player player) {
        int score = 0;
        for (int row = 1; row < board.length - 1; row++) {
            for (int col = 1; col < board.length - 1; col++) {
                if (board[row][col] == player.playerColor) {
                    score++;
                }
            }
        }
        return score;
    }

    public void printScore(Player player) {
        int score = getPlayerScore(player);
        System.out.println("C " + score);
    }

    //prints outline of board 
    public void printBoard() {
        char[] a2h = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        System.out.print("C ");
        for (int i = 0; i < a2h.length; i++) {
            System.out.print(a2h[i] + " ");
        }

        System.out.println();
    }

    //prints string representation of board
    @Override
    public String toString() {
        printBoard();
        String str = "";
        int count = 1;
        char playerChar = ' ';
        for (int row = 1; row < board.length - 1; row++) {
            str += "C " + count;
            for (int col = 1; col < board.length - 1; col++) {
                if (boardColor == 1) {
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
                            playerChar = '#'; //should not see this only to catch errors 
                            break;
                    }
                } else {

                    switch (board[row][col]) {
                        case -1:
                            playerChar = 'B';
                            break;
                        case 1:
                            playerChar = 'W';
                            break;
                        case 0:
                            playerChar = '-';
                            break;
                        default:
                            playerChar = '#'; //should not see this only to catch errors 
                            break;
                    }
                }
                str += " " + playerChar;
            }//end col loop
            count++;
            str += "\n";
        }//end row loop
        return str;
    }
}//end Board class

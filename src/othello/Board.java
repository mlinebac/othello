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

    public enum Cell {
        BLACK('B'),
        WHITE('W'),
        EMPTY('-'),
        BOARDER('*');

        private final char Char;

        public char getChar() {
            return Char;
        }

        Cell(char colorChar) {
            Char = colorChar;

        }

    }

    public enum Direction {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0),
        UPLEFT(-1, -1),
        UPRIGHT(1, -1),
        DOWNLEFT(-1, 1),
        DOWNRIGHT(1, 1);

        private final int x;
        private final int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        Direction(int xCord, int yCord) {
            x = xCord;
            y = yCord;
        }

    }

    private final int BOARD_SIZE = 10;
    Cell[][] board;

    //constructor for empty board;
    public Board() {
        board = new Cell[BOARD_SIZE][BOARD_SIZE];

    }

    //constructor that takes a player color and intiates board with boarders and 
    //players at starting positions
    public Board(char color) {

        this.board = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 1; i < BOARD_SIZE - 1; i++) {
            for (int j = 1; j < BOARD_SIZE - 1; j++) {
                this.board[i][j] = Cell.EMPTY;// initialize all cells to EMPTY
            }
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][0] = Cell.BOARDER;
            board[0][i] = Cell.BOARDER;
            board[BOARD_SIZE - 1][i] = Cell.BOARDER;
            board[i][BOARD_SIZE - 1] = Cell.BOARDER;
        }

        board[4][4] = Cell.WHITE;//opponent as White
        board[5][4] = Cell.BLACK;//me as Black
        board[4][5] = Cell.BLACK; //me as Black
        board[5][5] = Cell.WHITE; //opponent as White

    }

    //get new version of board by taking old state and applying it to new one
    public Board getCopy() {
        Board clone = new Board();
        clone.setState(getState());
        return clone;
    }

    //get current state of board and returns it
    public Cell[][] getState() {
        Cell[][] state = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(board[i], 0, state[i], 0, BOARD_SIZE);
        }
        return state;
    }

    //set current state of board 
    public void setState(Cell[][] state) {
        board = state;
    }

    //check if move is legal 
    public boolean isLegalMove(Move move) {
        boolean legal = false;

        Cell playerMoveColor = move.playerColorCell;
        Cell opponentMoveColor = move.opponentColorCell;

        int x = move.getX();
        int y = move.getY();

        //check to see if the cell is empty
        if (board[y][x] != Cell.EMPTY) {
            return false;
        } else {
            //check in all directions by applying list of moves
            for (Direction direction : Direction.values()) {
                int dirX = direction.getX();
                int dirY = direction.getY();
                int jump = 2;
                if (board[y + dirY][x + dirX] == opponentMoveColor) {
                    //do this while jump does not got out of bounds
                    while ((y + (jump * dirY)) > -1
                            && (y + (jump * dirY)) < 8
                            && (x + (jump * dirX)) < 8
                            && (x + (jump * dirX)) > -1) {
                        //if jump does not jump over empty or the players same color
                        //return is a legal move
                        if (board[y + jump * dirY][x + jump * dirX] != Cell.EMPTY) {
                            if (board[y + jump * dirY][x + jump * dirX] == playerMoveColor) {
                                return true;
                                //if jump does jump over players same color
                                //break out of while loop
                            } else if (board[y + jump * dirY][x + jump * dirX] == Cell.EMPTY) {
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
    public ArrayList<Move> generateMoves(char myColor) {
        ArrayList<Move> validMoves = new ArrayList<>();
        //check the entire board for legal moves a player can make

        for (char i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board.length - 1; j++) {

                Move move = new Move(myColor, i, j);
                //if it's a legal move add it to the list of legal moves
                if (isLegalMove(move)) {
                    validMoves.add(move);
                }
            }
        }
        //return list of legal moves for player
        return validMoves;
    }

    //returns updated board if move was to be made
    public Board applyMove(Move move) {
        Cell color = move.getMoveColor();

        //get new instance of board
        Board newBoard;
        newBoard = this.getCopy();
        int x = move.getX();
        int y = move.getY();
        //set marker location on new board
        setMarker(color, x, y);
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Cell space = newBoard.getMarker(x + i, y + j);
                int k = x + i;
                int l = y + j;
                while (space != Cell.BOARDER && space != color
                        && space != Cell.EMPTY) {
                    k += i;
                    l += j;
                    space = newBoard.getMarker(k, l);
                }
                if (space == color) {
                    k -= i;
                    l -= j;
                    space = newBoard.getMarker(k, l);
                    while (space != Cell.BOARDER && space != color
                            && space != Cell.EMPTY) {
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

    //put marker at this coordinate 
    public void setMarker(Cell color, int x, int y) {
        this.board[y][x] = color;

    }

    //get marker at this coordinate and return it 
    public Cell getMarker(int x, int y) {
        System.out.println(board[y][x]);
        return board[y][x];

    }

    public Move getMyMove() {
        //pick a random move from my list of valid moves
        Random rand = new Random();
        ArrayList<Move> myList = generateMoves(Othello.myColor);
        if (!myList.isEmpty()) {
            int index = rand.nextInt(myList.size());
            return myList.get(index);
        } else {
            System.out.println("C No more legal moves");
            return new Move(Othello.myColor);
        }
    }

    public Move getOpponentMove() {
        /*
        oppMove = assume invalid until they enter valid
        moves = generate legal moves
        if (no moves) -> add pass move to moves list
        while (opp has not entered a move in moves list)
            ask opp for move
        
        return oppMove
         */
        String strMove;
        Move opponentMove = null; 
        ArrayList<Move> opponentList = generateMoves(Othello.opponentColor);
        if (opponentList.isEmpty()) {
            if (Othello.opponentColor == 'B') {
                strMove = "W";
            } else {
                strMove = "B";
            }
           // opponentMove = new Move(strMove);
            opponentList.add(new Move(strMove));
        }
        while (opponentMove == null) {
            System.out.println("C " + "Enter Opponent Move: ");
            Scanner opponentScan = new Scanner(System.in);
            strMove = opponentScan.nextLine();
            opponentMove = new Move(strMove);
            if (!opponentList.contains(opponentMove)){
                System.out.println("C " + "Invalid Move!");
                opponentMove = null;
            }
            //if opponent move is not in opponent list
            //    print invalid move
            //    opponentMove = null
            
        }
       
            return opponentMove;

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
    public int getPlayerScore() {
        return playerScore;
    }

    int playerScore;

    public void setPlayerScore(char player) {
        Cell color;
        if (player == 'B') {
            color = Cell.BLACK;
        } else {
            color = Cell.WHITE;
        }
        int score = 0;
        for (int row = 1; row < board.length - 1; row++) {
            for (int col = 1; col < board.length - 1; col++) {
                if (board[row][col] == color) {
                    score++;
                }
            }
        }
        //return score;
        this.playerScore = score;
    }

    public void printScore(char me, char opponent) {
        setPlayerScore(me);
        int myScore;
        myScore = getPlayerScore();
        setPlayerScore(opponent);
        int opponentScore;
        opponentScore = getPlayerScore();
        System.out.println("C My score is: " + myScore + "\nC Opponent score is: " + opponentScore);
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
        char playerChar;// = ' ';
        for (int row = 1; row < board.length - 1; row++) {
            str += "C " + count;
            for (int col = 1; col < board.length - 1; col++) {
                playerChar = board[row][col].getChar();
                str += " " + playerChar;
            }//end col loop
            count++;
            str += "\n";
        }//end row loop
        return str;
    }
}//end Board class

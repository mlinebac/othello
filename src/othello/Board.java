/*
 * 
 */
package othello;

import java.util.ArrayList;
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

    public int playerScore;
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

    //get copy of board for alpha beta
    public Board boardCopy(Board original) {
        Board copy = new Board();
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(original.board[i], 0, copy.board[i], 0, BOARD_SIZE);
        }
        return copy;

    }

    //get new version of board by taking old state and applying it to new one
    public Board getCopy() {
        Board clone = new Board();
        clone.setState(getState());
        return clone;
    }

    //get current state of board and return it
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

        int x = move.x;
        int y = move.y;

        //check to see if the cell is empty
        if (board[y][x] != Cell.EMPTY) {
            return false;
        } else {
            //check in all directions by applying list of moves
            for (Directions direction : Directions.values()) {
                int dirX = direction.getX();
                int dirY = direction.getY();
                int jump = 2;
                if (board[y + dirY][x + dirX] == opponentMoveColor) {
                    //do this while jump does not got out of bounds
                    while ((y + (jump * dirY)) > -1
                            && (y + (jump * dirY)) < 9
                            && (x + (jump * dirX)) < 9
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
    public ArrayList<Move> generateMoves(char playerColor) {
        ArrayList<Move> validMoves = new ArrayList<>();
        //check the entire board for legal moves a player can make

        for (int i = 1; i < BOARD_SIZE; i++) {
            for (int j = 1; j < BOARD_SIZE; j++) {

                Move move = new Move(playerColor, i, j);
                //if it's a legal move add it to the list of legal moves
                if (isLegalMove(move)) {
                    validMoves.add(move);
                }
            }
        }
        return validMoves;
    }

    //returns updated board if move was to be made
    public Board applyMove(Move move) {
        if (move.getSize() == 1) {
            return this;
        } else {
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
    }

    //put Cell marker at this coordinate 
    public void setMarker(Cell color, int x, int y) {
        this.board[y][x] = color;

    }

    //get Cell marker at this coordinate and return it 
    public Cell getMarker(int x, int y) {
        return board[y][x];

    }
    //returns string move B or W depending on color picked at begining of game
    public Move myPassMove() {
        String strMove;
        if (Othello.myColor == 'B') {
            strMove = "B";
        } else {
            strMove = "W";
        }
        return new Move(strMove);
    }
    //returns string move B or W depending on color picked at begining of game
    public Move optPassMove() {
        String strMove;
        if (Othello.opponentColor == 'B') {
            strMove = "B";
        } else {
            strMove = "W";
        }
        return new Move(strMove);
    }
    //returns computers best move against its component 
    public Move getMyMove() {
        double alpha = Double.NEGATIVE_INFINITY;
        double beta = Double.MAX_VALUE;
        return alphaBeta(this, 0, Othello.myColor, alpha, beta, 6);
    }
    //evaluates board and calculates heuristic 
    public double evaluate() {
        //evaluating player scores 
        double playerScores;

        setPlayerScore(Othello.myColor);
        int myScore = getPlayerScore();
        setPlayerScore(Othello.opponentColor);
        int opponentScore = getPlayerScore();

        if (myScore > opponentScore) {
            playerScores = (100 * myScore) / (myScore + opponentScore);
        } else if (myScore < opponentScore) {
            playerScores = -(100 * opponentScore) / (myScore + opponentScore);
        } else {
            playerScores = 0.0;
        }

        //evaluating number of moves 
        ArrayList<Move> myMoves = generateMoves(Othello.myColor);
        ArrayList<Move> opponentMoves = generateMoves(Othello.opponentColor);
        int myMoveSize = myMoves.size();
        int opponentMoveSize = opponentMoves.size();
        double numMoves;

        if (myMoveSize > opponentMoveSize) {
            numMoves = (100 * myMoveSize) / (myMoveSize + opponentMoveSize);
        } else if (myMoveSize < opponentMoveSize) {
            numMoves = -(100 * opponentMoveSize) / (myMoveSize + opponentMoveSize);
        } else {
            numMoves = 0;
        }

        //Evaluating corners 
        double myCorner = 0.0;
        double opponentCorner = 0.0;
        double cornerScore;

        myCorner = myMoves.stream().filter((moves) -> (moves.getMoveValue() == 15)).map((_item) -> 1.0).reduce(myCorner, (accumulator, _item) -> accumulator + 1);
        opponentCorner = opponentMoves.stream().filter((moves) -> (moves.getMoveValue() == 15)).map((_item) -> 1.0).reduce(opponentCorner, (accumulator, _item) -> accumulator + 1);

        cornerScore = 500 * (100 * myCorner - 100 * opponentCorner);

        //adding up all evaluations 
        return playerScores + numMoves + cornerScore;

    }

    public Move getOpponentMove() {
        String strMove;
        Move opponentMove = null;
        ArrayList<Move> opponentList = generateMoves(Othello.opponentColor);
        if (opponentList.isEmpty()) {
            opponentMove = optPassMove();
            opponentList.add(opponentMove);
        }
        while (opponentMove == null) {
            System.out.println("C " + "Enter Opponent Move: ");
            Scanner opponentScan = new Scanner(System.in);
            strMove = opponentScan.nextLine();
            opponentMove = new Move(strMove);
            if (!opponentList.contains(opponentMove)) {
                System.out.println("C " + "Invalid Move!");
                opponentMove = null;
            }
        }
        return opponentMove;
    }
    //returns best move using alpha beta pruning 
    public Move alphaBeta(Board currentBoard, int ply, char playerColor, double alpha, double beta, int maxDepth) {
        char nextPlayerColor; //alternates color of player
        if (playerColor == Othello.myColor) {
            nextPlayerColor = Othello.opponentColor;
        } else {
            nextPlayerColor = Othello.myColor;
        }

        if (ply >= maxDepth) {
            Move returnMove = new Move();
            returnMove.value = currentBoard.evaluate();//evaluate
            return returnMove;
        } else {

            ArrayList<Move> moveList = currentBoard.generateMoves(playerColor);

            if (moveList.isEmpty()) {
                if (playerColor == Othello.myColor) {
                    moveList.add(myPassMove());
                } else {
                    moveList.add(optPassMove());
                }
            }

            Move bestMove = moveList.get(0);
            for (Move move : moveList) {
                Board newBoard = boardCopy(currentBoard);
                newBoard.applyMove(move);
                Move tempMove = alphaBeta(newBoard, ply + 1, nextPlayerColor, -beta, -alpha, maxDepth);
                move.value = -tempMove.getMoveValue();
                if (move.value > alpha) {
                    bestMove = move;
                    alpha = move.value;
                    if (alpha > beta) {
                        return bestMove;
                    }

                }
            }
            return bestMove;

        }

    }

    //evaluate moves and if there is no legal move then the game is over and return true
    public boolean gameOver() {
        return playerScore == 64;
    }

    //checks the board and counts num of player pieces on the board and returns count sum;
    public int getPlayerScore() {
        return playerScore;
    }

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
        int myScore = getPlayerScore();

        setPlayerScore(opponent);
        int opponentScore = getPlayerScore();

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

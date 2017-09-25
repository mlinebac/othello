/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.util.ArrayList;

/**
 *
 * @author matt
 */
public class Board {

    private final int BOARD_SIZE = 10;
    ArrayList moveList = new ArrayList();
    int[][] gameBoard = new int[BOARD_SIZE][BOARD_SIZE];
    //all possible move directions
    Move up = new Move(0, -1);
    Move down = new Move(0, 1);
    Move right = new Move(1, 0);
    Move left = new Move(-1, 0);
    Move upRight = new Move(1, -1);
    Move upLeft = new Move(-1, 1);
    Move downRight = new Move(1, 1);
    Move downLeft = new Move(-1, 1);
    //moveList = {up, down, right, left, upRight, upLeft, downRight, downLeft};

    public Board() {

    }

    public Board(int[][] newBoard) {
        this.gameBoard = newBoard;
    }

    public Board(Board oldBoard) {
        this.gameBoard = oldBoard.gameBoard;
    }

    public Board(Player color) {

    }

    public ArrayList generateMoves(Player player) {
        return moveList;
    }

    public int applyMove(Player player, Move move) {
        return 0;
    }

    public int evaluate() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }
}

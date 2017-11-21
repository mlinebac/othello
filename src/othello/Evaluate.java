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
public class Evaluate {
    
    //Get a random move
    

    public Move alphaBeta(Board currentBoard, int ply, char myColor, double alpha, double beta, int maxDepth) {
        if (ply >= maxDepth) {
            Move returnMove = new Move();
            //returnMove.value = currentBoard.evaluate();
            return returnMove;
        } else {
            /*
               1. generate Moves for player
               2. if MoveList is empty, add passmove to movelist
               3. bestMove = moveList.get(0)
               4. for each move in the move list
                  a.) newBoard = currentBoard.applyMove(player, move)
                  b.) tempMove = alphaBeta(newBoard, ply+1, -player, -beta, -alpha, maxDepth)
                  c.) move.value = -tempMove.value;
                  d.) if moveValue > alpha
                      c0) bestMove = move
                      c1) alpha = moveValue
                      c2) if alpha > beta
                            return bestMove
              5.) return bestMove        
             */
            ArrayList<Move> moves = currentBoard.generateMoves(myColor);
            String strMove;
            if (moves.isEmpty()) {
                if (Othello.myColor == 'B') {
                    strMove = "B";
                } else {
                    strMove = "W";
                }
                moves.add(new Move(strMove)); //add pass move if empty
            }
            Move bestMove = moves.get(0);
            for (Move move : moves) {
                Board newBoard = currentBoard.getCopy();
                newBoard.applyMove(move);//(player, move);

                Move tempMove = alphaBeta(newBoard, ply + 1, Othello.opponentColor, -beta, -alpha, maxDepth);
                move.value = -tempMove.value;
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
}

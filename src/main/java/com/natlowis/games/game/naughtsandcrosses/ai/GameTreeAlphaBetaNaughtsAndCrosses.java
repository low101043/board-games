package com.natlowis.games.game.naughtsandcrosses.ai;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.naughtsandcrosses.BoardNaughtsAndCrosses;
import com.natlowis.games.game.naughtsandcrosses.PieceNaughtsAndCrosses;

/**
 * Creates the GameTree for a Naughts and Crosses Game. This uses Alpha Beta
 * Pruning
 * 
 * @author low101043
 *
 */
public class GameTreeAlphaBetaNaughtsAndCrosses extends GameTreeNaughtsAndCrosses {

	/**
	 * The constructor
	 * 
	 * @param previousBoard The Parent of the node
	 * @param piece         The {@link PieceNaughtsAndCrosses} to add next
	 * @param alpha         The alpha value. The minimum value this state could be.
	 * @param beta          The beta value. The maximum value this state could be.
	 */
	public GameTreeAlphaBetaNaughtsAndCrosses(BoardNaughtsAndCrosses previousBoard, PieceNaughtsAndCrosses piece,
			int alpha, int beta) {
		node = previousBoard;

		if (terminalNode()) {
			return;
		}

		int v = setUp(piece);

		if (utility == -2) {
			for (int i = 0; i < previousBoard.currentBoard().length; i++) {
				for (int j = 0; j < previousBoard.currentBoard()[i].length; j++) {
					if (previousBoard.currentBoard()[i][j].type() == Type.EMPTY) {
						BoardNaughtsAndCrosses newBoard = (BoardNaughtsAndCrosses) previousBoard.clone();

						newBoard.add(piece, i, j);

						if (piece.type() == Type.CROSS) {

							GameTreeAlphaBetaNaughtsAndCrosses gt = new GameTreeAlphaBetaNaughtsAndCrosses(newBoard,
									new PieceNaughtsAndCrosses(Type.NAUGHT), alpha, beta);

							if (gt.returnUtility() < v) {
								nextMove = gt.getBoard();
								v = gt.returnUtility();
							}
							beta = Math.min(beta, v);

							if (beta <= alpha) {
								i = previousBoard.currentBoard().length - 1;
								j = previousBoard.currentBoard()[0].length + 5;
							}

						} else {

							GameTreeAlphaBetaNaughtsAndCrosses gt = new GameTreeAlphaBetaNaughtsAndCrosses(newBoard,
									new PieceNaughtsAndCrosses(Type.CROSS), alpha, beta);
							if (gt.returnUtility() > v) {
								nextMove = gt.getBoard();
								v = gt.returnUtility();
							}
							alpha = Math.max(alpha, v);

							if (beta <= alpha) {
								i = previousBoard.currentBoard().length - 1;
								j = previousBoard.currentBoard()[0].length + 5;
							}
						}
					}
				}
			}
			utility = v;
		}

	}

}

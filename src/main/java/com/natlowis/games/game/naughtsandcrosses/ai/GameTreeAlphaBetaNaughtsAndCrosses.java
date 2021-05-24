package com.natlowis.games.game.naughtsandcrosses.ai;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.interfaces.games.Board;
import com.natlowis.games.game.interfaces.games.ai.GameTree;
import com.natlowis.games.game.naughtsandcrosses.BoardNaughtsAndCrosses;
import com.natlowis.games.game.naughtsandcrosses.PieceNaughtsAndCrosses;

/**
 * Creates the GameTree for a Naughts and Crosses Game. This uses Alpha Beta
 * Pruning
 * 
 * @author low101043
 *
 */
public class GameTreeAlphaBetaNaughtsAndCrosses implements GameTree {

	/** The {@link BoardNaughtsAndCrosses} which is represented by this node */
	private BoardNaughtsAndCrosses node;
	/** The utility of this node. -2 if not set otherwise -1,0,1 */
	private int utility = -2;
	/** The best next move to do */
	private BoardNaughtsAndCrosses nextMove = null;

	/**
	 * The constructor
	 * 
	 * @param previousBoard The Parent of the node
	 * @param piece         The {@link PieceNaughtsAndCrosses} to add next
	 * @param alpha         The alpha value
	 * @param beta          The beta value
	 */
	public GameTreeAlphaBetaNaughtsAndCrosses(BoardNaughtsAndCrosses previousBoard, PieceNaughtsAndCrosses piece,
			int alpha, int beta) {
		node = previousBoard;
		if (node.won() == null) {
			utility = 0;
			nextMove = null;
			return;
		} else if (node.won() == Type.CROSS) {
			utility = -1;
			nextMove = null;
			return;
		} else if (node.won() == Type.NAUGHT) {
			utility = 1;
			nextMove = null;
			return;
		}
		int v;
		if (piece.type() == Type.CROSS) {
			v = Integer.MAX_VALUE;
		} else {
			v = Integer.MIN_VALUE;
		}
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

	@Override
	public int returnUtility() {
		return utility;
	}

	/**
	 * Gets the {@link BoardNaughtsAndCrosses} which is represented by this node
	 * 
	 * @return The {@link BoardNaughtsAndCrosses}
	 */
	private BoardNaughtsAndCrosses getBoard() {
		return node;
	}

	@Override
	public Board nextMove() {
		return nextMove;
	}
}

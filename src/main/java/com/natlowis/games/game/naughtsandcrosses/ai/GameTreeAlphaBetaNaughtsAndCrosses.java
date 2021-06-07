package com.natlowis.games.game.naughtsandcrosses.ai;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.interfaces.ai.GameTree;
import com.natlowis.games.game.interfaces.games.Board;
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
	 * @param alpha         The alpha value. The minimum value this state could be.
	 * @param beta          The beta value. The maximum value this state could be.
	 */
	public GameTreeAlphaBetaNaughtsAndCrosses(BoardNaughtsAndCrosses previousBoard, PieceNaughtsAndCrosses piece,
			int alpha, int beta) {
		node = previousBoard;
		createTree(piece, alpha, beta);

	}

	/**
	 * This will create the tree
	 * 
	 * @param piece The {@link PieceNaughtsAndCrosses} to add next
	 * @param alpha The alpha value. The Minimum the state can be
	 * @param beta  The beta value. The Maximum the state can be
	 */
	private void createTree(PieceNaughtsAndCrosses piece, int alpha, int beta) {
		if (terminalNode()) {
			return;
		}

		utility = setUpUtility(piece);

		for (int i = 0; i < node.currentBoard().length; i++) {
			for (int j = 0; j < node.currentBoard()[i].length; j++) {
				if (node.currentBoard()[i][j].type() == Type.EMPTY) {
					BoardNaughtsAndCrosses newBoard = (BoardNaughtsAndCrosses) node.clone();

					newBoard.add(piece, i, j);

					if (piece.type() == Type.CROSS) {

						GameTreeAlphaBetaNaughtsAndCrosses gt = new GameTreeAlphaBetaNaughtsAndCrosses(newBoard,
								new PieceNaughtsAndCrosses(Type.NAUGHT), alpha, beta);

						if (gt.returnUtility() < utility) {
							nextMove = gt.getBoard();
							utility = gt.returnUtility();
						}
						beta = Math.min(beta, utility);

						if (beta <= alpha) {
							i = node.currentBoard().length - 1;
							j = node.currentBoard()[0].length + 5;
						}

					} else {

						GameTreeAlphaBetaNaughtsAndCrosses gt = new GameTreeAlphaBetaNaughtsAndCrosses(newBoard,
								new PieceNaughtsAndCrosses(Type.CROSS), alpha, beta);
						if (gt.returnUtility() > utility) {
							nextMove = gt.getBoard();
							utility = gt.returnUtility();
						}
						alpha = Math.max(alpha, utility);

						if (beta <= alpha) {
							i = node.currentBoard().length - 1;
							j = node.currentBoard()[0].length + 5;
						}
					}
				}
			}

		}
	}

	/**
	 * This will set up the utility
	 * 
	 * @param piece The {@link PieceNaughtsAndCrosses} which is used
	 * @return The initial utility
	 */
	private int setUpUtility(PieceNaughtsAndCrosses piece) {

		if (piece.type() == Type.CROSS) {
			return Integer.MAX_VALUE;
		} else {
			return Integer.MIN_VALUE;
		}
	}

	/**
	 * Whether this is a terminal node
	 * 
	 * @return {@code true} if its a terminal node otherwise {@code false}
	 */
	private boolean terminalNode() {
		if (node.won() == null) {
			utility = 0;
			nextMove = null;
			return true;
		} else if (node.won() == Type.CROSS) {
			utility = -1;
			nextMove = null;
			return true;
		} else if (node.won() == Type.NAUGHT) {
			utility = 1;
			nextMove = null;
			return true;
		}
		return false;
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
	public int returnUtility() {
		return utility;
	}

	@Override
	public Board nextMove() {
		return nextMove;
	}
}

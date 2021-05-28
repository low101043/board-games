package com.natlowis.games.game.connectfour.ai;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.connectfour.BoardConnectFour;
import com.natlowis.games.game.connectfour.PieceConnectFour;
import com.natlowis.games.game.interfaces.ai.GameTree;
import com.natlowis.games.game.interfaces.games.Board;

/**
 * Creates the GameTree for a Connect Four Game. Uses MiniMax with Alpha Beta
 * Pruning
 * 
 * @author low101043
 *
 */
public class GameTreeConnectFour implements GameTree {

	/** The {@link BoardConnectFour} which is represented by this node */
	private BoardConnectFour node;
	/** The utility of this node. -2 if not set otherwise -1,0,1 */
	private int utility = -2;
	/** The best next move to do */
	private BoardConnectFour nextMove;

	/**
	 * The constructor
	 * 
	 * @param previousBoard The State of the node
	 * @param piece         The {@link PieceConnectFour} to add next
	 * @param alpha         The alpha value. The Minimum the state can be
	 * @param beta          The beta value. The Maximum the state can be
	 */
	public GameTreeConnectFour(BoardConnectFour previousBoard, PieceConnectFour piece, int alpha, int beta) {
		node = previousBoard;
		createTree(piece, alpha, beta);
	}

	/**
	 * This will create the tree
	 * 
	 * @param piece The {@link PieceConnectFour} to add next
	 * @param alpha The alpha value. The Minimum the state can be
	 * @param beta  The beta value. The Maximum the state can be
	 */
	private void createTree(PieceConnectFour piece, int alpha, int beta) {
		if (terminalNode()) {
			return;
		}

		utility = setUpUtility(piece);
		for (int i = 0; i < node.currentBoard()[0].length; i++) {

			if (node.currentBoard()[0][i].type() == Type.EMPTY) {
				BoardConnectFour newBoard = (BoardConnectFour) node.clone();
				newBoard.add(piece, i);

				if (piece.type() == Type.CROSS) {

					GameTreeConnectFour gt = new GameTreeConnectFour(newBoard, new PieceConnectFour(Type.NAUGHT), alpha,
							beta);

					if (gt.returnUtility() < utility) {
						nextMove = gt.getBoard();
						utility = gt.returnUtility();
					}
					beta = Math.min(beta, utility);

					if (beta <= alpha) {
						i = node.currentBoard()[0].length;

					}
				} else {
					GameTreeConnectFour gt = new GameTreeConnectFour(newBoard, new PieceConnectFour(Type.CROSS), alpha,
							beta);
					if (gt.returnUtility() > utility) {
						nextMove = gt.getBoard();
						utility = gt.returnUtility();
					}
					alpha = Math.max(alpha, utility);

					if (beta <= alpha) {
						i = node.currentBoard().length;

					}
				}
			}
		}

	}

	/**
	 * This will set up the utility
	 * 
	 * @param piece The {@link PieceConnectFour} which is used
	 * @return The initial utility
	 */
	private int setUpUtility(PieceConnectFour piece) {
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
	 * Gets the {@link BoardConnectFour} which is represented by this node
	 * 
	 * @return The {@link BoardConnectFour}
	 */
	private BoardConnectFour getBoard() {
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

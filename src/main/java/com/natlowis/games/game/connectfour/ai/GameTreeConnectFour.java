package com.natlowis.games.game.connectfour.ai;

import java.util.ArrayList;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.connectfour.BoardConnectFour;
import com.natlowis.games.game.connectfour.PieceConnectFour;
import com.natlowis.games.game.naughtsandcrosses.BoardNaughtsAndCrosses;
import com.natlowis.games.game.naughtsandcrosses.PieceNaughtsAndCrosses;
import com.natlowis.games.game.naughtsandcrosses.ai.GameTreeAlphaBetaNaughtsAndCrosses;

/**
 * Creates the GameTree for a Naughts and Crosses Game
 * 
 * @author low101043
 *
 */
public class GameTreeConnectFour {

	/** The {@link BoardConnectFour} which is represented by this node */
	private BoardConnectFour node;
	/** The utility of this node. -2 if not set otherwise -1,0,1 */
	private int utility = -2;
	/** The best next move to do */
	private BoardConnectFour nextMove;

	/**
	 * The constructor
	 * 
	 * @param previousBoard The Parent of the node
	 * @param piece         The {@link PieceConnectFour} to add next
	 * @param alpha The alpha value
	 * @param beta The beta value
	 */
	public GameTreeConnectFour(BoardConnectFour previousBoard, PieceConnectFour piece, int alpha, int beta) {
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

			for (int i = 0; i < previousBoard.currentBoard()[0].length; i++) {

				if (previousBoard.currentBoard()[0][i].type() == Type.EMPTY) {
					BoardConnectFour newBoard = (BoardConnectFour) previousBoard.clone();
					boolean completed = newBoard.add(piece, i);
					if (!completed) {
						System.out.println("ERROR");
					}

					if (piece.type() == Type.CROSS) {

						GameTreeConnectFour gt = new GameTreeConnectFour(newBoard,
								new PieceConnectFour(Type.NAUGHT), alpha, beta);

						if (gt.returnUtility() < v) {
							nextMove = gt.getBoard();
							v = gt.returnUtility();
						}
						beta = Math.min(beta, v);

						if (beta <= alpha) {
							i = previousBoard.currentBoard()[0].length;
							
						}
					} else {
						GameTreeConnectFour gt = new GameTreeConnectFour(newBoard,
								new PieceConnectFour(Type.CROSS), alpha, beta);
						if (gt.returnUtility() > v) {
							nextMove = gt.getBoard();
							v = gt.returnUtility();
						}
						alpha = Math.max(alpha, v);

						if (beta <= alpha) {
							i = previousBoard.currentBoard().length;
							
						}
					}
				}
			}
		}
		utility = v;

	}

	/**
	 * Sets the new utility value
	 * 
	 * @param newUtility The new value
	 */
	public void setUtility(int newUtility) {
		utility = newUtility;
	}

	/**
	 * Gets the current Utility value
	 * 
	 * @return
	 */
	public int returnUtility() {
		return utility;
	}

	/**
	 * Gets the {@link BoardNaughtsAndCrosses} which is represented by this node
	 * 
	 * @return The {@link BoardNaughtsAndCrosses}
	 */
	public BoardConnectFour getBoard() {
		return node;
	}

	/**
	 * Gets the next move to be done by this Node
	 * 
	 * @return The {@link BoardNaughtsAndCrosses} which is the next move
	 */
	public BoardConnectFour nextMove() {
		return nextMove;
	}
}

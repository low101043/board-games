package com.natlowis.games.game.naughtsandcrosses.ai;

import java.util.ArrayList;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.naughtsandcrosses.BoardNaughtsAndCrosses;
import com.natlowis.games.game.naughtsandcrosses.PieceNaughtsAndCrosses;

/**
 * Creates the GameTree for a Naughts and Crosses Game
 * 
 * @author low101043
 *
 */
public class GameTreeNaughtsAndCrosses {

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
	 */
	public GameTreeNaughtsAndCrosses(BoardNaughtsAndCrosses previousBoard, PieceNaughtsAndCrosses piece, int alpha, int beta) {
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
		}
		else {
			v = Integer.MIN_VALUE;
		}
		if (utility == -2) {
			for (int i = 0; i < previousBoard.currentBoard().length; i++) {
				for (int j = 0; j < previousBoard.currentBoard()[i].length; j++) {
					if (previousBoard.currentBoard()[i][j].type() == Type.EMPTY) {
						BoardNaughtsAndCrosses newBoard = (BoardNaughtsAndCrosses) previousBoard.clone();

						newBoard.add(piece, i, j);

						if (piece.type() == Type.CROSS) {
							
							
							GameTreeNaughtsAndCrosses gt = new GameTreeNaughtsAndCrosses(newBoard, new PieceNaughtsAndCrosses(Type.NAUGHT), alpha, beta); 
							
										
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

							GameTreeNaughtsAndCrosses gt = new GameTreeNaughtsAndCrosses(newBoard, new PieceNaughtsAndCrosses(Type.CROSS),alpha, beta); 
							if (gt.returnUtility() > v) {
								nextMove = gt.getBoard();
								v = gt.returnUtility();
							}
							alpha = Math.max(alpha, v);
							
							if (beta <= alpha) {
								i = previousBoard.currentBoard().length -1;
								j = previousBoard.currentBoard()[0].length + 5;
							}
						}
					}
				}
			}
			utility = v;
			
		}

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
	public BoardNaughtsAndCrosses getBoard() {
		return node;
	}

	/**
	 * Gets the next move to be done by this Node
	 * 
	 * @return The {@link BoardNaughtsAndCrosses} which is the next move
	 */
	public BoardNaughtsAndCrosses nextMove() {
		return nextMove;
	}
}

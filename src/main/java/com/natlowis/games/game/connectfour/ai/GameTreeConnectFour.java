package com.natlowis.games.game.connectfour.ai;

import java.util.ArrayList;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.connectfour.BoardConnectFour;
import com.natlowis.games.game.connectfour.PieceConnectFour;
import com.natlowis.games.game.naughtsandcrosses.BoardNaughtsAndCrosses;

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
	/** The children of this node */
	private ArrayList<GameTreeConnectFour> children = new ArrayList<GameTreeConnectFour>();
	/** The best next move to do */
	private BoardConnectFour nextMove;

	/**
	 * The constructor
	 * 
	 * @param previousBoard The Parent of the node
	 * @param piece         The {@link PieceConnectFour} to add next
	 */
	public GameTreeConnectFour(BoardConnectFour previousBoard, PieceConnectFour piece) {
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
		if (utility == -2) {

			for (int i = 0; i < previousBoard.currentBoard()[0].length; i++) {

				if (previousBoard.currentBoard()[0][i].type() == Type.EMPTY) {
					BoardConnectFour newBoard = (BoardConnectFour) previousBoard.clone();
					boolean completed = newBoard.add(piece, i);
					if (!completed) {
						System.out.println("ERROR");
					}

					if (piece.type() == Type.CROSS) {

						children.add(new GameTreeConnectFour(newBoard, new PieceConnectFour(Type.NAUGHT)));
					} else {

						children.add(new GameTreeConnectFour(newBoard, new PieceConnectFour(Type.CROSS)));
					}
				}
			}
		}
		int v;
		if (piece.type() == Type.CROSS) {
			v = Integer.MAX_VALUE;
		} else {
			v = Integer.MIN_VALUE;
		}
		for (GameTreeConnectFour child : children) {

			if (piece.type() == Type.CROSS) {

				if (child.returnUtility() < v) {
					v = child.returnUtility();
					nextMove = child.getBoard();
				}

			} else {

				if (child.returnUtility() > v) {
					v = child.returnUtility();
					nextMove = child.getBoard();
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

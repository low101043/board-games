package com.natlowis.naughtsandcrosses.ai;

import java.util.ArrayList;

import com.natlowis.naughtsandcrosses.BoardNaughtsAndCrosses;
import com.natlowis.naughtsandcrosses.PieceNaughtsAndCrosses;

/**
 * Creates the GameTree for a Naughts and Crosses Game
 * @author low101043
 *
 */
public class GameTreeNaughtsAndCrosses {

	/** The {@link BoardNaughtsAndCrosses} which is represented by this node */
	private BoardNaughtsAndCrosses node;
	/** The utility of this node.  -2 if not set otherwise -1,0,1 */
	private int utility = -2;
	/** The children of this node */
	private ArrayList<GameTreeNaughtsAndCrosses> children = new ArrayList<GameTreeNaughtsAndCrosses>();
	/** The best next move to do */
	private BoardNaughtsAndCrosses nextMove;

	public GameTreeNaughtsAndCrosses(BoardNaughtsAndCrosses previousBoard, PieceNaughtsAndCrosses piece) {
		node = previousBoard;
		if (node.won() == null) {
			utility = 0;
			nextMove = null;
			return;
		} else if (node.won().type().equals("Cross")) {
			utility = -1;
			nextMove = null;
			return;
		} else if (node.won().type().equals("Naught")) {
			utility = 1;
			nextMove = null;
			return;
		}
		if (utility == -2) {
			for (int i = 0; i < previousBoard.currentBoard().length; i++) {
				for (int j = 0; j < previousBoard.currentBoard()[i].length; j++) {
					if (previousBoard.currentBoard()[i][j].type() == "Blank") {
						BoardNaughtsAndCrosses newBoard = (BoardNaughtsAndCrosses) previousBoard.clone();
						try {
							newBoard.add(piece, i, j);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (piece.type().equals("Cross")) {
							if (newBoard == null) {
								System.out.println("ERROR");
							}
							children.add(new GameTreeNaughtsAndCrosses(newBoard,
									new PieceNaughtsAndCrosses("Naught", false)));
						} else {
							if (newBoard == null) {
								System.out.println("ERROR");
							}
							children.add(new GameTreeNaughtsAndCrosses(newBoard,
									new PieceNaughtsAndCrosses("Cross", false)));
						}
					}
				}
			}
			int v;
			if (piece.type().equals("Cross")) {
				v = Integer.MAX_VALUE;
			} else {
				v = Integer.MIN_VALUE;
			}
			for (GameTreeNaughtsAndCrosses child : children) {

				if (piece.type().equals("Cross")) {

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

	}

	/**
	 * Sets the new utility value
	 * @param newUtility The new value
	 */
	public void setUtility(int newUtility) {
		utility = newUtility;
	}

	/**
	 * Gets the current Utility value
	 * @return
	 */
	public int returnUtility() {
		return utility;
	}

	/**
	 * Gets the {@link BoardNaughtsAndCrosses} which is represented by this node
	 * @return The {@link BoardNaughtsAndCrosses}
	 */
	public BoardNaughtsAndCrosses getBoard() {
		return node;
	}

	/**
	 * Gets the next move to be done by this Node
	 * @return The {@link BoardNaughtsAndCrosses} which is the next move
	 */
	public BoardNaughtsAndCrosses nextMove() {
		return nextMove;
	}
}

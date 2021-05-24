package com.natlowis.games.game.naughtsandcrosses.ai;

import java.util.ArrayList;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.interfaces.games.ai.GameTree;
import com.natlowis.games.game.naughtsandcrosses.BoardNaughtsAndCrosses;
import com.natlowis.games.game.naughtsandcrosses.PieceNaughtsAndCrosses;

/**
 * Creates the GameTree for a Naughts and Crosses Game
 * 
 * @author low101043
 *
 */
public class GameTreeMiniMaxNaughtsAndCrosses implements GameTree {

	/** The {@link BoardNaughtsAndCrosses} which is represented by this node */
	private BoardNaughtsAndCrosses node;
	/** The utility of this node. -2 if not set otherwise -1,0,1 */
	private int utility = -2;
	/** The children of this node */
	private ArrayList<GameTreeMiniMaxNaughtsAndCrosses> children = new ArrayList<GameTreeMiniMaxNaughtsAndCrosses>();
	/** The best next move to do */
	private BoardNaughtsAndCrosses nextMove;

	/**
	 * The constructor
	 * 
	 * @param previousBoard The Parent of the node
	 * @param piece         The {@link PieceNaughtsAndCrosses} to add next
	 */
	public GameTreeMiniMaxNaughtsAndCrosses(BoardNaughtsAndCrosses previousBoard, PieceNaughtsAndCrosses piece) {
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
			for (int i = 0; i < previousBoard.currentBoard().length; i++) {
				for (int j = 0; j < previousBoard.currentBoard()[i].length; j++) {
					if (previousBoard.currentBoard()[i][j].type() == Type.EMPTY) {
						BoardNaughtsAndCrosses newBoard = (BoardNaughtsAndCrosses) previousBoard.clone();

						newBoard.add(piece, i, j);

						if (piece.type() == Type.CROSS) {

							children.add(new GameTreeMiniMaxNaughtsAndCrosses(newBoard,
									new PieceNaughtsAndCrosses(Type.NAUGHT)));
						} else {

							children.add(new GameTreeMiniMaxNaughtsAndCrosses(newBoard,
									new PieceNaughtsAndCrosses(Type.CROSS)));
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
			for (GameTreeMiniMaxNaughtsAndCrosses child : children) {

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

	}

	@Override
	public void setUtility(int newUtility) {
		utility = newUtility;
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
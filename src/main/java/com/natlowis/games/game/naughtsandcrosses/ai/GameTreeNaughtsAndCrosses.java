package com.natlowis.games.game.naughtsandcrosses.ai;

import java.util.ArrayList;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.interfaces.ai.GameTree;
import com.natlowis.games.game.interfaces.games.Board;
import com.natlowis.games.game.naughtsandcrosses.BoardNaughtsAndCrosses;
import com.natlowis.games.game.naughtsandcrosses.PieceNaughtsAndCrosses;

public abstract class GameTreeNaughtsAndCrosses implements GameTree {

	/** The {@link BoardNaughtsAndCrosses} which is represented by this node */
	protected BoardNaughtsAndCrosses node;
	/** The utility of this node. -2 if not set otherwise -1,0,1 */
	protected int utility = -2;
	/** The children of this node */
	protected ArrayList<GameTreeMiniMaxNaughtsAndCrosses> children = new ArrayList<GameTreeMiniMaxNaughtsAndCrosses>();
	/** The best next move to do */
	protected BoardNaughtsAndCrosses nextMove;

	protected boolean terminalNode() {
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

	protected int setUp(PieceNaughtsAndCrosses piece) {
		if (piece.type() == Type.CROSS) {
			return Integer.MAX_VALUE;
		} else {
			return Integer.MIN_VALUE;
		}
	}

	/**
	 * Gets the {@link BoardNaughtsAndCrosses} which is represented by this node
	 * 
	 * @return The {@link BoardNaughtsAndCrosses}
	 */
	protected BoardNaughtsAndCrosses getBoard() {
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

package com.natlowis.games.game.interfaces.ai;

import com.natlowis.games.game.interfaces.games.Board;

/**
 * States the methods needed for a {@code GameTree}
 * 
 * @author low101043
 *
 */
public interface GameTree {

	/**
	 * Gets the current Utility value
	 * 
	 * @return The Utility of the state
	 */
	public int returnUtility();

	/**
	 * Gets the next move to be done by this Node
	 * 
	 * @return The {@link Board} which is the next move
	 */
	public Board nextMove();
}

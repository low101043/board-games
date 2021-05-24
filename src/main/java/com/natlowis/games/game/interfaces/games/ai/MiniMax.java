package com.natlowis.games.game.interfaces.games.ai;

/**
 * This will specify how to use the MiniMax Classes for AI
 * 
 * @author low101043
 *
 */
public interface MiniMax {

	/**
	 * Returns the coordinates of the next move
	 * 
	 * @return A 2D array which contains the next move to do
	 */
	public int[] coordinates();

}

package com.natlowis.games.game.interfaces.games.ai;

public interface GameTree {

	/**
	 * Sets the new utility value
	 * 
	 * @param newUtility The new value
	 */
	public void setUtility(int newUtility);
	
	/**
	 * Gets the current Utility value
	 * 
	 * @return The Utility of the state
	 */
	public int returnUtility();
	
}

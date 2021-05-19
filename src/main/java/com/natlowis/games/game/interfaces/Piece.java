package com.natlowis.games.game.interfaces;

import com.natlowis.games.game.Type;

/**
 * The {@link Piece}'s used on the {@link Board}
 * 
 * @author low101043
 *
 */
public interface Piece {

	/**
	 * The type of the {@link Piece}
	 * 
	 * @return The {@link Type} format of it
	 */
	public Type type();

}

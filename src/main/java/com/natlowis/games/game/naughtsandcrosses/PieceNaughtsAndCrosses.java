package com.natlowis.games.game.naughtsandcrosses;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.interfaces.games.Piece;

/**
 * This creates the {@link Piece} for Naughts and Crosses
 * 
 * @author low101043
 *
 */
public class PieceNaughtsAndCrosses implements Piece {

	/** The {@code Type} of this {@link Piece} */
	private Type type;

	/**
	 * A Constructor
	 * 
	 * @param typeArg The {@link Type} of this {@link Piece}
	 */
	public PieceNaughtsAndCrosses(Type typeArg) {

		type = typeArg;

	}

	@Override
	public Type type() {
		return type;
	}

}

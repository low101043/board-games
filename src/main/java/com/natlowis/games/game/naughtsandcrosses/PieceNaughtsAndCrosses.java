package com.natlowis.games.game.naughtsandcrosses;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.interfaces.Piece;

/**
 * This creates the {@link Piece} for Naughts and Crosses
 * 
 * @author low101043
 *
 */
public class PieceNaughtsAndCrosses implements Piece {

	/** The {@code Type} of this {@link Piece} */
	private Type type;
	/** Whether this is Player One */
	private Boolean playerOne;

	/**
	 * A Constructor
	 * 
	 * @param typeStr         The type of the piece
	 * @param playerOneChoice {@code true} if player one else {@code false}
	 */
	public PieceNaughtsAndCrosses(Type typeArg, boolean playerOneChoice) {

		type = typeArg;
		if (typeArg == Type.EMPTY) {

			playerOne = null;

		} else {
			playerOne = playerOneChoice;
		}
	}

	@Override
	public Type type() {
		return type;
	}

	@Override
	public boolean playerOne() {

		if (playerOne == true) {
			return true;
		} else {
			return false;
		}
	}

}

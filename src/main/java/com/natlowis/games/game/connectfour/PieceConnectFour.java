package com.natlowis.games.game.connectfour;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.interfaces.games.Piece;

/**
 * This will contain all the information for a {@link Piece} used by Connect
 * Four
 * 
 * @author low101043
 *
 */
public class PieceConnectFour implements Piece {

	/** The {@link Type} of the {@link Piece} */
	private Type type;

	/**
	 * A Constructor which create a Piece
	 * 
	 * @param typeStr         The type it will be
	 * @param playerOneChoice Whether player one uses it
	 */
	public PieceConnectFour(Type typeArg) {

		type = typeArg;
	}

	@Override
	public Type type() {

		return type;
	}

}

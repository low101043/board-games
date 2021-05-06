package com.natlowis.games.game.connectfour;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.interfaces.Piece;

/**
 * This will contain all the information for a {@link Piece} used by Connect Four  
 * @author low101043
 *
 */
public class PieceConnectFour implements Piece {


	/** The {@link Type} of the {@link Piece} */
	private Type type;
	/** Whether this is player One */
	private Boolean playerOne;

	/**
	 * A Constructor which create a Piece
	 * @param typeStr The type it will be
	 * @param playerOneChoice Whether player one uses it 
	 */
	public PieceConnectFour(Type typeArg, boolean playerOneChoice) {

		type = typeArg;
		if (type == Type.EMPTY) {
			playerOne = null;
		}
		else {
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

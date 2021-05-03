package com.natlowis.connectfour;

import com.natlowis.interfaces.Piece;

/**
 * This will contain all the information for a {@link Piece} used by Connect Four  
 * @author low101043
 *
 */
public class PieceConnectFour implements Piece {

	/**
	 * The type of a {@link Piece}
	 * @author low101043
	 *
	 */
	private enum Type {
		RED, YELLOW, EMPTY
	}

	/** The {@link Type} of the {@link Piece} */
	private Type type;
	/** Whether this is player One */
	private Boolean playerOne;

	/**
	 * A Constructor which create a Piece
	 * @param typeStr The type it will be
	 * @param playerOneChoice Whether player one uses it 
	 */
	public PieceConnectFour(String typeStr, boolean playerOneChoice) {

		if (typeStr == "Empty") {
			type = Type.EMPTY;
			playerOne = null;

		} else if (typeStr == "Yellow") {
			type = Type.YELLOW;
			if (playerOneChoice == true) {
				playerOne = true;
			} else {
				playerOne = false;
			}
		} else {
			type = Type.RED;
			if (playerOneChoice == true) {
				playerOne = true;
			} else {
				playerOne = false;
			}
		}
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		if (type == Type.YELLOW) {
			return "Yellow";
		} else if (type == Type.RED) {
			return "Red";
		} else {
			return "Empty";
		}
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

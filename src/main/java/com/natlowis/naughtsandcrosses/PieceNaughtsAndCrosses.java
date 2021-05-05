package com.natlowis.naughtsandcrosses;

import com.natlowis.interfaces.Piece;

/**
 * This creates the {@link Piece} for Naughts and Crosses
 * @author low101043
 *
 */
public class PieceNaughtsAndCrosses implements Piece {

	/**
	 * The different kind of pieces you can have
	 * @author low101043
	 *
	 */
	private enum Type {
		BLANK, NAUGHT, CROSS
	}

	/** The {@code Type} of this {@link Piece} */ 
	private Type type;
	/** Whether this is Player One */
	private Boolean playerOne;

	/** 
	 * A Constructor 
	 * @param typeStr The type of the piece
	 * @param playerOneChoice {@code true} if player one else {@code false}
	 */
	public PieceNaughtsAndCrosses(String typeStr, boolean playerOneChoice) {

		if (typeStr == "Blank") {
			type = Type.BLANK;
			playerOne = null;

		} else if (typeStr == "Cross") {
			type = Type.CROSS;
			if (playerOneChoice == true) {
				playerOne = true;
			} else {
				playerOne = false;
			}
		} else {
			type = Type.NAUGHT;
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
		if (type == Type.NAUGHT) {
			return "Naught";
		} else if (type == Type.CROSS) {
			return "Cross";
		} else {
			return "Blank";
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

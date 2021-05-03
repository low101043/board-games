package com.natlowis.naughtsandcrosses;

import com.natlowis.interfaces.Piece;

public class PieceNaughtsAndCrosses implements Piece {

	private enum Type {
		BLANK, NAUGHT, CROSS
	}

	private Type type;
	private Boolean playerOne;

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

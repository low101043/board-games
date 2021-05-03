package com.natlowis.connectfour;

import com.natlowis.interfaces.Piece;

public class PieceConnectFour implements Piece {

	private enum Type {
		RED, YELLOW, EMPTY
	}

	private Type type;
	private Boolean playerOne;

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

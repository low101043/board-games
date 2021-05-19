package com.natlowis.games.game.connectfour.ai;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.connectfour.BoardConnectFour;
import com.natlowis.games.game.connectfour.PieceConnectFour;
import com.natlowis.games.game.interfaces.Board;
import com.natlowis.games.game.interfaces.Piece;

/**
 * Will implement the MinMax algorithm for Naughts and Crosses
 * 
 * @author low101043
 *
 */
public class MinMax {

	/** The next move to do position in the i'th position */
	private int iNext;

	/**
	 * The Constructor
	 * 
	 * @param board The {@link Board} to start from
	 * @param piece The {@link Piece} whose move it is
	 */
	public MinMax(Board board, Piece piece) {
		PieceConnectFour pieceToUse;
		if (piece.type() == Type.CROSS) {
			pieceToUse = new PieceConnectFour(Type.CROSS, false);
		} else {
			pieceToUse = new PieceConnectFour(Type.NAUGHT, false);
		}
		BoardConnectFour boardToUse = new BoardConnectFour(board.currentBoard(), pieceToUse);

		GameTreeConnectFour gameTree = new GameTreeConnectFour(boardToUse, pieceToUse);

		BoardConnectFour nextMove = gameTree.nextMove();

		for (int i = 0; i < boardToUse.currentBoard().length; i++) {

			if (boardToUse.currentBoard()[i][boardToUse.currentBoard()[i].length - 1].type() == Type.EMPTY
					&& nextMove.currentBoard()[i][boardToUse.currentBoard()[i].length - 1].type() != Type.EMPTY) {
				iNext = i;
				return;
			}

		}

	}

	/**
	 * Returns the coordinates of the next move
	 * 
	 * @return A 2D array which contains the next move to do
	 */
	public int[] coordinates() {
		int[] arrayToReturn = { iNext };
		return arrayToReturn;
	}
}

package com.natlowis.naughtsandcrosses.ai;

import com.natlowis.interfaces.Board;
import com.natlowis.interfaces.Piece;
import com.natlowis.naughtsandcrosses.BoardNaughtsAndCrosses;
import com.natlowis.naughtsandcrosses.PieceNaughtsAndCrosses;

/**
 * Will implement the MinMax algorithm for Naughts and Crosses
 * @author low101043
 *
 */
public class MinMax {

	/** The next move to do position in the i'th position */
	private int iNext;
	/** The next move to do position in the j'th position */
	private int jNext;

	/**
	 * The Constructor
	 * @param board The {@link Board} to start from
	 * @param piece The {@link Piece} whose move it is
	 */
	public MinMax(Board board, Piece piece) {
		PieceNaughtsAndCrosses pieceToUse;
		if (piece.type().equals("Cross")) {
			pieceToUse = new PieceNaughtsAndCrosses("Cross", false);
		} else {
			pieceToUse = new PieceNaughtsAndCrosses("Naught", false);
		}
		BoardNaughtsAndCrosses boardToUse = new BoardNaughtsAndCrosses(board.currentBoard(), pieceToUse);

		GameTreeNaughtsAndCrosses gameTree = new GameTreeNaughtsAndCrosses(boardToUse, pieceToUse);

		BoardNaughtsAndCrosses nextMove = gameTree.nextMove();

		for (int i = 0; i < boardToUse.currentBoard().length; i++) {
			for (int j = 0; j < boardToUse.currentBoard()[i].length; j++) {
				if (boardToUse.currentBoard()[i][j].type().equals("Blank")
						&& !nextMove.currentBoard()[i][j].type().equals("Blank")) {
					iNext = i;
					jNext = j;
					return;
				}
			}
		}

	}

	/**
	 * Returns the coordinates of the next move
	 * @return A 2D array which contains the next move to do
	 */
	public int[] coordinates() {
		int[] arrayToReturn = { iNext, jNext };
		return arrayToReturn;
	}
}

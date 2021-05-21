package com.natlowis.games.game.naughtsandcrosses.ai;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.interfaces.Board;
import com.natlowis.games.game.interfaces.Piece;
import com.natlowis.games.game.naughtsandcrosses.BoardNaughtsAndCrosses;
import com.natlowis.games.game.naughtsandcrosses.PieceNaughtsAndCrosses;

/**
 * Will implement the MinMax algorithm for Naughts and Crosses
 * 
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
	 * 
	 * @param board The {@link Board} to start from
	 * @param piece The {@link Piece} whose move it is
	 */
	public MinMax(Board board, Piece piece) {
		PieceNaughtsAndCrosses pieceToUse;
		if (piece.type() == Type.CROSS) {
			pieceToUse = new PieceNaughtsAndCrosses(Type.CROSS);
		} else {
			pieceToUse = new PieceNaughtsAndCrosses(Type.NAUGHT);
		}
		BoardNaughtsAndCrosses boardToUse = new BoardNaughtsAndCrosses(board.currentBoard(), pieceToUse);

		GameTreeNaughtsAndCrosses gameTree = new GameTreeNaughtsAndCrosses(boardToUse, pieceToUse, null, null);

		BoardNaughtsAndCrosses nextMove = gameTree.nextMove();

		for (int i = 0; i < boardToUse.currentBoard().length; i++) {
			for (int j = 0; j < boardToUse.currentBoard()[i].length; j++) {
				if (boardToUse.currentBoard()[i][j].type() == Type.EMPTY
						&& nextMove.currentBoard()[i][j].type() != Type.EMPTY) {
					iNext = i;
					jNext = j;
					return;
				}
			}
		}

	}

	/**
	 * Returns the coordinates of the next move
	 * 
	 * @return A 2D array which contains the next move to do
	 */
	public int[] coordinates() {
		int[] arrayToReturn = { iNext, jNext };
		return arrayToReturn;
	}
}

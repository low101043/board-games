package com.natlowis.naughtsandcrosses.ai;

import com.natlowis.interfaces.Board;
import com.natlowis.interfaces.Piece;
import com.natlowis.naughtsandcrosses.BoardNaughtsAndCrosses;
import com.natlowis.naughtsandcrosses.PieceNaughtsAndCrosses;

public class MinMax {

	private int iNext;
	private int jNext;

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

	public int[] coordinates() {
		int[] arrayToReturn = { iNext, jNext };
		return arrayToReturn;
	}
}

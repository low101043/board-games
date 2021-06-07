package com.natlowis.games.game.naughtsandcrosses.ai;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.interfaces.ai.GameTree;
import com.natlowis.games.game.interfaces.ai.MiniMax;
import com.natlowis.games.game.interfaces.games.Board;
import com.natlowis.games.game.interfaces.games.Piece;
import com.natlowis.games.game.naughtsandcrosses.BoardNaughtsAndCrosses;
import com.natlowis.games.game.naughtsandcrosses.PieceNaughtsAndCrosses;

/**
 * Will implement the MinMax algorithm for Naughts and Crosses
 * 
 * @author low101043
 *
 */
public class MiniMaxNaughtsAndCrosses implements MiniMax {

	/** The next move to do position in the i'th position */
	private int iNext;
	/** The next move to do position in the j'th position */
	private int jNext;

	/**
	 * The Constructor
	 * 
	 * @param board The {@link Board} to start from
	 * @param piece The {@link Piece} whose move it is
	 * @param type  The {@link AiType} to use
	 */
	public MiniMaxNaughtsAndCrosses(Board board, Piece piece, AiType type) {

		PieceNaughtsAndCrosses pieceToUse;
		if (piece.type() == Type.CROSS) {
			pieceToUse = new PieceNaughtsAndCrosses(Type.CROSS);
		} else {
			pieceToUse = new PieceNaughtsAndCrosses(Type.NAUGHT);
		}

		BoardNaughtsAndCrosses boardToUse = new BoardNaughtsAndCrosses(board.currentBoard());

		GameTree gameTree;
		if (type == AiType.ALPHA_BETA) {
			gameTree = new GameTreeAlphaBetaNaughtsAndCrosses(boardToUse, pieceToUse, Integer.MIN_VALUE,
					Integer.MAX_VALUE);
		} else {
			gameTree = new GameTreeMiniMaxNaughtsAndCrosses(boardToUse, pieceToUse);
		}

		BoardNaughtsAndCrosses nextMove = (BoardNaughtsAndCrosses) gameTree.nextMove();

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

	@Override
	public int[] coordinates() {
		int[] arrayToReturn = { iNext, jNext };
		return arrayToReturn;
	}
}

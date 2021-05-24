package com.natlowis.games.game.connectfour.ai;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.connectfour.BoardConnectFour;
import com.natlowis.games.game.connectfour.PieceConnectFour;
import com.natlowis.games.game.interfaces.games.Board;
import com.natlowis.games.game.interfaces.games.Piece;
import com.natlowis.games.game.interfaces.games.ai.MiniMax;

/**
 * Will implement the MinMax algorithm for Connect Four
 * 
 * @author low101043
 *
 */
public class MiniMaxConnectFour implements MiniMax {

	/** The next move to do position in the i'th position */
	private int iNext;

	/**
	 * The Constructor
	 * 
	 * @param board The {@link Board} to start from
	 * @param piece The {@link Piece} whose move it is
	 */
	public MiniMaxConnectFour(Board board, Piece piece) {
		PieceConnectFour pieceToUse;
		if (piece.type() == Type.CROSS) {
			pieceToUse = new PieceConnectFour(Type.CROSS);
		} else {
			pieceToUse = new PieceConnectFour(Type.NAUGHT);
		}
		BoardConnectFour boardToUse = new BoardConnectFour(board.currentBoard(), pieceToUse);

		GameTreeConnectFour gameTree = new GameTreeConnectFour(boardToUse, pieceToUse, Integer.MIN_VALUE,
				Integer.MAX_VALUE);

		BoardConnectFour nextMove = (BoardConnectFour) gameTree.nextMove();

		for (int i = 0; i < boardToUse.currentBoard().length; i++) {

			if (boardToUse.currentBoard()[i][boardToUse.currentBoard()[i].length - 1].type() == Type.EMPTY
					&& nextMove.currentBoard()[i][boardToUse.currentBoard()[i].length - 1].type() != Type.EMPTY) {
				iNext = i;
				return;
			}

		}

	}

	@Override
	public int[] coordinates() {
		int[] arrayToReturn = { iNext };
		return arrayToReturn;
	}
}

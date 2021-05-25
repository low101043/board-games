package com.natlowis.games.game.naughtsandcrosses.ai;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.naughtsandcrosses.BoardNaughtsAndCrosses;
import com.natlowis.games.game.naughtsandcrosses.PieceNaughtsAndCrosses;

/**
 * Creates the GameTree for a Naughts and Crosses Game
 * 
 * @author low101043
 *
 */
public class GameTreeMiniMaxNaughtsAndCrosses extends GameTreeNaughtsAndCrosses {

	/**
	 * The constructor
	 * 
	 * @param previousBoard The Parent of the node
	 * @param piece         The {@link PieceNaughtsAndCrosses} to add next
	 */
	public GameTreeMiniMaxNaughtsAndCrosses(BoardNaughtsAndCrosses previousBoard, PieceNaughtsAndCrosses piece) {
		node = previousBoard;

		if (terminalNode()) {
			return;
		}

		if (utility == -2) {
			for (int i = 0; i < previousBoard.currentBoard().length; i++) {
				for (int j = 0; j < previousBoard.currentBoard()[i].length; j++) {
					if (previousBoard.currentBoard()[i][j].type() == Type.EMPTY) {
						BoardNaughtsAndCrosses newBoard = (BoardNaughtsAndCrosses) previousBoard.clone();

						newBoard.add(piece, i, j);

						if (piece.type() == Type.CROSS) {

							children.add(new GameTreeMiniMaxNaughtsAndCrosses(newBoard,
									new PieceNaughtsAndCrosses(Type.NAUGHT)));
						} else {

							children.add(new GameTreeMiniMaxNaughtsAndCrosses(newBoard,
									new PieceNaughtsAndCrosses(Type.CROSS)));
						}
					}
				}
			}
			int v = setUp(piece);

			for (GameTreeMiniMaxNaughtsAndCrosses child : children) {

				if (piece.type() == Type.CROSS) {

					if (child.returnUtility() < v) {
						v = child.returnUtility();
						nextMove = child.getBoard();
					}

				} else {

					if (child.returnUtility() > v) {
						v = child.returnUtility();
						nextMove = child.getBoard();
					}
				}
			}
			utility = v;
		}

	}
}
package com.natlowis.games.game.naughtsandcrosses;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.interfaces.games.Board;
import com.natlowis.games.game.interfaces.games.Piece;

/**
 * This will hold the game used in Naughts and Crosses
 * 
 * @author low101043
 *
 */
public class BoardNaughtsAndCrosses implements Board {

	/** This is the board which contains the {@link Piece}s */
	private Piece[][] board;

	/** The width of the 2D array */
	private int WIDTH = 3;
	/** The height of the 2D array */
	private int HEIGHT = 3;

	/**
	 * A constructor which created the board
	 */
	public BoardNaughtsAndCrosses() {
		board = new Piece[HEIGHT][WIDTH];

		Piece blank = new PieceNaughtsAndCrosses(Type.EMPTY);

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				board[i][j] = blank;
			}
		}
	}

	/**
	 * A constructor which is used to create a {@code GameTree}
	 * 
	 * @param boardCopy A copy of the previous board
	 */
	public BoardNaughtsAndCrosses(Piece[][] boardCopy) {
		Piece blank = new PieceNaughtsAndCrosses(Type.EMPTY);
		Piece cross = new PieceNaughtsAndCrosses(Type.CROSS);
		Piece naughts = new PieceNaughtsAndCrosses(Type.NAUGHT);
		board = new Piece[HEIGHT][WIDTH];
		for (int i = 0; i < boardCopy.length; i++) {
			for (int j = 0; j < boardCopy[i].length; j++) {
				Type type = boardCopy[i][j].type();
				if (type == Type.EMPTY) {
					board[i][j] = blank;

				} else if (type == Type.CROSS) {
					board[i][j] = cross;
				} else {
					board[i][j] = naughts;
				}
			}

		}
	}

	@Override
	public Type won() {

		for (int i = 0; i < HEIGHT; i++) {
			Piece firstPiece = board[i][0];
			boolean allSame = true;
			for (int j = 1; j < WIDTH; j++) {
				// System.out.println(board[i][j].type());
				if (board[i][j] != firstPiece) {
					allSame = false;
				}
			}
			// System.out.println(allSame);
			if (allSame == true && firstPiece.type() != Type.EMPTY) {
				return firstPiece.type();
			}
		}

		for (int j = 0; j < WIDTH; j++) {
			Piece firstPiece = board[0][j];
			boolean allSame = true;
			for (int i = 1; i < HEIGHT; i++) {
				if (board[i][j] != firstPiece) {
					allSame = false;
				}
			}
			if (allSame == true && firstPiece.type() != Type.EMPTY) {
				return firstPiece.type();
			}
		}

		Piece middle = board[1][1];
		Piece topLeft = board[0][0];
		Piece topRight = board[0][2];
		Piece bottomLeft = board[2][0];
		Piece bottomRight = board[2][2];

		if (middle.type() != Type.EMPTY && middle.type() == topLeft.type() && middle.type() == bottomRight.type()) {
			return middle.type();
		}

		if (middle.type() != Type.EMPTY && middle.type() == topRight.type() && middle.type() == bottomLeft.type()) {
			return middle.type();
		}

		boolean allFull = true;
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (board[i][j].type() == Type.EMPTY) {
					allFull = false;
				}
			}
		}

		if (allFull) {
			return null;
		} else {
			return Type.EMPTY;
		}

	}

	@Override
	public boolean add(Piece input, int i, int j) {

		if (i < 0 || i >= HEIGHT) {
			return false;
		} else if (j < 0 || j >= WIDTH) {
			return false;
		}
		if (board[i][j].type() == Type.EMPTY) {
			board[i][j] = input;
			return true;
		}
		return false;

	}

	@Override
	public void remove(Piece input, int i, int j) {
		System.out.println("ERROR");
		;

	}

	@Override
	public Piece[][] currentBoard() {

		return board;
	}

	@Override
	public Object clone() {

		try {
			return (BoardNaughtsAndCrosses) super.clone();
		} catch (CloneNotSupportedException e) {
			return new BoardNaughtsAndCrosses(board);
		}
	}

}

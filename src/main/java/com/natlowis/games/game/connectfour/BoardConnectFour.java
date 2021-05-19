package com.natlowis.games.game.connectfour;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.interfaces.Board;
import com.natlowis.games.game.interfaces.Piece;
import com.natlowis.games.game.naughtsandcrosses.PieceNaughtsAndCrosses;

/**
 * This contains all the information of a {@link Board} for Connect Four
 * 
 * @author low101043
 *
 */
public class BoardConnectFour implements Board {

	/** Will contain all the {@link Piece} */
	private Piece[][] board;
	/** The width of the board */
	private int WIDTH = 7;
	/** The Height of the board */
	private int HEIGHT = 6;

	/**
	 * A Constructor which created the board and an empty {@link Piece}
	 */
	public BoardConnectFour() {
		board = new Piece[HEIGHT][WIDTH];

		Piece empty = new PieceConnectFour(Type.EMPTY, false);

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				board[i][j] = empty;
			}
		}

	}

	/**
	 * A constructor which is used to create a {@code GameTree}
	 * 
	 * @param boardCopy A copy of the previous board
	 * @param blankCopy A copy of the blank {@link Piece}
	 */
	public BoardConnectFour(Piece[][] boardCopy, Piece blankCopy) {
		Piece blank = new PieceNaughtsAndCrosses(Type.EMPTY, false);
		Piece cross = new PieceNaughtsAndCrosses(Type.CROSS, false);
		Piece naughts = new PieceNaughtsAndCrosses(Type.NAUGHT, false);
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

		// Horizontal
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH - 3; j++) {
				Piece one = board[i][j];
				if (one.type() == Type.EMPTY) {
					;
				} else {
					boolean allSame = true;
					int k = 1;
					while (allSame && k < 4) {
						Piece next = board[i][j + k];
						if (one.type() != next.type()) {
							allSame = false;
						}
						k++;
					}
					if (allSame) {
						return one.type();
					}
				}
			}
		}

		// Vertical
		for (int j = 0; j < WIDTH; j++) {
			for (int i = 0; i < HEIGHT - 3; i++) {
				Piece one = board[i][j];
				if (one.type() == Type.EMPTY) {
					;
				} else {

					boolean allSame = true;
					int k = 1;
					while (allSame && k < 4) {
						Piece next = board[i + k][j];
						if (one.type() != next.type()) {
							allSame = false;
						}
						k++;
					}
					if (allSame) {
						return one.type();
					}
				}
			}
		}

		// Diagonal Left Right

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (i + 3 >= HEIGHT || j + 3 >= WIDTH) {
					;
				} else {
					Piece one = board[i][j];
					if (one.type() == Type.EMPTY) {
						;
					} else {
						boolean allSame = true;
						int k = 1;
						while (allSame && k < 4) {
							Piece next = board[i + k][j + k];
							if (one != next) {
								allSame = false;
							}
							k++;
						}
						if (allSame) {
							return one.type();
						}
					}
				}
			}
		}

		// Diagonal Right to Left

		for (int i = 0; i < HEIGHT; i++) {
			// System.out.println(i);
			for (int j = WIDTH - 1; j >= 0; j--) {
				if (i + 3 >= HEIGHT || j - 3 < 0) {
					;
				} else {

					Piece one = board[i][j];
					if (one.type() == Type.EMPTY) {
						;
					} else {
						boolean allSame = true;
						int k = 1;
						while (allSame && k < 4) {
							Piece next = board[i + k][j - k];
							if (one.type() != next.type()) {
								allSame = false;
							}
							k++;
						}
						if (allSame) {
							return one.type();
						}
					}
				}
			}
		}

		// Check full;
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (board[i][j].type() == Type.EMPTY) {
					return Type.EMPTY;
				}
			}
		}
		return null;

	}

	@Override
	public boolean add(Piece input, int i, int j) throws Exception {

		System.out.println("Error");
		throw new Exception();
	}

	/**
	 * Will add a {@link Piece} to the board
	 * 
	 * @param input  The input {@link Piece} to use
	 * @param column The column number to add at
	 * @return {@code true} if it was added otherwise {@code false}
	 * @throws Exception ??
	 */
	public boolean add(Piece input, int column) throws Exception { // TODO Add exceptions

		if (column < 0 || column >= WIDTH) {
			return false;
		}

		int row = 0;
		Piece data = board[row][column];
		while (row < HEIGHT && data.type() == Type.EMPTY) {
			row++;
			// System.out.println(row);
			if (row < HEIGHT) {
				data = board[row][column];
			}
		}

		if (row != 0) {
			board[row - 1][column] = input;
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void remove(Piece input, int i, int j) {

		System.out.println("ERROR"); // SORT

	}

	@Override
	public Piece[][] currentBoard() {

		return board; // SHOULD BE DEEPCOPY
	}

	@Override
	public Object clone() {

		try {
			return (BoardConnectFour) super.clone();
		} catch (CloneNotSupportedException e) {
			return new BoardConnectFour(board, new PieceConnectFour(Type.EMPTY, false));
		}
	}

}

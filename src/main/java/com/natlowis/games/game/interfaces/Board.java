package com.natlowis.games.game.interfaces;

/**
 * An interface which states how to use a {@link Board}
 * @author low101043
 *
 */
public interface Board {

	/**
	 * Says whether it has won
	 * @return The {@link Piece} which has won
	 */
	public Piece won();

	/**
	 * Adds a {@link Piece} to the {@link Board}
	 * @param input The input {@link Piece} to use
	 * @param i The row to add it at
	 * @param j The column to add it at
	 * @return {@code true} if it was added otherwise {@code false}
	 * @throws Exception ??
	 */
	public boolean add(Piece input, int i, int j) throws Exception;

	/**
	 * Will remove a {@link Piece} from the {@link Board}
	 * @param input The {@link Piece} to remove
	 * @param i The row 
	 * @param j The column
	 */
	public void remove(Piece input, int i, int j);

	/**
	 * The board at its current state 
	 * @return The {@link Board} as a 2D array of {@link Piece}'s
	 */
	public Piece[][] currentBoard();

}

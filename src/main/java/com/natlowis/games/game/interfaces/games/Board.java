package com.natlowis.games.game.interfaces.games;

import com.natlowis.games.game.Type;

/**
 * An interface which states how to use a {@link Board}
 * 
 * @author low101043
 *
 */
public interface Board {

	/**
	 * Says whether it has won
	 * 
	 * @return The {@link Type} which has won or {@code Type.Empty} if its still not
	 *         full or {@code null} if its full
	 */
	public Type won();

	/**
	 * Adds a {@link Piece} to the {@link Board}
	 * 
	 * @param input The input {@link Piece} to use
	 * @param i     The row to add it at
	 * @param j     The column to add it at
	 * @return {@code true} if it was added otherwise {@code false}
	 */
	public boolean add(Piece input, int i, int j);

	/**
	 * Will remove a {@link Piece} from the {@link Board}
	 * 
	 * @param input The {@link Piece} to remove
	 * @param i     The row
	 * @param j     The column
	 */
	public void remove(Piece input, int i, int j);

	/**
	 * The board at its current state
	 * 
	 * @return The {@link Board} as a 2D array of {@link Piece}'s
	 */
	public Piece[][] currentBoard();

}

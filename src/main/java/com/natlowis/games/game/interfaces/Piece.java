package com.natlowis.games.game.interfaces;

/** 
 * The {@link Piece}'s used on the {@link Board}
 * @author low101043
 *
 */
public interface Piece {

	/** 
	 * The type of the {@link Piece}
	 * @return The {@link String} format of it
	 */
	public String type();

	/**
	 * Whether the {@link Piece} is player One
	 * @return {@code true} if they are Player one otherwise {@code false}
	 */
	public boolean playerOne();
}

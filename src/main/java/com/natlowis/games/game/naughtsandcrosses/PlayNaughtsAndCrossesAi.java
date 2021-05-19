package com.natlowis.games.game.naughtsandcrosses;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.interfaces.Board;
import com.natlowis.games.game.interfaces.Piece;
import com.natlowis.games.game.interfaces.Play;
import com.natlowis.games.game.naughtsandcrosses.ai.MinMax;
import com.natlowis.games.ui.cli.InputOutput;

/**
 * Plays Naughts and crosses with an AI player
 * 
 * @author low101043
 *
 */
public class PlayNaughtsAndCrossesAi implements Play {

	/** The {@link Board} to use */
	private Board board;
	/** The {@link InputOutput} to use */
	private InputOutput inputOutput;
	/** Whether the user goes first or not */
	private boolean first;

	/**
	 * Basic Constructor
	 * 
	 * @param inputOutput The {@link InputOutput} to use
	 * @param first       {@code true} if user goes first otherwise {@code false}
	 */
	public PlayNaughtsAndCrossesAi(InputOutput inputOutput, boolean first) {
		board = new BoardNaughtsAndCrosses();
		this.inputOutput = inputOutput;
		this.first = first;
	}

	@Override
	public void run() {
		Piece playerOne;
		Piece playerTwo;
		boolean playerOneGo;
		if (first) {
			playerOne = new PieceNaughtsAndCrosses(Type.CROSS, true);
			playerTwo = new PieceNaughtsAndCrosses(Type.NAUGHT, false);

			playerOneGo = true;
		} else {
			playerTwo = new PieceNaughtsAndCrosses(Type.CROSS, false);
			playerOne = new PieceNaughtsAndCrosses(Type.NAUGHT, true);
			playerOneGo = false;
		}

		while (!completed()) {
			print();

			boolean done = false;
			while (playerOneGo && !done) {
				inputOutput.output("Please enter number of place to place position");
				int place = inputOutput.input();
				int j = place % 3;
				int i = (place - j) / 3;
				try {
					done = board.add(playerOne, i, j);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while (!playerOneGo && !done) {
				inputOutput.output("AI Running");
				MinMax minMax = new MinMax(board, playerTwo);
				int[] coord = minMax.coordinates();

				int i = coord[0];
				int j = coord[1];
				try {
					done = board.add(playerTwo, i, j);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			playerOneGo = !playerOneGo;

		}
		print();

	}

	/**
	 * Checks if the game is completed
	 * 
	 * @return {@code true} if the game is completed otherwise {@code false}
	 */
	private boolean completed() {

		Type won = board.won();

		// System.out.println(won.type());
		if (won == null) {
			return true;
		} else if (won == Type.EMPTY) {
			return false;

		} else {
			return true;
		}

	}

	/**
	 * Will print the board to the screen
	 */
	private void print() {
		String output = "";
		Piece[][] boardToOutput = board.currentBoard();
		for (int i = 0; i < boardToOutput.length; i++) {
			for (int j = 0; j < boardToOutput[i].length; j++) {
				if (boardToOutput[i][j].type() == Type.EMPTY) {
					String place = String.valueOf((i * 3) + j);
					output += (place + ",");
				} else if (boardToOutput[i][j].type() == Type.CROSS) {
					output += "x,";
				} else {
					output += "o,";
				}
			}
			output += "\n";

		}
		inputOutput.output(output);

	}

}

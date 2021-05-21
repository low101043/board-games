package com.natlowis.games.game.connectfour;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.connectfour.ai.MinMax;
import com.natlowis.games.game.interfaces.Board;
import com.natlowis.games.game.interfaces.Piece;
import com.natlowis.games.game.interfaces.Play;
import com.natlowis.games.ui.cli.InputOutput;

/**
 * Plays Naughts and crosses with an AI player
 * 
 * @author low101043
 *
 */
public class PlayConnectFourAi implements Play {

	/** The {@link Board} to use */
	private BoardConnectFour board;
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
	public PlayConnectFourAi(InputOutput inputOutput, boolean first) {
		board = new BoardConnectFour();
		this.inputOutput = inputOutput;
		this.first = first;
	}

	@Override
	public void run() {
		Piece playerOne;
		Piece playerTwo;
		boolean playerOneGo;
		if (first) {
			playerOne = new PieceConnectFour(Type.CROSS);
			playerTwo = new PieceConnectFour(Type.NAUGHT);

			playerOneGo = true;
		} else {
			playerTwo = new PieceConnectFour(Type.CROSS);
			playerOne = new PieceConnectFour(Type.NAUGHT);
			playerOneGo = false;
		}

		while (!completed()) {
			print();

			boolean done = false;
			while (playerOneGo && !done) {
				inputOutput.output("Please enter which row you want to choose");
				int i = inputOutput.input();
				done = board.add(playerOne, i);

			}
			while (!playerOneGo && !done) {
				inputOutput.output("AI Running");
				MinMax minMax = new MinMax(board, playerTwo);
				int[] coord = minMax.coordinates();

				int i = coord[0];
				int j = coord[1];

				done = board.add(playerTwo, i, j);

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
	 * Will output the board
	 */
	private void print() {
		String output = "0,1,2,3,4,5,6\n";
		Piece[][] boardToOutput = board.currentBoard();
		for (int i = 0; i < boardToOutput.length; i++) {
			for (int j = 0; j < boardToOutput[i].length; j++) {
				if (boardToOutput[i][j].type() == Type.EMPTY) {
					output += " ,";
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

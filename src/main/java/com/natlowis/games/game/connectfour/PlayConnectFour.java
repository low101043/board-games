package com.natlowis.games.game.connectfour;

import com.natlowis.games.game.Type;
import com.natlowis.games.game.interfaces.games.Board;
import com.natlowis.games.game.interfaces.games.Piece;
import com.natlowis.games.game.interfaces.games.Play;
import com.natlowis.games.ui.cli.InputOutput;

/**
 * This will actually Play Connect Four
 * 
 * @author low101043
 *
 */
public class PlayConnectFour implements Play {

	/** The {@link Board} to use */
	private BoardConnectFour board = new BoardConnectFour();
	/** The {@link InputOutput} to use */
	private InputOutput inputOutput;

	/**
	 * A constructor which creates the game
	 * 
	 * @param inputOutput The {@link InputOutput} to use
	 */
	public PlayConnectFour(InputOutput inputOutput) {
		this.inputOutput = inputOutput;
	}

	@Override
	public void run() {
		Piece playerOne = new PieceConnectFour(Type.CROSS);
		Piece playerTwo = new PieceConnectFour(Type.NAUGHT);

		boolean playerOneGo = true;

		while (!completed()) {
			print();

			boolean done = false;
			while (playerOneGo && !done) {
				inputOutput.output("Please enter which row you want to choose");
				int i = inputOutput.input("Please enter which row you want to choose");
				done = board.add(playerOne, i);

			}
			while (!playerOneGo && !done) {
				inputOutput.output("Please enter which row you want to choose");
				int i = inputOutput.input("Please enter which row you want to choose");
				done = board.add(playerTwo, i);

			}
			playerOneGo = !playerOneGo;

		}
		print();

	}

	/**
	 * Whether the game has been completed
	 * 
	 * @return {@code true} if the game is finished otherwise {@code false}
	 */
	private boolean completed() {

		Type won = board.won();

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

package com.natlowis.connectfour;

import com.natlowis.interfaces.Piece;
import com.natlowis.interfaces.Play;
import com.natlowis.ui.cli.InputOutput;

/**
 * This will actually Play Connect Four
 * @author low101043
 *
 */
public class PlayConnectFour implements Play {

	/** The {@link Board} to use */
	private BoardConnectFour board;
	/** The {@link InputOutput} to use */
	private InputOutput inputOutput;

	/**
	 * A constructor which creates the game
	 * @param inputOutput The {@link InputOutput} to use
	 */
	public PlayConnectFour(InputOutput inputOutput) {
		board = new BoardConnectFour();
		this.inputOutput = inputOutput;
	}

	@Override
	public void run() {
		Piece playerOne = new PieceConnectFour("Red", true);
		Piece playerTwo = new PieceConnectFour("Yellow", false);

		boolean playerOneGo = true;

		while (!completed()) {
			print();

			boolean done = false;
			while (playerOneGo && !done) {
				inputOutput.output("Please enter which row you want to choose");
				int i = inputOutput.input();
				try {
					done = board.add(playerOne, i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			while (!playerOneGo && !done) {
				inputOutput.output("Please enter which row you want to choose");
				int i = inputOutput.input();

				try {
					done = board.add(playerTwo, i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			playerOneGo = !playerOneGo;

		}

	}

	/**
	 * Whether the game has been completed
	 * @return {@code true} if the game is finished otherwise false
	 */
	private boolean completed() {

		Piece won = board.won();

		if (won == null) {
			return true;
		} else if (won.type().equals("Empty")) {
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
				if (boardToOutput[i][j].type().equals("Empty")) {
					output += " ,";
				} else if (boardToOutput[i][j].type().equals("Red")) {
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

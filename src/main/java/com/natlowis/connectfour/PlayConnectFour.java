package com.natlowis.connectfour;

import com.natlowis.interfaces.Piece;
import com.natlowis.interfaces.Play;
import com.natlowis.ui.cli.InputOutput;

public class PlayConnectFour implements Play {

	private BoardConnectFour board;
	private InputOutput inputOutput;

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

	private boolean completed() {

		Piece won = board.won();

		// System.out.println(won.type());
		if (!(won.equals(null)) && won.type().equals("Empty")) {
			return false;

		} else {
			return true;
		}

	}

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

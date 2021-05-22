package com.natlowis.games.ui.cli;

import com.natlowis.games.game.connectfour.PlayConnectFourAi;
import com.natlowis.games.game.interfaces.Play;
import com.natlowis.games.game.naughtsandcrosses.PlayNaughtsAndCrosses;
import com.natlowis.games.game.naughtsandcrosses.PlayNaughtsAndCrossesAi;

/**
 * Will control the menu which the user sees
 * 
 * @author low101043
 *
 */
public class Run {

	/** The {@link InputOutput} used by the system */
	private InputOutput inputOutput;

	/**
	 * The Basic Constructor
	 */
	public Run() {
		inputOutput = new InputOutput();

	}

	/**
	 * Shows the menu and runs the game
	 */
	public void run() {

		int menuChoice = menu();

		while (menuChoice != 4) {

			if (menuChoice == 1) {
				naughtsAndCrosses();
			} else if (menuChoice == 2) {
				connectFour();
			} else if (menuChoice == 3) {
				chess();
			} else {
				inputOutput.output("Impressive getting here");
			}
			menuChoice = menu();
		}

		inputOutput.output("Goodbye");
		inputOutput.close();
	}

	/**
	 * Runs Chess
	 */
	private void chess() {
		inputOutput.output("Chess coming soon");

	}

	/**
	 * Runs Connect 4 for 2 players
	 */
	private void connectFourTwoPlayer() {
		inputOutput.output("Welcome to the Two Player version of Connect Four.\n\nSingle Player coming soon");
		inputOutput.output("Crosses go first\n\n");

		Play connectFour = new PlayConnectFourAi(inputOutput, true);
		connectFour.run();

	}

	/**
	 * Shows the options for Connect Four
	 */
	private void connectFour() {
		String output = "Please choose Single Player (1) or Two Player (2)";
		inputOutput.output(output);
		int choice = inputOutput.input("Please choose Single Player (1) or Two Player (2)");

		while (choice > 2 || choice <= 0) {
			inputOutput.output("Please choose Single Player (1) or Two Player (2)");
			choice = inputOutput.input("Please choose Single Player (1) or Two Player (2)");
		}
		if (choice == 1) {
			connectFourSinglePlayer();
		} else {
			connectFourTwoPlayer();
		}
	}

	/**
	 * Plays Single Player Connect Four
	 */
	private void connectFourSinglePlayer() {
		inputOutput.output(
				"Welcome to the Single Player Version of Connect Four.  \nNote.  This AI opponent takes a lonnnnnng time to make a move.  Like really long.  Like watch a film still not done long (I haven't done this but still). \n  Choose to go first (1) or Second (2)");

		int choice = inputOutput.input("Choose to go first (1) or Second (2)");

		while (choice > 2 || choice <= 0) {
			inputOutput.output("Please enter an actual choice");
			choice = inputOutput.input("Choose to go first (1) or Second (2)");
		}

		Play naughtsAndCrosses;
		if (choice == 1) {
			naughtsAndCrosses = new PlayConnectFourAi(inputOutput, true);
		} else {
			naughtsAndCrosses = new PlayConnectFourAi(inputOutput, false);
		}
		naughtsAndCrosses.run();

	}

	/**
	 * Gets the input for the menu
	 * 
	 * @return The {@code int} which the user has given
	 */
	private int menu() {
		String output = "Please choose which game you would like to play:\n1)Naughts & Crosses\n2)Connect 4\n3)Chess\n4)Exit";
		inputOutput.output(output);
		int choice = inputOutput.input(output + "\nPlease choose an actual number");

		while (choice > 4 || choice <= 0) {
			inputOutput.output("Please enter an actual choice");
			choice = inputOutput.input(output + "\nPlease choose an actual number");
		}

		return choice;
	}

	/**
	 * Shows the options for Naughts and Crosses
	 */
	private void naughtsAndCrosses() {
		String output = "Please choose Single Player (1) or Two Player (2)";
		inputOutput.output(output);
		int choice = inputOutput.input("Please choose Single Player (1) or Two Player (2)");

		while (choice > 2 || choice <= 0) {
			inputOutput.output("Please choose Single Player (1) or Two Player (2)");
			choice = inputOutput.input("Please choose Single Player (1) or Two Player (2)");
		}
		if (choice == 1) {
			naughtsAndCrossesSinglePlayer();
		} else {
			naughtsAndCrossesTwoPlayer();
		}
	}

	/**
	 * Plays Single Player Naughts and Crosses
	 */
	private void naughtsAndCrossesSinglePlayer() {
		inputOutput.output(
				"Welcome to the Single Player Version of Naughts and Crosses.  Choose to go first (1) or Second (2)");

		int choice = inputOutput.input("Choose to go first (1) or Second (2)");

		while (choice > 2 || choice <= 0) {
			inputOutput.output("Please enter an actual choice");
			choice = inputOutput.input("Choose to go first (1) or Second (2)");
		}

		Play naughtsAndCrosses;
		if (choice == 1) {
			naughtsAndCrosses = new PlayNaughtsAndCrossesAi(inputOutput, true);
		} else {
			naughtsAndCrosses = new PlayNaughtsAndCrossesAi(inputOutput, false);
		}
		naughtsAndCrosses.run();

	}

	/**
	 * Plays Two Player Naughts and Crosses
	 */
	private void naughtsAndCrossesTwoPlayer() {
		inputOutput.output("Welcome to the Two Player version of Naughts & Crosses.\n\nSingle Player coming soon");
		inputOutput.output("Crosses go first");
		Play naughtsAndCrosses = new PlayNaughtsAndCrosses(inputOutput);
		naughtsAndCrosses.run();

	}

}

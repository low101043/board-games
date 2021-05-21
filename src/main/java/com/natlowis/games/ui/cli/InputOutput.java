package com.natlowis.games.ui.cli;

import java.util.Scanner;

/**
 * Handles all the input and output uses of the game
 * 
 * @author low101043
 *
 */
public class InputOutput {

	/** The {@link Scanner} to use */
	private Scanner input;

	/**
	 * The basic Constructor
	 */
	public InputOutput() {
		input = new Scanner(System.in);
	}

	/**
	 * To get the input from the screen
	 * 
	 * @param falseData The error message to send if the user enters incorrect data
	 * @return An {@code int} which was last entered by the user
	 */
	public int input(String falseData) {
		String inputStr = input.next();
		
		while (!inputStr.matches("[0-9]+")) {
			this.output(falseData);
			inputStr = input.next();
		}

		return Integer.parseInt(inputStr);
		
	}

	/**
	 * Outputs the argument to the screen
	 * 
	 * @param output The {@link String} which needs to be outputted
	 */
	public void output(String output) {
		System.out.println(output);
	}

	/**
	 * Closes and cleans the class
	 */
	public void close() {
		input.close();
	}

}

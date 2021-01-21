package com.natlowis.ui.cli;

import com.natlowis.connectfour.PlayConnectFour;
import com.natlowis.interfaces.Play;
import com.natlowis.naughtsandcrosses.PlayNaughtsAndCrosses;

public class Run {
	
	private InputOutput inputOutput;
	
	public Run() {
		inputOutput = new InputOutput();
		
	}
	
	public void run() {
		
		int menuChoice = menu();
		
		while (menuChoice != 4) {
			
			if (menuChoice == 1) {
				naughtsAndCrosses();
			}
			else if (menuChoice == 2) {
				connectFour();
			}
			else if (menuChoice == 3) {
				chess();
			}
			else {
				inputOutput.output("Impressive getting here");
			}
			menuChoice = menu();
		}
		
		inputOutput.output("Goodbye");
		inputOutput.close();
	}
	
	private void chess() {
		inputOutput.output("Chess coming soon");
		
	}

	private void connectFour() {
		// TODO Auto-generated method stub
		inputOutput.output("Welcome to the Two Player version of Connect Four.\n\nSingle Player coming soon");
		inputOutput.output("Crosses go first\n\n");
		
		Play connectFour = new PlayConnectFour(inputOutput);
		connectFour.run();
		
	}

	private int menu() {
		String output = "Please choose which game you would like to play:\n1)Naughts & Crosses\n2)Connect 4\n3)Chess\n4)Exit";
		inputOutput.output(output);
		int choice = inputOutput.input();
		
		while (choice > 4 || choice <= 0) {
			inputOutput.output("Please enter an actual choice");
			choice = inputOutput.input();
		}
		
		return choice;
	}
	
	private void naughtsAndCrosses() {
		inputOutput.output("Welcome to the Two Player version of Naughts & Crosses.\n\nSingle Player coming soon");
		inputOutput.output("Crosses go first");
		Play naughtsAndCrosses = new PlayNaughtsAndCrosses(inputOutput);
		naughtsAndCrosses.run();
		
	}
	
}

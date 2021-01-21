package com.natlowis.naughtsandcrosses;

import com.natlowis.interfaces.Board;
import com.natlowis.interfaces.Piece;
import com.natlowis.interfaces.Play;
import com.natlowis.ui.cli.InputOutput;

import java.util.Scanner;

public class PlayNaughtsAndCrosses implements Play {
	
	private Board board;
	private InputOutput inputOutput;
	
	public PlayNaughtsAndCrosses(InputOutput inputOutput) {
		board = new BoardNaughtsAndCrosses();
		this.inputOutput = inputOutput;
	}

	@Override
	public void run() {
		Piece playerOne = new PieceNaughtsAndCrosses("Cross", true);
		Piece playerTwo = new PieceNaughtsAndCrosses("Naughts", false);
		
		boolean playerOneGo = true;
		
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
				inputOutput.output("Please enter number of place to place position");
				int place = inputOutput.input();
				int j = place % 3;
				int i = (place - j) / 3;
				try {
					done = board.add(playerTwo, i, j);
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
		
		
		//System.out.println(won.type());
		if (!(won.equals(null)) && won.type().equals("Blank")){
			return false;
			
		}
		else {
			return true;
		}
		
	}
	
	private void print() {
		String output = "";
		Piece[][] boardToOutput = board.currentBoard();
		for (int i = 0; i < boardToOutput.length; i++) {
			for (int j = 0; j < boardToOutput[i].length; j++) {
				if (boardToOutput[i][j].type().equals("Blank")) {
					String place = String.valueOf((i*3)+j);
					output += (place+",");
				}
				else if (boardToOutput[i][j].type().equals("Cross")) {
					output += "x,";
				}
				else {
					output += "o,";
				}
			}
			output += "\n";
			
		}
		inputOutput.output(output);
		
	}

}

package com.natlowis.naughtsandcrosses;

import com.natlowis.interfaces.Board;
import com.natlowis.interfaces.Piece;
import com.natlowis.interfaces.Play;

import java.util.Scanner;

public class PlayNaughtsAndCrosses implements Play {
	
	private Board board;
	private Scanner input;
	
	public PlayNaughtsAndCrosses() {
		board = new BoardNaughtsAndCrosses();
		input = new Scanner(System.in);
	}

	@Override
	public void run() {
		Piece playerOne = new PieceNaughtsAndCrosses("Cross", true);
		Piece playerTwo = new PieceNaughtsAndCrosses("Naughts", false);
		
		boolean playerOneGo = true;
		
		while (!completed()) {
			
			
			boolean done = false;
			while (playerOneGo && !done) {
				int i = input.nextInt();
				int j = input.nextInt();
				try {
					done = board.add(playerOne, i, j);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while (!playerOneGo && !done) {
				int i = input.nextInt();
				int j = input.nextInt();
				try {
					done = board.add(playerTwo, i, j);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			playerOneGo = !playerOneGo;
			print();
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
					output += " ,";
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
		System.out.println(output);
	}

}

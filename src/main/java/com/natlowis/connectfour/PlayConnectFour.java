package com.natlowis.connectfour;

import java.util.Scanner;

import com.natlowis.interfaces.Board;
import com.natlowis.interfaces.Piece;
import com.natlowis.interfaces.Play;
import com.natlowis.connectfour.BoardConnectFour;
import com.natlowis.connectfour.PieceConnectFour;

public class PlayConnectFour implements Play {

	
	private BoardConnectFour board;
	private Scanner input;
	
	public PlayConnectFour() {
		board = new BoardConnectFour();
		input = new Scanner(System.in);
	}

	@Override
	public void run() {
		Piece playerOne = new PieceConnectFour("Red", true);
		Piece playerTwo = new PieceConnectFour("Yellow", false);
		
		boolean playerOneGo = true;
		
		while (!completed()) {
			
			
			boolean done = false;
			while (playerOneGo && !done) {
				int i = input.nextInt();	
				try {
					done = board.add(playerOne, i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			while (!playerOneGo && !done) {
				int i = input.nextInt();
				
				try {
					done = board.add(playerTwo, i);
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
		if (!(won.equals(null)) && won.type().equals("Empty")){
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
				if (boardToOutput[i][j].type().equals("Empty")) {
					output += " ,";
				}
				else if (boardToOutput[i][j].type().equals("Red")) {
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

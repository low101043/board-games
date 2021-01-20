package com.natlowis.naughtsandcrosses;

import com.natlowis.interfaces.Board;
import com.natlowis.interfaces.Piece;

public class BoardNaughtsAndCrosses implements Board {

	private Piece[][] board;
	private int WIDTH = 3;
	private int HEIGHT = 3;
	private Piece blank;
	
	public BoardNaughtsAndCrosses() {
		board = new Piece[HEIGHT][WIDTH];
		
		blank = new PieceNaughtsAndCrosses("Blank", false);
		
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				board[i][j] = blank;
			}
		}
	}
	
	@Override
	public Piece won() {
	
		for (int i = 0; i < HEIGHT; i++) {
			Piece firstPiece = board[i][0];
			boolean allSame = true;
			for (int j = 1; j < WIDTH; j++) {
				//System.out.println(board[i][j].type());
				if (board[i][j] != firstPiece) {
					allSame = false;
				}
			}
			//System.out.println(allSame);
			if (allSame == true && !(firstPiece.type().equals("Blank"))) {
				return firstPiece;
			}
		}
		
		for (int j = 0; j < WIDTH; j++) {
			Piece firstPiece = board[0][j];
			boolean allSame = true;
			for (int i = 1; i < HEIGHT; i++) {
				if (board[i][j] != firstPiece) {
					allSame = false;
				}
			}
			if (allSame == true && !(firstPiece.type().equals("Blank"))) {
				return firstPiece;
			}
		}
		
		Piece middle = board[1][1];
		Piece topLeft = board[0][0];
		Piece topRight = board[0][2];
		Piece bottomLeft = board[2][0];
		Piece bottomRight = board[2][2];
		
		if (!(middle.type().equals("Blank")) && middle == topLeft && middle == bottomRight) {
			return middle;			
		}
		
		if (!(middle.type().equals("Blank")) && middle == topRight && middle == bottomLeft) {
			return middle;			
		} 
		
		boolean allFull = true;
		for (int i =0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (board[i][j] == blank) {
					allFull = false;
				}
			}
		}
		
		if (allFull) {
			return null;
		}
		else {
			return blank;
		}
		
		
	}

	@Override
	public boolean add(Piece input, int i, int j) throws Exception {  // ADD EXCEPTIONS
		
		if (i < 0  || i >= HEIGHT) {
			return false;
		}
		else if (j < 0 || j >= WIDTH) {
			return false;
		}
		if (board[i][j] == blank) {
			board[i][j] = input;
			return true;
		}
		return false;

	}

	@Override
	public void remove(Piece input, int i, int j) {
		board[i][j] = blank;

	}

	@Override
	public Piece[][] currentBoard() {
		
		return board;
	}

}

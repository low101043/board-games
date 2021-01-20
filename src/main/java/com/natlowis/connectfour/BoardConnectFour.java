package com.natlowis.connectfour;

import com.natlowis.interfaces.Board;
import com.natlowis.interfaces.Piece;
import com.natlowis.naughtsandcrosses.PieceNaughtsAndCrosses;

public class BoardConnectFour implements Board {
	
	private Piece[][] board;
	private int WIDTH = 7;
	private int HEIGHT = 6;
	private Piece empty;

	public BoardConnectFour() {
		board = new Piece[HEIGHT][WIDTH];
		
		empty = new PieceConnectFour("Empty", false);
		
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				board[i][j] = empty;	
			}
		}
		
		
	}
	
	@Override
	public Piece won() {
		// TODO Auto-generated method stub
		
		// Horizontal
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH-3;j++) {
				Piece one = board[i][j];
				if (one == empty) {
					;
				}
				else {
					boolean allSame = true;
					int k = 1;
					while (allSame && k < 4) {
						Piece next = board[i][j+k];
						if (one != next) {
							allSame = false;
						}
						k++;
					}
					if (allSame) {
						return one;
					}
				}
			}
		}
		
		
		// Vertical
		for (int j = 0; j < WIDTH; j++) {
			for (int i = 0; i < HEIGHT-3; i++) {
				Piece one = board[i][j];
				if (one == empty) {
					;
				}
				else {
				
					boolean allSame = true;
					int k = 1;
					while (allSame && k < 4) {
						Piece next = board[i+k][j];
						if (one != next) {
							allSame = false;
						}
						k++;
					}
					if (allSame) {
						return one;
					}
				}
			}
		}
		
		// Diagonal Left Right
		
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (i+3>= HEIGHT || j+3 >= WIDTH) {
					;
				}
				else {
					Piece one = board[i][j];
					if (one == empty) {
						;
					}
					else {
						boolean allSame = true;
						int k = 1;
						while (allSame && k < 4) {
							Piece next = board[i+k][j+k];
							if (one != next) {
								allSame = false;
							}
							k++;
						}
						if (allSame) {
							return one;
						}
					}
				}
			}
		}
		
		// Diagonal Right to Left
		
		for (int i = 0; i < HEIGHT; i++) {
			//System.out.println(i);
			for (int j = WIDTH-1; j >= 0; j--) {
				if (i+3>= HEIGHT || j-3 < 0) {
					;
				}
				else {
					
					Piece one = board[i][j];
					if (one == empty) {
						;
					}
					else {
						boolean allSame = true;
						int k = 1;
						while (allSame && k < 4) {
							Piece next = board[i+k][j-k];
							if (one != next) {
								allSame = false;
							}
							k++;
						}
						if (allSame) {
							return one;
						}
					}
				}
			}
		}
		
		
		//Check full;
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (board[i][j] == empty) {
					return empty;
				}
			}
		}
		return null;
		
	}

	@Override
	public boolean add(Piece input, int i, int j) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Error");
		throw new Exception();
	}
	
	public boolean add(Piece input, int column) throws Exception {  // TODO Add exceptions
		
		if (column < 0 || column >= WIDTH) {
			return false;
		}
		
		int row = 0;
		Piece data = board[row][column];
		while (row < HEIGHT && data == empty) {
			row++;
			System.out.println(row);
			if (row < HEIGHT) {
				data = board[row][column];
			}
		}
		
		if (row !=0) {
			board[row-1][column] = input;
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public void remove(Piece input, int i, int j) {
		// TODO Auto-generated method stub
		
		System.out.println("ERROR");  //SORT

	}

	@Override
	public Piece[][] currentBoard() {
		// TODO Auto-generated method stub
		return board;  // SHOULD BE DEEPCOPY
	}
	
	

}

package com.natlowis.naughtsandcrosses.ai;

import java.util.ArrayList;

import com.natlowis.naughtsandcrosses.BoardNaughtsAndCrosses;
import com.natlowis.naughtsandcrosses.PieceNaughtsAndCrosses;

public class GameTreeNaughtsAndCrosses {

	private BoardNaughtsAndCrosses node;
	private int utility = -2;
	private ArrayList<GameTreeNaughtsAndCrosses> children = new ArrayList<GameTreeNaughtsAndCrosses>();
	private BoardNaughtsAndCrosses nextMove;

	public GameTreeNaughtsAndCrosses(BoardNaughtsAndCrosses previousBoard, PieceNaughtsAndCrosses piece) {
		node = previousBoard;
		if (node.won() == null) {
			utility = 0;
			nextMove = null;
			return;
		} else if (node.won().type().equals("Cross")) {
			utility = -1;
			nextMove = null;
			return;
		} else if (node.won().type().equals("Naught")) {
			utility = 1;
			nextMove = null;
			return;
		}
		if (utility == -2) {
			for (int i = 0; i < previousBoard.currentBoard().length; i++) {
				for (int j = 0; j < previousBoard.currentBoard()[i].length; j++) {
					if (previousBoard.currentBoard()[i][j].type() == "Blank") {
						BoardNaughtsAndCrosses newBoard = (BoardNaughtsAndCrosses) previousBoard.clone();
						try {
							newBoard.add(piece, i, j);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (piece.type().equals("Cross")) {
							if (newBoard == null) {
								System.out.println("ERROR");
							}
							children.add(new GameTreeNaughtsAndCrosses(newBoard,
									new PieceNaughtsAndCrosses("Naught", false)));
						} else {
							if (newBoard == null) {
								System.out.println("ERROR");
							}
							children.add(new GameTreeNaughtsAndCrosses(newBoard,
									new PieceNaughtsAndCrosses("Cross", false)));
						}
					}
				}
			}
			int v;
			if (piece.type().equals("Cross")) {
				v = Integer.MAX_VALUE;
			} else {
				v = Integer.MIN_VALUE;
			}
			for (GameTreeNaughtsAndCrosses child : children) {

				if (piece.type().equals("Cross")) {

					if (child.returnUtility() < v) {
						v = child.returnUtility();
						nextMove = child.getBoard();
					}

				} else {

					if (child.returnUtility() > v) {
						v = child.returnUtility();
						nextMove = child.getBoard();
					}
				}
			}
			utility = v;
		}

	}

	public void setUtility(int newUtility) {
		utility = newUtility;
	}

	public int returnUtility() {
		return utility;
	}

	public BoardNaughtsAndCrosses getBoard() {
		return node;
	}

	public BoardNaughtsAndCrosses nextMove() {
		return nextMove;
	}
}

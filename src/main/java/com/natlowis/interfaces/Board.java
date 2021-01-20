package com.natlowis.interfaces;

public interface Board {
	
	public Piece won();
	
	public boolean add(Piece input, int i, int j) throws Exception;
	
	public void remove(Piece input, int i, int j);
	
	public Piece[][] currentBoard();

}

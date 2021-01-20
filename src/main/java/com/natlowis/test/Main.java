package com.natlowis.test;

import com.natlowis.connectfour.BoardConnectFour;
import com.natlowis.connectfour.PlayConnectFour;
import com.natlowis.interfaces.Play;
import com.natlowis.naughtsandcrosses.PlayNaughtsAndCrosses;

public class Main {

	public static void main(String[] args) {
		

		PlayNaughtsAndCrosses play = new PlayNaughtsAndCrosses(); 
		System.out.println("HEllo");
		
		Play four = new PlayConnectFour();
		
		four.run();
		
		System.out.println("Finished");

	}

}

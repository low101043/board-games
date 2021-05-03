package com.natlowis.ui.cli;

import java.util.Scanner;

public class InputOutput {

	private Scanner input;

	public InputOutput() {
		input = new Scanner(System.in);
	}

	public int input() {
		int num = input.nextInt();

		return num;
	}

	public void output(String output) {
		System.out.println(output);
	}

	public void close() {
		input.close();
	}

}

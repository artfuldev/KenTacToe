package com.tictactoe.main;

import java.util.Scanner;

import com.tictactoe.game.*;
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner inputStream=new Scanner(System.in);
		System.out.println("Enter your name:");
		String userName=inputStream.nextLine();
		Game ticTacToe=new Game(userName);
		ticTacToe.init();
		ticTacToe.play();
		inputStream.close();
	}

}

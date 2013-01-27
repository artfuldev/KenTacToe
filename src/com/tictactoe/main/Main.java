package com.tictactoe.main;

import java.util.Scanner;
import com.tictactoe.game.*;
public class Main
{
	/**
	 * Main function
	 */
	public static void main(String[] args)
	{
		Scanner inputStream=new Scanner(System.in);
		byte aiOrUser=0, turnNumber=0;
		Game ticTacToe;
		String userName, secondUserName="NoName";
		System.out.println("Play against AI or human? Enter 0 for AI:");
		aiOrUser=inputStream.nextByte();
		System.out.println("Enter your name:");
		userName=inputStream.next();
		if(aiOrUser!=0)
		{
			System.out.println("Enter second player's name:");
			secondUserName=inputStream.next();
		}
		if(aiOrUser==0)
			System.out.println("Welcome to KenTacToe, "+userName+"!");
		else
			System.out.println("Welcome to KenTacToe, "+userName+" and "
					+secondUserName+"!");
		System.out.println("Please enter valid inputs, as the lazy author did " +
				"not implement exception handling in me. Hope you keep that " +
				"in mind!");
		if(aiOrUser==0)
		{
			System.out.println("Enter you turn to play (1 for X, 2 for O):");
			turnNumber=inputStream.nextByte();
		}
		System.out.println("Enter grid size you want to play in (n means nxn grid):");
		byte gridSize=inputStream.nextByte();
		if(aiOrUser==0)
		{
			System.out.println("Enter search depth (zero or negative values take " +
					"default search depth as moves available):");
			byte searchDepth=inputStream.nextByte();
			ticTacToe=new Game(gridSize, userName, turnNumber, searchDepth);
		}
		else
			ticTacToe=new Game(gridSize,userName,secondUserName);
		ticTacToe.init();
		ticTacToe.play();
		inputStream.close();
	}

}

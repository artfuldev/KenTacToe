/*
 * This file is part of KenTacToe, (c) Kenshin Himura, 2013.
 * 
 * KenTacToe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * KenTacToe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with KenTacToe.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package com.tictactoe.main;

import java.util.Scanner;
import com.tictactoe.game.*;
import com.tictactoe.player.Player;
public class Main
{
	public static final String appName="KenTacToe";
	public static final String appVersion="2.3";
	public static final String appAuthor="Kenshin Himura (Sudarsan Balaji)";
	/**
	 * Main function
	 */
	public static void main(String[] args)
	{
		Scanner inputStream=new Scanner(System.in);
		byte aiOrUser=0, turnNumber=0, playAgain=1;
		Game ticTacToe;
		String userName, secondUserName="NoName";
		while(playAgain!=0)
		{
			Player.playerCount=0;
			System.out.println(appName+" "+appVersion);
			System.out.println("by "+appAuthor);
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
			System.out.println("Enter grid size you want to play in " +
					"(n means nxn grid):");
			byte gridSize=inputStream.nextByte();
			if(aiOrUser==0)
			{
				System.out.println("Enter search depth (zero or negative values " +
						"take default search depth as moves available):");
				byte searchDepth=inputStream.nextByte();
				ticTacToe=new Game(gridSize, userName, turnNumber, searchDepth);
			}
			else
				ticTacToe=new Game(gridSize,userName,secondUserName);
			ticTacToe.play();
			System.out.println("Play again? [0 for no]: ");
			playAgain=inputStream.nextByte();
		}	
		inputStream.close();
	}
}
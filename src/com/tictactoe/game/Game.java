package com.tictactoe.game;

import java.util.Scanner;

import com.tictactoe.player.*;
import com.tictactoe.search.*;
import com.tictactoe.table.*;

public class Game {
	private Table gameGrid;
	private Player playerOne;
	private Player playerTwo;
	private Player currentPlayer;
	private Search currentSearch;
	private boolean gameOver;
	public Game()
	{
		gameGrid=new Table(3,3);
		playerOne=new Player("UserPlayer");
		playerTwo=new Player();
	}
	public Game(int gridSize)
	{
		gameGrid=new Table(gridSize,gridSize);
		playerOne=new Player("UserPlayer");
		playerTwo=new Player();
	}
	public Game(int gridSize,String userName)
	{
		gameGrid=new Table(gridSize,gridSize);
		playerOne=new Player(userName);
		playerTwo=new Player();
	}
	public Game(String userName)
	{
		gameGrid=new Table(3,3);
		playerOne=new Player(userName);
		playerTwo=new Player();
	}
	public void init()
	{
		System.out.println("Game Initialized...");
		gameGrid.printTable();
		System.out.println(playerOne.getPlayerName()+" "+playerOne.getPlayerSign());
		System.out.println(playerTwo.getPlayerName()+" "+playerTwo.getPlayerSign());
		currentPlayer=playerOne;
	}
	public void getInput()
	{
		int index=0;
		Scanner inputStream=new Scanner(System.in);
		do{
			do{
				System.out.println("Enter the index of the table you want to sign, "+currentPlayer.getPlayerName()+":");
				index=inputStream.nextInt();
			}while((index<0)||(index>9));
		}while(!gameGrid.isEmpty(index));
		gameGrid.updateTable(index, currentPlayer.getPlayerSign());
		if(currentPlayer==playerOne)
			currentPlayer=playerTwo;
		else
			currentPlayer=playerOne;
		System.out.println("Grid updated...");
		gameGrid.printTable();
	}
	public void play()
	{
		for(int i=0;i<9;i++)
			getInput();
	}
}
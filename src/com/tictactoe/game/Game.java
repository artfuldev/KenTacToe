package com.tictactoe.game;

import com.tictactoe.player.*;
import com.tictactoe.search.*;
import com.tictactoe.table.*;

public class Game {
	private Table gameGrid;
	private Player playerOne;
	private Player playerTwo;
	private Search currentSearch;
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
		System.out.println(playerOne.getPlayerName()+playerOne.getPlayerSign());
		System.out.println(playerTwo.getPlayerName()+playerTwo.getPlayerSign());
	}
}

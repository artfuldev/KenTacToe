package com.tictactoe.game;

import java.util.Scanner;

import com.tictactoe.player.*;
import com.tictactoe.search.*;
import com.tictactoe.table.*;
import com.tictactoe.move.*;

/**
 * The <code>Game</code> class is designed to be a class which holds any type of game,
 * but in this program, it is defined as if it is a tic tac toe game by default,
 * for convenience. Thus, it has a table called gameGrid, and two players, and a
 * reference to the current player of the game. It has a status indicator for whether
 * the game is over. Also, a search is there, for use by the AI. (Later, we may add a
 * moves list to store the different moves made in the right order, for review or
 * other purposes.)
 * @author Kenshin Himura
 *
 */
public class Game
{
	/**
	 * Stores the current table of the game.
	 * Primarily used for displaying, processing and passing as argument to the
	 * various methods the <code>Game</code> class can make use of.
	 */
	private Table gameGrid;
	/**
	 * Stores the first <code>Player</code>.
	 */
	private Player playerOne;
	/**
	 * Stores the second <code>Player</code>.
	 */
	private Player playerTwo;
	/**
	 * Is a reference to the current player, and shifts between playerOne and
	 * playerTwo between turns.
	 */
	private Player currentPlayer;
	/**
	 * An object of the <code>Search</code> class, which can be used by the AI.
	 */
	private Search currentSearch;
	/**
	 * A status indicator of type <code>boolean</code> used to indicate if the gameOver
	 * conditions have been met. Primarily used to end the game.
	 */
	private boolean gameOver=false;
	/**
	 * Default constructor of the <code>Game</code> class.
	 * Creates a 3x3 Table for use with the game and a two players,
	 * one of type "User" with name "UserPlayer" and another of Type AI.
	 */
	public Game()
	{
		gameGrid=new Table(3,3);
		playerOne=new Player("UserPlayer");
		playerTwo=new Player();
		currentSearch=new Search();
	}
	/**
	 * Same as the default constructor, except that this constructor allows
	 * for the use of varying grid size of the table used in the game.
	 * @param gridSize Size of the grid used in the game (no of rows (or)
	 * no of columns (ie) both must be equal).
	 */
	public Game(int gridSize)
	{
		gameGrid=new Table(gridSize,gridSize);
		playerOne=new Player("UserPlayer");
		playerTwo=new Player();
		currentSearch=new Search();
	}
	/**
	 * This is the constructor to be called in the main function of the program,
	 * if the grid size is more than 3x3.
	 * It takes in two parameters to personalize the game more effectively
	 * by taking a parameter for the name of the player and a grid size.
	 * @param gridSize Size of the grid used in the game (no of rows (or)
	 * no of columns (ie) both must be equal).
	 * @param userName Name of the Player
	 */
	public Game(int gridSize,String userName)
	{
		gameGrid=new Table(gridSize,gridSize);
		playerOne=new Player(userName);
		playerTwo=new Player();
		currentSearch=new Search();
	}
	/**
	 * This is the constructor to be called in the main function of the program,
	 * if the grid size can be chosen, and when the player wants to be able to
	 * choose to be the first or the second player.
	 * It takes in three parameters to personalize the game more effectively
	 * by taking a parameter for the name of the player and a grid size
	 * and an integer to decide whether the user needs to be player one or two.
	 * @param gridSize Size of the grid used in the game (no of rows (or)
	 * no of columns (ie) both must be equal).
	 * @param userName Name of the Player
	 */
	public Game(int gridSize,String userName,int turn)
	{
		gameGrid=new Table(gridSize,gridSize);
		if(turn==1)
		{
			playerOne=new Player(userName);
			playerTwo=new Player();
		}
		else
		{
			playerOne=new Player();
			playerTwo=new Player(userName);
		}
		currentSearch=new Search();
	}
	/**
	 * This is the constructor called in the main function of the program.
	 * It takes in a string parameter to personalize the game more effectively
	 * by taking a parameter for the name of the player. Creates a 3x3 grid.
	 * @param userName Name of the Player.
	 */
	public Game(String userName)
	{
		gameGrid=new Table(3,3);
		playerOne=new Player(userName);
		playerTwo=new Player();
		currentSearch=new Search();
	}
	/**
	 * Initialization method of the <code>Game</code> class.
	 * Initially, simple used as a debug-friendly method to know if things were
	 * working. Now, its the start of the program once it receives enough
	 * parameters for creating the game. It tells the sign of the players and
	 * their respective names. Also sets the reference of current player to
	 * player one.
	 */
	public void init()
	{
		System.out.println("Game Initialized...");
		gameGrid.printTable();
		System.out.println(playerOne.getPlayerName()+" "+playerOne.getPlayerSign());
		System.out.println(playerTwo.getPlayerName()+" "+playerTwo.getPlayerSign());
		currentPlayer=playerOne;
	}
	/**
	 * This method is used to get inputs for the table from both players.
	 * As of now, this program supports only one user player. If the player
	 * is of type AI, then the search object is used, else the player enters
	 * the index of the box he wants to sign in.
	 */
	public void getInput()
	{
		int index=gameGrid.getFirstDashIndex();
		Move moveToMake;
		@SuppressWarnings("resource")
		Scanner inputStream=new Scanner(System.in);
		if(currentPlayer.getPlayerType()=="AI")
		{
			currentSearch=new Search(gameGrid,currentPlayer);
			moveToMake=currentSearch.getBestMove();
			gameGrid=gameGrid.makeMove(moveToMake);
		}
		else
		{
			do{
				do{
					if((index<0)||(index>gameGrid.getSizeOfTable()))
						System.out.println("Please enter valid values for index (0-"+gameGrid.getSizeOfTable()+").");
					if(!gameGrid.isEmpty(index-1))
						System.out.println("Please enter the index of an empty box.");
					System.out.println("Enter the index of the box you want to sign, "+currentPlayer.getPlayerName()+":");
					index=inputStream.nextInt();
				}while((index<0)||(index>gameGrid.getSizeOfTable()));
			}while(!gameGrid.isEmpty(index-1));
			gameGrid.updateTable(index-1, currentPlayer.getPlayerSign());
		}
		if(currentPlayer==playerOne)
			currentPlayer=playerTwo;
		else
			currentPlayer=playerOne;
		System.out.println("Grid updated...");
		gameGrid.printTable();
		if(gameGrid.isComplete()!=-1)
			gameOver=true;
	}
	/**
	 * This is the method of the game called by the main function of the program.
	 * It just gets the values from both players and keeps updating the grid until
	 * the game is over.
	 */
	public void play()
	{
		for(int i=0;((gameOver==false)&&(i<gameGrid.getSizeOfTable()));i++)
			getInput();
		if(gameGrid.isComplete()==1)
			System.out.println(playerOne.getPlayerName()+"("+playerOne.getPlayerType()+") won!");
		else if(gameGrid.isComplete()==0)
			System.out.println(playerTwo.getPlayerName()+"("+playerTwo.getPlayerType()+") won!");
		else
			System.out.println("Game drawn!");
	}
}
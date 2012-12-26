package com.tictactoe.search;

import com.tictactoe.move.*;
import com.tictactoe.player.*;
import com.tictactoe.table.*;

/**
 * The <code>Search</code> class is a class made useful for making
 * searches in a known and generated search tree. It is not yet
 * fully implemented as envisioned but will suffice for now.
 * Adding a search object to the game is necessary.
 * (As of now, the thought that players, and not games, should have
 * search objects, have been discarded, and only objects of the
 * <code>Game</code> class have objects of this class. This may be
 * alternate approach may be looked into in the future.)
 * @author Kenshin Himura
 *
 */
public class Search {
	/**
	 * Holds the current player for whom it is searching, as this is
	 * necessary for the search, especially if algorithms like NegaMax
	 * are used.
	 */
	private Player currentPlayer;
	/**
	 * Holds the current state of the table used in the game. Passed
	 * as argument during construction and MUST remain unchanged, as
	 * Java passes arguments as reference by default, and any change
	 * will be reflected back.
	 */
	private Table currentState;
	/**
	 * The current best state of the search, may be updated as and when
	 * search gets deeper or shallower, with respect to the best move
	 * found.
	 */
	private Table bestState;
	/**
	 * Holds the current best move found in the search.
	 */
	private Move bestMove;
	/**
	 * Used to hold the score of the best state found so far in the search.
	 */
	private float bestScore;
	/**
	 * Holds the required search depth for the current instance of class
	 * <code>Search</code>.
	 */
	private int searchDepth;
	/**
	 * Unused as of now, but may be used to enforce a time constraint on
	 * the search. Will most likely be specified in milliseconds.
	 */
	private float time;
	/**
	 * The moveStack is an array of objects of type <code>Move</code>
	 * and is produced by adding all legal moves on the current state
	 * and pushing them to the moveStack. This is performed by the moveGen()
	 * method.
	 */
	private Move moveStack[];
	/**
	 * This holds the maximum number of possible legal moves for the given
	 * state and so by default acts as the sizeOf() or length member of the
	 * moveStack array.
	 */
	private int maxMoves;
	/**
	 * Default constructor of <code>Search</code> class. No use in default
	 * construction of a search object, so an empty constructor is made, to enable
	 * definition of parameterized constructors.
	 */
	public Search()
	{
		
	}
	/**
	 * Since the <code>Search</code> class is independant of other classes, viz.
	 * Player, Game and Table, the current player and the current state of the
	 * game must be passed on to the constructor to properly initialize an
	 * instantiation of this class.
	 * @param currentState Current State of the Game (Type <code>Table</code>)
	 * @param currentPlayer Current Player of the Game (Type <code>Player</code>)
	 */
	public Search(Table currentState,Player currentPlayer)
	{
		setCurrentState(currentState);
		setCurrentPlayer(currentPlayer);
		setSearchDepth(this.currentState.getNoOfDs());
	}
	/**
	 * Generic getter method to get the current state of the Search.
	 * It, being a private variable, can only be accessed using public
	 * getter and setter methods.
	 * @return Current State of the Search
	 */
	public Table getCurrentState() {
		return currentState;
	}
	/**
	 * Generic setter method to set the current state of the Search.
	 * It, being a private variable, can only be accessed using public
	 * getter and setter methods.
	 * @param currentState Current State of the Search
	 */
	public void setCurrentState(Table currentState) {
		this.currentState = currentState;
	}
	/**
	 * Generic getter method to get the current best state of the Search.
	 * It, being a private variable, can only be accessed using public
	 * getter and setter methods.
	 * @return Current Best State of the Search
	 */
	public Table getBestState() {
		return bestState;
	}
	/**
	 * Generic setter method to set the current best state of the Search.
	 * It, being a private variable, can only be accessed using public
	 * getter and setter methods.
	 * @param bestState Current Best State of the Search
	 */
	public void setBestState(Table bestState) {
		this.bestState = bestState;
	}
	/**
	 * Generic getter method to get the current best score of the Search.
	 * It, being a private variable, can only be accessed using public
	 * getter and setter methods.
	 * @return Current Best Score of the Search
	 */
	public float getBestScore() {
		return bestScore;
	}
	/**
	 * Generic setter method to set the best score of the Search.
	 * It, being a private variable, can only be accessed using public
	 * getter and setter methods.
	 * @param bestScore Best Score of the Search
	 */
	public void setBestScore(float bestScore) {
		this.bestScore = bestScore;
	}
	/**
	 * Generic getter method to get the depth of the Search.
	 * It, being a private variable, can only be accessed using public
	 * getter and setter methods.
	 * @return Depth of the Search
	 */
	public int getSearchDepth() {
		return searchDepth;
	}
	/**
	 * Generic setter method to set the depth of the Search.
	 * It, being a private variable, can only be accessed using public
	 * getter and setter methods.
	 * @param searchDepth Depth of the Search
	 */
	public void setSearchDepth(int searchDepth) {
		this.searchDepth = searchDepth;
	}
	/**
	 * Generic getter method to get the time limit of the Search.
	 * It, being a private variable, can only be accessed using public
	 * getter and setter methods.
	 * @return Time Limit of the Search in milliseconds
	 */
	public float getTime() {
		return time;
	}
	/**
	 * Generic setter method to set the time limit of the Search.
	 * It, being a private variable, can only be accessed using public
	 * getter and setter methods.
	 * @param time Time Limit of the Search in milliseconds
	 */
	public void setTime(float time) {
		this.time = time;
	}
	/**
	 * The generic getter for the bestMove variable has been modified such
	 * that it generates moves using the moveGen() function, and reads
	 * every move from the moveStack, evaluates the resulting states (table
	 * positions) and then picks the best move out of the moveStack and
	 * sets this as the best move, before returning it.
	 * @return Best Move found in the current Search
	 */
	public Move getBestMove(){
		bestScore=-100;
		float currentScore=0;
		moveGen();
		for(int i=0;i<maxMoves;i++)
		{
			currentScore=moveStack[i].getTableNext().getScore();
			if(currentScore>bestScore)
			{
				setBestScore(currentScore);
				setBestMove(moveStack[i]);
			}
		}
		return bestMove;
	}
	/**
	 * Generic setter method to set the best move of the Search.
	 * It, being a private variable, can only be accessed using public
	 * getter and setter methods.
	 * @param bestMove Best Move of the Search
	 */
	public void setBestMove(Move bestMove) {
		this.bestMove = bestMove;
	}
	/**
	 * This method generates the legal moves for the current Search object
	 * and pushes the moves to the moveStack.
	 */
	public void moveGen()
	{
		maxMoves=currentState.getNoOfDs();
		moveStack=new Move[maxMoves];
		int j=-1;
		for(int i=0;i<maxMoves;i++)
		{
			for(;j<8;)
			{
				j++;
				if(currentState.isEmpty(j))
				{
					Table nextState=currentState.clone();
					nextState.updateTable(j, currentPlayer.getPlayerSign());
					moveStack[i]=new Move(currentState,nextState);
					break;
				}
			}
		}
	}
	/**
	 * Generic getter method to get the current of the Search.
	 * It, being a private variable, can only be accessed using public
	 * getter and setter methods.
	 * @return Current player of the Search
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	/**
	 * Generic setter method to set the current player of the Search.
	 * It, being a private variable, can only be accessed using public
	 * getter and setter methods.
	 * @param currentPlayer Current player of the Search
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
}
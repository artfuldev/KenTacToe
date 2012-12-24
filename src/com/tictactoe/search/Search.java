package com.tictactoe.search;

import com.tictactoe.move.*;
import com.tictactoe.player.*;
import com.tictactoe.table.*;

public class Search {
	private Player currentPlayer;
	private Table currentState;
	private Table bestState;
	private Move bestMove;
	private float bestScore;
	private int searchDepth;
	private float time;
	private Move moveStack[];
	private int maxMoves;
	public Search()
	{
		
	}
	public Search(Table currentState,Player currentPlayer)
	{
		setCurrentState(currentState);
		setCurrentPlayer(currentPlayer);
		setSearchDepth(this.currentState.getNoOfDs());
	}
	public Table getCurrentState() {
		return currentState;
	}
	public void setCurrentState(Table currentState) {
		this.currentState = currentState;
	}
	public Table getBestState() {
		return bestState;
	}
	public void setBestState(Table bestState) {
		this.bestState = bestState;
	}
	public float getBestScore() {
		return bestScore;
	}
	public void setBestScore(float bestScore) {
		this.bestScore = bestScore;
	}
	public int getSearchDepth() {
		return searchDepth;
	}
	public void setSearchDepth(int searchDepth) {
		this.searchDepth = searchDepth;
	}
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
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
	public void setBestMove(Move bestMove) {
		this.bestMove = bestMove;
	}
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
					//clone() is working properly now. Redesigned it like a copy constructor.
					//init() really helped.
					nextState.updateTable(j, currentPlayer.getPlayerSign());
					moveStack[i]=new Move(currentState,nextState);
					System.out.println("New Move Generated...");
					moveStack[i].getTableCurrent().printTable();
					System.out.println(" to ");
					moveStack[i].getTableNext().printTable();
					break;
				}
			}
		}
		//MoveStack Generated and filled with valid moves
	}
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
}

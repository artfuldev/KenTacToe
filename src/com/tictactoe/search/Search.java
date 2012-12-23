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
		this.currentState=new Table();
		this.setCurrentPlayer(new Player());
		setCurrentState(currentState);
		setCurrentPlayer(currentPlayer);
	}
	public Search(Table currentState,int searchDepth)
	{
		this.currentState=new Table();
		setCurrentState(currentState);
		setSearchDepth(searchDepth);
	}
	public Search(Table currentState,float time)
	{
		this.currentState=new Table();
		setCurrentState(currentState);
		setTime(time);
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
	public Move getBestMove() {
		bestScore=0;
		float currentScore=0;
		moveGen();
		for(int i=0;i<maxMoves;i++)
		{
			currentState=moveStack[i].getTableNext();
			currentScore=currentState.getScore();
			if(currentScore>bestScore)
			{
				bestScore=currentScore;
				bestMove=moveStack[i];
			}
			currentState=moveStack[i].getTableCurrent();
		}
		return bestMove;
	}
	public void setBestMove(Move bestMove) {
		this.bestMove = bestMove;
	}
	public void moveGen()
	{
		for(int i=0;i<9;i++)
			if(currentState.isEmpty(i))
				maxMoves++;
		moveStack=new Move[maxMoves];
		for(int i=0,j=0;i<maxMoves;i++)
			for(;j<9;j++)
				if(currentState.isEmpty(j))
				{
					Table nextState=currentState;
					nextState.updateTable(j, currentPlayer.getPlayerSign());
					moveStack[i]=new Move(currentState,nextState);
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

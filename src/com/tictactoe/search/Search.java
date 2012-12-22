package com.tictactoe.search;

import com.tictactoe.move.*;
import com.tictactoe.table.*;

public class Search {
	private Table currentState;
	private Table bestState;
	private Move bestMove;
	private float bestScore;
	private int searchDepth;
	private float time;
	public Search()
	{
		
	}
	public Search(Table currentState)
	{
		this.currentState=new Table();
		setCurrentState(currentState);
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
		return bestMove;
	}
	public void setBestMove(Move bestMove) {
		this.bestMove = bestMove;
	}
}

package com.tictactoe.player;

public class Player {
	private String playerName;
	private String playerType;
	private char playerSign;
	static private int playerCount=1;
	public Player()
	{
		setPlayerName("DefaultPlayer"+playerCount);
		setPlayerType("AI");
		setPlayerSign((char)('O'+playerCount-1));
		playerCount++;
	}
	public Player(String playerName)
	{
		setPlayerName(playerName);
		setPlayerType("User");
		setPlayerSign('X');
		playerCount++;
		
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerType() {
		return playerType;
	}
	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}
	public char getPlayerSign() {
		return playerSign;
	}
	public void setPlayerSign(char playerSign) {
		this.playerSign = playerSign;
	}
}

package com.tictactoe.player;

/**
 * The <code>Player</code> class is a must for every player-based game.
 * It holds the player attributes and player-related methods.
 * This class in particular, designed specifically for the Tic Tac Toe
 * implementation has very few important attributes to make use of. Name and Type
 * are a must. Sign of the player is also required (players siign in the boxes in Tic Tac Toe).
 * Additionally, a static variable to count the number of Players may be used (if 2 AI are used,
 * for training, etc.)
 * @author Kenshin Himura
 *
 */
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

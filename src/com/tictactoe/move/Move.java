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

package com.tictactoe.move;

import com.tictactoe.table.Table;

/**
 * The <code>Move</code> class is used to represent a move. A <code>Game</code> will
 * have some players and many moves. To represent this is a programming fashion,
 * this class has been defined. Several move objects can be placed on a move stack
 * of the Game (later, not as of now). Also, a Search must generate a set of possible
 * legal moves and then process them to find a best move.
 * It contains two tables, a source and a destination.
 * @author Kenshin Himura
 *
 */
public class Move
{
	/**
	 * Holds the current table. Source of the move.
	 */
	private Table tableCurrent;
	/**
	 * Holds the table to be obtained after the move has been made.
	 */
	private Table tableNext;
	/**
	 * Default constructor of the class <code>Move</code>.
	 * Calls the default constructors of the two <code>Table</code> objects.
	 */
	public Move()
	{
		tableCurrent=new Table();
		tableNext=new Table();
	}
	/**
	 * Constructor of the <code>Move</code> class used for genrating moves.
	 * Takes a source table and a destination table as parameters.
	 * @param tableFrom The current state of the table (before the move has been made).
	 * @param tableTo The next state of the table (after the move has been made).
	 */
	public Move(Table tableFrom,Table tableTo)
	{
		tableCurrent=tableFrom;
		tableNext=tableTo;
	}
	/**
	 * Generic getter method of the tableCurrent variable. It, being private,
	 * has to be accessed only by public getter and setter methods.
	 * Is generally used in place of unMakeMove() function here.
	 * @return The current state of the table (before the move has been made).
	 */
	public Table getTableCurrent()
	{
		return tableCurrent;
	}
	/**
	 * Generic getter method of the tableNext variable. It, being private,
	 * has to be accessed only by public getter and setter methods.
	 * Is generally used in place of makeMove() function here.
	 * @return The next state of the table (after the move has been made).
	 */
	public Table getTableNext()
	{
		return tableNext;
	}
}
package com.tictactoe.move;

import com.tictactoe.table.Table;

public class Move {
	private Table tableCurrent;
	private Table tableNext;
	public Move()
	{
		tableCurrent=new Table();
		tableNext=new Table();
	}
	public Move(Table tableFrom,Table tableTo)
	{
		tableCurrent=new Table();
		tableNext=new Table();
		tableCurrent=tableFrom;
		tableNext=tableTo;
	}
	public Table getTableCurrent() {
		return tableCurrent;
	}
	public Table getTableNext() {
		return tableNext;
	}
	public Table makeMove()
	{
		return tableNext;
	}
	public Table unmakeMove()
	{
		return tableCurrent;
	}
}

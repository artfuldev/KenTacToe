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

package com.tictactoe.table;

import com.tictactoe.move.Move;

/**
 * The <code>Table</code> class forms the basic framework of grid like structures.
 * Used to create a game grid in the tic tac toe game class.
 * Cells, Rows, Columns and Diagonals are nested in this class to properly
 * define the inter-relationships and not consume excess memory, while
 * still implementing pure object oriented programming (ie) Tables contain
 * rows, columns, cells, while rows, columns also contain cells, which are
 * not duplicates but references to the actual cells of the table itself.
 * @author Kenshin Himura
 */
public class Table implements Cloneable
{
	/**
	 * The <code>Cell</code> is the basic building block of the table.
	 * In addition to remembering of which table it is a part, it also
	 * returns rows and columns it belongs to and also has row and column indices
	 * which can be set during construction of the table.
	 * @author Kenshin Himura
	 */
	public class Cell
	{
		/**
		 * Represents the value of the cell.
		 * Represents a character.
		 * Usually will be set to '-'(empty cell),'X' OR 'O'
		 */
		private char value;
		/**
		 * Holds the row index of the cell in the parent table.
		 */
		private byte rowIndex;
		/**
		 * Holds the column index of the cell in the parent table.
		 */
		private byte colIndex;
		/**
		 * Constructor of Cell.
		 * Sets the default value of the cell to <code>'-'</code>
		 */
		public Cell()
		{
			this.value='-';
		}
		/**
		 * <code>getCol()</code> is used to get the parent column of the cell.
		 * It returns a reference of type Col, which is the class which represents columns.
		 * @return	Column of the cell
		 */
		public Col getCol()
		{
			return Table.this.getCol(colIndex);
		}
		/**
		 * This method is used to get the column index of the cell.
		 * @return	Column Index of the cell
		 */
		public byte getColIndex()
		{
			return colIndex;
		}
		/**
		 * <code>getRow()</code> is used to get the parent row of the cell.
		 * It returns a reference of type Row, which is the class which represents rows.
		 * @return	Row of the cell
		 */
		public Row getRow()
		{
			return Table.this.getRow(rowIndex);
		}
		/**
		 * This method is used to get the row index of the cell.
		 * @return	Row Index of the cell
		 */
		public byte getRowIndex()
		{
			return rowIndex;
		}
		/**
		 * <code>getTable()</code> is used to get the parent Table of the cell.
		 * It returns a reference of type Table, which is the class which represents tables.
		 * @return	Table(parent) of the cell
		 */
		public Table getTable()
		{
			return Table.this;
		}
		/**
		 * This method is used to obtain the private member <code>value</code> of the cell
		 * @return Value of cell
		 */
		public char getValue()
		{
			return value;
		}
		/**
		 * This method sets the column index of the cell.
		 * Used automatically during Table construction.
		 * Need/Must not be used anywhere else.
		 */
		public void setColIndex(byte colIndex)
		{
			this.colIndex=colIndex;
		}
		/**
		 * This method sets the row index of the cell.
		 * Used automatically during Table construction.
		 * Need/Must not be used anywhere else.
		 */
		public void setRowIndex(byte rowIndex)
		{
			this.rowIndex=rowIndex;
		}
		/**
		 * This is the generic set function of the <code>value</code>
		 * member of the class <code>Cell</code>. Since it is a private member, the set
		 * function is used to set it.
		 * @param value
		 */
		public void setValue(char value)
		{
			this.value=value;
		}
	}
	/**
	 * The <code>Col</code> class represents the column of a table.
	 * It also remembers its corresponding table and its index in the table,
	 * and also the number of rows in the table as so many cells should be
	 * instantiated during construction.
	 * @author Kenshin Himura
	 *
	 */
	public class Col
	{
		/**
		 * An array of type <code>Cell</code>.
		 * Holds the cells of the column, row-wise
		 */
		private Cell cells[];
		/**
		 * Holds the column index of the colummn in its parent table.
		 */
		private byte colIndex;
		/**
		 * Default constructor of class
		 * <code>Col</code>, which represents columns of a <code>Table</code> class.
		 */
		public Col()
		{
			cells=new Cell[noOfRows];
			for(byte i=0;i<noOfRows;i++)
				cells[i]=new Cell();
		}
		/**
		 * This method is used to get a specific cell of a column(j) using its row index.
		 * @param rowIndex (i)
		 * @return Cell of the particular row index(i) in the column(j),
		 * (i.e.)Cell[i][j] of Table
		 */
		public Cell getCell(byte rowIndex)
		{
			return cells[rowIndex];
		}
		/**
		 * This method is used to set a cell to the column by specifying its row index
		 * @param rowIndex row index of the cell
		 * @param cell Cell to be set
		 */
		public void setCell(byte rowIndex, Cell cell)
		{
			this.cells[rowIndex]=cell;
		}
		/**
		 * This method is used to get all the cells of the particular column.
		 * @return All cells of column
		 */
		public Cell[] getCells()
		{
			return cells;
		}
		/**
		 * This method is used to get the column index of the column in table it pertains to.
		 * @return Column index
		 */
		public byte getColIndex()
		{
			return colIndex;
		}
		/**
		 * <code>getTable()</code> is used to get the parent <code>Table</code> of the column.
		 * It returns a reference of type <code>Table</code>, which is the class which represents tables.
		 * @return	Table(parent) of the column
		 */
		public Table getTable()
		{
			return Table.this;
		}
		/**
		 * This method is used to check if the column is complete,
		 * (i.e.) filled with the same characters.
		 * Note that '-' denotes an empty cell and does not fulfill the completion criterion.
		 * @return -1 if not Complete, 1 if X is Complete, 0 if O is Complete
		 */
		public byte isComplete()
		{
			if(cells[0].getValue()!='-')
			{
				byte j=0;
				for(byte i=0;i<(noOfRows-1);i++)
					if(cells[i].getValue()==cells[i+1].getValue())
						j++;
				if(j==(noOfRows-1))
				{
					if(cells[0].getValue()=='X')
						return 1;
					else
						return 0;
				}
			}
			return -1;	
		}
		/**
		 * Generic setter method to set column index of the column of the table
		 * @param colIndex
		 */
		public void setColIndex(byte colIndex)
		{
			this.colIndex = colIndex;
		}
	}
	/**
	 * The <code>Diag</code> class is simply the instance of a diagonal in square tables,
	 * used here as it will prove useful to check if diagonals have been completed
	 * in a game of tic tac toe. Needless to point out, it has to remember its table and its max no.
	 * of elements.
	 * @author Kenshin Himura
	 *
	 */
	public class Diag
	{
		/**
		 * An array of type <code>Cell</code>.
		 * Holds the cells of the diagonal, row-wise
		 */
		private Cell cells[];
		/**
		 * Default constructor of the <code>Diag</code> class
		 */
		public Diag()
		{
			cells=new Cell[noOfRows];
			for(byte i=0;i<noOfRows;i++)
				cells[i]=new Cell();
		}
		/**
		 * This method is used to get a specific cell of a diagonal using its index(i).
		 * @param index (i)
		 * @return Cell of the particular row index(i) in the column(i),
		 * (i.e.)Cell[i][i] of Table
		 */
		public Cell getCell(byte index)
		{
			return cells[index];
		}
		/**
		 * This method is used to get all the cells of the particular diagonal.
		 * @return All cells of the diagonal
		 */
		public Cell[] getCells()
		{
			return cells;
		}
		/**
		 * This method is used to check if the diagonal is complete,
		 * (i.e.) filled with the same characters.
		 * Note that '-' denotes an empty cell and does not fulfill the completion criterion.
		 * @return -1 if not Complete, 1 if X is Complete, 0 if O is Complete
		 */
		public byte isComplete()
		{
			if(cells[0].getValue()!='-')
			{
				byte j=0;
				for(byte i=0;i<(noOfRows-1);i++)
					if(cells[i].getValue()==cells[i+1].getValue())
						j++;
				if(j==(noOfRows-1))
				{
					if(cells[0].getValue()=='X')
						return 1;
					else
						return 0;
				}
			}
			return -1;	
		}
		/**
		 * <code>getTable()</code> is used to get the parent <code>Table</code> of the diagonal.
		 * It returns a reference of type <code>Table</code>, which is the class which represents tables.
		 * @return	Table(parent) of the diagonal
		 */
		public Table getTable()
		{
			return Table.this;
		}
		/**
		 * Generic setter method to set the cell of an index of a diagonal of the table
		 * @param i index of the cell in the diagonal
		 * @param cell Cell to be set
		 */
		public void setCell(byte i, Cell cell)
		{
			this.cells[i]=cell;
		}
	}
	/**
	 * The <code>Row</code> class represents the row of a table.
	 * It also remembers its corresponding table and its index in the table,
	 * and also the number of columns in the table as so many cells should be
	 * instantiated during construction.
	 * @author Kenshin Himura
	 *
	 */
	public class Row
	{
		/**
		 * An array of type <code>Cell</code>.
		 * Holds the cells of the row, column-wise
		 */
		private Cell cells[];
		/**
		 * Holds the column index of the row in its parent table.
		 */
		private byte rowIndex;
		/**
		 * Default constructor of the <code>Row</code> class
		 */
		public Row()
		{
			cells=new Cell[noOfCols];
			for(byte i=0;i<noOfCols;i++)
				cells[i]=new Cell();
		}
		/**
		 * This method is used to get a specific cell of a row(i) using its column index.
		 * @param colIndex (j)
		 * @return Cell of the particular column index(j) in the row(i),
		 * (i.e.)Cell[i][j] of Table
		 */
		public Cell getCell(byte colIndex)
		{
			return cells[colIndex];
		}
		/**
		 * This method is used to set a cell to the row by specifying its col index
		 * @param colIndex column index of the cell
		 * @param cell Cell to be set
		 */
		public void setCell(byte colIndex, Cell cell)
		{
			this.cells[colIndex]=cell;
		}
		/**
		 * This method is used to get all the cells of the particular row.
		 * @return All cells of the row
		 */
		public Cell[] getCells()
		{
			return cells;
		}
		/**
		 * This method is used to get the row index of the row in table it pertains to.
		 * @return Row index
		 */
		public byte getRowIndex()
		{
			return rowIndex;
		}
		/**
		 * <code>getTable()</code> is used to get the parent <code>Table</code> of the row.
		 * It returns a reference of type <code>Table</code>, which is the class which represents tables.
		 * @return	Table(parent) of the row
		 */
		public Table getTable()
		{
			return Table.this;
		}
		/**
		 * This method is used to check if the row is complete,
		 * (i.e.) filled with the same characters.
		 * Note that '-' denotes an empty cell and does not fulfill the completion criterion.
		 * @return -1 if not Complete, 1 if X is Complete, 0 if O is Complete
		 */
		public byte isComplete()
		{
			if(cells[0].getValue()!='-')
			{
				byte j=0;
				for(byte i=0;i<(noOfCols-1);i++)
					if(cells[i].getValue()==cells[i+1].getValue())
						j++;
				if(j==(noOfCols-1))
				{
					if(cells[0].getValue()=='X')
						return 1;
					else
						return 0;
				}
			}
			return -1;	
		}
		/**
		 * Generic setter method to set row index of the row of the table
		 * @param rowIndex
		 */
		public void setRowIndex(byte rowIndex)
		{
			this.rowIndex = rowIndex;
		}
	}
	/**
	 * An array of type <code>Cell</code>.
	 * Holds the cells of the table, row-wise,column-wise, maximum of noOfRowsxnoOfColumns cells
	 */
	private Cell cells[];
	/**
	 * An array of type <code>Row</code>.
	 * Holds the rows of the table, column-wise.
	 */
	private Row rows[];
	/**
	 * An array of type <code>Col</code>.
	 * Holds the columns of the table, row-wise.
	 */
	private Col cols[];
	/**
	 * An array of type <code>Diag</code>.
	 * Holds the diagonals of the table, row-wise.
	 */
	private Diag diags[];
	/**
	 * Holds the number of rows of the table at any time.
	 */
	private byte noOfRows;
	/**
	 * Holds the number of columns of the table at any time.
	 */
	private byte noOfCols;
	/**
	 * Holds the number of cells (rows * cols) of the table at any time.
	 * Set to 0 if the number of rows and columns are zero.
	 */
	private byte sizeOfTable;
	/**
	 * Represents the score of the table as a signed double value,
	 * from X's perspective.
	 */
	private double score=0;
	/**
	 * Holds the winning score of the table, from X's perspective.
	 */
	private double winScore;
	/**
	 * Default Constructor of <code>Table</code> class.
	 * Never used, simply defined to define parameterized constructors.
	 */
	public Table()
	{
		
	}
	/**
	 * This is the constructor of class <code>Table</code> which is used in the program.
	 * It takes two parameters which may not be equal and constructs a table with the specified number of rows and columns and sets proper references
	 * to the cells of the table so that they can be accessed both by the <code>Row</code> objects and <code>Col</code> objects of the <code>Table</code> object.
	 * Also, the constructor calls the <code>init()</code> function of the <code>Table</code> class, to initialize the <code>Table</code>.
	 * @param noOfRows The number of rows in the table to be created.
	 * @param noOfCols The number of columns in the table to be created.
	 */
	public Table(byte noOfRows,byte noOfCols)
	{
		this.noOfRows=noOfRows;
		this.noOfCols=noOfCols;
		this.sizeOfTable=(byte)(noOfRows*noOfCols);
		rows=new Row[this.noOfRows];
		for(byte i=0;i<noOfRows;i++)
			rows[i]=new Row();
		cols=new Col[this.noOfCols];
		for(byte i=0;i<noOfCols;i++)
			cols[i]=new Col();
		cells=new Cell[this.sizeOfTable];
		for(byte i=0;i<sizeOfTable;i++)
			cells[i]=new Cell();
		diags=new Diag[2];
		for(byte i=0;i<2;i++)
			diags[i]=new Diag();
		setWinScore(Math.pow(sizeOfTable+1, sizeOfTable+1)-1);
		init();
	}
	/**
	 * This method is used to obtain the cells of a specific column of the table using its column index.
	 * @param colIndex Index of the column in the table
	 * @return Array of type <code>Cell</code> of the specified column of the table
	 */
	public Col getCol(byte colIndex)
	{
		return cols[colIndex];
	}
	/**
	 * Generic get method to get the number of columns of the <code>Table</code>.
	 * Almost never used.
	 * @return The number of columns of the <code>Table</code>.
	 */
	public byte getNoOfCols()
	{
		return noOfCols;
	}
	/**
	 * Generic get method to get the number of rows of the <code>Table</code>.
	 * Almost never used.
	 * @return The number of rows of the <code>Table</code>.
	 */
	public byte getNoOfRows()
	{
		return noOfRows;
	}
	/**
	 * This method is used to obtain the cells of a specific row of the table using its column index.
	 * @param rowIndex Index of the row in the table
	 * @return Array of type <code>Cell</code> of the specified row of the table
	 */
	public Row getRow(byte rowIndex)
	{
		return rows[rowIndex];
	}
	/**
	 * This method is used to initialize the </code>Table</code>.
	 * References are set properly here.
	 */
	public void init()
	{
		byte index;
		for(byte i=0;i<this.noOfRows;i++)
			for(byte j=0;j<this.noOfCols;j++)
			{
				index=(byte)((i*noOfRows)+j);
				getRow(i).setCell(j, cells[index]);
				getCol(j).setCell(i, cells[index]);
				getRow(i).getCell(j).setColIndex(j);
				getRow(i).getCell(j).setRowIndex(i);
				getRow(i).setRowIndex(i);
				getCol(j).setColIndex(j);
				//left diagonal
				if(i==j)
					diags[0].setCell(i,cells[index]);
				//right diagonal
				if((i+j+1)==noOfRows)
					diags[1].setCell(i,cells[index]);
			}
	}
	/**
	 * This method is used to print the <code>Table</code>, either for debugging or display purposes.
	 * The printing is in the form of a matrix of the no. of rows and columns as of the table used.
	 */
	public void printTable()
	{
		for(byte i=0;i<noOfRows;i++)
		{
			for(byte j=0;j<noOfCols;j++)
				System.out.print(getRow(i).getCell(j).getValue()+" ");
			System.out.println();
		}
	}
	/**
	 * This method is used to update a specific cell of the table with a specific value(character)
	 * @param index Index of the cell of the table to be updated, can take values between 0 to (sizeOfTable-1).
	 * @param updateChar Character to be updated in the specified <code>Cell</code>'s value data member.
	 */
	public void updateTable(byte index, char updateChar)
	{
		cells[index].setValue(updateChar);
	}
	/**
	 * This method is used to check if the table is complete,
	 * (i.e.) if any rows, columns or diagonals are filled with the same characters.
	 * Note that '-' denotes an empty cell and does not fulfill the completion criterion.
	 * @return -1 if not Complete, 1 if X is Complete, 0 if O is Complete
	 */
	public byte isComplete()
	{
		for(byte i=0;i<2;i++)
			if(diags[i].isComplete()!=-1)
				return diags[i].isComplete();
		for(byte i=0;i<this.noOfRows;i++)
			if(rows[i].isComplete()!=-1)
				return rows[i].isComplete();
		for(byte i=0;i<this.noOfCols;i++)
			if(cols[i].isComplete()!=-1)
				return cols[i].isComplete();
		return -1;	
	}
	/**
	 * This method is used to check if a given cell of a table is empty.
	 * @param index Index of the cell of the table, can take values from 0 to (sizeOfTable-1).
	 * @return <code>True</code> if the specified <code>Cell</code> is empty, <code>False</code> otherwise.
	 */
	public boolean isEmpty(byte index)
	{
		if(cells[index].getValue()=='-')
			return true;
		return false;
	}
	/**
	 * This method is used to get the total number of blank cells in the table.
	 * Used in calculation of score, and in the generation of next possible moves. 
	 * @return The total number of blank cells in the table.
	 */
	public byte getNoOfDs()
	{
		byte returnValue=0;
		for(byte i=0;i<sizeOfTable;i++)
			if(isEmpty(i))
				returnValue++;
		return returnValue;
	}
	/**
	 * This method is used to get the score of the current Table.
	 * Score is returned as a double value, from X's perspective of the game at
	 * all times.
	 * The score calculation is made here (This is the evaluation function).
	 * @return Score of the table as a double value.
	 */
	public double getScore()
	{
		if(isComplete()==1)
			return (Math.pow(sizeOfTable+1,sizeOfTable+1)-1);
		if(isComplete()==0)
			return -(Math.pow(sizeOfTable+1,sizeOfTable+1)-1);
		score=0;
		score-=getOScore();
		score+=getXScore();
		score+=linesWithoutO();
		score-=linesWithoutX();
		score+=winningChances();
		return score;
	}
	/**
	 * This method is used to find the winning chances of both sides in the game,
	 * eg, if there is a fork, the winning chance of the forking side is 2 in a 3x3
	 * game. Consequently in an nxn game, there may be many moves which may produce
	 * more than one winning chance.
	 * Specifically, it calculates the number of winning chances for X and then O
	 * and then returns the difference, multiplied by the one with a greater
	 * closeness to finish the game.
	 * @return The winning chances, positive if there are more winning chances for
	 * X and negative if there are more winning chances for O, as scoring is done
	 * from X's perspective always.
	 */
	private double winningChances() {
		double returnValue=0;
		int maxCloseness=-1;
		byte count1, count2, maxCloseCountX=-1;
		int closenessCount;
		byte maxCloseCountO=-1;
		for(byte i=0;i<noOfRows;i++)
		{
			closenessCount=0;
			count1=0;
			count2=0;
			for(byte j=0;j<noOfCols;j++)
			{
				if(rows[i].getCell(j).getValue()=='X')
					count1++;
				if(rows[i].getCell(j).getValue()=='O')
				{
					count1=0;
					break;
				}
				if(rows[i].getCell(j).getValue()=='-')
					count2++;
			}
			if(count1!=0)
				closenessCount=count1+count2;
			if(closenessCount!=0)
			{
				if(closenessCount>maxCloseness)
				{
					maxCloseness=closenessCount;
					maxCloseCountX=1;
				}
				if(closenessCount==maxCloseness)
					maxCloseCountX++;
			}
		}
		for(byte i=0;i<noOfRows;i++)
		{
			closenessCount=0;
			count1=0;
			count2=0;
			for(byte j=0;j<noOfCols;j++)
			{
				if(cols[i].getCell(j).getValue()=='X')
					count1++;
				if(cols[i].getCell(j).getValue()=='O')
				{
					count1=0;
					break;
				}
				if(cols[i].getCell(j).getValue()=='-')
					count2++;
			}
			if(count1!=0)
				closenessCount=count1+count2;
			if(closenessCount!=0)
			{
				if(closenessCount>maxCloseness)
				{
					maxCloseness=closenessCount;
					maxCloseCountX=1;
				}
				if(closenessCount==maxCloseness)
					maxCloseCountX++;
			}
		}
		for(byte i=0;i<2;i++)
		{
			closenessCount=0;
			count1=0;
			count2=0;
			for(byte j=0;j<noOfCols;j++)
			{
				if(diags[i].getCell(j).getValue()=='X')
					count1++;
				if(diags[i].getCell(j).getValue()=='O')
				{
					count1=0;
					break;
				}
				if(diags[i].getCell(j).getValue()=='-')
					count2++;
			}
			if(count1!=0)
				closenessCount=count1+count2;
			if(closenessCount!=0)
			{
				if(closenessCount>maxCloseness)
				{
					maxCloseness=closenessCount;
					maxCloseCountX=1;
				}
				if(closenessCount==maxCloseness)
					maxCloseCountX++;
			}
		}
		returnValue+=(maxCloseCountX*Math.pow(sizeOfTable+1,maxCloseness));
		maxCloseness=-1;
		for(byte i=0;i<noOfRows;i++)
		{
			closenessCount=0;
			count1=0;
			count2=0;
			for(byte j=0;j<noOfCols;j++)
			{
				if(rows[i].getCell(j).getValue()=='O')
					count1++;
				if(rows[i].getCell(j).getValue()=='X')
				{
					count1=0;
					break;
				}
				if(rows[i].getCell(j).getValue()=='-')
					count2++;
			}
			if(count1!=0)
				closenessCount=count1+count2;
			if(closenessCount!=0)
			{
				if(closenessCount>maxCloseness)
				{
					maxCloseness=closenessCount;
					maxCloseCountO=1;
				}
				if(closenessCount==maxCloseness)
					maxCloseCountO++;
			}
		}
		for(byte i=0;i<noOfRows;i++)
		{
			closenessCount=0;
			count1=0;
			count2=0;
			for(byte j=0;j<noOfCols;j++)
			{
				if(cols[i].getCell(j).getValue()=='O')
					count1++;
				if(cols[i].getCell(j).getValue()=='X')
				{
					count1=0;
					break;
				}
				if(cols[i].getCell(j).getValue()=='-')
					count2++;
			}
			if(count1!=0)
				closenessCount=count1+count2;
			if(closenessCount!=0)
			{
				if(closenessCount>maxCloseness)
				{
					maxCloseness=closenessCount;
					maxCloseCountO=1;
				}
				if(closenessCount==maxCloseness)
					maxCloseCountO++;
			}
		}
		for(byte i=0;i<2;i++)
		{
			closenessCount=0;
			count1=0;
			count2=0;
			for(byte j=0;j<noOfCols;j++)
			{
				if(diags[i].getCell(j).getValue()=='O')
					count1++;
				if(diags[i].getCell(j).getValue()=='X')
				{
					count1=0;
					break;
				}
				if(diags[i].getCell(j).getValue()=='-')
					count2++;
			}
			if(count1!=0)
				closenessCount=count1+count2;
			if(closenessCount!=0)
			{
				if(closenessCount>maxCloseness)
				{
					maxCloseness=closenessCount;
					maxCloseCountO=1;
				}
				if(closenessCount==maxCloseness)
					maxCloseCountO++;
			}
		}
		returnValue-=(maxCloseCountO*Math.pow(sizeOfTable+1,maxCloseness));
		return returnValue;
	}
	/**
	 * This function returns the lines without O in the grid.
	 * @return The number of lines along which X can be completed,
	 * that is, the number of rows, columns and diagonals which don't
	 * have even a single 'O'. 
	 */
	public double linesWithoutO()
	{
		double returnValue=0;
		byte count1=noOfRows,count2=noOfCols,count3=2;
		for(byte i=0;i<noOfRows;i++)
			for(byte j=0;j<noOfCols;j++)
				if(rows[i].getCell(j).getValue()=='O')
				{
					count1--;
					break;
				}
		for(byte i=0;i<noOfRows;i++)
			for(byte j=0;j<noOfCols;j++)
				if(cols[i].getCell(j).getValue()=='O')
				{
					count2--;
					break;
				}
		for(byte i=0;i<2;i++)
			for(byte j=0;j<noOfRows;j++)
				if(diags[i].getCell(j).getValue()=='O')
				{
					count3--;
					break;
				}
		returnValue+=count1+count2+count3;
		returnValue*=Math.pow((sizeOfTable+1),(noOfRows-1));
		return returnValue;
	}
	/**
	 * This function returns the lines without X in the grid.
	 * @return The number of lines along which O can be completed,
	 * that is, the number of rows, columns and diagonals which don't
	 * have even a single 'X'. 
	 */
	public double linesWithoutX()
	{
		double returnValue=0;
		byte count1=noOfRows,count2=noOfCols,count3=2;
		for(byte i=0;i<noOfRows;i++)
			for(byte j=0;j<noOfCols;j++)
				if(rows[i].getCell(j).getValue()=='X')
				{
					count1--;
					break;
				}
		for(byte i=0;i<noOfRows;i++)
			for(byte j=0;j<noOfCols;j++)
				if(cols[i].getCell(j).getValue()=='X')
				{
					count2--;
					break;
				}
		for(byte i=0;i<2;i++)
			for(byte j=0;j<noOfRows;j++)
				if(diags[i].getCell(j).getValue()=='X')
				{
					count3--;
					break;
				}
		returnValue+=count1+count2+count3;
		returnValue*=Math.pow((sizeOfTable+1),(noOfRows-1));
		return returnValue;
	}
	/**
	 * This function is used to get the X's score of the game.
	 * @return X's score
	 */
	public double getXScore()
	{
		double returnValue=0;
		byte count1,count2,count3;
		for(byte i=1;i<=noOfRows;i++)
		{
			count1=0;
			count2=0;
			count3=0;
			for(byte j=0;j<noOfRows;j++)
				for(byte k=0;k<noOfCols;k++)
				{
					if(rows[j].getCell(k).getValue()=='X')
						count1++;
					if(cols[j].getCell(k).getValue()=='X')
						count2++;
					if(j<2)
						if(diags[j].getCell(k).getValue()=='X')
							count3++;
				}
			if(count1>i)
				returnValue+=(Math.pow((sizeOfTable+1),i)*i);
			if(count2>i)
				returnValue+=(Math.pow((sizeOfTable+1),i)*i);
			if(count3>i)
				returnValue+=(Math.pow((sizeOfTable+1),i)*i);				
		}
		return returnValue;
	}
	/**
	 * This function is used to get the O's score of the game.
	 * @return O's score
	 */
	public double getOScore()
	{
		double returnValue=0;
		byte count1,count2,count3;
		for(byte i=1;i<=noOfRows;i++)
		{
			count1=0;
			count2=0;
			count3=0;
			for(byte j=0;j<noOfRows;j++)
			{
				for(byte k=0;k<noOfCols;k++)
				{
					if(rows[j].getCell(k).getValue()=='O')
						count1++;
					if(cols[j].getCell(k).getValue()=='O')
						count2++;
					if(j<2)
						if(diags[j].getCell(k).getValue()=='O')
							count3++;
				}
			}
			if(count1>i)
				returnValue+=(Math.pow((sizeOfTable+1),i)*i);
			if(count2>i)
				returnValue+=(Math.pow((sizeOfTable+1),i)*i);
			if(count3>i)
				returnValue+=(Math.pow((sizeOfTable+1),i)*i);
		}
		return returnValue;
	}
	/**
	 * Clone method, which overrides Object clone(), is used in place of a copy constructor
	 * @return Clone of the specified table if possible, else <code>null</code>
	 */	
	public Table clone()
	{
			Table returnTable=new Table(this.noOfRows,this.noOfCols);
			for(byte i=0;i<this.sizeOfTable;i++)
			{
				returnTable.cells[i]=new Cell();
				returnTable.cells[i].setValue(this.cells[i].getValue());
			}
			returnTable.init();
			return returnTable;
	}
	/**
	 * This method returns the index of the first empty cell of the table.
	 * Useful for some things, as we will see in the code.
	 */
	public byte getFirstDashIndex()
	{
		byte returnValue = 0;
		for(byte i=0;i<sizeOfTable;i++)
			if(isEmpty(i))
				returnValue=i;
		return returnValue;
	}
	/**
	 * This is the generic getter method for getting the size of the table.
	 * Very useful to check for game end.
	 * @return Size of the table (no of rows * no of columns)
	 */
	public byte getSizeOfTable()
	{
		return sizeOfTable;
	}
	/**
	 * Generic getter method for accessing the winning score of a particular
	 * table after it has been created.
	 * @return The winning score of the table, from X's perspective
	 */
	public double getWinScore() {
		return winScore;
	}
	/**
	 * Generic setter method to set the value of the private variable
	 * winScore of the Table. Defined as good programming practice and
	 * used during construction.
	 * @param winScore The winning score of the table.
	 */
	public void setWinScore(double winScore) {
		this.winScore = winScore;
	}
	/**
	 * A makeMove method, but this one returns the resulting table.
	 * @param moveToMake The move to be made.
	 * @return The table after the move has been made.
	 */
	public Table makeMove(Move moveToMake)
	{
		return moveToMake.getTableNext();
	}
	/**
	 * An unMakeMove method, but this one returns the resulting table.
	 * @param moveToUnMake The move to be un-made.
	 * @return The table after the move has been un-made.
	 */
	public Table unMakeMove(Move moveToUnMake)
	{
		return moveToUnMake.getTableCurrent();
	}
}
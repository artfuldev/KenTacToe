package com.tictactoe.table;

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
public class Table {
	/**
	 * The <code>Cell</code> is the basic building block of the table.
	 * In addition to remembering of which table it is a part, it also
	 * returns rows and columns it belongs to and also has row and column indices
	 * which can be set during construction of the table.
	 * @author Kenshin Himura
	 */
	public class Cell {
		private char value;
		private int rowIndex;
		private int colIndex;
		public Cell()
		{
			this.value='-';
		}
		public Col getCol()
		{
			return Table.this.getCol(colIndex);
		}
		public int getColIndex()
		{
			return colIndex;
		}
		public Row getRow()
		{
			return Table.this.getRow(rowIndex);
		}
		public int getRowIndex()
		{
			return rowIndex;
		}
		public Table getTable()
		{
			return Table.this;
		}
		public char getValue()
		{
			return value;
		}
		public void setColIndex(int colIndex)
		{
			this.colIndex=colIndex;
		}
		public void setRowIndex(int rowIndex)
		{
			this.rowIndex=rowIndex;
		}
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
	public class Col {
		private Cell cells[];
		private int colIndex;
		public Col()
		{
			cells=new Cell[noOfRows];
			for(int i=0;i<noOfRows;i++)
				cells[i]=new Cell();
		}
		public Cell getCell(int rowIndex)
		{
			return cells[rowIndex];
		}
		public Cell[] getCells()
		{
			return cells;
		}
		public int getColIndex() {
			return colIndex;
		}
		public Table getTable()
		{
			return Table.this;
		}
		public int isComplete()
		{
			if(cells[0].getValue()!='-')
			{
				int j=0;
				for(int i=0;i<(noOfRows-1);i++)
				{
					if(cells[i].getValue()==cells[i+1].getValue())
						j++;
				}
				if(j==(noOfRows-1))
				{
					if(cells[0].getValue()=='X')
						return 1;
					if(cells[0].getValue()=='O')
						return 0;
				}
			}
			return -1;	
		}
		public void setColIndex(int colIndex) {
			this.colIndex = colIndex;
		}
	}
	/**
	 * The <code>Diag</code> class is simply the instance of a diagonal in square tables,
	 * used here as it will prove useful to check if diagonals have been completed
	 * in a game of tictac toe. Needless to point out, it has to remember its table and its max no.
	 * of elements.
	 * @author Kenshin Himura
	 *
	 */
	public class Diag {
		private Cell cells[];
		public Diag()
		{
			cells=new Cell[sizeOfTable];
			for(int i=0;i<sizeOfTable;i++)
				cells[i]=new Cell();
		}
		public Cell getCell(int index)
		{
			return cells[index];
		}
		public Cell[] getCells()
		{
			return cells;
		}
		public int isComplete()
		{
			if(cells[0].getValue()!='-')
			{
				int j=0;
				for(int i=0;i<(noOfRows-1);i++)
				{
					if(cells[i].getValue()==cells[i+1].getValue())
						j++;
				}
				if(j==(noOfRows-1))
				{
					if(cells[0].getValue()=='X')
						return 1;
					if(cells[0].getValue()=='O')
						return 0;
				}
			}
			return -1;	
		}
		public Table getTable()
		{
			return Table.this;
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
	public class Row {
		private Cell cells[];
		private int rowIndex;
		public Row()
		{
			cells=new Cell[noOfCols];
			for(int i=0;i<noOfCols;i++)
				cells[i]=new Cell();
		}
		public Cell getCell(int colIndex)
		{
			return cells[colIndex];
		}
		public Cell[] getCells()
		{
			return cells;
		}
		public int getRowIndex() {
			return rowIndex;
		}
		public Table getTable()
		{
			return Table.this;
		}
		public int isComplete()
		{
			if(cells[0].getValue()!='-')
			{
				int j=0;
				for(int i=0;i<(noOfCols-1);i++)
				{
					if(cells[i].getValue()==cells[i+1].getValue())
						j++;
				}
				if(j==(noOfCols-1))
				{
					if(cells[0].getValue()=='X')
						return 1;
					if(cells[0].getValue()=='O')
						return 0;
				}
			}
			return -1;	
		}
		public void setRowIndex(int rowIndex) {
			this.rowIndex = rowIndex;
		}
	}
	private Row rows[];
	private Col cols[];
	private Diag diag;
	private int noOfRows;
	private int noOfCols;
	private int sizeOfTable;//=rows=cols
	private float score=0;
	public Table()
	{
		System.exit(0);
	}
	public Table(int noOfRows,int noOfCols)
	{
		this.noOfRows=noOfRows;
		this.noOfCols=noOfCols;
		if(noOfRows==noOfCols)
			this.sizeOfTable=noOfRows;
		else
			this.sizeOfTable=0;//if not a square table, diagonal cannot be drawn.
		rows=new Row[this.noOfRows];
		for(int i=0;i<noOfRows;i++)
			rows[i]=new Row();
		cols=new Col[this.noOfCols];
		for(int i=0;i<noOfCols;i++)
			cols[i]=new Col();
		diag=new Diag();
		init();
	}
	public Col getCol(int colIndex) {
		return cols[colIndex];
	}
	public int getNoOfCols() {
		return noOfCols;
	}
	public int getNoOfRows() {
		return noOfRows;
	}
	public Row getRow(int rowIndex) {
		return rows[rowIndex];
	}
	public void init()
	{
		for(int i=0;i<this.noOfRows;i++)
			for(int j=0;j<this.noOfCols;j++)
			{
				getRow(i).getCell(j).setColIndex(j);
				getRow(i).getCell(j).setRowIndex(i);
			}

	}
	public void printTable()
	{
		for(int i=0;i<noOfRows;i++)
		{
			for(int j=0;j<noOfCols;j++)
				System.out.print(getRow(i).getCell(j).getValue()+" ");
			System.out.println();
		}
	}
	public void updateTable(int index, char updateChar)
	{
		int rowIndex=(index-1)/3;
		int colIndex=(index+2)%3;
		getRow(rowIndex).getCell(colIndex).setValue(updateChar);
	}
	public int isComplete()
	{
		if(diag.isComplete()!=-1)
			return diag.isComplete();
		for(int i=0;i<this.noOfRows;i++)
			if(rows[i].isComplete()!=-1)
				return rows[i].isComplete();
		for(int i=0;i<this.noOfCols;i++)
			if(cols[i].isComplete()!=-1)
				return cols[i].isComplete();
		return -1;	
	}
	public boolean isEmpty(int index)
	{
		int rowIndex=(index-1)/3;
		int colIndex=(index+2)%3;
		if(getRow(rowIndex).getCell(colIndex).getValue()=='-')
			return true;
		return false;
	}
	private int getNoOfXs()
	{
		int rowIndex,colIndex,returnValue=0;
		for(int i=0;i<9;i++)
		{
			rowIndex=(i-1)/3;
			colIndex=(i+2)%3;
			if(getRow(rowIndex).getCell(colIndex).getValue()=='X')
				returnValue++;
		}
		return returnValue;
	}
	private int getNoOfOs()
	{
		int rowIndex,colIndex,returnValue=0;
		for(int i=0;i<9;i++)
		{
			rowIndex=(i-1)/3;
			colIndex=(i+2)%3;
			if((getRow(rowIndex).getCell(colIndex).getValue()!='X')&&(getRow(rowIndex).getCell(colIndex).getValue()!='-'))
				returnValue++;
		}
		return returnValue;
	}
	private int getNoOfDs()
	{
		int rowIndex,colIndex,returnValue=0;
		for(int i=0;i<9;i++)
		{
			rowIndex=(i-1)/3;
			colIndex=(i+2)%3;
			if(getRow(rowIndex).getCell(colIndex).getValue()=='-')
				returnValue++;
		}
		return returnValue;
	}
	public float getScore() {
		score+=(0.2*getNoOfOs());
		score-=(0.3*getNoOfXs());
		score-=(0.5*getNoOfDs());
		if(isComplete()==0)
			score+=9;
		if(isComplete()==1)
			score-=9;
		//calculate score
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
}
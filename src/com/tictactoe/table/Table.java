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
public class Table implements Cloneable{
	/**
	 * The <code>Cell</code> is the basic building block of the table.
	 * In addition to remembering of which table it is a part, it also
	 * returns rows and columns it belongs to and also has row and column indices
	 * which can be set during construction of the table.
	 * @author Kenshin Himura
	 */
	public class Cell implements Cloneable{
		private char value;
		private int rowIndex;
		private int colIndex;
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
		public int getColIndex()
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
		public int getRowIndex()
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
		public void setColIndex(int colIndex)
		{
			this.colIndex=colIndex;
		}
		/**
		 * This method sets the row index of the cell.
		 * Used automatically during Table construction.
		 * Need/Must not be used anywhere else.
		 */
		public void setRowIndex(int rowIndex)
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
		public Cell clone()
		{
			try {
				Cell returnCell=(Cell)super.clone();
				return returnCell;
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return null;
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
	public class Col implements Cloneable{
		private Cell cells[];
		private int colIndex;
		/**
		 * Default constructor of class
		 * <code>Col</code>, which represents columns of a <code>Table</code> class.
		 */
		public Col()
		{
			cells=new Cell[noOfRows];
			for(int i=0;i<noOfRows;i++)
				cells[i]=new Cell();
		}
		/**
		 * This method is used to get a specific cell of a column(j) using its row index.
		 * @param rowIndex (i)
		 * @return Cell of the particular row index(i) in the column(j),
		 * (i.e.)Cell[i][j] of Table
		 */
		public Cell getCell(int rowIndex)
		{
			return cells[rowIndex];
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
		public int getColIndex() {
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
		/**
		 * Generic setter method to set column index of the column of the table
		 * @param colIndex
		 */
		public void setColIndex(int colIndex) {
			this.colIndex = colIndex;
		}
		public Col clone()
		{
			try {
				Col returnCol=(Col)super.clone();
				for(int i=0;i<noOfRows;i++)
					returnCol.cells[i]=cells[i].clone();
				return returnCol;
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return null;
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
	public class Diag implements Cloneable{
		private Cell cells[];
		/**
		 * Default constructor of the <code>Diag</code> class
		 */
		public Diag()
		{
			cells=new Cell[sizeOfTable];
			for(int i=0;i<sizeOfTable;i++)
				cells[i]=new Cell();
		}
		/**
		 * This method is used to get a specific cell of a diagonal using its index(i).
		 * @param index (i)
		 * @return Cell of the particular row index(i) in the column(i),
		 * (i.e.)Cell[i][i] of Table
		 */
		public Cell getCell(int index)
		{
			return cells[index];
		}/**
		 * This method is used to get all the cells of the particular diagonal.
		 * @return All cells of the diagonal
		 */
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
		public void setCell(int i, Cell cell) {
			cells[i]=cell;
		}
		public Diag clone()
		{
			try {
				Diag returnDiag=(Diag)super.clone();
				for(int i=0;i<sizeOfTable;i++)
					returnDiag.cells[i]=cells[i].clone();
				return returnDiag;
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return null;
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
	public class Row implements Cloneable{
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
		public Row clone()
		{
			try {
				Row returnRow=(Row)super.clone();
				for(int i=0;i<noOfCols;i++)
					returnRow.cells[i]=cells[i].clone();
				return returnRow;
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	private Row rows[];
	private Col cols[];
	private Diag diags[];
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
		diags=new Diag[2];
		diags[0]=new Diag();
		diags[1]=new Diag();
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
				//left diagonal
				if(i==j)
					diags[0].setCell(i,getRow(i).getCell(j));
				//right diagonal
				if((i+j)==noOfRows)
					diags[1].setCell(i,getRow(i).getCell(j));
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
		for(int i=0;i<2;i++)
			if(diags[i].isComplete()!=-1)
				return diags[i].isComplete();
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
	public int getNoOfDs()
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
		score=0;
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
	public Table clone()
	{
		try {
			Table returnTable=(Table)super.clone();
			for(int i=0;i<noOfRows;i++)
				returnTable.cols[i]=cols[i].clone();
			for(int i=0;i<noOfCols;i++)
				returnTable.rows[i]=rows[i].clone();
			for(int i=0;i<2;i++)
				returnTable.diags[i]=diags[i].clone();
			return returnTable;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
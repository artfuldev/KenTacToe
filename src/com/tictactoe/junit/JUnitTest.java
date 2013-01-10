package com.tictactoe.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tictactoe.table.Table;

public class JUnitTest {

	@Test
	public void test() {
		Table t=new Table(3,3);
		t.updateTable(0,'P');
		t.updateTable(1,'X');
		t.updateTable(2,'X');
		t.updateTable(3,'X');
		t.updateTable(4,'P');
		//t.updateTable(5,'P');
		//t.updateTable(6,'X');
		//t.updateTable(7,'X');
		t.updateTable(8,'P');
		System.out.println(t.getScore());
		assertEquals("Done",900,t.getScore(),1000);
		t.printTable();
	}

}

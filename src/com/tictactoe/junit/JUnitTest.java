package com.tictactoe.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tictactoe.table.Table;

public class JUnitTest {

	@Test
	public void test() {
		Table t=new Table((byte)3,(byte)3);
		t.updateTable((byte)0,'X');
		t.updateTable((byte)8,'O');
		System.out.println(t.getScore());
		System.out.println(t.isComplete());
		assertEquals("Done",0,t.getScore(),0);
		t.printTable();
	}

}

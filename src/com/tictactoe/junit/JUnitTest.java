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

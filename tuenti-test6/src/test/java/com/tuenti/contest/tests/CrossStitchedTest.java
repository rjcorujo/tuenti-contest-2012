package com.tuenti.contest.tests;

import org.junit.Assert;
import org.junit.Test;

import com.tuenti.contest.solver.CrossStitchedSolver;

public class CrossStitchedTest {
	
	@Test
	public void testSimple() {
		Assert.assertEquals(18, CrossStitchedSolver.solveProblem(10, 3, 2, "tuenti ;)"));
	}
	
	@Test
	public void testSimple2() {
		Assert.assertEquals(55, CrossStitchedSolver.solveProblem(10, 2, 10, "god is a sheep"));
	}
	@Test
	public void testSimple3() {
		Assert.assertEquals(13, CrossStitchedSolver.solveProblem(5, 10, 2, "once upon a time"));
	}
	
	@Test
	public void testSimple4() {
		Assert.assertEquals(64, CrossStitchedSolver.solveProblem(11, 5, 5, "^_^ *-* -_-U"));
	}
	
	@Test
	public void testSimple5() {
		Assert.assertEquals(27, CrossStitchedSolver.solveProblem(5, 1, 20,"The winter is coming, man!"));
	}

	@Test
	public void testInvalidCharacter() {
		try {
			CrossStitchedSolver.solveProblem(10, 2, 10, "god is a sheepÇ");
			Assert.fail("Should raise an exception");
		} catch (Exception e) {
			
		}
	}
	@Test
	public void testInvalidCharacter2() {
		try {
			CrossStitchedSolver.solveProblem(10, 2, 10, "god is a sheep$");
			Assert.fail("Should raise an exception");
		} catch (Exception e) {
			
		}
	}
}

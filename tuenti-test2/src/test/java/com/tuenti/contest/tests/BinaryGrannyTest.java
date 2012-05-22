package com.tuenti.contest.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigInteger;

import org.junit.Test;

import com.tuenti.contest.solver.BinaryGrannySolver;

public class BinaryGrannyTest {

	@Test
	public void test0() {
		assertEquals(0,BinaryGrannySolver.solveProblem(BigInteger.ZERO));
	}
	
	@Test
	public void testProvided() {
		assertEquals(1,BinaryGrannySolver.solveProblem(BigInteger.ONE));
		assertEquals(4,BinaryGrannySolver.solveProblem(BigInteger.valueOf(6)));
		assertEquals(14,BinaryGrannySolver.solveProblem(BigInteger.valueOf(2135)));
	}
	
	@Test
	public void testNegative() {
		try {
			BinaryGrannySolver.solveProblem(BigInteger.ONE.negate());
			fail("Should not accept negative numbers");
		} catch(IllegalArgumentException e) {
		}
	}
	
	
	@Test
	public void testNull() {
		try {
			BinaryGrannySolver.solveProblem(null);
			fail("Should not accept null");
		} catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testBigNumber() {
		assertEquals(82,BinaryGrannySolver.solveProblem(new BigInteger("10000000000000000000")));
	}
	
	
}

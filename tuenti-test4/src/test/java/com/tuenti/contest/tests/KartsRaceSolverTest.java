package com.tuenti.contest.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;

import com.tuenti.contest.solver.KartsRaceSolver;

public class KartsRaceSolverTest {

	@Test
	public void testSimple() {
		assertEquals(BigInteger.valueOf(12),KartsRaceSolver.solveProblem(3, 6, Arrays.asList(1,2,4,3,1)));
	}
	
	@Test
	public void testMoreKartsThanPeople() {
		assertEquals(BigInteger.valueOf(50),KartsRaceSolver.solveProblem(50, 10, Arrays.asList(1)));
	}
	
	@Test
	public void testSingleKart() {
		assertEquals(BigInteger.valueOf(50),KartsRaceSolver.solveProblem(50, 1, Arrays.asList(1,1,1)));
	}
	
	@Test
	public void testBlockedQueue() {
		try {
			KartsRaceSolver.solveProblem(50, 1, Arrays.asList(1,5,3));
			fail("Blocked queue, an exception should be thrown");
		} catch (Exception e) {
		}
	}
	
	
	@Test
	public void testLarge() {
		assertEquals(new BigInteger("350000000"),KartsRaceSolver.solveProblem(5000000, 150, Arrays.asList(1,3,5,6,8,6,4,5,10,2,2,6,4,8)));
	}
	
}

package com.tuenti.contest.test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.junit.Test;

import com.tuenti.contest.solver.TextingTimeSolver;

public class TextingTimeProcessorTest {

	@Test
	public void testSimplePress() {
		assertEquals(new Integer(100),TextingTimeSolver.solveProblem("0"));
	}
	
	@Test
	public void testSimpleMove() {
		assertEquals(new Integer(900+100),TextingTimeSolver.solveProblem("a"));
	}
	
	@Test
	public void testSameButton() {
		assertEquals(new Integer(100+500+100+500+100),TextingTimeSolver.solveProblem("000"));
	}
	
	@Test
	public void testNumbers() {
		assertEquals(new Integer(100+950+2*100+200+4*100+200+4*100+550+4*100+200+4*100+200+4*100+550+5*100+200+4*100+200+5*100),TextingTimeSolver.solveProblem("0123456789"));
	}
	
	@Test
	public void testAllUpper() {
		assertEquals(new Integer(4000),TextingTimeSolver.solveProblem("HI 20"));
	}
	
	@Test
	public void testAllLower() {
		assertEquals(new Integer(1100),TextingTimeSolver.solveProblem("tu"));
	}
	
	@Test
	public void testSwitchCase() {
		assertEquals(new Integer(6350),TextingTimeSolver.solveProblem("HoLa"));
	}
	
}

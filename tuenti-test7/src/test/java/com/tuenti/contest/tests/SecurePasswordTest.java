package com.tuenti.contest.tests;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import com.tuenti.contest.solver.SecurePasswordSolver;

public class SecurePasswordTest {

	@Test
	public void testSimple() {
		List<String> codes = Arrays.asList("2Ad","12c","2Ac","2Bc","1cd","1xZ","1dx","BdZ");
		SecurePasswordSolver solver = new SecurePasswordSolver(codes);
		List<String> passwords = solver.solveProblem();
		assertEquals(2, passwords.size());
		assertEquals("12ABcdxZ", passwords.get(0));
		assertEquals("12BAcdxZ", passwords.get(1));
	}
	

	@Test
	public void testNotConnected() {
		List<String> codes = Arrays.asList("1a","2b");
		SecurePasswordSolver solver = new SecurePasswordSolver(codes);
		List<String> passwords = solver.solveProblem();
		assertEquals(6, passwords.size());
		assertEquals("12ab", passwords.get(0));
		assertEquals("12ba", passwords.get(1));
		assertEquals("1a2b", passwords.get(2));
		assertEquals("21ab", passwords.get(3));
		assertEquals("21ba", passwords.get(4));
		assertEquals("2b1a", passwords.get(5));
	}
	
}

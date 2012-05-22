package com.tuenti.contest.tests;

import org.junit.Test;

import com.tuenti.contest.solver.SearchSolver;

public class SearchTest {

	@Test
	public void test() {
		SearchSolver solver = new SearchSolver();
		System.out.println(solver.searchTerm("plasm",2));
		solver.close();
	}
}

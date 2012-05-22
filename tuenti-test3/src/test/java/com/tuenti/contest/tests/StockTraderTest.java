package com.tuenti.contest.tests;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tuenti.contest.domain.StockHackingOperation;
import com.tuenti.contest.solver.StockTraderSolver;

public class StockTraderTest {

	@Test
	public void testProvided() {
		List<Integer> list = Arrays.asList(70,93,69,75,52,73,67,81,65,48,67);
		StockHackingOperation operation = StockTraderSolver.solveProblem(list);
		assertNotNull(operation);
		assertEquals(400, operation.getBuyingTime());
		assertEquals(700, operation.getSellingTime());
		assertEquals(29, operation.getProfit());
	}
	
	@Test
	public void testLarger() {
		List<Integer> list = Arrays.asList(121,191,199,190,135,122,105,190,169,113,173,155,194,168,140,186,123,117,128,175,135,179,121,150,113,159,100,159,143,152);
		StockHackingOperation operation = StockTraderSolver.solveProblem(list);
		assertNotNull(operation);
		assertEquals(600, operation.getBuyingTime());
		assertEquals(1200, operation.getSellingTime());
		assertEquals(89, operation.getProfit());
	}
}

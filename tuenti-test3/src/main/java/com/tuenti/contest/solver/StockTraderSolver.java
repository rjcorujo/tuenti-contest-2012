package com.tuenti.contest.solver;

import java.util.List;

import com.tuenti.contest.domain.StockHackingOperation;

public class StockTraderSolver {
	private static final Integer TIME_RESOLUTION = 100;
	
	public static StockHackingOperation solveProblem(List<Integer> stockSamples) {
		if (stockSamples != null && stockSamples.size() > 1) {
			StockHackingOperation bestOperation = null;
			Integer maxProfit = Integer.MIN_VALUE;
			Integer maxFutureValue = Integer.MIN_VALUE; 
			Integer indexMaxFutureValue = -1; 
			for (int i = stockSamples.size()-2; i >= 0; i--) {
				if (stockSamples.get(i+1) > maxFutureValue) {
					maxFutureValue = stockSamples.get(i+1);
					indexMaxFutureValue = i+1;
				}
				int profit = maxFutureValue - stockSamples.get(i);
				if (profit > maxProfit) {
					maxProfit = profit;
					bestOperation = new StockHackingOperation(i*TIME_RESOLUTION, indexMaxFutureValue*TIME_RESOLUTION, profit);
				}
			}
			return bestOperation;
		} else {
			throw new IllegalArgumentException("A valid list of stock samples with at least 2 elements must be provided");
		}
	}
}

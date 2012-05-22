package com.tuenti.contest.processor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.tuenti.contest.domain.StockHackingOperation;
import com.tuenti.contest.solver.StockTraderSolver;

public class TestCasesProcessor {
	
	public static void processInput() {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		List<Integer> stockSamples = new ArrayList<Integer>();
		try {
			String line = bf.readLine();
			while (line != null) {
				Integer value = Integer.parseInt(line.trim());
				stockSamples.add(value);
				line = bf.readLine();
			}
		} catch (Exception e) {
			System.out.println("Error processing input");
		}
		
		try {
			StockHackingOperation operation = StockTraderSolver.solveProblem(stockSamples);
			if (operation != null) {
				System.out.println(operation.getBuyingTime()+" "+operation.getSellingTime()+" "+operation.getProfit());
			} else {
				System.out.println("No profit can be achieved");
			}
		} catch (Exception e) {
			System.out.println("Error processing algorithm");
		}
	}
}

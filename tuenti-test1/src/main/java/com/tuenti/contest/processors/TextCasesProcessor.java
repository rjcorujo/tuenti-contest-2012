package com.tuenti.contest.processors;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.tuenti.contest.solver.TextingTimeSolver;

public class TextCasesProcessor {

	public static void processInput() {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int numCases = -1;
		try {
			numCases = Integer.parseInt(bf.readLine());
		} catch (Exception e) {
			throw new IllegalArgumentException("Error reading input, first line must be the number of cases");
		}
		try {
			for (int i = 0; i < numCases; i++) {
				String text = bf.readLine();
				System.out.println(TextingTimeSolver.solveProblem(text));
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Error processing input");
		}
	}
}

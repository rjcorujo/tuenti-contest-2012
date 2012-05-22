package com.tuenti.contest.processor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

import com.tuenti.contest.solver.BinaryGrannySolver;

public class TestCasesProcessor {
	
	public static void processInput() {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int numCases = -1;
		try {
			numCases = Integer.parseInt(bf.readLine());
		} catch (Exception e) {
			throw new IllegalArgumentException("Error reading input, first line must be the number of cases");
		}
		for (int i = 0; i < numCases; i++) {
			int caseNum = i+1;
			try {
				String text = bf.readLine();
				BigInteger n = new BigInteger(text);
				int result = BinaryGrannySolver.solveProblem(n);
				System.out.println("Case #"+caseNum+": "+result);
			} catch (Exception e) {
				throw new IllegalArgumentException("Error processing input for case #"+caseNum);
			}
		}
	}
}

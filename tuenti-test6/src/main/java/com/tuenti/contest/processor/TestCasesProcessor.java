package com.tuenti.contest.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tuenti.contest.solver.CrossStitchedSolver;

public class TestCasesProcessor {
	private static Pattern casePattern = Pattern.compile("\\s*(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*");

	public static void processInput() {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			Integer cases = Integer.parseInt(bf.readLine());
			for (int i = 1; i <= cases; i++) {
				String line = bf.readLine();
				Matcher matcher = casePattern.matcher(line);
				int width = 0;
				int height= 0;
				int count = 0;
				if (matcher.matches()) {
					width = Integer.parseInt(matcher.group(1));
					height = Integer.parseInt(matcher.group(2));
					count = Integer.parseInt(matcher.group(3));
				} else {
					throw new IllegalArgumentException("Error processing input "+line);
				}
				
				String text = bf.readLine().trim();
				int threadLength = CrossStitchedSolver.solveProblem(width, height, count, text);
				System.out.println("Case #"+i+": "+threadLength);
			}
		} catch (IOException e) {
			System.out.println("Error processing input");
		} catch (Exception e) {
			System.out.println("Error solving problem");
		}
		
	}
	
	
}

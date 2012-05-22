package com.tuenti.contest.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tuenti.contest.solver.SearchSolver;

public class TestCasesProcessor {
	private static Pattern searchPattern = Pattern.compile("(\\w+)\\s+(\\d+)");

	public static void processInput() {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String line = bf.readLine();
			int tests = Integer.parseInt(line.trim());
			for (int i = 0; i < tests; i++) {
				
			}
			line = bf.readLine();
			while (line != null) {
				Matcher matcher = searchPattern.matcher(line);
				if (matcher.matches()) {
					String word = matcher.group(1);
					Integer time = Integer.parseInt(matcher.group(2));
					SearchSolver solver = new SearchSolver();
					String result = solver.searchTerm(word,time);
					System.out.println(result);
				} else {
					throw new IllegalArgumentException("Wrong format "+line);
				}
				line = bf.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error processing input");
		} catch (Exception e) {
			System.out.println("Error solving problem");
		}
		
	}
	
	
}

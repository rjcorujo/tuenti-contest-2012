package com.tuenti.contest.processor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tuenti.contest.solver.KartsRaceSolver;

public class TestCasesProcessor {

	private static Pattern patternKartRace = Pattern.compile("(\\d+) (\\d+) (\\d+)");
	private static Pattern patternGroups = Pattern.compile("(\\d+)");
	
	public static void processInput() {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int testCases = 0;
		try {
			testCases = Integer.parseInt(bf.readLine());
		} catch (Exception e) {
			new IllegalArgumentException("Error processing input (getting number of test cases)");
		}

		try {
			for (int i = 0; i < testCases; i++) {
				String line = bf.readLine();
				Matcher matcher = patternKartRace.matcher(line);
				int races;
				int karts;
				int numGroups;
				List<Integer> groupsSizes = new ArrayList<Integer>();
				if (matcher.matches()) {
					races = Integer.parseInt(matcher.group(1));
					karts = Integer.parseInt(matcher.group(2));
					numGroups = Integer.parseInt(matcher.group(3));
					String groupLine = bf.readLine();
					Matcher matcherGroup = patternGroups.matcher(groupLine);
					while (matcherGroup.find()) {
						groupsSizes.add(Integer.parseInt(matcherGroup.group(0)));
					}
					if (groupsSizes.size() > 0 && numGroups == groupsSizes.size()) {
						//execute
						System.out.println(KartsRaceSolver.solveProblem(races, karts, groupsSizes));
					} else {
						throw new IllegalArgumentException("Input format is unexpected groups could not be parsed");
					}
				} else {
					throw new IllegalArgumentException("Input format is unexpected test definition could not be parsed");
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Error processing test cases",e);
		}
	}
	
}

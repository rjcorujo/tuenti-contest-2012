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

import com.tuenti.contest.solver.CloningSolver;

public class TestCasesProcessor {
	private static Pattern transformationPattern = Pattern.compile("(\\w)=>(\\w+)");

	public static void processInput() {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String line = bf.readLine();
			List<Character> persons = new ArrayList<Character>();
			for (Character person : line.toCharArray()) {
				persons.add(person);
			}
			line = bf.readLine();
			List<Map<Character,String>> series = new ArrayList<Map<Character,String>>();
			while (line != null) {
				Map<Character,String> transformationMap = new HashMap<Character, String>();
				String[] transforms = line.split(",");
				for (String transform: transforms) {
					Matcher matcher = transformationPattern.matcher(transform);
					if (matcher.matches()) {
						Character person = matcher.group(1).charAt(0);//just one character
						String clones = matcher.group(2);
						transformationMap.put(person, clones);
					} else {
						throw new IllegalArgumentException("Wrong format for transformations");
					}
				}
				series.add(transformationMap);
				line = bf.readLine();
			}
			CloningSolver solver = new CloningSolver(persons,series);
			String md5sum = solver.solveProblem();
			System.out.println(md5sum);
		} catch (IOException e) {
			System.out.println("Error processing input");
		} catch (Exception e) {
			System.out.println("Error solving problem");
		}
		
	}
	
	
}

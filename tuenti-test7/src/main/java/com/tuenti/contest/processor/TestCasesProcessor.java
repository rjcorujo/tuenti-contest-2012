package com.tuenti.contest.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.tuenti.contest.solver.SecurePasswordSolver;

public class TestCasesProcessor {

	public static void processInput() {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			List<String> codes = new ArrayList<String>();
			String line = bf.readLine();
			while (line != null) {
				String code = line.trim();
				codes.add(code);
				line = bf.readLine();
			}
			SecurePasswordSolver solver = new SecurePasswordSolver(codes);
			List<String> passwords = solver.solveProblem();
			for (String password : passwords) {
				System.out.println(password);
			}
		} catch (IOException e) {
			System.out.println("Error processing input");
		} catch (Exception e) {
			System.out.println("Error solving problem");
		}
		
	}
	
	
}

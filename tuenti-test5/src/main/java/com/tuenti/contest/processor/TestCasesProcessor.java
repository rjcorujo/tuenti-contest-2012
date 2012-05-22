package com.tuenti.contest.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tuenti.contest.solver.ClocksEnergySolver;

public class TestCasesProcessor {
	
	private static Pattern datePattern = Pattern.compile("(\\d\\d\\d\\d)-(\\d\\d)-(\\d\\d) (\\d\\d):(\\d\\d):(\\d\\d)");
	
	public static void processInput() {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String line = bf.readLine();
			
			while (line != null) {
				String[] datesStr = line.split(" - ");
				if (datesStr.length == 2) {
					Date start = parseDate(datesStr[0]);
					Date end = parseDate(datesStr[1]);
					if (start != null && end != null) {
						BigInteger savedEnergy = ClocksEnergySolver.solveProblem(start, end);
						System.out.println(savedEnergy);
					}
				} else {
					System.out.println("Error processing input "+line);
				}
				line = bf.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error processing input");
		} catch (Exception e) {
			System.out.println("Error solving problem");
		}
		
	}
	
	private static Date parseDate(String dateStr) {
		try {
			Matcher matcher = datePattern.matcher(dateStr);
			if (matcher.matches()) {
				Integer year = Integer.parseInt(matcher.group(1));
				Integer month = Integer.parseInt(matcher.group(2));
				Integer day = Integer.parseInt(matcher.group(3));
	
				Integer hour = Integer.parseInt(matcher.group(4));
				Integer minute = Integer.parseInt(matcher.group(5));
				Integer second = Integer.parseInt(matcher.group(6));
				
				Calendar cal = Calendar.getInstance();
				cal.set(year, month, day, hour, minute, second);
				return cal.getTime();
			}
		} catch (Exception e) {
		}
		return null;
	}
	
}

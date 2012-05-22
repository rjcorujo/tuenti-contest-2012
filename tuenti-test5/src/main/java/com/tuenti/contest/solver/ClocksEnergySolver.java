package com.tuenti.contest.solver;

import java.math.BigInteger;
import java.util.Date;


public class ClocksEnergySolver {

	public static BigInteger solveProblem(Date start, Date end) {
		if (start == null || end == null) {
			throw new IllegalArgumentException("Both dates must be specified start and end, and latter must be after the first one");
		} else if (end.getTime() < start.getTime()) {
			throw new IllegalArgumentException("Both dates must be specified start and end, and latter must be after the first one");
		}
		BigInteger modern = ModernClockEnergySolver.calculateLedsOnInperiod(start, end);
		BigInteger old = OldClockEnergySolver.calculateLedsOnInperiod(start, end);
		return old.subtract(modern);
	}

	
}

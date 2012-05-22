package com.tuenti.contest.solver;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KartsRaceSolver {
	
	public static BigInteger solveProblem(int races, int karts, List<Integer> groupsSizes) {
		//Make all possible groups for efficiency
		Map<Integer,RaceGrouping> grouping = makeGroups(karts,groupsSizes);
		
		int nextGroup = 0;
		BigInteger totalGas = BigInteger.ZERO;
		for (int race = 0; race < races; race++) {
			int occupiedKarts = 0;
			
			RaceGrouping groupInfo = grouping.get(nextGroup);
			occupiedKarts = groupInfo.getKarts();
			nextGroup = (groupInfo.getEnd() + 1) % groupsSizes.size();
			
			if (occupiedKarts == 0) {
				throw new RuntimeException("Queue has been blocked, some group is too large or zero");
			}
			totalGas = totalGas.add(BigInteger.valueOf(occupiedKarts));
		}
		return totalGas;
	}
	
	private static Map<Integer, RaceGrouping> makeGroups(int karts, List<Integer> groupsSizes) {
		Map<Integer,RaceGrouping> grouping = new HashMap<Integer, RaceGrouping>();
		for (int startGroup = 0; startGroup < groupsSizes.size(); startGroup++) {
			
			int occupiedKarts = 0;
			int j;
			for (j = startGroup; j != startGroup || (j == startGroup && occupiedKarts == 0); j = (j + 1) % groupsSizes.size()) {
				int group = groupsSizes.get(j);
				if (occupiedKarts + group > karts) {
					break;
				}
				occupiedKarts += group;
			}
			grouping.put(startGroup, new KartsRaceSolver.RaceGrouping(startGroup,j-1,occupiedKarts));
		}
		return grouping;
	}

	private static class RaceGrouping {
		private int start;
		private int end;
		private int karts;
		
		public RaceGrouping(int start, int end, int karts) {
			super();
			this.start = start;
			this.end = end;
			this.karts = karts;
		}

		public int getEnd() {
			return end;
		}

		public int getKarts() {
			return karts;
		}

		@Override
		public String toString() {
			return "start:"+start+" end:"+end+" karts:"+karts;
		}
		
	}
}

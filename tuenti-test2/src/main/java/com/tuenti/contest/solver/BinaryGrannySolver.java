package com.tuenti.contest.solver;

import java.math.BigInteger;

public class BinaryGrannySolver {
	/**
	 * The idea starts finding the number x with maximum ocurrences of 1's:
	 * given n = "1001 0101 1010", x will be "0111 1111 1111", then we only have to figure out y so y = n - x
	 * @param n
	 * @return Max ocurrences of 1's in binary representation of x and y; for any x and y that x + y = n
	 */
	public static int solveProblem(BigInteger n) {
		if (n != null && n.compareTo(BigInteger.valueOf(0)) >= 0) {
			String binaryN = n.toString(2);
			int firstOcurrence = binaryN.indexOf('1');
			if (firstOcurrence > -1) {
				String firstPart = binaryN.substring(0, firstOcurrence)+'0';
				String secondPart = binaryN.substring(firstOcurrence+1).replaceAll("0", "1");
				String madeUpNumStr = firstPart+secondPart;
				
				BigInteger x = new BigInteger(madeUpNumStr,2);
				BigInteger y = n.subtract(x);
				return numberOfOnes(x) + numberOfOnes(y);
			} else {
				return 0;
			}
		} else {
			throw new IllegalArgumentException("You must provide a positive number");
		}
	}
	
	public static int solveProblemHard(BigInteger n) {
		if (n != null && n.compareTo(BigInteger.valueOf(0)) >= 0) {
			int max = 0;
			for (BigInteger x = n; x.compareTo(BigInteger.ZERO) > 0; x = x.subtract(BigInteger.ONE)) {
				BigInteger y = n.subtract(x);
				int oneOcurrences = numberOfOnes(x)+numberOfOnes(y);
				if (oneOcurrences > max) {
					max = oneOcurrences;
				}
			}
			return max;
		} else {
			throw new IllegalArgumentException("You must provide a positive number");
		}
	}
	

	private static int numberOfOnes(BigInteger num) {
		String numStr = num.toString(2);
		return numStr.length() - numStr.replaceAll("1", "").length();
	}
}

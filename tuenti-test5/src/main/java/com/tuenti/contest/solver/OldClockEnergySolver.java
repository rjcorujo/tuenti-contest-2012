package com.tuenti.contest.solver;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OldClockEnergySolver extends ClockEnergySolver {
	private static List<Integer> ledsOnDigit;
	private static BigInteger ledsOneDay;

	static {
		ledsOnDigit = new ArrayList<Integer>();
		for (int i = 0; i <= 9; i++) {
			ledsOnDigit.add(ledsOnForDigit(i));
		}
		ledsOneDay = calculateLedsOnWholeDay();
	}
	
	public static BigInteger calculateLedsOnInperiod(Date start, Date end) {
		TimeInstance timeStart = new TimeInstance(start);
		TimeInstance timeEnd = new TimeInstance(end);
		
		if (timeStart.isSameDay(timeEnd)) {
			return calculateLedsOnInPeriodSameDay(timeStart, timeEnd);
		} else {
			//Calculate first and last day only if they are not complete days
		
			BigInteger firstDay = BigInteger.ZERO;
			//is first day a whole day?
			if (!timeStart.isTime(0, 0, 0)) {
				firstDay = calculateLedsOnInPeriodSameDay(timeStart,timeStart.cloneWithTime(23, 59, 59));
			}
			
			BigInteger lastDay = BigInteger.ZERO;
			if (!timeEnd.isTime(0, 0, 0)) {
				lastDay = calculateLedsOnInPeriodSameDay(timeEnd.cloneWithTime(0, 0, 0),timeEnd);
			} else {
				//complete days does not take into account that a complete day is from 00:00:00 to 00:00:00, only to 23:59:59
				//so we need to add the display of last 00:00:00
				lastDay = BigInteger.valueOf(ledsOnForDigit(0)*6);
			}
				
			//COMPLETE DAYS
			BigInteger completedays = TimeInstance.wholeDaysBetween(timeStart, timeEnd);
			BigInteger ledsOnInDays = completedays.multiply(ledsOneDay);
			
			return ledsOnInDays.add(firstDay).add(lastDay);
		}
	}

	private static BigInteger calculateLedsOnWholeDay() {
		TimeInstance start = new TimeInstance(new Date());
		TimeInstance end = new TimeInstance(new Date());
		return calculateLedsOnInPeriodSameDay(start.cloneWithTime(0, 0, 0), end.cloneWithTime(23,59,59));
	}
	
	private static BigInteger calculateLedsOnInPeriodSameDay(TimeInstance start, TimeInstance end) {
		BigInteger totalCost = BigInteger.ZERO;
		boolean first = true;
		for (int h = start.getHour(); h <= end.getHour(); h++) {
			int h1 = h / 10;
			int h0 = h % 10;
			for (int m = first ? start.getMinute() : 0; m <= ((h == end.getHour()) ? end.getMinute(): 59); m++) {
				int m1 = m / 10;
				int m0 = m % 10;
				for (int s = first ? start.getSecond(): 0; s <= ((h == end.getHour() && m == end.getMinute()) ? end.getSecond() : 59); s++) {
					int s1 = s /10;
					int s0 = s % 10;
					int cost = ledsOnDigit.get(h1) + ledsOnDigit.get(h0) + ledsOnDigit.get(m1) + ledsOnDigit.get(m0) + ledsOnDigit.get(s1) + ledsOnDigit.get(s0);
					totalCost = totalCost.add(BigInteger.valueOf(cost));
					first = false;
				}
			}
		}
		return totalCost;
	}
	
}

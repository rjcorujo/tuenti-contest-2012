package com.tuenti.contest.solver;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModernClockEnergySolver extends ClockEnergySolver {
	private static BigInteger ledsOneDay;
	private static List<List<Integer>> ledsTransitions;
	
	static {
		ledsTransitions = calculateLedstransitions();
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
			
			//First time has to turn on ALL digits
			firstDay = BigInteger.valueOf(ledsOnForTimeInstance(timeStart));
			
			if (!timeStart.isTime(0, 0, 0)) {
				firstDay = firstDay.add(calculateLedsOnInPeriodSameDay(timeStart,timeStart.cloneWithTime(23, 59, 59)));
			}
			
			BigInteger lastDay = BigInteger.ZERO;
			if (!timeEnd.isTime(0, 0, 0)) {
				//next method does not count the start
				lastDay = BigInteger.valueOf(ledsOnForTimeInstanceChange(timeStart.cloneWithTime(23, 59, 59), timeStart.cloneWithTime(0, 0, 0)));
				lastDay = lastDay.add(calculateLedsOnInPeriodSameDay(timeEnd.cloneWithTime(0, 0, 0),timeEnd));
			} else {
				//complete days does not take into account that a complete day is from 00:00:00 to 00:00:00, only to 23:59:59
				//so we need to add the display of last 00:00:00
				int lastTransition = ledsOnForTimeInstanceChange(timeStart.cloneWithTime(23, 59, 59), timeStart.cloneWithTime(0, 0, 0));
				lastDay = BigInteger.valueOf(lastTransition);
			}
				
			//COMPLETE DAYS
			BigInteger completeDays = TimeInstance.wholeDaysBetween(timeStart, timeEnd);
			BigInteger ledsOnInDays = completeDays.multiply(ledsOneDay);
			
			BigInteger ledsOnInTransitions = BigInteger.ZERO;
			//Every complete Day did not count the first 00:00:00
			if (timeStart.isTime(0, 0, 0)) { 
				//First already took into account counted so transitions will be days -1
				if (completeDays.compareTo(BigInteger.ONE) > 0) { //More than one
					int transitionPerDay = ledsOnForTimeInstanceChange(timeStart.cloneWithTime(23, 59, 59), timeStart.cloneWithTime(0, 0, 0));
					//subtract one, because the first one was taken care about
					ledsOnInTransitions =  completeDays.subtract(BigInteger.ONE).multiply(BigInteger.valueOf(transitionPerDay));
				}
			} else {
				if (completeDays.compareTo(BigInteger.ZERO) > 0) { //At least one whole day
					int transitionPerDay = ledsOnForTimeInstanceChange(timeStart.cloneWithTime(23, 59, 59), timeStart.cloneWithTime(0, 0, 0));
					ledsOnInTransitions =  completeDays.multiply(BigInteger.valueOf(transitionPerDay));
				}
			}
			
			return ledsOnInDays.add(firstDay).add(lastDay).add(ledsOnInTransitions);
		}
	}
	
	/*
	 * The first second is not taking in account only transitions
	 */
	private static BigInteger calculateLedsOnInPeriodSameDay(TimeInstance start, TimeInstance end) {
		BigInteger totalCost = BigInteger.ZERO;
		boolean first = true;
		int lastH1 = -1;
		int lastH0 = -1;
		int lastM1 = -1;
		int lastM0 = -1;
		int lastS1 = -1;
		int lastS0 = -1;
		for (int h = start.getHour(); h <= end.getHour(); h++) {
			int h1 = h / 10;
			int h0 = h % 10;
			if (lastH1 >= 0 && lastH1 != h1) {
				totalCost = totalCost.add(BigInteger.valueOf(ledsTransitions.get(lastH1).get(h1)));
			}
			if (lastH0 >= 0 && lastH0 != h0) {
				totalCost = totalCost.add(BigInteger.valueOf(ledsTransitions.get(lastH0).get(h0)));
			}
			lastH0 = h0;
			lastH1 = h1;
			for (int m = first ? start.getMinute() : 0; m <= (h == end.getHour() ? end.getMinute() : 59); m++) {
				int m1 = m / 10;
				int m0 = m % 10;
				if (lastM1 >= 0 && lastM1 != m1) { //m1 change, first time and when h0 rolls over
					totalCost = totalCost.add(BigInteger.valueOf(ledsTransitions.get(lastM1).get(m1)));
				}
				if (lastM0 >= 0 && lastM0 != m0) {
					totalCost = totalCost.add(BigInteger.valueOf(ledsTransitions.get(lastM0).get(m0)));
				}
				lastM1 = m1;
				lastM0 = m0;
				for (int s = first ? start.getSecond() : 0; s <= (h == end.getHour() && m == end.getMinute() ? end.getSecond() : 59); s++) {
					int s1 = s /10;
					int s0 = s % 10;
					if (lastS1 >= 0 && lastS1 != s1) {
						totalCost = totalCost.add(BigInteger.valueOf(ledsTransitions.get(lastS1).get(s1)));
					}
					if (lastS0 >= 0 && lastS0 != s0) {
						totalCost = totalCost.add(BigInteger.valueOf(ledsTransitions.get(lastS0).get(s0)));
					}
					lastS1 = s1;
					lastS0 = s0;
					first = false;
				}
			}
		}
		return totalCost;
	}
	
	/* 
	 * When time changed from time1 to time2
	 */
	protected static int ledsOnForTimeInstanceChange(TimeInstance time1, TimeInstance time2){
		int t1h1 = time1.getHour() / 10;
		int t1h0 = time1.getHour() % 10;
		int t1m1 = time1.getMinute() / 10;
		int t1m0 = time1.getMinute() % 10;
		int t1s1 = time1.getSecond() / 10;
		int t1s0 = time1.getSecond() % 10;
		
		int t2h1 = time2.getHour() / 10;
		int t2h0 = time2.getHour() % 10;
		int t2m1 = time2.getMinute() / 10;
		int t2m0 = time2.getMinute() % 10;
		int t2s1 = time2.getSecond() / 10;
		int t2s0 = time2.getSecond() % 10;
		
		int leds = ledsTransitions.get(t1h1).get(t2h1);
		leds += ledsTransitions.get(t1h0).get(t2h0);
		leds += ledsTransitions.get(t1m1).get(t2m1);
		leds += ledsTransitions.get(t1m0).get(t2m0);
		leds += ledsTransitions.get(t1s1).get(t2s1);
		leds += ledsTransitions.get(t1s0).get(t2s0);
		return leds;
	}
	
	private static BigInteger calculateLedsOnWholeDay() {
		TimeInstance start = new TimeInstance(new Date());
		TimeInstance end = new TimeInstance(new Date());
		return calculateLedsOnInPeriodSameDay(start.cloneWithTime(0, 0, 0), end.cloneWithTime(23,59,59));
	}
	
	private static List<List<Integer>> calculateLedstransitions() {
		List<List<Integer>> transitionList = new ArrayList<List<Integer>>();
		for (int i = 0; i <= 9; i++) {
			List<Integer> currentList = new ArrayList<Integer>();
			for (int j = 0; j <= 9; j++) {
				currentList.add(calculateDeltaLeds(i, j));
			}
			transitionList.add(currentList);
		}
		return transitionList;
	}
	
	private static Integer calculateDeltaLeds(Integer current, Integer next) {
		byte currentMask = ledsMapping.get(current).byteValue();
		byte nextMask = ledsMapping.get(next).byteValue();
		
		int nextLedsOn = ledsOnForDigit(next);
		int commonLeds = countOnes((byte)(currentMask & nextMask));
		return nextLedsOn - commonLeds;
	}
}

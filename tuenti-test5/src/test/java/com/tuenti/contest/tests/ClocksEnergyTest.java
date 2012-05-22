package com.tuenti.contest.tests;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.tuenti.contest.solver.ClocksEnergySolver;

public class ClocksEnergyTest {
	
	@Test
	public void test1() throws Exception {
		Date start = getDate(2012, 1, 2, 0, 0, 0);
		Date end = getDate(2012, 1, 9, 0, 19, 55);
		
		Assert.assertEquals(BigInteger.valueOf(15822521),ClocksEnergySolver.solveProblem(start, end));
	}
	
	@Test
	public void test2() throws Exception {
		Date start = getDate(2012, 1, 6, 0, 0, 0);
		Date end = getDate(2012, 1, 10, 11, 14, 44);
		
		Assert.assertEquals(BigInteger.valueOf(10139168),ClocksEnergySolver.solveProblem(start, end));
	}
	
	@Test
	public void test3() throws Exception {
		Date start = getDate(2012, 1, 9, 0, 0, 0);
		Date end = getDate(2012, 1, 15, 10, 27, 32);
		
		Assert.assertEquals(BigInteger.valueOf(14581994),ClocksEnergySolver.solveProblem(start, end));
	}
	
	@Test
	public void test4() throws Exception {
		Date start = getDate(2012, 1, 17, 0, 0, 0);
		Date end = getDate(2012, 1, 24, 9, 57, 23);
		
		Assert.assertEquals(BigInteger.valueOf(16791741),ClocksEnergySolver.solveProblem(start, end));
	}
	
	@Test
	public void test5() throws Exception {
		Date start = getDate(2012, 1, 15, 0, 0, 0);
		Date end = getDate(2012, 1, 22, 13, 23, 31);
		
		Assert.assertEquals(BigInteger.valueOf(17083065),ClocksEnergySolver.solveProblem(start, end));
	}
	
	@Test
	public void testStartNotZero() throws Exception {
		Date start = getDate(2012, 1, 15, 0, 0, 1);
		Date end = getDate(2012, 1, 22, 13, 23, 31);
		
		Assert.assertEquals(BigInteger.valueOf(17083033),ClocksEnergySolver.solveProblem(start, end));
	}
	
	
	private Date getDate(int year, int month, int day, int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		return cal.getTime();
	}
}

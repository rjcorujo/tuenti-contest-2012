package com.tuenti.contest.solver;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ClockEnergySolver {
	protected static List<Byte> ledsMapping;
	
	static {
		ledsMapping = Arrays.asList(
				Byte.parseByte("1111110", 2),
				Byte.parseByte("0110000",2),
				Byte.parseByte("1101101",2),
				Byte.parseByte("1111001",2),
				Byte.parseByte("0110011",2),
				Byte.parseByte("1011011",2),
				Byte.parseByte("1011111",2),
				Byte.parseByte("1110000",2),
				Byte.parseByte("1111111",2),
				Byte.parseByte("1111011",2)
				);
	}
	
	protected static int ledsOnForTimeInstance(TimeInstance time){
		int h1 = time.getHour() / 10;
		int h0 = time.getHour() % 10;
		int m1 = time.getMinute() / 10;
		int m0 = time.getMinute() % 10;
		int s1 = time.getSecond() / 10;
		int s0 = time.getSecond() % 10;
		
		int leds = ledsOnForDigit(h1);
		leds += ledsOnForDigit(h0);
		leds += ledsOnForDigit(m1);
		leds += ledsOnForDigit(m0);
		leds += ledsOnForDigit(s1);
		leds += ledsOnForDigit(s0);
		return leds;
	}
	
	protected static int ledsOnForDigit(Integer digit) {
		byte byte2Count = ledsMapping.get(digit);
		return countOnes(byte2Count);
	}
	
	
	protected static int countOnes(byte byteValue) {
		byte byte2Count = byteValue;
		int ones = 0;
		while (byte2Count != 0) {
			ones += (byte2Count & 1);
			byte2Count >>= 1;
		}
		return ones;
	}
	
	
	protected static class TimeInstance {
		private int year;
		private int dayOfYear;
		private int hour;
		private int minute;
		private int second;
		private static final long MILISECONS_PER_DAY = 1000*60*60*24;
		
		private TimeInstance(int year, int dayOfYear, int hour, int minute, int second) {
			this.year = year;
			this.dayOfYear = dayOfYear;
			this.hour = hour;
			this.minute = minute;
			this.second = second;
		}
		
		public TimeInstance(Date date) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			this.year = cal.get(Calendar.YEAR); 
			this.dayOfYear = cal.get(Calendar.DAY_OF_YEAR); 
			this.hour = cal.get(Calendar.HOUR_OF_DAY); 
			this.minute = cal.get(Calendar.MINUTE); 
			this.second = cal.get(Calendar.SECOND); 
		}
		
		public static BigInteger wholeDaysBetween(TimeInstance timeStart, TimeInstance timeEnd) {
			Calendar calStart = timeStart.getCalendar();
			if (!timeStart.isTime(0, 0, 0)) {
				calStart.add(Calendar.DAY_OF_YEAR, 1);
				calStart.set(Calendar.HOUR, 0);
				calStart.set(Calendar.MINUTE, 0);
				calStart.set(Calendar.SECOND, 0);
			}
			
			Calendar calEnd = timeEnd.getCalendar();
			if (!timeEnd.isTime(0, 0, 0)) {
				calEnd.set(Calendar.HOUR, 0);
				calEnd.set(Calendar.MINUTE, 0);
				calEnd.set(Calendar.SECOND, 0);
			}
			
			BigInteger miliseconds = BigInteger.valueOf(calEnd.getTime().getTime() - calStart.getTime().getTime());
			
			BigInteger days = miliseconds.divide(BigInteger.valueOf(MILISECONS_PER_DAY));
			if (days.compareTo(BigInteger.ZERO) >= 0) {
				return days;
			} else {
				return BigInteger.ZERO;
			}
		}
		
		public boolean isTime(int hour, int minute, int second) {
			return this.hour == hour && this.minute == minute && this.second == second;
		}
		
		private Calendar getCalendar() {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, this.year);
			cal.set(Calendar.DAY_OF_YEAR, this.dayOfYear);
			cal.set(Calendar.HOUR_OF_DAY, this.hour);
			cal.set(Calendar.MINUTE, this.minute);
			cal.set(Calendar.SECOND, this.second);
			return cal;
		}
		
		public TimeInstance cloneWithTime(int hour, int minute, int second) {
			return new TimeInstance(this.year, this.dayOfYear, hour, minute, second);
		}
		
		public int getHour() {
			return hour;
		}
		
		public int getMinute() {
			return minute;
		}
		
		public int getSecond() {
			return second;
		}
		
		public boolean isSameDay(TimeInstance time2) {
			if (time2 != null) {
				return (this.year == time2.year && this.dayOfYear == time2.dayOfYear);
			}
			return false;
		}
	}
}

package com.tuenti.contest.domain;

public class StockHackingOperation {
	private int buyingTime;
	private int sellingTime;
	private int profit;
	
	public StockHackingOperation(int buyingTime, int sellingTime, int profit) {
		super();
		this.buyingTime = buyingTime;
		this.sellingTime = sellingTime;
		this.profit = profit;
	}

	public int getBuyingTime() {
		return buyingTime;
	}

	public int getSellingTime() {
		return sellingTime;
	}

	public int getProfit() {
		return profit;
	}
	
	
	
	
}

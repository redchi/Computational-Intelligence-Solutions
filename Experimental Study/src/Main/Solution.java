package Main;

import java.util.ArrayList;

public class Solution {
	
	private ArrayList<CuttingStock> allStockUsed;
	
	public Solution(ArrayList<CuttingStock> stockUsed) {
		allStockUsed = stockUsed;
	}

	public ArrayList<CuttingStock> getAllStockUsed() {
		return allStockUsed;
	}

	public float getTotalCost() {
		float total = 0;
		for(CuttingStock stock:allStockUsed) {
			total = total + stock.getCost();
		}
		return total;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "sol - "+getTotalCost();
	}
	
	
}

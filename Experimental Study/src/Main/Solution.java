package Main;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
	
	public CuttingStock findOrderCuttingStock(Order order) {
		for(CuttingStock stock:allStockUsed) {
			if(stock.orderExistsInsideStock(order) == true) {
				return stock;
			}
		}
		//should never happen
		return null;
	}
	//deep clone
	public Solution clone() {
		ArrayList<CuttingStock> nw =  (ArrayList<CuttingStock>) allStockUsed.stream() .map( i -> i.clone()) .collect(Collectors.toList());
		Solution res = new Solution(nw);
		return res;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "sol - "+getTotalCost();
	}
	
	
}

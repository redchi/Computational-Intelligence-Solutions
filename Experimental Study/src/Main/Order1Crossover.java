package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Order1Crossover {

	
	private Random rand;
	
	public Order1Crossover(long seed) {
		rand = new Random(seed);
	}
	
	public ArrayList<Solution> generateChildren(ArrayList<Solution> parents,int amount) {
		ArrayList<Solution> children = new ArrayList<Solution>();
		for(int i = 0;i<amount;i++) {
			int pIndex = rand.nextInt(parents.size());
			Solution parent1 = parents.get(pIndex);
			pIndex = rand.nextInt(parents.size());
			Solution parent2 = parents.get(pIndex);
			Solution child = createChild(parent1, parent2);
			children.add(child);
		}
		return children;
	} 
	
	
	// creates 1 child only
	public Solution createChild(Solution parent1,Solution parent2) {
		ArrayList<CuttingStock> p1Stock = new ArrayList<CuttingStock>(parent1.getAllStockUsed());
		int p1Index = rand.nextInt(p1Stock.size());
		
		// amount of p1 stock that will be in child
		int amtSelected = p1Stock.size()/2;
		
		// select random half of p1 to be used as 1st part of child
		ArrayList<CuttingStock> p1ChildParts = new ArrayList<CuttingStock>();
		for(int i = 0;i<amtSelected;i++) {
			CuttingStock stock = p1Stock.get(p1Index + i);
			p1ChildParts.add(stock.clone());
		}
		// copy parent 2 cuttingstock and deep clone it
		ArrayList<CuttingStock> p2Stockraw = new ArrayList<CuttingStock>(parent2.getAllStockUsed());
		List<CuttingStock> p2Stock = p2Stockraw.stream() .map( i -> i.clone()) .collect(Collectors.toList());
		
		//get all order objects inside parent1 part of child
		ArrayList<Order> allOrdersInP1 = new ArrayList<Order>();
		for(CuttingStock stock:p1ChildParts) {
			allOrdersInP1.addAll(stock.getOrdersCutByStock());
		}
		
		// remove all parent 1 orders part of child from all of parent 2  
		for(Order order:allOrdersInP1) {
			for(CuttingStock stock:p2Stock) {
				if(stock.orderExistsInsideStock(order)==true) {
					stock.removeOrder(order);
					break;
				}
			}
		}
		
		//clean up empty p2 cutting stock 
		for(int i = 0;i<p2Stock.size();i++) {
			CuttingStock stock = p2Stock.get(i);
			if(stock.getOrdersCutByStock().size() ==0) {
				p2Stock.remove(stock);
			}
		}
		
		// now remaing p2 stock is 2nd part of child
		ArrayList<CuttingStock> child = new ArrayList<CuttingStock>();
		child.addAll(p1ChildParts);
		child.addAll(p2Stock);
		
		// shift - dont really matter but still needed for future evolutions
		Collections.rotate(child, p1Index);
		
		Solution res = new Solution(child);
		return res;
	}
	
	
}

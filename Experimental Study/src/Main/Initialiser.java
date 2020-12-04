package Main;

import java.util.ArrayList;
import java.util.Random;

public class Initialiser {
	private ProblemSpecification spec;
	private Random rand;
	
	public Initialiser(ProblemSpecification spec,long seed) {
		this.spec = spec;
		rand = new Random(seed);
	}
	
	
	public ArrayList<Solution> generatePopulation(int size){
		ArrayList<Solution> population = new ArrayList<Solution>();
		for(int i =0;i<size;i++) {
			
			ArrayList<Float> avalableCuttingLengths = spec.getStandardCuttingLengths();
			ArrayList<Order> allOrders = new ArrayList<Order>(spec.getAllOrders());
			ArrayList<CuttingStock> cuttingStockUsed = new ArrayList<CuttingStock>();
			
			while(allOrders.size()>0) {
		
				int index = rand.nextInt(allOrders.size());
				Order order = allOrders.get(index);
				allOrders.remove(index);
				
				int spaceInCuttingStock = findSpaceInCuttingStock(cuttingStockUsed,order);
				
				if(spaceInCuttingStock<0) {
					// gen new cutting stock
					int cuttingStockindex = rand.nextInt(avalableCuttingLengths.size());
					float cuttingStockLength = avalableCuttingLengths.get(cuttingStockindex);
					float cuttingStockCost = spec.getCostofCuttingStock(cuttingStockLength);
					CuttingStock stock = new CuttingStock(cuttingStockLength, cuttingStockCost);
					stock.addOrder(order);
					cuttingStockUsed.add(stock);
				}
				else {
					CuttingStock stockUsable = cuttingStockUsed.get(spaceInCuttingStock);
					stockUsable.addOrder(order);
				}
				
			}
			Solution solution = new Solution(cuttingStockUsed);
			population.add(solution);
		}
		return population;
	}
	
	
	
	
	
	
	
	
	
	
	
	public Solution gen1Sol() {
		Random rand = new Random(123);
		ArrayList<Float> avalableCuttingLengths = spec.getStandardCuttingLengths();
		ArrayList<Order> allOrders = new ArrayList<Order>(spec.getAllOrders());
		
		// 1 solution only !
		
		//boolean stop = false;
		ArrayList<CuttingStock> cuttingStockUsed = new ArrayList<CuttingStock>();
		
		while(allOrders.size()>0) {
	
			int index = rand.nextInt(allOrders.size());
			Order order = allOrders.get(index);
			allOrders.remove(index);
			
			int spaceInCuttingStock = findSpaceInCuttingStock(cuttingStockUsed,order);
			
			if(spaceInCuttingStock<0) {
				// gen new cutting stock
				int cuttingStockindex = rand.nextInt(avalableCuttingLengths.size());
				float cuttingStockLength = avalableCuttingLengths.get(cuttingStockindex);
				float cuttingStockCost = spec.getCostofCuttingStock(cuttingStockLength);
				CuttingStock stock = new CuttingStock(cuttingStockLength, cuttingStockCost);
				stock.addOrder(order);
				cuttingStockUsed.add(stock);
			}
			else {
				CuttingStock stockUsable = cuttingStockUsed.get(spaceInCuttingStock);
				stockUsable.addOrder(order);
			}
			
		}
		Solution solution = new Solution(cuttingStockUsed);
		return solution;
	}
	
	
	private int findSpaceInCuttingStock(ArrayList<CuttingStock> cuttingStocks,Order order) {
		
		if(cuttingStocks.size() == 0) {
			return  - 1;
		}
		// could be more random? picks first one only
		for(int i = 0;i<cuttingStocks.size();i++)
		{
			CuttingStock stock = cuttingStocks.get(i);
			if(stock.canCutOrder(order) == true) {
				return i;
			} 
		}		
		
		return -1;
		
	}
	
}






















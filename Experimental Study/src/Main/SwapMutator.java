package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SwapMutator {

	private Random rand;
	private ProblemSpecification probSpec;
	private int mutationChance;
	
	public SwapMutator(long seed,int mutatationChance,ProblemSpecification probSpec ) {
		this.probSpec = probSpec;
		this.mutationChance = mutatationChance;
		this.probSpec = probSpec;
	}
	
	
	public ArrayList<Solution> mutateChildren(ArrayList<Solution> children){
		ArrayList<Solution> mutatedChildren = new ArrayList<Solution>(); 
		for(Solution child:children) {
			int roll = rand.nextInt(101);
			if(roll<=mutationChance) {
				//mutate
				Solution m1 = swapMutateCuttingStock(child);
				Solution m2 = swapMutateOrder(m1);
				mutatedChildren.add(m2);
			}
			else {
				mutatedChildren.add(child);
			}
		}
		return mutatedChildren;
	}
	
	public Solution swapMutateOrder(Solution original) {
		
		ArrayList<Order> allOrders = new ArrayList<Order>();
		for(CuttingStock stock:original.getAllStockUsed()) {
			allOrders.addAll(stock.clone().getOrdersCutByStock());
		}
		int index = rand.nextInt(allOrders.size());
		Order swapOrder1 = allOrders.get(index);
		//finds cutting stock of selected swap order
		//CuttingStock SO1CutStock = original.findOrderCuttingStock(swapOrder1);
		ArrayList<Order> possibleSwaps = findPossibleSwaps(swapOrder1, null);
		
		if(possibleSwaps.size() == 0) {
			return original;
		}
	
		int index2 = rand.nextInt(possibleSwaps.size());
		Order swapOrder2 = possibleSwaps.get(index2);
	//	CuttingStock SO2CutStock = original.findOrderCuttingStock(swapOrder2);
		
		//original deep clone - make sure this doesnt change original !'''
	
		Solution newSol = original.clone();
		Order tempOrder = new Order("XXX",123); //# tag order to find place when u swap
		CuttingStock SO1CutStock = newSol.findOrderCuttingStock(swapOrder1);
		int so1Index = SO1CutStock.getOrdersCutByStock().indexOf(swapOrder1);
		SO1CutStock.getOrdersCutByStock().set(so1Index, tempOrder);// dont really need this :/
		
		
		CuttingStock SO2CutStock = newSol.findOrderCuttingStock(swapOrder2);
		int so2Index = SO2CutStock.getOrdersCutByStock().indexOf(swapOrder2);
		SO2CutStock.getOrdersCutByStock().set(so2Index, swapOrder1);
		
		
		SO1CutStock.getOrdersCutByStock().set(so1Index, swapOrder2);
		
		return newSol;
		
	}
	
	private ArrayList<Order> findPossibleSwaps(Order swapOrder, ArrayList<CuttingStock> allStock){
		ArrayList<Order> possibleSwaps = new ArrayList<Order>();
		for(CuttingStock stock:allStock) {
			for(Order order:stock.getOrdersCutByStock() ) {
				float orderLen = order.getLength();
				float swapOrderLen = swapOrder.getLength();
				float stockUsedLength = stock.getUsedLength();
				float stockLength = stock.getLength();
				float newUsedLength = stockUsedLength - orderLen + swapOrderLen;
				if(newUsedLength<=stockLength) {
					//swappable !
					possibleSwaps.add(order);
				}
			}
		}
		possibleSwaps.remove(swapOrder);
		return possibleSwaps;
	}
	
	public Solution swapMutateCuttingStock(Solution original) {
		
		ArrayList<CuttingStock> allStock = original.getAllStockUsed();
		int index = rand.nextInt(allStock.size());
		CuttingStock selectedStock = allStock.get(index);
		
		ArrayList<Float> lengthChanges = findPossibleChangesToStockLength(selectedStock);
		if(lengthChanges.size() == 0) {
			return original;
		}
		
		int index2 = rand.nextInt(lengthChanges.size());
		float newStockLength = lengthChanges.get(index2);
		float newStockcost = this.probSpec.getCostofCuttingStock(newStockLength);
		CuttingStock newStock = new CuttingStock(newStockLength, newStockcost);
		for(Order order:selectedStock.getOrdersCutByStock()) {
			newStock.addOrder(order);
		}
		
		//make sure new solution cutting stock replacement does not effect original!! test this!!
		ArrayList<CuttingStock> newSol = new ArrayList<CuttingStock>(original.getAllStockUsed());
		newSol.set(index, newStock);
		
		Solution sol = new Solution(newSol);
		return sol;
	}
	
	private ArrayList<Float> findPossibleChangesToStockLength(CuttingStock stock){
		ArrayList<Float> allStandardLengths = probSpec.getStandardCuttingLengths();
		ArrayList<Float> possibleSwaps = new ArrayList<Float>();
		float LengthUsed = stock.getUsedLength();
		for(float standardLength:allStandardLengths) {
			if(standardLength>=LengthUsed) {
				possibleSwaps.add(standardLength);
			}
		}
		possibleSwaps.remove(stock.getLength());
		return possibleSwaps;
	}
}





















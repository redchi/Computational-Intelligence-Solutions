package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class SurviverSelector {
	
	public ArrayList<Solution> selectSurvivors(ArrayList<Solution> parents,ArrayList<Solution> children,int amount){
		
		ArrayList<Solution> allPop = new ArrayList<Solution>();
		allPop.addAll(parents);
		allPop.addAll(children);
		
		SortByCost comp = new SortByCost();
		Collections.sort(allPop, comp);
		Solution bestSol = allPop.get(0);
		children.add(bestSol);
		
		Collections.sort(children,comp);
		ArrayList<Solution> survivors = new ArrayList<Solution>();
		for(int i = 0;i<amount;i++) {
			Solution sol = children.get(i);
			survivors.add(sol);
		}
		
		return survivors;
	}
	 
	
	class SortByCost implements Comparator<Solution>{
		@Override
		public int compare(Solution o1, Solution o2) {
			if(o1.getTotalCost() < o2.getTotalCost()) {
				return -1;
			}
			else if(o1.getTotalCost()>o2.getTotalCost()) {
				return 1;
			}
			return 0;
		}
	}
	
	
	
	
}

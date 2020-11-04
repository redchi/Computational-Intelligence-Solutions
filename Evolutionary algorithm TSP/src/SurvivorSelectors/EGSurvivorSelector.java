package SurvivorSelectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Main.Route;

public class EGSurvivorSelector {

	public EGSurvivorSelector(){
		
	}
	
	// saves best route to children, even if it wa a parent, then kills all parents
	public ArrayList<Route> selectSurvivors(ArrayList<Route> parents,ArrayList<Route> children,int populationSize){
		ArrayList<Route> allPop = new ArrayList<Route>();
		allPop.addAll(parents);
		allPop.addAll(children);
		
		Route bestRoute = allPop.get(0);
		double bestRoutecost = bestRoute.getCostOfRoute();
		
		for(Route route:allPop) {
			double cost = route.getCostOfRoute();
			if(cost<bestRoutecost) {
				bestRoute = route;
				bestRoutecost = cost;
			}
		}
		
		children.add(bestRoute);
		
		ArrayList<Route> newPopulation = new ArrayList<Route>();
		 Collections.sort(children, new SortbyCost());
		 
		 for(int i = 0;i<populationSize;i++) {
			 newPopulation.add(children.get(i));
			 System.out.println("survivor dist= "+children.get(i).getCostOfRoute());
		 }
		System.out.println("\n");
		return newPopulation;
	}
	
	
	
	class SortbyCost implements Comparator<Route> 
	{ 
	    // -1 if a<b acending order
	    public int compare(Route a, Route b) 
	    { 
	        if(a.getCostOfRoute()<b.getCostOfRoute()) {
	        	return -1;
	        }
	        else if(a.getCostOfRoute()>b.getCostOfRoute()) {
	        	return +1;
	        }
	        else {
	        	return 0;
	        }
	    } 
	}
	
}

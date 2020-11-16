package SurvivorSelectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Main.Route;

// TODO: Auto-generated Javadoc
/**
 * Elitist and generational survivor selector

 */
public class EGSurvivorSelector {

	/**
	 * 
	 * Select surviors from total population.
	 * 
	 * Saves 1 best route to children, even if it is a parent(Elitist), 
	 * then kills all parents, then sorts and selects top children survivors(Genarational)
	 *
	 * @param parents the parents
	 * @param children the children
	 * @param populationSize the population size
	 * @return the array list
	 */
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
		 }
		return newPopulation;
	}
	
	
	
	class SortbyCost implements Comparator<Route> 
	{ 

    	// -1 if a<b, ascending order
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

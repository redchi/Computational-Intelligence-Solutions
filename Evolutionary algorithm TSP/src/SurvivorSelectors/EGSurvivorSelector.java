package SurvivorSelectors;

import java.util.ArrayList;

import Main.Route;

public class EGSurvivorSelector {

	public EGSurvivorSelector(){
		
	}
	
	// saves best route to children, even if it wa a parent, then kills all parents
	public ArrayList<Route> selectSurvivors(ArrayList<Route> parents,ArrayList<Route> children){
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
		return children;
	}
	
}

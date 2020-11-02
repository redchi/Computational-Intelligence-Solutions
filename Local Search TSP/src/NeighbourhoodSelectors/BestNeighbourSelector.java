package NeighbourhoodSelectors;

import java.util.ArrayList;

import Main.Location;
import Main.Route;

public class BestNeighbourSelector {

	
	public Route selectBestNeighbour(ArrayList<Route> neighbours) {
		
		Route bestRoute = neighbours.get(0);
		double currentBestCost = getCostOfRoute(bestRoute);
		for(Route route:neighbours) {
			double routeCost = getCostOfRoute(route);
			if(routeCost<currentBestCost) {
				bestRoute = route;
				currentBestCost = getCostOfRoute(bestRoute);
			}
		}
		return bestRoute;
	}
	
	
	public double getCostOfRoute(Route route) {
		double totalDistance = 0;
		ArrayList<Location> path = route.getPath();
		int pathSize = path.size();
		Location currentCity = path.get(0);
		for(int i = 1;i<pathSize;i++) {
			Location nextCity = path.get(i);
			double x = (nextCity.getX() - currentCity.getX());
			double y = (nextCity.getY() - currentCity.getY());
			double distance = Math.sqrt((x*x)+(y*y));
			totalDistance = totalDistance + distance;
			currentCity = path.get(i);
		}
		return totalDistance;
	}
}

package ParentSelectors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import Main.Location;
import Main.Route;

public class ParentTournamentSelector {

	Random rand;
	
	public ParentTournamentSelector(long seed) {
		rand = new Random(seed);
	}
	
	
	
	
	public ArrayList<Route> selectParents(ArrayList<Route> population, int amount){
		
		int maxRange = population.size();
		
		int maxParticipants = maxRange/2;
		ArrayList<Route> parents = new ArrayList<Route>();

		for(int i = 0;i<amount;i++) {
			ArrayList<Route> participants = new ArrayList<Route>();
			// Route selection for 1 tournament 
			for(int k = 0;k<maxParticipants;k++ ) {
				int index = rand.nextInt(maxRange);
				Route participant = population.get(index);
				participants.add(participant);
			}
			Route winner = runTournament(participants);
			parents.add(winner);
		}
		
//		int total = 0;
//		ArrayList<Route> copy = new ArrayList<Route>(parents);
//		for(int i = 0;i<parents.size();i++) {
//			int count = 0;
//			Route parent = parents.get(i);
//			for(Route other:copy) {
//				if(parent.equals(other) == true) {
//					count = count +1 ;
//				}
//			}
//			if(count>=1) {
//				System.out.println("route won - "+count + " times dist = "+parent.getCostOfRoute());
//			}
//			
//			total = total + count;
//			copy.removeAll(Collections.singleton(parent));
//		}
//
//		System.out.println(" end  total = "+total);
//		System.out.println(" parent size = "+parents.size());
//		
		return parents;
	}
	
	
	private Route runTournament(ArrayList<Route> participants) {
		Route bestRoute = participants.get(0);
		double bestRouteCost = getCostOfRoute(bestRoute);
		for (Route route:participants) {
			double currentCost = getCostOfRoute(route);
			if(currentCost<bestRouteCost) {
				bestRoute = route;
				bestRouteCost = currentCost;
			}
		}
		return bestRoute;
	}
	
	private double getCostOfRoute(Route route) {
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

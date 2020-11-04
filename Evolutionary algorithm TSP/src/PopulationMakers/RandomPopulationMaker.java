package PopulationMakers;

import java.util.ArrayList;
import java.util.Random;

import Main.Map;
import Main.Route;
import RouteCalculator.RandomRouteGenerator;

public class RandomPopulationMaker {


	
	public ArrayList<Route> generatePopulation(Map map,int size,long seed){
		
		
		//Random rand = new Random(seed);
		RandomRouteGenerator RRG = new RandomRouteGenerator(map,seed);
		ArrayList<Route> routes = new ArrayList<Route>();
		for(int i = 0;i<size;i++) {
			Route route = RRG.generateRoute();
			routes.add(route);
		}
		
		return routes;
	}
}

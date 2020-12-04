package PopulationMakers;

import java.util.ArrayList;
import java.util.Random;

import Main.Map;
import Main.Route;
import RouteCalculator.RandomRouteGenerator;

// TODO: Auto-generated Javadoc
/**
 * The Class RandomPopulationMaker.
 */
public class RandomPopulationMaker {


	
	/**
	 * initializes a random population.
	 *
	 * @param map the map
	 * @param size the size of population
	 * @param seed the seed for random generation
	 * @return the array list of routes/population
	 */
	public ArrayList<Route> generatePopulation(Map map,int size,long seed){
		
		RandomRouteGenerator RRG = new RandomRouteGenerator(map,seed);
		ArrayList<Route> routes = new ArrayList<Route>();
		for(int i = 0;i<size;i++) {
			Route route = RRG.generateRoute();
			routes.add(route);
		}
		
		return routes;
	}
}

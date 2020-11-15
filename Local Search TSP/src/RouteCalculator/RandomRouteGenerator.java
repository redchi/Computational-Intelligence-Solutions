package RouteCalculator;

import java.util.ArrayList;
import java.util.Random;

import Main.*;

/**
 * 
 *  Randomly generate a {@link Route} from any {@link Map}
 */
public class RandomRouteGenerator {

	
	/** The map that will be used to calculate a random route from */
	private Map map;
	
	/** The {@link Random} object used */
	private Random rand;
	
	/**
	 * Instantiates a new random route generator.
	 *
	 * @param map the map
	 */
	public RandomRouteGenerator(Map map) {
		this.map = map;
		rand = new Random();
	}
	
	/**
	 * Instantiates a new random route generator.
	 *
	 * @param map the map obj that we need to calculate a route from
	 * @param seed the seed for rand
	 */
	public RandomRouteGenerator(Map map,long seed) {
		this.map = map;
		rand = new Random(seed);
	}
	
	/**
	 * Generate valid random route.
	 * Route always starts and end at start city by getting start city index from {@link Map#getStartCityIndex},
	 * adds this at start/end of new route then removes from available cities to chose from.
	 * Then randomly picks remaining cities, to generate a random route
	 *
	 *start city is added twice here
	 * @return the route generated
	 */
	public Route generateRoute() {
		Route newRoute = new Route();
		ArrayList<Location> cities = new ArrayList<Location>(map.getCities());
		int startCityIndex = map.getStartCityIndex();
		Location StartCity = cities.get(startCityIndex);
		newRoute.addCity(StartCity);
		cities.remove(startCityIndex);		
		int citySize = cities.size();
		for(int i = 0 ; i<citySize;i++) {
			int index = rand.nextInt(cities.size());
			Location selectedCity = cities.get(index);
			cities.remove(selectedCity);
			newRoute.addCity(selectedCity);
		}
		newRoute.addCity(StartCity); // returning to start city
		return newRoute;
	}
}

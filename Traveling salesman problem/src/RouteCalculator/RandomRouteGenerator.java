package RouteCalculator;

import java.util.ArrayList;
import java.util.Random;

import Main.*;

public class RandomRouteGenerator {

	
	Map map;
	Random rand;
	public RandomRouteGenerator(Map map) {
		this.map = map;
		rand = new Random();
	}
	
	public RandomRouteGenerator(Map map,long seed) {
		this.map = map;
		rand = new Random(seed);
	}
	
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

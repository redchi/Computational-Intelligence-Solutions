package Main;

import java.util.ArrayList;

import RouteCalculator.RandomRouteGenerator;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Main main = new Main();
        main.generateRandomRouteFromCSVMap();
	}

	

	/**
	 * Generate a random {@link Route} from a list of cities on a CSV file
	 */
	public void generateRandomRouteFromCSVMap() {
		// create map
		Map map = new Map();
		map.generateCitiesFromCSV(0);
		
		//create route
		RandomRouteGenerator rrg = new RandomRouteGenerator(map);
		Route solution = rrg.generateRoute();
		
		//print route
		System.out.println(solution);
		double distanceOfRoute = solution.getCostOfRoute();
		System.out.println("Route Cost = "+distanceOfRoute);
		
	

	}
	
	/**
	 * Generate random {@link Route} from random map.
	 */
	public void generateRandomRouteFromRandomMap() {
		// create map
		Map map = new Map();
		map.generateCitiesRandomly(50, 0, 200, 200, 942359);;
		
		//create route
		RandomRouteGenerator rrg = new RandomRouteGenerator(map);
		Route solution = rrg.generateRoute();
		
		//print route
		System.out.println(solution);
		double distanceOfRoute = solution.getCostOfRoute();
		System.out.println("Route Cost = "+distanceOfRoute);
		
	}
	/*
	 * find cost of a manualy made route from csv,
	 * note enter location index NOT ID, starts from 0 and goes in order
	 */
	public void manualRouteGenFromCSVMap() {
		Map map = new Map();
		map.generateCitiesFromCSV(0);
		ArrayList<Location> cities = map.getCities();
		
		Route route = new Route();
		route.addCity(cities.get(0));
		route.addCity(cities.get(1));
		route.addCity(cities.get(0));
		
		System.out.println("Route Cost = "+route.getCostOfRoute());
	}
}

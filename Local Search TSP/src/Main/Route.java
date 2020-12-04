package Main;

import java.util.ArrayList;


/**
 * Stores the path that salesman will follow in order from start to finish
 */
public class Route {

	/** Stores cities that the salesman will travel in order,
	 * 0 index = start city,
	 * n-1 index = end city,
	 * start and end city are the same city
	 * */
	private ArrayList<Location> path;
	
	/**
	 * Instantiates a new route.
	 */
	public Route() {
		path = new ArrayList<Location>();
	}
	
	/**
	 * Adds the city to path
	 *
	 * @param city the city
	 */
	public void addCity(Location city) {
		path.add(city);
	}
	
	/**
	 * String of the IDs of the cities, in path
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "ROUTE PATH ";
		for(Location loc:path) {
			result = result + loc.getID() +"-";
		}
		return result;
	}
	
	/**
	 * Gets the path of route
	 *
	 * @return the path
	 */
	public ArrayList<Location> getPath(){
		return new ArrayList<Location>(path);
	}

	
	/**
	 * Gets the cost of this route.
	 *
	 * @return the cost of route
	 */
	public double getCostOfRoute() {
		double totalDistance = 0;
		ArrayList<Location> path = getPath();
		int pathSize = path.size();
		Location currentCity = path.get(0);
		for (int i = 1; i < pathSize; i++) {
			Location nextCity = path.get(i);
			double x = (nextCity.getX() - currentCity.getX());
			double y = (nextCity.getY() - currentCity.getY());
			double distance = Math.sqrt((x * x) + (y * y));
			totalDistance = totalDistance + distance;
			currentCity = path.get(i);
		}
		return totalDistance;
	}
}

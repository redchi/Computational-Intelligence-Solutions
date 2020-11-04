package Main;

import java.util.ArrayList;

public class Route {

	private ArrayList<Location> path;
	
	public Route() {
		path = new ArrayList<Location>();
	}
	
//	public Route(ArrayList<Location> path) {
//		this.path = path ;
//	}
	public void addCity(Location city) {
		path.add(city);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "\nROUTE PATH";
		for(Location loc:path) {
			result = result + "\n"+ loc ;
		}
		return result;
	}
	
	public ArrayList<Location> getPath(){
		return path;
	}
	
	public double getCostOfRoute() {
		double totalDistance = 0;
		//ArrayList<Location> path = this.getPath();
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

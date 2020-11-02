package Main;

import java.util.ArrayList;

public class Route {

	ArrayList<Location> path;
	
	public Route() {
		path = new ArrayList<Location>();
	}
	
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
		return new ArrayList<Location>(path);
	}
		
}

package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class Map {

	ArrayList<Location> allCities;
	int startCityIndex;
	public Map() {
		allCities = new ArrayList<Location>();
	}
	
	public void generateCitiesRandomly(int city_num,int startCityIndex,int max_x, int max_y,long seed) {
		Random rand = new Random(seed);
		this.startCityIndex = startCityIndex;
		// can make duplicate values
		for(int i = 0;i<city_num;i++) {
			int x = rand.nextInt(max_x+1);
			int y = rand.nextInt(max_y+1);
			Location city = new Location(x,y);
			
			allCities.add(city);
		}
		
		
	}
	

	public void generateCitiesFromCSV(int startCityIndex) {
		 String csvFile = "./Data/ulysses16.csv";
		 this.startCityIndex = startCityIndex;
	        BufferedReader br = null;
	        String line = "";
	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            while ((line = br.readLine()) != null) {       	            	
	            	String [] elm = line.split(",");
	        		float x = Float.parseFloat(elm[1]); 
	        		float y = Float.parseFloat(elm[2]); 

	        		Location city = new Location(x,y);
	        		allCities.add(city);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	         finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	         }
	}
	
	// shouldnt be here
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
	
	public ArrayList<Location> getCities(){
		return allCities;
	}
	
	public int getStartCityIndex() {
		return startCityIndex;
	}
}

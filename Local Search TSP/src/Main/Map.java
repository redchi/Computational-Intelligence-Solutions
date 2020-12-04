package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Map.
 */
public class Map {

	/** stores all the cities in this map
	 * there is no link between them, just an arraylist of the order they were generated or read from the CSV
	 *  */
	private ArrayList<Location> allCities;
	
	/** The start city index, tells you which city in {@link Map#allCities} is the start city */
	private int startCityIndex;

	/**
	 * Instantiates a new map.
	 */
	public Map() {
		allCities = new ArrayList<Location>();
	}

	/**
	 * Generate all cities within map object randomly.
	 *
	 * @param city_num = the max number of cities in map
	 * @param startCityIndex =  the start city index
	 * @param max_x = the max x value a city can have
	 * @param max_y = the max y value a city can have
	 * @param seed =  the seed for {@link Random}
	 */
	public void generateCitiesRandomly(int city_num, int startCityIndex, int max_x, int max_y, long seed) {
		Random rand = new Random(seed);
		this.startCityIndex = startCityIndex;

		for (int i = 0; i < city_num; i++) {
			int x = rand.nextInt(max_x + 1);
			int y = rand.nextInt(max_y + 1);
			String ID = i + "";
			Location city = new Location(x, y,ID);

			allCities.add(city);
		}

	}

	/**
	 * Generate cities from a CSV file.
	 *
	 * @param startCityIndex = which city will be the starting city in CSV, provide the index, 0 being 1st and n-1 being last city
	 */
	public void generateCitiesFromCSV(int startCityIndex) {
		String csvFile = "./Data/ulysses16.csv";
		this.startCityIndex = startCityIndex;
		BufferedReader br = null;
		String line = "";
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] elm = line.split(",");
				String ID = elm[0]; 
				float x = Float.parseFloat(elm[1]);
				float y = Float.parseFloat(elm[2]);
				
				Location city = new Location(x, y,ID);
				allCities.add(city);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}



	/**
	 * Gets all cities in map as {@link Location}.
	 *
	 * @return the cities
	 */
	public ArrayList<Location> getCities() {
		return allCities;
	}

	/**
	 * Gets the start city index, tells you which city in {@link Map#allCities} is the start city
	 *
	 * @return the start city index
	 */
	public int getStartCityIndex() {
		return startCityIndex;
	}
}

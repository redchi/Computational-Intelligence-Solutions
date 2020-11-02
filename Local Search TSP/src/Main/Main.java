package Main;

import java.util.ArrayList;

import RouteCalculator.RandomRouteGenerator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
//		
//        ArrayList<String> al = new ArrayList<String>();
//
//        //Adding elements to the ArrayList
//        al.add("Apple");
//        al.add("Orange");
//        al.add("Mango");
//        al.add("Grapes");
//        System.out.println("ArrayList: "+al);
//   
//        ArrayList<String> al2 = new ArrayList<String>(al);
//        System.out.println("Shallow copy of ArrayList: "+ al2);
//   
//        //add and remove on original ArrayList
//        al2.add("Fig");
//        //Display of both ArrayLists after add & remove
//        System.out.println("Original ArrayList:"+al);
//        System.out.println("Cloned ArrayList:"+al2);
//        
        
        Main main = new Main();
        main.start();
	}

	
	public void start() {
		Map map = new Map();
		
//		map.generateCities(5,0, 30, 30, 1234);
		
//		map.generateCitiesFromCSV(0);
//		
//		RandomRouteGenerator rrg = new RandomRouteGenerator(map);
//		Route solution = rrg.generateRoute();
//		System.out.println(solution);
//		double distanceOfRoute = map.getCostOfRoute(solution);
//		System.out.println("\nTOTAL DISTANCE = "+distanceOfRoute+"\n");
//		
		
		ArrayList<Location> ar1 = new ArrayList<Location>();
		ar1.add(new Location(1,2));
		ar1.add(new Location(5,7));
		ar1.add(new Location(3,9));
		ArrayList<Location> ar2 = new ArrayList<Location>(ar1);
		ar1.get(0).setX(9);
		System.out.println(ar2.get(0));

	}
	
}

package Main;

import java.util.ArrayList;
import java.util.Arrays;

import NeighbourhoodMakers.TwoOpt;
import NeighbourhoodSelectors.BestNeighbourSelector;
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
		
		map.generateCitiesFromCSV(0);
		
		RandomRouteGenerator rrg = new RandomRouteGenerator(map);
		Route solution = rrg.generateRoute();
		
		TwoOpt twoOpt = new TwoOpt();
		BestNeighbourSelector BNS = new BestNeighbourSelector();
		
		int repeatAmt = 10000;
		
		System.out.println("1st tour gen distance - "+ BNS.getCostOfRoute(solution));
		
		for(int i = 0;i<repeatAmt;i++) {
		//	System.out.println("cycle = "+i);
			//System.out.println("size = "+solution.getPath().size());
			ArrayList<Route> neighbourhood = twoOpt.generateNeighbours(solution);
			solution = BNS.selectBestNeighbour(neighbourhood);
			
		}
		System.out.println(" Best Route Found ");
		for(Location loc:solution.getPath()) {
			System.out.println(loc+" ");
		}
		System.out.println(" Distance = "+BNS.getCostOfRoute(solution));

	
//		System.out.println(solution);
//		double distanceOfRoute = map.getCostOfRoute(solution);
//		System.out.println("\nTOTAL DISTANCE = "+distanceOfRoute+"\n");

	

		
	
	}
	
	
	void test() {
		
		int size = 30;
		
		
		
		
		int mainRouteSize = size - 2;
		ArrayList<Integer> routeIndexs = numCounter(0,mainRouteSize);
		routeIndexs.add(0); // returning to start city
		int cLinksTotal = routeIndexs.size() -1;
		for(int cLink = 0; cLink<=cLinksTotal;cLink++) {
			int fwdCLink = (cLink+1)%(cLinksTotal+1);
			int dwnCLink = (cLink-1)%(cLinksTotal+1);
			ArrayList<Integer> swappableCLinks = numCounter(0,(cLinksTotal -1));
			swappableCLinks.removeAll(Arrays.asList(cLink,fwdCLink,dwnCLink));
			for(int otherCLink:swappableCLinks) {
				int FCL = cLink;
				int SCL = otherCLink;
				if(cLink>otherCLink) {
					 FCL = otherCLink;
					 SCL = cLink;
				}
				ArrayList<Integer> newRouteIndexes = numCounter(0,FCL);
				newRouteIndexes.addAll(numCounter(SCL,(FCL+1)));
				
				if(SCL<cLinksTotal) {
					newRouteIndexes.addAll(numCounter((SCL+1),cLinksTotal));
				}
				newRouteIndexes.add(0);
				//System.out.println("NEW ROUTE - "+cLink);
				for(int num:newRouteIndexes ) {
					System.out.print(" "+num+" ");
				}
				System.out.println("");
			}
		}
	}
	
	
	public ArrayList<Integer> numCounter(int start,int end){
		ArrayList<Integer> count = new ArrayList<Integer>();
		count.add(start);
		int inverter = 1;
		int diff = end - start ;
		if(diff<0) {
			diff = diff * -1;
			inverter = -1;
		}
		for(int i = 1;(i-1)<diff;i++ ) {
			int res = start + (i*inverter);
			count.add(res);
		}
		return count;
	}
	
	
	
}

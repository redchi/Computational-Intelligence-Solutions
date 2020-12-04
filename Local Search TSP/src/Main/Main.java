package Main;

import java.util.ArrayList;
import java.util.Arrays;

import NeighbourhoodMakers.TwoOpt;
import NeighbourhoodSelectors.BestNeighbourSelector;
import RouteCalculator.RandomRouteGenerator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        Main main = new Main();
        main.start();
	}

	
	public void start() {
		Map map = new Map();
		map.generateCitiesFromCSV(0);
		
		RandomRouteGenerator rrg = new RandomRouteGenerator(map);
		Route solution = rrg.generateRoute();
		
		TwoOpt twoOpt = new TwoOpt();
		BestNeighbourSelector BNS = new BestNeighbourSelector();
		
		int repeatAmt = 100;
		
		System.out.println("1st tour generated Cost = "+ solution.getCostOfRoute());
		
		for(int i = 0;i<repeatAmt;i++) {
			ArrayList<Route> neighbourhood = twoOpt.generateNeighbours(solution);
			solution = BNS.selectBestNeighbour(neighbourhood);
			
		}
		System.out.println("\nLocal Search Iterated "+repeatAmt+" Times");
		System.out.println("Best Route Found\n"+solution);
		
		System.out.println("Tour Cost = "+solution.getCostOfRoute());
		
	
	}
	
	
	
	
}

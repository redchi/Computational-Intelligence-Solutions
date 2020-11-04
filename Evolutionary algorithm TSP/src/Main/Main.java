package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Evolver.Evolver;
import PopulationMakers.RandomPopulationMaker;
import RouteCalculator.RandomRouteGenerator;

public class Main {

	public static void main(String[] args) {
        Main main = new Main();
        main.test();
	}

	void test() {
		ArrayList<String> strs = new ArrayList<String>(Arrays.asList("a","b","c","d","e"));
		Collections.rotate(strs, 1);
		for(String s:strs) {
			
			System.out.println(s + "");
		}
		
	}
	
	public void start() {
		Map map = new Map();
		
//		map.generateCities(5,0, 30, 30, 1234);
		map.generateCitiesFromCSV(0);
		RandomPopulationMaker populationMaker = new RandomPopulationMaker();
		ArrayList<Route> population = populationMaker.generatePopulation(map,100,9998);	
		Route  currentSolution = population.get(0);
		double solutionCost = map.getCostOfRoute(currentSolution);
		
		Evolver evolver = new Evolver();
		
		int repeatAmt = 100;
		
		for(int i =0;i<repeatAmt;i++) {		
			population = evolver.evolvePopulation(population);
			Route currentBestRoute = bestRoute(population);
			double cBestRouteCost = map.getCostOfRoute(currentBestRoute);
			if(cBestRouteCost<solutionCost) {
				currentSolution = currentBestRoute;
				solutionCost = cBestRouteCost;
			}
		}
		

	}
	
	private Route bestRoute(ArrayList<Route> population) {
		return null;
	}
	
}

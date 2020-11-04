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
        main.start();
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
		
		int populationSize = 100;
		
		ArrayList<Route> population = populationMaker.generatePopulation(map,populationSize,9998);	
		Route  currentSolution = population.get(0);
		double solutionCost = map.getCostOfRoute(currentSolution);
		
		Evolver evolver = new Evolver();
		
		int repeatAmt = 30;
		
		for(int i =0;i<repeatAmt;i++) {		
			System.out.println("Cycle "+i);
			population = evolver.evolvePopulation(population,populationSize);
			Route currentBestRoute = bestRoute(population);
			double cBestRouteCost = map.getCostOfRoute(currentBestRoute);
			if(cBestRouteCost<solutionCost) {
				currentSolution = currentBestRoute;
				solutionCost = cBestRouteCost;
			}
		}
		System.out.println("DONE COST = " + solutionCost );

	}
	
	private Route bestRoute(ArrayList<Route> population) {
		Route bestRoute = population.get(0);
		double bestRoutecost = bestRoute.getCostOfRoute();
		
		for(Route route:population) {
			double cost = route.getCostOfRoute();
			if(cost<bestRoutecost) {
				bestRoute = route;
				bestRoutecost = cost;
			}
		}
		return bestRoute;
	}
	
}

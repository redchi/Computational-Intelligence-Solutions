package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

import Evolver.Evolver;
import PopulationMakers.RandomPopulationMaker;
import RouteCalculator.RandomRouteGenerator;

public class Main {

	public static void main(String[] args) {
        Main main = new Main();
        main.start();
	}

	
	public void start() {
		Map map = new Map();
		
	//	map.generateCitiesRandomly(1000,0, 5000, 5000, 1234);
		map.generateCitiesFromCSV(0);
		RandomPopulationMaker populationMaker = new RandomPopulationMaker();
		
		int populationSize = 100;
		
		ArrayList<Route> population = populationMaker.generatePopulation(map,populationSize,9998);	
		Route  currentSolution = population.get(0);
		double solutionCost = currentSolution.getCostOfRoute();
		
		Evolver evolver = new Evolver();
		
		int repeatAmt = 1000;
		System.out.println("START COST = " + solutionCost );

		for(int i =0;i<repeatAmt;i++) {		
			//System.out.println("Cycle "+i);
			population = evolver.evolvePopulation(population,populationSize);
			Route currentBestRoute = bestRoute(population);
			double cBestRouteCost = currentBestRoute.getCostOfRoute();
			
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

package Main;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {
	
	public static void main(String[] args) {
		Main main = new Main();
		main.t1();
	}
	
	public void start() {
		ProblemSpecification probSpec = new ProblemSpecification("./problems/prob1.txt");
		Initialiser ini = new Initialiser(probSpec,12314);
		int populationSize = 100;
		ArrayList<Solution> population = ini.generatePopulation(populationSize);
		
		int genarations = 1000;
		Evolver evolver = new Evolver(probSpec);
		for(int i = 0;i<genarations;i++) {
			population = evolver.evolvePopulation(population);
		}
	}
	
	
	private Solution getBestSolution(ArrayList<Solution> population) {
		
		Solution bestSol = population.get(0);
		for(Solution sol:population) {
			if(sol.getTotalCost()<bestSol.getTotalCost()) {
				bestSol = sol;
			}
		}
		return bestSol;
	}
	
	
	private void t0() {
		ProblemSpecification probSpec = new ProblemSpecification("./problems/prob1.txt");
		Initialiser ini = new Initialiser(probSpec,12314);
		ArrayList<Solution> sols = ini.generatePopulation(10);
		System.out.println("done");
	}
	private void t1() {
		
		ArrayList<Integer> x1 = new ArrayList<Integer>();
		x1.add(3);
		x1.add(5);
		x1.add(9);
		ArrayList<Integer> x2 = (ArrayList<Integer>) x1.stream() .map( i -> i*3) .collect(Collectors.toList());
		System.out.println("d2");
		
	}
}

package Main;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
	
	public void start() {
		ProblemSpecification probSpec = new ProblemSpecification("./problems/prob1.txt");
		Initialiser ini = new Initialiser(probSpec,12314);
		int populationSize = 100;
		ArrayList<Solution> population = ini.generatePopulation(populationSize);
		System.out.println(population.size());
		int genarations = 3000;
		Evolver evolver = new Evolver(probSpec);
		Solution bestSol = population.get(0);
		for(int i = 0;i<genarations;i++) {
			population = evolver.evolvePopulation(population);
			bestSol = getBestSolution(population);
			System.out.println(i +"  Best Solution Cost - "+bestSol.getTotalCost() + "   " + population.size());
		}
		printSolution(bestSol);
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
	
	private void printSolution(Solution solution) {
		int c = 0;
		for(CuttingStock stock:solution.getAllStockUsed()) {
			float length = stock.getLength();
			float usedL = stock.getUsedLength();
			
			System.out.println("Stock num "+c +"  length = "+length + "    Used Length = "+usedL);
			System.out.println("   Orders completed by stock");
			for(Order order:stock.getOrdersCutByStock()) {
				float l = order.getLength();
				String id = order.getID();
				System.out.println("   ID = "+id + "   length = "+l);
			}
			
			
			c = c + 1;
		}
	}
	
	
	private void t0() {
		ProblemSpecification probSpec = new ProblemSpecification("./problems/prob1.txt");
		Initialiser ini = new Initialiser(probSpec,12314);
		ArrayList<Solution> sols = ini.generatePopulation(10);
		
		for(Solution sol:sols) {
			System.out.println("\n\n");
			printSolution(sol);
		}
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

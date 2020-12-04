package Main;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
	
	public void start() {
		ProblemSpecification probSpec = new ProblemSpecification("./problems/prob1.txt");
		Initialiser ini = new Initialiser(probSpec,12314);
		ArrayList<Solution> sols = ini.generatePopulation(10);
		System.out.println("done");
	}
}

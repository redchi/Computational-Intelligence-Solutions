package Main;

import java.util.ArrayList;

public class Evolver {

	private ParentTournamentSelector parentSelector;
	private Order1Crossover recombinator;
	private SwapMutator mutator;
	private SurviverSelector survivorSelector;
	private ProblemSpecification probSpec;
	
	public Evolver(ProblemSpecification probSpec) {
		this.probSpec = probSpec;
		parentSelector = new ParentTournamentSelector(12412);
		recombinator = new Order1Crossover(12462);
		mutator = new SwapMutator(123124, 70, this.probSpec);
		survivorSelector = new SurviverSelector();
	}
	
	public ArrayList<Solution> evolvePopulation(ArrayList<Solution> population){
		int parentAmt = population.size()/2;
		ArrayList<Solution> parents = parentSelector.selectBestParents(population, parentAmt);
		
		int childrenAmt = population.size();
		ArrayList<Solution> children = recombinator.generateChildren(parents, childrenAmt);
		ArrayList<Solution> mutatedChildren = mutator.mutateChildren(children);
		
		int survivorAmt = population.size();
		ArrayList<Solution> survivors = survivorSelector.selectSurvivors(parents, mutatedChildren,survivorAmt);
		
		return survivors;
	}
}
























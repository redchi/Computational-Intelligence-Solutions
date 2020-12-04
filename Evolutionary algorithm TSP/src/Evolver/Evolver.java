package Evolver;

import java.util.ArrayList;

import Main.Route;
import Mutators.SwapMutator;
import ParentSelectors.ParentTournamentSelector;

import Recombinators.Order1Crossover;
import SurvivorSelectors.EGSurvivorSelector;

// TODO: Auto-generated Javadoc
/**
 * Evolves a population
 */
public class Evolver {

	private ParentTournamentSelector parentSelector;
	private Order1Crossover recombinator;
	private SwapMutator mutator;
	private EGSurvivorSelector survivorSelector;
	
	/**
	 * Instantiates a new evolver.
	 */
	public Evolver() {
		parentSelector = new ParentTournamentSelector(1264841234);
		recombinator = new Order1Crossover(234527);
		mutator = new SwapMutator(214365, 100);
		survivorSelector = new EGSurvivorSelector();
	}
	
	/**
	 * Evolve population.
	 * selects best parents, makes children from them, mutate the children, select best survivors 
	 *
	 * @param currentPopulation the current population
	 * @param sizeLimit the size limit
	 * @return the new generation of evolved population
	 */
	public ArrayList<Route> evolvePopulation(ArrayList<Route> currentPopulation,int sizeLimit) {
		int selectionAmt = (currentPopulation.size()/2);
		ArrayList<Route> parents = parentSelector.selectParents(currentPopulation,selectionAmt);
		int childrenAmt = parents.size() * 2;
		ArrayList<Route> children = recombinator.generateChildren(parents,childrenAmt);
		for(int i = 0;i<children.size();i++) {
			Route child = children.get(i);
			child = mutator.mutate(child);
			children.set(i,child);
		}
		ArrayList<Route> survivors  =  survivorSelector.selectSurvivors(parents,children,sizeLimit);	
		return survivors;
	}
}

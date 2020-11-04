package Evolver;

import java.util.ArrayList;

import Main.Route;
import Mutators.SwapMutator;
import ParentSelectors.ParentTournamentSelector;

import Recombinators.Order1Crossover;
import SurvivorSelectors.EGSurvivorSelector;

public class Evolver {

	private ParentTournamentSelector parentSelector;
	private Order1Crossover recombinator;
	private SwapMutator mutator;
	private EGSurvivorSelector survivorSelector;
	
	public Evolver() {
		parentSelector = new ParentTournamentSelector(126484);
		recombinator = new Order1Crossover(234527);
		mutator = new SwapMutator(214365, 50);
		survivorSelector = new EGSurvivorSelector();
	}
	
	public ArrayList<Route> evolvePopulation(ArrayList<Route> currentPopulation) {
		int selectionAmt = (currentPopulation.size()/2);
		ArrayList<Route> parents = parentSelector.selectParents(currentPopulation,selectionAmt);
		int childrenAmt = parents.size() * 2;
		ArrayList<Route> children = recombinator.generateChildren(parents,childrenAmt);
		
		for(Route child: children) {
			child = mutator.mutate(child);
		}
		
		ArrayList<Route> survivors  =  survivorSelector.selectSurvivors(parents,children);	
		return survivors;
	}
}

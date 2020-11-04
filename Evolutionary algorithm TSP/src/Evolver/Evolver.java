package Evolver;

import java.util.ArrayList;

import Main.Route;
import Mutators.SwapMutator;
import ParentSelectors.ParentTournamentSelector;
import PopulationMakers.Population;
import Recombinators.Order1Crossover;
import SurvivorSelectors.EGSurvivorSelector;

public class Evolver {

	private ParentTournamentSelector parentSelector;
	private Order1Crossover recombinator;
	private SwapMutator mutator;
	private EGSurvivorSelector survivorSelector;
	
	public Evolver() {
		
	}
	
	public ArrayList<Route> evolvePopulation(ArrayList<Route> currentPopulation) {
		int selectionAmt = (currentPopulation.size()/2);
		ArrayList<Route> parents = parentSelector.selectParents(currentPopulation,selectionAmt);
		ArrayList<Route> children = recombinator.generateChildren(parents);
		ArrayList<Route> survivors  = survivorSelector.selectSurvivors(parents,children);	
		return survivors;
	}
}

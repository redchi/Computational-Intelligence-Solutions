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
		parentSelector = new ParentTournamentSelector(1264841234);
		recombinator = new Order1Crossover(234527);
		mutator = new SwapMutator(214365, 100);
		survivorSelector = new EGSurvivorSelector();
	}
	
	public ArrayList<Route> evolvePopulation(ArrayList<Route> currentPopulation,int sizeLimit) {
		int selectionAmt = (currentPopulation.size()/2);
		ArrayList<Route> parents = parentSelector.selectParents(currentPopulation,selectionAmt);
		int childrenAmt = parents.size() * 2;
		ArrayList<Route> children = recombinator.generateChildren(parents,childrenAmt);
	//	ArrayList<Route> mutatedChildren = new A
		for(int i = 0;i<children.size();i++) {
			Route child = children.get(i);
			child = mutator.mutate(child);
			children.set(i,child);
			//Route child = 
		}
		
//		for(Route child: children) {
//			
//		//	System.out.println("New child dist = "+ child.getCostOfRoute());
//		}
		
	
		for(Route child: children) {
			//child = mutator.mutate(child);
	//		System.out.println(" child dist = "+ child.getCostOfRoute());
		}
		ArrayList<Route> survivors  =  survivorSelector.selectSurvivors(parents,children,sizeLimit);	
		return survivors;
	}
}

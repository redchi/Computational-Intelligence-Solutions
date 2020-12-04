package ParentSelectors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import Main.Location;
import Main.Route;

// TODO: Auto-generated Javadoc
/**
 * The Class ParentTournamentSelector.
 */
public class ParentTournamentSelector {

	/** The random obj */
	private Random rand;
	
	/**
	 * Instantiates a new parent tournament selector.
	 *
	 * @param seed the seed
	 */
	public ParentTournamentSelector(long seed) {
		rand = new Random(seed);
	}
	
	
	
	
	/**
	 * Select best parents.
	 * runs a certain amount of tournaments from randomly selects parents,
	 * picks the best parents.
	 * 
	 * @param population the total population
	 * @param amount the amount of parents we want pack
	 * @return the array list of selected parents
	 */
	public ArrayList<Route> selectParents(ArrayList<Route> population, int amount){
		
		int maxRange = population.size();
		
		int maxParticipants = maxRange/2;
		ArrayList<Route> parents = new ArrayList<Route>();

		for(int i = 0;i<amount;i++) {
			ArrayList<Route> participants = new ArrayList<Route>();
			// Route selection for 1 tournament 
			for(int k = 0;k<maxParticipants;k++ ) {
				int index = rand.nextInt(maxRange);
				Route participant = population.get(index);
				participants.add(participant);
			}
			Route winner = runTournament(participants);
			parents.add(winner);
		}
		
		return parents;
	}
	
	
	/**
	 * Run a single tournament.
	 *find the best route and that is the winner
	 * @param participants the participants
	 * @return the route
	 */
	private Route runTournament(ArrayList<Route> participants) {
		Route bestRoute = participants.get(0);
		double bestRouteCost = bestRoute.getCostOfRoute();
		for (Route route:participants) {
			double currentCost = route.getCostOfRoute();
			if(currentCost<bestRouteCost) {
				bestRoute = route;
				bestRouteCost = currentCost;
			}
		}
		return bestRoute;
	}
	
	
	
}

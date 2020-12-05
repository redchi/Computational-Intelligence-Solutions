package Main;

import java.util.ArrayList;
import java.util.Random;

public class ParentTournamentSelector {

	private Random rand;
	
	public ParentTournamentSelector(long seed) {
		rand = new Random(seed);
	}
	
	public ArrayList<Solution> selectBestParents(ArrayList<Solution> population,int amount){
		int k = population.size()/2;
		ArrayList<Solution> parents = new ArrayList<Solution>();
		for(int i = 0;i<amount;i++) {
			ArrayList<Solution> tounamentCandidates = new ArrayList<Solution>();
			for(int j = 0;j<k;j++) {
				int index = rand.nextInt(population.size());
				Solution candidate = population.get(index);
				tounamentCandidates.add(candidate);
			}
			Solution bestCandidate = runSingleTournament(tounamentCandidates);
			parents.add(bestCandidate);
		}
		return parents;
		
	}
	
	private Solution runSingleTournament(ArrayList<Solution> parents) {
		Solution bestParent = parents.get(0);
		for(Solution parent:parents) {
			float bestcost = bestParent.getTotalCost();
			float cost = parent.getTotalCost();
			if(cost<bestcost) {
				bestParent = parent;
			}
		}
		return bestParent;
	}
}

package Recombinators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

import Main.Location;
import Main.Route;

// TODO: Auto-generated Javadoc
/**
 * The Class Order1Crossover.
 */
public class Order1Crossover {

	/** The rand. */
	private Random rand;
	
	/**
	 * Instantiates a new order 1 crossover.
	 *
	 * @param seed the seed
	 */
	public Order1Crossover(long seed) {
		rand = new Random(seed);
	}
	
	/**
	 * Generate all children.
	 *
	 * @param parents the parents of children
	 * @param amount the amount of children created
	 * @return the array list of children
	 */
	public ArrayList<Route> generateChildren(ArrayList<Route> parents,int amount){
		
		int maxRange = parents.size();
		ArrayList<Route> children = new ArrayList<Route>(); 
		
		for(int i = 0;i<amount;i++) {
			int pIndex = rand.nextInt(maxRange); 
			Route parent1 = parents.get(pIndex);
			pIndex = rand.nextInt(maxRange);
			Route parent2 = parents.get(pIndex);
			Route child = createChild(parent1, parent2);
			children.add(child);
		}	
		
		return children;
		
		
	}
	
	/**
	 * Creates a child.
	 *	
	 * select random index to start from in parent 1 path,
	 * splits parent 1 route in halve from incrementing from that index(can wrap around parent), adds to new child route,
	 * the index where parent 1 copied route ended, parent 2 will copy all of it self starting from that index to child route,
	 * then remove duplicated values from new child route, then shift array right by index value at which you started parent 1 copy,
	 * then add start and end city and a new child route is created.
	 *
	 * @param parent1 the parent 1
	 * @param parent2 the parent 2
	 * @return the child
	 */
	private Route createChild(Route parent1,Route parent2) {
		ArrayList<Location> p1 = new ArrayList<Location>(parent1.getPath());
		p1.remove(0);p1.remove(p1.size()-1); // start and end city removed
		ArrayList<Location> p2 = new ArrayList<Location>(parent2.getPath());
		p2.remove(0);p2.remove(p2.size()-1);// start and end city removed
		
		
		int maxRange = p1.size();
		int startIndex = rand.nextInt(maxRange);
		int copyAmt = p1.size()/2;
		
		ArrayList<Location> p1CopiedParts = new ArrayList<Location>();
		for (int i = 0;i<copyAmt;i++) {
			int index = (startIndex + i)%(p1.size());
			Location selectedP1Loc = p1.get(index);
			p1CopiedParts.add(selectedP1Loc);
		}
		ArrayList<Location> p2CopiedParts = new ArrayList<Location>();
		//p2.removeAll(p1CopiedParts);
		int p2StartIndex = startIndex + copyAmt - 1;
		for(int i = 0;i<p2.size();i++) {
			int index = (p2StartIndex + i)%(p1.size());
			Location p2Loc = p2.get(index);
			p2CopiedParts.add(p2Loc);
		}
		p2CopiedParts.removeAll(p1CopiedParts);
		
		
		ArrayList<Location> childLocs = new ArrayList<Location>();
		childLocs.addAll(p1CopiedParts);
		childLocs.addAll(p2CopiedParts);
		Collections.rotate(childLocs, startIndex);// shifts arraylist right
		Route child = new Route();
		child.addCity(parent1.getPath().get(0).clone());
		for(Location loc:childLocs) {
			Location loc2 = loc.clone();
			child.addCity(loc2);
		}
		child.addCity(parent1.getPath().get(0).clone());
		 return child;
	}
}

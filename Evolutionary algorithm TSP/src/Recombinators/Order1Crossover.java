package Recombinators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

import Main.Location;
import Main.Route;

public class Order1Crossover {

	Random rand;
	
	public Order1Crossover(long seed) {
		rand = new Random(seed);
	}
	
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
	
	private Route createChild(Route parent1,Route parent2) {
		ArrayList<Location> p1 = new ArrayList<Location>(parent1.getPath());
		p1.remove(0);p1.remove(p1.size()-1);
		ArrayList<Location> p2 = new ArrayList<Location>(parent2.getPath());
		p2.remove(0);p2.remove(p2.size()-1);
		
		
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
		Collections.rotate(childLocs, startIndex);
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

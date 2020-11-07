package Mutators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Main.Location;
import Main.Route;

public class SwapMutator {

	Random rand;
	int mutationRate;
	
	
	public SwapMutator(long seed,int mutationRate) {
		rand = new Random(seed);
		this.mutationRate = mutationRate;
	}
	
	public Route mutate(Route child) {
		int chance = rand.nextInt(101);
		
		if(chance<=mutationRate) {
		//	System.out.println("mutated!");
			//System.out.println("before = " + child.getCostOfRoute());
			Route newChild = randomSwapCity(child);
			//System.out.println("after = " + newChild.getCostOfRoute());
			return newChild;
		}
		else {
			return child;
		}
	}
	
	
	
	private Route randomSwapCity(Route child) {
		ArrayList<Location> mainPath = child.getPath();
		int mainRouteSize = mainPath.size()-2;
		ArrayList<Integer> routeIndexs = numCounter(0,mainRouteSize);
		
		routeIndexs.add(0); // returning to start city
		int cLinksTotal = routeIndexs.size() -2;
		int cLink = rand.nextInt(cLinksTotal);
		
			int fwdCLink = (cLink+1)%(cLinksTotal+1);
			int dwnCLink = (cLink-1)%(cLinksTotal+1);
			ArrayList<Integer> swappableCLinks = numCounter(0,(cLinksTotal -1));
			swappableCLinks.removeAll(Arrays.asList(cLink,fwdCLink,dwnCLink));
			
			int swappableClinkRandomIndex = rand.nextInt(swappableCLinks.size());
			int otherCLink = swappableCLinks.get(swappableClinkRandomIndex);
				int FCL = cLink;
				int SCL = otherCLink;
				if(cLink>otherCLink) {
					 FCL = otherCLink;
					 SCL = cLink;
				}
				ArrayList<Integer> newRouteIndexes = numCounter(0,FCL);
				newRouteIndexes.addAll(numCounter(SCL,(FCL+1)));			
				if(SCL<cLinksTotal) {
					newRouteIndexes.addAll(numCounter((SCL+1),cLinksTotal));
				}
				newRouteIndexes.add(0);
				
				Route newChild = new Route();
				for(int num:newRouteIndexes ) {	
					Location city = mainPath.get(num).clone();
					newChild.addCity(city);				
				}	
		return newChild;
	}
	
	
	public ArrayList<Integer> numCounter(int start,int end){
		ArrayList<Integer> count = new ArrayList<Integer>();
		count.add(start);
		int inverter = 1;
		int diff = end - start ;
		if(diff<0) {
			diff = diff * -1;
			inverter = -1;
		}
		for(int i = 1;(i-1)<diff;i++ ) {
			int res = start + (i*inverter);
			count.add(res);
		}
		return count;
	}
	
	
}

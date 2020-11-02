package NeighbourhoodMakers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Main.*;

public class TwoOpt {

	public ArrayList<Route> generateNeighbours(Route mainRoute){
		ArrayList<Route> neighbours = new ArrayList<Route>();
		ArrayList<Location> mainPath = mainRoute.getPath();
		int mainRouteSize = mainPath.size()-2;
		ArrayList<Integer> routeIndexs = numCounter(0,mainRouteSize);
		
		routeIndexs.add(0); // returning to start city
		int cLinksTotal = routeIndexs.size() -2;
		for(int cLink = 0; cLink<=cLinksTotal;cLink++) {
			int fwdCLink = (cLink+1)%(cLinksTotal+1);
			int dwnCLink = (cLink-1)%(cLinksTotal+1);
			ArrayList<Integer> swappableCLinks = numCounter(0,(cLinksTotal -1));
			swappableCLinks.removeAll(Arrays.asList(cLink,fwdCLink,dwnCLink));
			for(int otherCLink:swappableCLinks) {
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
				
				Route route = new Route();
				for(int num:newRouteIndexes ) {	
					Location city = mainPath.get(num).clone();
					route.addCity(city);				
				}
				neighbours.add(route);
				
			}
			
		}
		
		return neighbours;

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

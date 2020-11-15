package NeighbourhoodMakers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Main.*;

/**
 * 2OPT Neighbourhood function
 */
public class TwoOpt {

	/**
	 * Generates all routes that can be made by swapping all cityLinks with another CityLink from The original route.
	 * A city link has start city and end city, shows direction. eg route 1-3-2-1 has 3 city links, 1-3,3-2,2-1
	 * but in this method its more abstract, all original routes are always like 0-1-2-0, so we have 3 cityLinks 
	 * cityLinks[i] = i,i+1, eg cityLinks[0] = 0,0+1 = 0-1, we dont care about exact order of route as its not necessary,
	 * as we know that path[0] = 1 and path[1] = 3,so 0-1 is the same as 1-3
	 * 
	 * Works by selecting inorder from 0 to n the first cityLink(CLink) from list of allCLinks
	 * then it removes that Link and all adjacent Clinks so 1 above and 1 below it from allCLinks this is now a list of swappableCLinks
	 * then inorder from 0 to n selects the second Clink from swappableCLinks
	 * It then verifys the order to make sure first Clink is in fromt of second Clink, if not swap them
	 * then it swiches the CLinks by linking Clink 1 Start city to Clink2 start city and CLink 1 endCity to Clink 2 end city,
	 * any cities in between them will have there order reversed then a new route is created from this order
	 * 
	 * 
	 * @param mainRoute the main route
	 * @return the array list
	 */
	public ArrayList<Route> generateNeighbours(Route mainRoute){
		ArrayList<Route> neighbours = new ArrayList<Route>();
		neighbours.add(mainRoute);
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

	/**
	 * custom util method for counting
	 * eg 0,4 return array list of int from 0 to 4,
	 * also works with 4,0
	 * @param start the start number
	 * @param end the end number
	 * @return the array list
	 */
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

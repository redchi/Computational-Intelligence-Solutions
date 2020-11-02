package NeighbourhoodMakers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Main.*;

public class TwoOpt {

	public ArrayList<Route> generateNeighbours(Route mainRoute){
		ArrayList<CityLink> cityLinks = generateCityLinks(mainRoute);
		
		for(int cityLIndex = 0 ;cityLIndex<cityLinks.size();cityLIndex ++ ) {
			CityLink currentCityLink = cityLinks.get(cityLIndex);
		//	ArrayList<CityLink> newCityLinks = new ArrayList<CityLink>(cityLinks); // for new solution
			//newCityLinks.remove(cityLIndex);
			int linksSize = cityLinks.size();
			// possiblelinkswaps = all the other citylinks (indexes) that can be swapped with selected link
			List<Integer> possibleLinkSwaps = Stream.iterate(0, n -> n + 1)
	                .limit(linksSize-1)
	                .collect(Collectors.toList());
			int fwdLinkIndex = (cityLIndex+1)%linksSize;
			int dwnLinkIndex = (cityLIndex-1)%linksSize;
			possibleLinkSwaps.removeAll(Arrays.asList(fwdLinkIndex,dwnLinkIndex));
			
			// othercityLink = the link which will be swapped with current selected link
			for(int otherCityLinkIndex: possibleLinkSwaps) {
				CityLink otherCityLink = cityLinks.get(otherCityLinkIndex);
				
				// these will be updates, as we cannot update the original
				CityLink currentCityLinkUpdated = currentCityLink.clone();
				CityLink otherCityLinkUpdated = otherCityLink.clone();
				currentCityLinkUpdated.setEndCity(otherCityLinkUpdated.getEndCity());
				// swap
			//	newCityLinks.remove(index)
				
			}
		}
	}
	

	private int[] possibleCityLinkSwaps(int currentCityLinkIndex,int size) {
	
		
		
	}
	
	
	private ArrayList<CityLink> generateCityLinks(Route route){
		ArrayList<Location> cities = route.getPath();
		ArrayList<CityLink> cityLinks = new ArrayList<TwoOpt.CityLink>();
		for(int i = 0;i<cities.size()-1;i++) {
			Location startCity = cities.get(i);
			Location endCity = cities.get(i+1);
			CityLink cl = new CityLink(startCity,endCity);
			cityLinks.add(cl);
		}
		return cityLinks;
	}
	
	private class CityLink{
		private Location startCity;
		private Location endCity;
		public CityLink(Location startCity,Location endCity) {
			this.startCity = startCity;
			this.endCity = endCity;
		}
		public Location getStartCity() {
			return startCity;
		}
		public Location getEndCity() {
			return endCity;
		}
		public void setStartCity(Location loc) {
			startCity = loc;
		}
		public void setEndCity(Location loc) {
			endCity = loc;
		}
		public CityLink clone() {
			return new CityLink(startCity.clone(),endCity.clone());
		}

	}
}

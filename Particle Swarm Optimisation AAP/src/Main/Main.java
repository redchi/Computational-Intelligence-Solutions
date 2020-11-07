package Main;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Main main = new Main();
		main.start();
	}

	
	private AntennaArray antennaArray;
	
	public void start() {
		int antennaNum = 24;
		antennaArray = new AntennaArray(antennaNum, 90);
	//	System.out.println(aa.bounds()[0][1]);
		
		generateRandomPosition(20,antennaNum,0.25);
	}
	// make class for this?
	private ArrayList<AxisValues> generateRandomPosition(int numOfPos,double antenaNum,double minAntennaDistance) {
		
	
		long seed = 213123;
		Random rand = new Random(seed);
		
		ArrayList<AxisValues> positions = new ArrayList<AxisValues>();
		
		for(int i = 0;i<numOfPos;i++) {
			ArrayList<Double> values = new ArrayList<Double>();
			
			double maxValue = antenaNum/2;
			double minBound = 0;
			double maxBound = maxValue - ((antenaNum-1)*minAntennaDistance);
			
			double firstAntennaPos = minBound + rand.nextDouble() * (maxBound - minBound);
			// do validation here
			values.add(firstAntennaPos);
			double prevValue  = firstAntennaPos;
			for(int j = 0;j<(antenaNum-2);j++) {
				minBound = prevValue + minAntennaDistance;
				maxBound = maxBound + minAntennaDistance;
				double nextPos  = minBound + rand.nextDouble() * (maxBound - minBound);
				prevValue= nextPos;
				values.add(nextPos);
			}
			values.add(antenaNum/2);
			double [] temp = values.stream().mapToDouble(x -> x).toArray();
			System.out.println(antennaArray.is_valid(temp));
			
			AxisValues res = new AxisValues(values);
			positions.add(res);
		}
		
	//	return res;
		return positions;
		
	}
	
	//private double [] generateAxis(int antenaNum )
	
	
}

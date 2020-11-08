package Main;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Main main = new Main();
		main.start();
	}

	

	public void start() {
		//int antennaNum = 24;
		AntennaArray antennaArray = new AntennaArray(3, 90);

		
		double vals[] = new double[]  {0.25,0.8,1.5 };
		double cost = antennaArray.evaluate(vals);
		System.out.println(" c = "+ cost);
		System.out.println(antennaArray.is_valid(vals));
		ParticleHandler PH = new ParticleHandler(antennaArray);
		AxisValues gBest =  PH.runParticleSwarmOptimization(10);
		
		System.out.println("\nPSO TERMINATED\nGlobal Best");
		
		System.out.println("Cost = "+ antennaArray.evaluate(gBest.getRaw()));
		for(int i = 0;i<antennaArray.getNumOfAntenna();i++) {
			double val = gBest.getAntennaPos().get(i);
			int antenna = i + 1;
			System.out.println("Antenna " + antenna + " position = "+val);
		}

		
	}

	
}

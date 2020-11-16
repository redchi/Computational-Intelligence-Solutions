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
		AntennaArray antennaArray = new AntennaArray(10, 20);
		
		ParticleHandler PH = new ParticleHandler(antennaArray);
		AxisValues gBest =  PH.runParticleSwarmOptimization(50,10);
		
		System.out.println("\nPSO TERMINATED\nGlobal Best");
		
		System.out.println("Cost = "+ antennaArray.evaluate(gBest.getRaw()));
		for(int i = 0;i<antennaArray.getNumOfAntenna();i++) {
			double val = gBest.getAntennaPos().get(i);
			int antenna = i + 1;
			System.out.println("Antenna " + antenna + " position = "+val);
		}

		
	}

	
}

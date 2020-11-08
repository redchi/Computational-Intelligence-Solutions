package Main;

import java.util.ArrayList;
import java.util.Random;

public class ParticleHandler {

	private AntennaArray antennaArray;
	private Random rand;
	
	public ParticleHandler(AntennaArray antennaArray) {
		this.antennaArray = antennaArray;
		rand = new Random(213123);
	}

	
	public AxisValues runParticleSwarmOptimization(int numOfParticles) {	
		ArrayList<Particle> particles = new ArrayList<Particle>();
		Particle.initalizeParticleClass(34243, 0.5, 1, 1);
		for(int i = 0;i<numOfParticles;i++) {
			ArrayList<AxisValues> posAndVelocity = generateRandomPosition(2);
			AxisValues particlePos = posAndVelocity.get(0);
			AxisValues particleVel = posAndVelocity.get(1);
			Particle particle = new Particle(particlePos, particleVel, antennaArray);
			particles.add(particle);
		}
	
		int cycles = 100;
		for(int i = 0;i<cycles;i++) {
			double cost = antennaArray.evaluate(Particle.gBest.getRaw());
			System.out.println("\n"+i + " cost = "+cost);
			
			for(Particle particle:particles) {
				
				particle.update();
			}
			
//			for(double pos:Particle.gBest.getAntennaPos()) {
//				System.out.print(pos + " ");
//				
//			}
//			System.out.println();
		}
		return Particle.gBest;
		
	}
	
	private ArrayList<AxisValues> generateRandomPosition(int numOfPos) {
	
		ArrayList<AxisValues> positions = new ArrayList<AxisValues>();
		double antenaNum = antennaArray.getNumOfAntenna();
		double minAntennaDistance = AntennaArray.MIN_SPACING;
		
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
		//	System.out.println(antennaArray.is_valid(temp));
			
			AxisValues res = new AxisValues(values);
			positions.add(res);
		}
		
		return positions;
		
	}
}
